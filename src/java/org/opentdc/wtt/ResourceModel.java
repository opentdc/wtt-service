package org.opentdc.wtt;

import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A Resource.
 * We only keep a reference (= ID) as the foreign key to a resource service.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ResourceModel {
	private String id;

	/******************************* Constructors *****************************/
	public ResourceModel(String id) {
		this.id = id;
	}

	// only for testing purposes, where the foreign key is not relevant
	public ResourceModel() {
		this.id = UUID.randomUUID().toString();
	}

	/********************************** ID *****************************/
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	// only for testing purposes, where the foreign key is not relevant
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
}
