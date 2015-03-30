package org.opentdc.wtt;

import java.util.Formatter;
import java.util.List;
import java.util.Locale;

public class PrettyPrinter {
	
	
	/******************************* Company *****************************/
	public static String companyToString(CompanyModel c, String ident) {
		StringBuilder _sb = new StringBuilder();
		Formatter _formatter = new Formatter(_sb, Locale.US);
		_formatter
				.format("%s{\n%s\tid:\t%s\n%s\txri:\t%s\n%s\ttitle:\t%s\n%s\tdescription:\t%s\n",
						ident, ident, c.getId(), ident, c.getXri(), ident, c.getTitle(), ident, c.getDescription());
		_formatter.format("%s\tprojects:\n%s\t[\n%s%s\t]\n", ident, ident, projectsToString(c.getProjects(), ident + "\t\t"), ident);
		_formatter.format("%s}", ident);
		_formatter.close();
		return _sb.toString();
	}
	
	/********************************** Project *****************************/
	public static String projectToString(ProjectModel p, String ident) {
		StringBuilder _sb = new StringBuilder();
		Formatter _formatter = new Formatter(_sb, Locale.US);
		_formatter
				.format("%s{\n%s\tid:\t%s\n%s\txri:\t%s\n%s\ttitle:\t%s\n%s\tdescription:\t%s\n",
						ident, ident, p.getId(), ident, p.getXri(), ident, p.getTitle(), ident, p.getDescription());
		_formatter.format("%s\tprojects:\n%s\t[\n%s%s\t]\n", ident, ident, projectsToString(p.getProjects(), ident + "\t\t"), ident);
		_formatter.format("%s\tresources:\n%s\t[\n%s%s\t]\n", ident, ident, resourcesToString(p.getResources(), ident + "\t\t"), ident);
		_formatter.format("%s}", ident);
		_formatter.close();
		return _sb.toString();
	}
	
	public static String projectsToString(List<ProjectModel> projects, String ident) {
		StringBuilder _sb = new StringBuilder();
		Formatter _formatter = new Formatter(_sb, Locale.US);
		for (ProjectModel _p : projects) {
			_formatter.format("%s\n", projectToString(_p, ident));
		}
		_formatter.close();
		return _sb.toString();
	}
	
	/********************************** Resource *****************************/
	public static String resourcesToString(List<ResourceModel> resources, String ident) {
		StringBuilder _sb = new StringBuilder();
		Formatter _formatter = new Formatter(_sb, Locale.US);
		for (ResourceModel _resource : resources) {
			_formatter.format("%s{ %s }\n", ident, _resource.getId());
		}
		_formatter.close();
		return _sb.toString();
	}
}
