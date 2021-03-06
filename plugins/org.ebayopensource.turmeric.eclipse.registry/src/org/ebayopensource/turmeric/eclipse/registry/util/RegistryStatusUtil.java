/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.eclipse.registry.util;

import org.ebayopensource.turmeric.eclipse.registry.intf.IValidationStatus;
import org.eclipse.core.runtime.IStatus;


/**
 * The Class RegistryStatusUtil.
 */
public class RegistryStatusUtil {

	/**
	 * Checks if is login fail status.
	 *
	 * @param status the status
	 * @return true, if is login fail status
	 */
	public static boolean isLoginFailStatus(IStatus status) {
		return (IStatus.WARNING == status.getSeverity())
				&& (IValidationStatus.CODE_LOGIN_FAILURE == status.getCode());
	}
}
