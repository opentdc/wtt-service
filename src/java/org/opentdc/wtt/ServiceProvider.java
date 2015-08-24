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

import javax.servlet.http.HttpServletRequest;

import org.opentdc.service.exception.DuplicateException;
import org.opentdc.service.exception.InternalServerErrorException;
import org.opentdc.service.exception.NotFoundException;
import org.opentdc.service.exception.ValidationException;

public interface ServiceProvider {
	
	public abstract List<CompanyModel> listCompanies(
			String query, 
			String queryType, 
			int position, 
			int size);

	public abstract CompanyModel createCompany(
			HttpServletRequest request,
			CompanyModel company) 
					throws DuplicateException, ValidationException;

	public abstract CompanyModel readCompany(
			String id) 
					throws NotFoundException;

	public abstract CompanyModel updateCompany(
			HttpServletRequest request,
			String compId,
			CompanyModel company
	)  throws NotFoundException, ValidationException;

	public abstract void deleteCompany(
			String id
	) throws NotFoundException, InternalServerErrorException;

	/************************* projects *****************************/
	public abstract List<ProjectModel> listProjects(
			String compId,
			String query, 
			String queryType, 
			int position, 
			int size);

	public abstract ProjectModel createProject(
			HttpServletRequest request,
			String compId, 
			ProjectModel project)
		throws DuplicateException, ValidationException;
	
	public abstract ProjectModel readProject(
			String compId,
			String projId)
		throws NotFoundException;

	public abstract ProjectModel updateProject(
			HttpServletRequest request,
			String compId,
			String projId,
			ProjectModel project
	) throws NotFoundException, ValidationException;

	public abstract void deleteProject(
		String compId, 
		String projId
	) throws NotFoundException, InternalServerErrorException;
	
	public abstract ProjectTreeNodeModel readAsTree(
			String compId)
		throws NotFoundException;
	
	/************************* subprojects *****************************/
	public abstract List<ProjectModel> listSubprojects(
			String compId,
			String projId,
			String query, 
			String queryType, 
			int position, 
			int size);

	public abstract ProjectModel createSubproject(
			HttpServletRequest request,
			String compId, 
			String projId,
			ProjectModel project)
		throws DuplicateException, ValidationException;
	
	public abstract ProjectModel readSubproject(
			String compId,
			String projId,
			String subprojId)
		throws NotFoundException;

	public abstract ProjectModel updateSubproject(
			HttpServletRequest request,
			String compId,
			String projId,
			String subprojId,
			ProjectModel project
	) throws NotFoundException, ValidationException;

	public abstract void deleteSubproject(
		String compId, 
		String projId,
		String subprojId
	) throws NotFoundException, InternalServerErrorException;

	/******************************** resource ***********************/
	public abstract List<ResourceRefModel> listResourceRefs(
			String compId,
			String projId,
			String query, 
			String queryType, 
			int position, 
			int size)
		throws NotFoundException;

	public abstract ResourceRefModel addResourceRef(
			HttpServletRequest request,
			String compId,
			String projId, 
			ResourceRefModel resourceRef)
		throws NotFoundException, DuplicateException, ValidationException;

	public abstract void removeResourceRef(
			String compId,
			String projId, 
			String resourceId)
		throws NotFoundException, InternalServerErrorException;
}
