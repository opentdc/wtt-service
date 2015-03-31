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

import org.opentdc.service.exception.DuplicateException;
import org.opentdc.service.exception.InternalServerErrorException;
import org.opentdc.service.exception.NotFoundException;

public interface ServiceProvider {
	
	public abstract List<CompanyModel> listCompanies(
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
	public abstract List<ProjectModel> listProjects(
			String compId,
			String query, 
			String queryType, 
			long position, 
			long size);

	public abstract List<ProjectModel> listAllProjects(
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
	public abstract List<ResourceModel> listResources(
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
