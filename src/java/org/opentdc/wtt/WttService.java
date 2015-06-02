/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Arbalo AG
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.opentdc.wtt;

import java.util.List;
import java.util.logging.Logger;

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





import org.opentdc.service.GenericService;
import org.opentdc.service.exception.DuplicateException;
import org.opentdc.service.exception.InternalServerErrorException;
import org.opentdc.service.exception.NotAllowedException;
import org.opentdc.service.exception.NotFoundException;
import org.opentdc.service.exception.ValidationException;

@Path("/api/company")
public class WttService extends GenericService<ServiceProvider> {
	
	private ServiceProvider sp = null;

	// instance variables
	private static final Logger logger = Logger.getLogger(WttService.class.getName());

	/**
	 * Invoked for each service invocation (Constructor)
	 * @throws ReflectiveOperationException 
	 */
	public WttService(
		@Context ServletContext context
	) throws ReflectiveOperationException {
		logger.info("> WttService()");
		if (sp == null) {
			sp = this.getServiceProvider(WttService.class, context);
		}
		logger.info("WttService() initialized");
	}

	/******************************** company *****************************************/
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<CompanyModel> listCompanies(
		@DefaultValue(DEFAULT_QUERY) @QueryParam("query") String query,
		@DefaultValue(DEFAULT_QUERY_TYPE) @QueryParam("queryType") String queryType,
		@DefaultValue(DEFAULT_POSITION) @QueryParam("position") int position,
		@DefaultValue(DEFAULT_SIZE) @QueryParam("size") int size
	) {
		return sp.listCompanies(query, queryType, position, size);
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public CompanyModel createCompany(
		CompanyModel company
	) throws DuplicateException, ValidationException {
		return sp.createCompany(company); 
	}

	@GET
	@Path("/{cid}")
	@Produces(MediaType.APPLICATION_JSON)
	public CompanyModel readCompany(
		@PathParam("cid") String cid
	) throws NotFoundException {
		return sp.readCompany(cid);
	}

	@PUT
	@Path("/{cid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CompanyModel updateCompany(
		@PathParam("cid") String cid,
		CompanyModel company
	) throws NotFoundException, NotAllowedException {
		return sp.updateCompany(cid, company);
	}

	@DELETE
	@Path("/{cid}")
	public void deleteCompany(
		@PathParam("cid") String cid
	) throws NotFoundException, InternalServerErrorException {
		sp.deleteCompany(cid);
	}
	
	@GET
	@Path("/{cid}/astree")
	@Produces(MediaType.APPLICATION_JSON)
	public ProjectTreeNodeModel readAsTree(
			@PathParam("cid") String cid
			) throws NotFoundException {
		return sp.readAsTree(cid);
	}

	/********************************** project ***************************************/
	@GET
	@Path("/{cid}/project")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProjectModel> listProjects(
		@PathParam("cid") String cid,
		@DefaultValue(DEFAULT_QUERY) @QueryParam("query") String query,
		@DefaultValue(DEFAULT_QUERY_TYPE) @QueryParam("queryType") String queryType,
		@DefaultValue(DEFAULT_POSITION) @QueryParam("position") int position,
		@DefaultValue(DEFAULT_SIZE) @QueryParam("size") int size
	) {
		return sp.listProjects(cid, query, queryType, position, size);
	}

	@POST
	@Path("/{cid}/project")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProjectModel createProject(
		@PathParam("cid") String cid, 
		ProjectModel project
	) throws DuplicateException, ValidationException {
		return sp.createProject(cid, project);
	}
	
	@GET
	@Path("/{cid}/project/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProjectModel readProject(
		@PathParam("cid") String cid,
		@PathParam("pid") String pid
	) throws NotFoundException {
		return sp.readProject(cid, pid);
	}

	@PUT
	@Path("/{cid}/project/{pid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProjectModel updateProject(
		@PathParam("cid") String cid,
		@PathParam("pid") String pid,
		ProjectModel project
	) throws NotFoundException, NotAllowedException {
		return sp.updateProject(cid, pid, project);
	}

	@DELETE
	@Path("/{cid}/project/{pid}")
	public void deleteProject(
		@PathParam("cid") String cid,
		@PathParam("pid") String pid
	) throws NotFoundException, InternalServerErrorException {
		sp.deleteProject(cid, pid);
	}

	/********************************** subproject ***************************************/
	@GET
	@Path("/{cid}/project/{pid}/project")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProjectModel> listSubProjects(
		@PathParam("cid") String cid,
		@PathParam("pid") String pid,
		@DefaultValue(DEFAULT_QUERY) @QueryParam("query") String query,
		@DefaultValue(DEFAULT_QUERY_TYPE) @QueryParam("queryType") String queryType,
		@DefaultValue(DEFAULT_POSITION) @QueryParam("position") int position,
		@DefaultValue(DEFAULT_SIZE) @QueryParam("size") int size
	) {
		return sp.listSubprojects(cid, pid, query, queryType, position, size);
	}

	@POST
	@Path("/{cid}/project/{pid}/project")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProjectModel createSubProject(
		@PathParam("cid") String cid, 
		@PathParam("pid") String pid,
		ProjectModel project
	) throws DuplicateException, ValidationException {
		return sp.createSubproject(cid, pid, project);
	}
	
	@GET
	@Path("/{cid}/project/{pid}/project/{spid}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProjectModel readSubproject(
		@PathParam("cid") String cid,
		@PathParam("pid") String pid,
		@PathParam("spid") String spid
	) throws NotFoundException {
		return sp.readSubproject(cid, pid, spid);
	}

	@PUT
	@Path("/{cid}/project/{pid}/project/{spid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProjectModel updateSubproject(
		@PathParam("cid") String cid,
		@PathParam("pid") String pid,
		@PathParam("spid") String spid,
		ProjectModel project
	) throws NotFoundException, NotAllowedException {
		return sp.updateSubproject(cid, pid, spid, project);
	}

	@DELETE
	@Path("/{cid}/project/{pid}/project/{spid}")
	public void deleteProject(
		@PathParam("cid") String cid,
		@PathParam("pid") String pid,
		@PathParam("spid") String spid
	) throws NotFoundException, InternalServerErrorException {
		sp.deleteSubproject(cid, pid, spid);
	}

	/******************************** resource reference *****************************************/
	// list all resourceRefs of project pid
	@GET
	@Path("/{cid}/project/{pid}/resource")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ResourceRefModel> listResources(
		@PathParam("cid") String cid, 
		@PathParam("pid") String pid,
		@DefaultValue(DEFAULT_QUERY) @QueryParam("query") String query,
		@DefaultValue(DEFAULT_QUERY_TYPE) @QueryParam("queryType") String queryType,
		@DefaultValue(DEFAULT_POSITION) @QueryParam("position") int position,
		@DefaultValue(DEFAULT_SIZE) @QueryParam("size") int size
	) throws NotFoundException {
		return sp.listResources(cid, pid, query, queryType, position, size);
	}

	// add a resourceRef to project pid
	@POST
	@Path("/{cid}/project/{pid}/resource")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResourceRefModel addResource(
		@PathParam("cid") String cid, 
		@PathParam("pid") String pid, 
		ResourceRefModel resourceRef
	) throws NotFoundException, DuplicateException, ValidationException {
		return sp.addResource(cid, pid, resourceRef);
	}

	// remove resourceRef rid from project pid
	@DELETE
	@Path("/{cid}/project/{pid}/resource/{rid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteResource(
		@PathParam("cid") String cid, 
		@PathParam("pid") String pid, 
		@PathParam("rid") String rid
	) throws NotFoundException, InternalServerErrorException {
		sp.removeResource(cid, pid, rid);
	}
}
