package org.opentdc.wtt;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ProjectTreeNodeModel {
		private String id ;
		private String title;
		private ArrayList<ProjectTreeNodeModel> projects;
		private ArrayList<String> resources;

		public ProjectTreeNodeModel() {
			projects = new ArrayList<ProjectTreeNodeModel>();
			resources = new ArrayList<String>();
		}
		
		public String getId() {
			return id;
		}
		
		public void setId(String id) {
			this.id = id;
		}
		
		public List<ProjectTreeNodeModel> getProjects() {
			return projects;
		}
		
		public void setProjects(ArrayList<ProjectTreeNodeModel> projects) {
			this.projects = projects;
		}
		
		public void addProject(ProjectTreeNodeModel p) {
			this.projects.add(p);
		}
		
		public boolean removeProject(ProjectTreeNodeModel p) {
			return this.projects.remove(p);
		}

		/**
		 * @return the resources
		 */
		public ArrayList<String> getResources() {
			return resources;
		}

		/**
		 * @param resources the resources to set
		 */
		public void setResources(ArrayList<String> resources) {
			this.resources = resources;
		}
		
		public void addResource(String rid) {
			this.resources.add(rid);
		}

		/**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * @param title the title to set
		 */
		public void setTitle(String title) {
			this.title = title;
		}
}
