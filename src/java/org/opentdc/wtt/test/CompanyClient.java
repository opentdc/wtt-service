package org.opentdc.wtt.test;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.client.WebClient;
import org.opentdc.wtt.CompanyModel;

public class CompanyClient {
	public static final String APP_URI = "http://localhost:8080/wtt/api/company/";	
	private int status = 0;
	
	public CompanyClient() {
		// System.out.println("> CompanyClient() (constructor)");
	}
	
	public int getStatus() {
		return status;
	}

	public List<CompanyModel> listCompanies() {
		// System.out.println("> listCompanies()");
		WebClient _wc = null;
		try {
			_wc = WttTest.initWebClient(APP_URI);
			Response _r = _wc.get();
			status = _r.getStatus();
			if (status == Status.OK.getStatusCode()) {
				return new ArrayList<CompanyModel>(_wc.getCollection(CompanyModel.class));
			}
			else {
				return new ArrayList<CompanyModel>();
			}
		}
		finally {
			_wc.close();
		}
	}

	public CompanyModel createCompany(CompanyModel c) {
		// System.out.println("> createCompany(" + c.getId() + ")");
		WebClient _wc = null;
		try {
			_wc = WttTest.initWebClient(APP_URI);
			Response _r = _wc.post(c);
			status = _r.getStatus();
			if (status == Status.OK.getStatusCode()) {
				return _r.readEntity(CompanyModel.class);
			}
			else {
				return null;
			}
		}
		finally {
			_wc.close();
		}
	}

	public CompanyModel readCompany(String id) {
		// System.out.println("> readCompany(" + id + ")");
		WebClient _wc = null;
		try {
			_wc = WttTest.initWebClient(APP_URI);
			Response _r = _wc.path(id).get();
			status = _r.getStatus();
			if (status == Status.OK.getStatusCode()) {
				return _r.readEntity(CompanyModel.class);
			}
			else {
				return null;
			}
		}
		finally {
			_wc.close();
		}
	}

	public CompanyModel updateCompany(CompanyModel c) {
		// System.out.println("> updateCompany()");
		WebClient _wc = null;
		try {
			_wc = WttTest.initWebClient(APP_URI);
			Response _r = _wc.put(c);
			status = _r.getStatus();
			if (status == Status.OK.getStatusCode()) {
				return _r.readEntity(CompanyModel.class);
			}
			else {
				return null;
			}
		}
		finally {
			_wc.close();
		}
	}

	public int deleteCompany(String id) {
		// System.out.println("> deleteCompany(" + id + ")");
		WebClient _wc = null;
		try {
			_wc = WttTest.initWebClient(APP_URI);
			Response _r = _wc.path(id).delete();
			status = _r.getStatus();
			return status;
		}
		finally {
			_wc.close();
		}
	}

	public int countCompanies() {
		// System.out.println("> countCompanies()");
		String _countStr = WttTest.initWebClient(APP_URI).path("count").get().readEntity(String.class);
		// System.out.println("countCompanies (stringified): " + _countStr);
		return new Integer(_countStr).intValue();
	}

}
