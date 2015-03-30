package org.opentdc.wtt.test;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.client.WebClient;
import org.opentdc.exception.DuplicateException;
import org.opentdc.exception.NotFoundException;
import org.opentdc.wtt.ProjectModel;
import org.opentdc.wtt.ResourceModel;

public class ProjectClient {
	public static final String APP_URI = "http://localhost:8080/wtt/api/company/";	
	public static final String PATH_EL_PROJECT = "project";
	public static final String PATH_EL_RESOURCE = "resource";
	
	private int status = 0;
	
	public ProjectClient() {
		// System.out.println("> ProjectClient() (constructor)");
	}
	
	public int getStatus() {
		return status;
	}

	public List<ProjectModel> listProjects(String compId) {
		// System.out.println("> listProjects()");
		WebClient _wc = null;
		try {
			_wc = WttTest.initWebClient(APP_URI);
			Response _r = _wc.path(compId).path(PATH_EL_PROJECT).get();
			status = _r.getStatus();
			if (status == Status.OK.getStatusCode()) {
				return new ArrayList<ProjectModel>(_wc.getCollection(ProjectModel.class));
			}
			else {
				return new ArrayList<ProjectModel>();
			}
		}
		finally {
			_wc.close();
		}
	}
	
	public List<ProjectModel> listProjectsHierarchically(String compId, boolean asTree) {
		// System.out.println("> listProjectsHierarchically()");
		WebClient _wc = null;
		try {
			_wc = WttTest.initWebClient(APP_URI);
			Response _r = null; 
			if (asTree == true) {
				_r = _wc.path(compId).path(PATH_EL_PROJECT).path("astree").get();
			}
			else {
				_r = _wc.path(compId).path(PATH_EL_PROJECT).path("flat").get();				
			}
			status = _r.getStatus();
			if (status == Status.OK.getStatusCode()) {
				return new ArrayList<ProjectModel>(_wc.getCollection(ProjectModel.class));
			}
			else {
				return new ArrayList<ProjectModel>();
			}
		}
		finally {
			_wc.close();
		}

	}
	
	public ProjectModel createProject(String compId, ProjectModel p) {
		// System.out.println("> createProject(" + compId + ", " + p.getId() + ")");
		WebClient _wc = null;
		try {
			_wc = WttTest.initWebClient(APP_URI);
			Response _r = _wc.path(compId).path(PATH_EL_PROJECT).post(p);
			status = _r.getStatus();
			if (status == Status.OK.getStatusCode()) {
				// System.out.println("-> OK (id=" + _r.readEntity(ProjectData.class).getId() + ")");
				return _r.readEntity(ProjectModel.class);
			}
			else {
				// System.out.println("-> NOK (" + status + ")");
				return null;
			}
		}
		finally {
			_wc.close();
		}
	}
	
	public ProjectModel createSubproject(String compId, String projId, ProjectModel p) {
		// System.out.println("> createProject(" + compId + ", " + projId + ", " + p.getId() + ")");
		WebClient _wc = null;
		try {
			_wc = WttTest.initWebClient(APP_URI);
			Response _r = _wc.path(compId).path(PATH_EL_PROJECT).path(projId).post(p);
			status = _r.getStatus();
			if (status == Status.OK.getStatusCode()) {
				return _r.readEntity(ProjectModel.class);
			}
			else {
				return null;
			}
		}
		finally {
			_wc.close();
		}
	}

	public ProjectModel readProject(String compId, String projId) {
		// System.out.println("> readProject(" + compId + ", " + projId + ")");
		WebClient _wc = null;
		try {
			_wc = WttTest.initWebClient(APP_URI);
			Response _r = _wc.path(compId).path(PATH_EL_PROJECT).path(projId).get();
			status = _r.getStatus();
			if (status == Status.OK.getStatusCode()) {
				return _r.readEntity(ProjectModel.class);
			}
			else {
				return null;
			}
		}
		finally {
			_wc.close();
		}
	}

	public ProjectModel updateProject(String compId, ProjectModel p) {
		// System.out.println("> updateProject(" + compId + ", " + p + ")");
		WebClient _wc = null;
		try {
			_wc = WttTest.initWebClient(APP_URI);
			Response _r = _wc.path(compId).path(PATH_EL_PROJECT).put(p);
			status = _r.getStatus();
			if (status == Status.OK.getStatusCode()) {
				return _r.readEntity(ProjectModel.class);
			}
			else {
				return null;
			}
		}
		finally {
			_wc.close();
		}
	}

	public int deleteProject(String compId, String projId) {
		// System.out.println("> deleteProject(" + compId + ", " + projId + ")");
		WebClient _wc = null;
		try {
			_wc = WttTest.initWebClient(APP_URI);
			Response _r = _wc.path(compId).path(PATH_EL_PROJECT).path(projId).delete();
			status = _r.getStatus();
			return status;
		}
		finally {
			_wc.close();
		}
	}

	public int countProjects(String compId) {
		// System.out.println("> countProjects(" + compId + ")");
		WebClient _wc = null;
		try {
			_wc = WttTest.initWebClient(APP_URI);
			Response _r = _wc.path(compId).path(PATH_EL_PROJECT).path("count").get();
			// System.out.println("Response of GET(" + APP_URI + "/" + compId + "/" + PATH_EL + "/count) -> " + _r);
			String _countStr = _r.readEntity(String.class);
			// String _countStr = WttTest.initWebClient(APP_URI).path(compId).path(PATH_EL).path("count").get().readEntity(String.class);
			// System.out.println("countProjects (stringified): " + _countStr);
			if (_countStr == null || _countStr.length() == 0) {
				_countStr = "0";
			}
			return new Integer(_countStr).intValue();
		}
		finally {
			_wc.close();
		}	
	}	
	
	public int countSubprojects(String compId, String projId) {
		ProjectModel _p = readProject(compId, projId);
		return _p.getProjects().size();
		// System.out.println("> countCompanies()");

	}
	
	/***************************** resource **********************************/
	// GET "/{cid}/project/{pid}/resource"
	public ArrayList<ResourceModel> listResources(String compId, String projId) {
		// System.out.println("> listResources(" + compId + ", " + projId + ")");
		WebClient _wc = null;
		try {
			_wc = WttTest.initWebClient(APP_URI);
			Response _r = _wc.path(compId).path(PATH_EL_PROJECT).path(projId).path(PATH_EL_RESOURCE).get();
			status = _r.getStatus();
			if (status == Status.OK.getStatusCode()) {
				return new ArrayList<ResourceModel>(_wc.getCollection(ResourceModel.class));
			}
			else {
				return new ArrayList<ResourceModel>();
			}
		}
		finally {
			_wc.close();
		}
	}
	
	// POST "/{cid}/project/{pid}/resource"
	public String addResource(String compId, String projId, String resourceId) {
		// System.out.println("> listResources(" + compId + ", " + projId + ", " + resourceId + ")");
		WebClient _wc = null;
		try {
			_wc = WttTest.initWebClient(APP_URI);
			Response _r = _wc.path(compId).path(PATH_EL_PROJECT).path(projId).path(PATH_EL_RESOURCE).post(resourceId);
			status = _r.getStatus();
			if (status == Status.OK.getStatusCode()) {
				return resourceId;
			}
			else {
				return null;
			}
		}
		finally {
			_wc.close();
		}
	}

	// DELETE "/{cid}/project/{pid}/resource/{rid}"
	public void deleteResource(String compId, String projId, String resourceId) {
		// System.out.println("> deleteResource(" + compId + ", " + projId + ", " + resourceId + ")");
		WebClient _wc = null;
		try {
			_wc = WttTest.initWebClient(APP_URI);
			Response _r = _wc.path(compId).path(PATH_EL_PROJECT).path(projId).path(PATH_EL_RESOURCE).path(resourceId).delete();
			status = _r.getStatus();
		}
		finally {
			_wc.close();
		}
	}
	
	// GET "/{cid}/project/{pid}/resource/count"
	public int countResources(String compId, String projId) {
		// System.out.println("> countResources(" + compId + ", " + projId + ")");
		WebClient _wc = null;
		try {
			_wc = WttTest.initWebClient(APP_URI);
			Response _r = _wc.path(compId).path(PATH_EL_PROJECT).path(projId).path(PATH_EL_RESOURCE).path("count").get();
			String _countStr = _r.readEntity(String.class);
			// System.out.println("countProjects (stringified): " + _countStr);
			if (_countStr == null || _countStr.length() == 0) {
				_countStr = "0";
			}
			return new Integer(_countStr).intValue();
		}
		finally {
			_wc.close();
		}	
	}
}
