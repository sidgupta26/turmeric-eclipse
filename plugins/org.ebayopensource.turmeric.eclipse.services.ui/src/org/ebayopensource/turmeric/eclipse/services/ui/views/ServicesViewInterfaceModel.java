/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.eclipse.services.ui.views;

import org.apache.commons.lang.StringUtils;
import org.ebayopensource.turmeric.eclipse.ui.UIConstants;


/**
 * The Class ServicesViewInterfaceModel.
 *
 * @author smathew
 */
public class ServicesViewInterfaceModel extends ServicesViewBaseModel {

	private String interfaceName;

	/**
	 * Gets the interface name.
	 *
	 * @return the interface name
	 */
	public String getInterfaceName() {
		return interfaceName;
	}

	/**
	 * Sets the interface name.
	 *
	 * @param interfaceName the new interface name
	 */
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	/**
	 * Instantiates a new services view interface model.
	 *
	 * @param interfaceName the interface name
	 */
	public ServicesViewInterfaceModel(String interfaceName) {
		super();
		this.interfaceName = interfaceName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String[] formattedArray = { UIConstants.SERVICES_VIEW_INTERFACE,
				UIConstants.SERVICES_VIEW_COLON, " ", interfaceName };
		return StringUtils.join(formattedArray);
	}
	
	/**
	 * Gets the image name.
	 *
	 * @return the image name
	 */
	public static String getImageName() {
		return UIConstants.IMAGE_INTERFACE;
	}
}
