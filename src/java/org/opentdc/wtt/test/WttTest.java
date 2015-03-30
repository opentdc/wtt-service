package org.opentdc.wtt.test;

import javax.ws.rs.core.MediaType;
import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.opentdc.wtt.WttService;

@RunWith(Suite.class)
@SuiteClasses({ CompanyTest.class, ProjectTest.class, ResourceTest.class })
public class WttTest {

	public static WebClient initWebClient(String uri) {
		JAXRSClientFactoryBean _sf = new JAXRSClientFactoryBean();
		_sf.setResourceClass(WttService.class);
		_sf.setAddress(uri);
		BindingFactoryManager _manager = _sf.getBus().getExtension(
				BindingFactoryManager.class);
		JAXRSBindingFactory _factory = new JAXRSBindingFactory();
		_factory.setBus(_sf.getBus());
		_manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID,
				_factory);
		WebClient _wc = _sf.createWebClient();
		_wc.type(MediaType.APPLICATION_JSON).accept(
				MediaType.APPLICATION_JSON);
		return _wc;
	}

	/********************************** main *********************************/
	/**
	 * Main method to run the JUnit tests outside Eclipse via standard Java code
	 * e.g. with ant.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Testing Company...");
		printResult(JUnitCore.runClasses(CompanyTest.class));
		System.out.println("Testing Project...");
		printResult(JUnitCore.runClasses(ProjectTest.class));
		System.out.println("Tests completed.");
	}
	
	private static void printResult(Result result) {
		for (Failure _failure : result.getFailures()) {
			System.out.println(_failure.toString());
		}
	}
}
