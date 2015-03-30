package org.opentdc.wtt;

import java.util.HashMap;
import java.util.Map;

public enum ServiceType {
	TRANSIENT("transient"), FILE("file"), OPENCRX("opencrx"), MONGODB("mongodb");

	private String label;
	private static Map<String, ServiceType> stringToEnumMapping;

	private ServiceType(String label) {
		this.label = label;
	}

	public static ServiceType getServiceType(String label) {
		if (stringToEnumMapping == null) {
			initMapping();
		}
		return stringToEnumMapping.get(label);
	}

	private static void initMapping() {
		stringToEnumMapping = new HashMap<String, ServiceType>();
		for (ServiceType _st : values()) {
			stringToEnumMapping.put(_st.label, _st);
		}
	}

	public String getLabel() {
		return label;
	}
}
