package org.opentdc.wtt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.opentdc.exception.DuplicateException;
import org.opentdc.exception.InternalServerErrorException;
import org.opentdc.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ServiceProvider {
	protected static ArrayList<CompanyModel> companies = null;
	protected static Map<String, CompanyModel> companyIndex = null;
	protected static Map<String, ProjectModel> projectIndex = null;
	protected static ArrayList<String> resources = null;
	
	// instance variables
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public void initStorageProvider() {
		logger.info("> initStorageProvider()");

		if (companies == null) {
			companies = new ArrayList<CompanyModel>();
		}
		if (companyIndex == null) {
			companyIndex = new HashMap<String, CompanyModel>();
		}
		if (projectIndex == null) {
			projectIndex = new HashMap<String, ProjectModel>();
		}
		if (resources == null) {
			resources = new ArrayList<String>();
		}

		logger.info("initStorageProvider() initialized");
	}


	/************************* companies *****************************/
	public abstract ArrayList<CompanyModel> listCompanies(
			boolean asTree,
			String query, 
			String queryType, 
			long position, 
			long size);

	public abstract CompanyModel createCompany(
			CompanyModel company) 
					throws DuplicateException;

	public abstract CompanyModel readCompany(
			String id) 
					throws NotFoundException;

	public abstract CompanyModel updateCompany(
			CompanyModel company) 
					throws NotFoundException;

	public abstract void deleteCompany(
			String id) 
					throws NotFoundException, InternalServerErrorException;

	public abstract int countCompanies();

	/************************* projects *****************************/
	public abstract ArrayList<ProjectModel> listProjects(
			String compId,
			String query, 
			String queryType, 
			long position, 
			long size);

	public abstract ArrayList<ProjectModel> listAllProjects(
			String compId, 
			boolean asTree,
			String query, 
			String queryType, 
			long position, 
			long size);

	public abstract ProjectModel createProject(
			String compId, 
			ProjectModel project)
		throws DuplicateException;
	
	public abstract ProjectModel createProjectAsSubproject(
			String compId, 
			String projId, 
			ProjectModel project)
		throws DuplicateException;

	public abstract ProjectModel readProject(
			String projId)
		throws NotFoundException;

	public abstract ProjectModel updateProject(
			String compId, 
			ProjectModel project)
		throws NotFoundException;

	public abstract void deleteProject(
			String compId, 
			String projId)
		throws NotFoundException, InternalServerErrorException;

	public abstract int countProjects(
			String compId);

	/******************************** resource ***********************/
	public abstract ArrayList<ResourceModel> listResources(
			String projId,
			String query, 
			String queryType, 
			long position, 
			long size)
		throws NotFoundException;

	public abstract String addResource(
			String projId, 
			String resourceId)
		throws NotFoundException, DuplicateException;

	public abstract void removeResource(
			String projId, 
			String resourceId)
		throws NotFoundException;

	public abstract int countResources(
			String projId)
		throws NotFoundException;
}
