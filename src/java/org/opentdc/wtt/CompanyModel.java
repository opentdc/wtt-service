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

import java.util.Comparator;

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
	private String id;
	private String title;
	private String description;

	/******************************* Constructors *****************************/
	public CompanyModel() {
	}

	public CompanyModel(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	/******************************* ID *****************************/
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
			
	/******************************* Comparator *****************************/
	public static Comparator<CompanyModel> CompanyComparator = new Comparator<CompanyModel>() {

		public int compare(CompanyModel company1, CompanyModel company2) {
			if (company1.getTitle() == null) {
				return -1;
			}
			if (company2.getTitle() == null) {
				return 1;
			}

			String _companyTitle1 = company1.getTitle().toUpperCase();
			String _companyTitle2 = company2.getTitle().toUpperCase();

			// ascending order
			return _companyTitle1.compareTo(_companyTitle2);

			// descending order
			// return _companyTitle2.compareTo(_companyTitle1);
		}
	};
}
