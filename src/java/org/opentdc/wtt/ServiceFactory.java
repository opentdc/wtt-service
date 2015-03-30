package org.opentdc.wtt;

import javax.servlet.ServletContext;

import org.opentdc.exception.NotImplementedException;
import org.opentdc.util.ServiceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceFactory {
	private static Logger logger = LoggerFactory
			.getLogger("org.opentdc.wtt.ServiceFactory");

	public static ServiceProvider getServiceProvider(ServletContext context) {
		ServiceType _serviceType = ServiceType.getStorageType(context
				.getInitParameter("service.type"));
		logger.info("> getServiceProvider(): using serviceType " + _serviceType);

		ServiceProvider _sp = null;
		switch (_serviceType) {
		case TRANSIENT:
			throw new NotImplementedException(
				"WTT services is not yet implemented with transient service");
		case FILE:
			throw new NotImplementedException(
				"WTT services is not yet implemented with file service");
		case OPENCRX:
			throw new NotImplementedException(
					"WTT services is not yet implemented with openCRX service");
		case MONGODB:
			throw new NotImplementedException(
					"WTT services is not yet implemented with MongoDB service");
		default:
			throw new NotImplementedException(
					"WTT services has unknown service type <" + _serviceType
							+ "> configured.");
		}
		//return _sp;
	}
}