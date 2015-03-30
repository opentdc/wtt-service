package org.opentdc.wtt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A Project.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ProjectModel {
	public final static String DEF_XRI = "XRI_UNDEFINED";
	public final static String DEF_TITLE = "TITLE_UNDEFINED";
	public final static String DEF_DESC = "DESCRIPTION_UNDEFINED";

	private String id;
	private String xri;
	private String title;
	private String description;
	private ArrayList<ProjectModel> projects;

	// resources are kept as a reference by their ID; query the details on the resource service
	private ArrayList<ResourceModel> resources;

	/******************************* Constructors *****************************/
	public ProjectModel() {
		this.id = UUID.randomUUID().toString();
		this.xri = DEF_XRI;
		this.title = DEF_TITLE;
		this.description = DEF_DESC;
		this.projects = new ArrayList<ProjectModel>();
		this.setResources(new ArrayList<ResourceModel>());
	}

	public ProjectModel(String title, String description) {
		this.id = UUID.randomUUID().toString();
		this.xri = DEF_XRI;
		this.title = title;
		this.description = description;
		this.projects = new ArrayList<ProjectModel>();
		this.setResources(new ArrayList<ResourceModel>());
	}

	public ProjectModel(String xri, String title, String description) {
		this.id = UUID.randomUUID().toString();
		this.xri = xri;
		this.title = title;
		this.description = description;
		this.projects = new ArrayList<ProjectModel>();
		this.setResources(new ArrayList<ResourceModel>());
	}
	
	public ProjectModel(ProjectModel p, boolean asTree) {
		this.id = p.getId();
		this.xri = p.getXri();
		this.title = p.getTitle();
		this.description = p.getDescription();
		this.projects = new ArrayList<ProjectModel>();
		if (asTree == true) {
			for (ProjectModel _p : p.getProjects()) {
				this.projects.add(_p);
			}
		}
		this.setResources(p.getResources());
	}

	/********************************** ID *****************************/
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	public void setId() {
		this.id = UUID.randomUUID().toString();
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/********************************** XRI *****************************/
	/**
	 * @return the xri
	 */
	public String getXri() {
		return xri;
	}

	/**
	 * @param xri
	 *            the xri to set
	 */
	public void setXri(String xri) {
		this.xri = xri;
	}

	/********************************** Title *****************************/
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/***************************** Description *****************************/	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/******************************* Project *****************************/
	/**
	 * 
	 * @param project
	 */
	public void addProject(ProjectModel project) {
		if (project != null) {
			this.projects.add(project);
		}
	}

	/**
	 * @param id
	 * @return true if the project was removed
	 */
	public boolean removeProject(String id) {
		for (ProjectModel _p : projects) {
			if (_p.getId().equalsIgnoreCase(id)) {
				return projects.remove(_p);
			}
		}
		return false;
	}
	
	public List<ProjectModel> getProjects() {
		return projects;
	}

	/**
	 * @param newProjects
	 *            the projects to set
	 */
	public void setProjects(ArrayList<ProjectModel> newProjects) {
		this.projects = newProjects;
	}
	
	/******************************* Resource *****************************/
	public void addResource(String resId) {
		if (resId != null) {
			resources.add(new ResourceModel(resId));
		}
	}
	
	public void addResource(ResourceModel res) {
		if (res != null) {
			resources.add(res);
		}
	}

	public boolean removeResource(String resId) {
		for (ResourceModel _res : resources) {
			if (_res.getId().equals(resId)) {
				resources.remove(_res);
				return true;
			}
		}
		return false;
	}

	public ArrayList<ResourceModel> getResources() {
		return resources;
	}

	public void setResources(ArrayList<ResourceModel> resources) {
		this.resources = resources;
	}
	
	/********************************** Comparator *****************************/
	public static Comparator<ProjectModel> ProjectComparator = new Comparator<ProjectModel>() {

		public int compare(ProjectModel project1, ProjectModel project2) {

			String _projectTitle1 = project1.getTitle().toUpperCase();
			String _projectTitle2 = project2.getTitle().toUpperCase();

			// ascending order
			return _projectTitle1.compareTo(_projectTitle2);

			// descending order
			// return _projectTitle2.compareTo(_projectTitle1);
		}
	};
}
