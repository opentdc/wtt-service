package org.opentdc.wtt;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
// import com.wordnik.swagger.annotations.*;



import org.opentdc.exception.DuplicateException;
import org.opentdc.exception.InternalServerErrorException;
import org.opentdc.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CXFNonSpringJaxrsServlet (defined in web.xml) uses Singleton as a default
 * scope for service classes specified by a jaxrs.serviceClasses servlet
 * parameter.
 * 
 * @author Bruno Kaiser
 *
 */
@Path("/api/company")
// @Api(value = "/company", description="API for work time tracking (wtt)")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WttService {
	private static ServiceProvider sp = null;
	// query: a semicolon-separated list of query verbs. e.g. modifiedAt();lessThan(3);orderByFirstName();ascending()
	private static final String DEFAULT_QUERY = "";
	// queryType: specifies the type of objects to be returned
	// TODO: how to make queryTypes generic, i.e. independent of serviceProviders ?
	private static final String DEFAULT_QUERY_TYPE = "";
	// position: the result set starts from the given position. 
	// Increasing the position by a batch size allows to iterate the result set.
	// hasMore=false indicates that there are no more objects to be returned
	private static final String DEFAULT_POSITION = "0";
	// size: specifies the batch size, i.e. the amount of records returned starting from position.
	private static final String DEFAULT_SIZE = "25";

	// instance variables
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Invoked for each service invocation (Constructor)
	 */
	public WttService(@Context ServletContext context) {
		logger.info("> WttService()");
		if (sp == null) {
			sp = ServiceFactory.getServiceProvider(context);
		}
		logger.info("WttService() initialized");
	}
	
	/******************************** company *****************************************/
	@GET
	@Path("/")
	public List<CompanyModel> listCompanies(
			@DefaultValue(DEFAULT_QUERY) @QueryParam("query") String query,
			@DefaultValue(DEFAULT_QUERY_TYPE) @QueryParam("queryType") String queryType,
			@DefaultValue(DEFAULT_POSITION) @QueryParam("position") long position,
			@DefaultValue(DEFAULT_SIZE) @QueryParam("size") long size) {
		return sp.listCompanies(false, query, queryType, position, size);
	}
	
	@GET
	@Path("/astree")
// 	@ApiOperation(value="List all companies", notes="", response = List<CompanyData>.class)
	public List<CompanyModel> listCompaniesAsTree(
			@DefaultValue(DEFAULT_QUERY) @QueryParam("query") String query,
			@DefaultValue(DEFAULT_QUERY_TYPE) @QueryParam("queryType") String queryType,
			@DefaultValue(DEFAULT_POSITION) @QueryParam("position") long position,
			@DefaultValue(DEFAULT_SIZE) @QueryParam("size") long size) {
		return sp.listCompanies(true, query, queryType, position, size);
	}

	@POST
	@Path("/")
// 	@ApiOperation(value="Create a new company", notes="", response = CompanyData.class)
//	@ApiResponse(value= {
//			@ApiResponse(code = 409, message = "Company already exists")
//	})
	public CompanyModel createCompany(
//			@ApiParam(value="The new company") 
			CompanyModel company) 
			throws DuplicateException {
		return sp.createCompany(company); 
	}

	@GET
	@Path("/{cid}")
	public CompanyModel readCompany(
			@PathParam("cid") String compId)
			throws NotFoundException {
		return sp.readCompany(compId);
	}

	@PUT
	@Path("/")
	public CompanyModel updateCompany(
			CompanyModel company) 
			throws NotFoundException {
		return sp.updateCompany(company);
	}

	@DELETE
	@Path("/{cid}")
	public void deleteCompany(
			@PathParam("cid") String compId)
			throws NotFoundException, InternalServerErrorException {
		sp.deleteCompany(compId);
	}

	@GET
	@Path("/count")
	public int countCompanies() {
		return sp.countCompanies();
	}

	/********************************** project ***************************************/
	@GET
	@Path("/{cid}/project")
	public List<ProjectModel> listProjects(
			@PathParam("cid") String compId,
			@DefaultValue(DEFAULT_QUERY) @QueryParam("query") String query,
			@DefaultValue(DEFAULT_QUERY_TYPE) @QueryParam("queryType") String queryType,
			@DefaultValue(DEFAULT_POSITION) @QueryParam("position") long position,
			@DefaultValue(DEFAULT_SIZE) @QueryParam("size") long size) {
		return sp.listProjects(compId, query, queryType, position, size);
	}

	@GET
	@Path("/{cid}/project/flat")
	public List<ProjectModel> listAllProjectsFlat(
			@PathParam("cid") String compId,
			@DefaultValue(DEFAULT_QUERY) @QueryParam("query") String query,
			@DefaultValue(DEFAULT_QUERY_TYPE) @QueryParam("queryType") String queryType,
			@DefaultValue(DEFAULT_POSITION) @QueryParam("position") long position,
			@DefaultValue(DEFAULT_SIZE) @QueryParam("size") long size) {
		return sp.listAllProjects(compId, false, query, queryType, position, size);
	}

	@GET
	@Path("/{cid}/project/astree")
	public List<ProjectModel> listAllProjectsAsTree(
			@PathParam("cid") String compId,
			@DefaultValue(DEFAULT_QUERY) @QueryParam("query") String query,
			@DefaultValue(DEFAULT_QUERY_TYPE) @QueryParam("queryType") String queryType,
			@DefaultValue(DEFAULT_POSITION) @QueryParam("position") long position,
			@DefaultValue(DEFAULT_SIZE) @QueryParam("size") long size) {
		return sp.listAllProjects(compId, true, query, queryType, position, size);
	}

	@POST
	@Path("/{cid}/project")
	public ProjectModel createProject(
			@PathParam("cid") String compId, 
			ProjectModel project) 
				throws DuplicateException {
		return sp.createProject(compId, project);
	}

	@POST
	@Path("/{cid}/project/{pid}")
	public ProjectModel createProject(
			@PathParam("cid") String compId, 
			@PathParam("pid") String projId,
			ProjectModel project) 
				throws DuplicateException {
		return sp.createProjectAsSubproject(compId, projId, project);
	}
	
	@GET
	@Path("/{cid}/project/{pid}")
	public ProjectModel readProject(
			@PathParam("cid") String compId,
			@PathParam("pid") String projId) 
				throws NotFoundException {
		return sp.readProject(projId);
	}

	@PUT
	@Path("/{cid}/project")
	public ProjectModel updateProject(
			@PathParam("cid") String compId,
			ProjectModel project) 
					throws NotFoundException {
		return sp.updateProject(compId, project);
	}

	@DELETE
	@Path("/{cid}/project/{pid}")
	public void deleteProject(
			@PathParam("cid") String compId,
			@PathParam("pid") String projId) 
					throws NotFoundException, InternalServerErrorException {
		sp.deleteProject(compId, projId);
	}

	@GET
	@Path("/{cid}/project/count")
	public int countProjects(
			@PathParam("cid") String compId) {
		return sp.countProjects(compId);
	}

	/******************************** resource *****************************************/
	@GET
	@Path("/{cid}/project/{pid}/resource")
	public List<ResourceModel> listResources(
			@PathParam("cid") String compId, 
			@PathParam("pid") String projId,
			@DefaultValue(DEFAULT_QUERY) @QueryParam("query") String query,
			@DefaultValue(DEFAULT_QUERY_TYPE) @QueryParam("queryType") String queryType,
			@DefaultValue(DEFAULT_POSITION) @QueryParam("position") long position,
			@DefaultValue(DEFAULT_SIZE) @QueryParam("size") long size)
		throws NotFoundException {
		return sp.listResources(projId, query, queryType, position, size);
	}

	@POST
	@Path("/{cid}/project/{pid}/resource")
	public String addResource(
			@PathParam("cid") String compId, 
			@PathParam("pid") String projId, String rid) 
		throws NotFoundException, DuplicateException {
		return sp.addResource(projId, rid);
	}

	@DELETE
	@Path("/{cid}/project/{pid}/resource/{rid}")
	public void deleteResource(
			@PathParam("cid") String compId, 
			@PathParam("pid") String projId, 
			@PathParam("rid") String resourceId)
			throws NotFoundException {
		sp.removeResource(projId, resourceId);
	}

	@GET
	@Path("/{cid}/project/{pid}/resource/count")
	public int countResources(
			@PathParam("cid") String compId, 
			@PathParam("pid") String projId) 
		throws NotFoundException {
		return sp.countResources(projId);
	}
}
