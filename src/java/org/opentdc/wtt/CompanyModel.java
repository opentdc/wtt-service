package org.opentdc.wtt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A Company.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class CompanyModel {
	public final static String DEF_XRI = "XRI_UNDEFINED";
	public final static String DEF_TITLE = "TITLE_UNDEFINED";
	public final static String DEF_DESC = "DESCRIPTION_UNDEFINED";

	private String id;
	private String xri;
	private String title;
	private String description;
	private ArrayList<ProjectModel> projects;

	/******************************* Constructors *****************************/
	public CompanyModel() {
		this.id = UUID.randomUUID().toString();
		this.xri = DEF_XRI;
		this.title = DEF_TITLE;
		this.description = DEF_DESC;
		this.projects = new ArrayList<ProjectModel>();
	}

	public CompanyModel(String title, String description) {
		this.id = UUID.randomUUID().toString();
		this.xri = DEF_XRI;
		this.title = title;
		this.description = description;
		this.projects = new ArrayList<ProjectModel>();
	}

	public CompanyModel(String xri, String title, String description) {
		this.id = UUID.randomUUID().toString();
		this.xri = xri;
		this.title = title;
		this.description = description;
		this.projects = new ArrayList<ProjectModel>();
	}
	
	public CompanyModel(CompanyModel c, boolean asTree) {
		this.id = c.getId();
		this.xri = c.getXri();
		this.title = c.getTitle();
		this.description = c.getDescription();
		this.projects = new ArrayList<ProjectModel>();
		if (asTree == true) {
			for (ProjectModel _p : c.getProjects()) {
				this.projects.add(_p);
			}
		}
	}

	/******************************* ID *****************************/
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

	/******************************* XRI *****************************/
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

	/******************************* Title *****************************/
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/******************************* Description *****************************/
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
	
	public ArrayList<ProjectModel> getProjects() {
		return projects;
	}

	/**
	 * @param projects
	 *            the projects to set
	 */
	public void setProjects(ArrayList<ProjectModel> newProjects) {
		projects = newProjects;
	}
	
	/******************************* Comparator *****************************/
	public static Comparator<CompanyModel> CompanyComparator = new Comparator<CompanyModel>() {

		public int compare(CompanyModel company1, CompanyModel company2) {

			String _companyTitle1 = company1.getTitle().toUpperCase();
			String _companyTitle2 = company2.getTitle().toUpperCase();

			// ascending order
			return _companyTitle1.compareTo(_companyTitle2);

			// descending order
			// return _companyTitle2.compareTo(_companyTitle1);
		}
	};
}
