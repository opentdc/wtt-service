package org.opentdc.wtt;

import java.util.Arrays;

/*
Fixing Serialization bug in Jettison.
*/
public class WttProvider<T> extends org.apache.cxf.jaxrs.provider.json.JSONProvider<T> {

	public WttProvider(
	) {
		super();
		this.setSerializeAsArray(true);
		this.setArrayKeys(Arrays.asList("projects", "resources"));
	}	
}
