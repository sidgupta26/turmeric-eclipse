/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
/**
 * 
 */
package org.ebayopensource.turmeric.eclipse.registry.intf;

import org.ebayopensource.turmeric.eclipse.registry.RegistryActivator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;


/**
 * This is used to provide an additional severity: May, which is required by the
 * Assertion service. The mapping would be the followings: ERROR = MUST, WARNING
 * = SHOULD, WARNING.CODE_MAY = MAY, OK = OK.
 * 
 * Add line number variable to indicates which line causes this problem.
 * 
 * @author yayu
 * 
 */
public class AssertionStatus extends Status implements IValidationStatus {
	/**
	 * indicates which line causes this issue.
	 */
	private int lineNumber = -1;

	/**
	 * @param severity
	 * @param pluginId
	 * @param message
	 */
	public AssertionStatus(String message) {
		this(message, null);
	}		

	public AssertionStatus(int severity, String pluginId, int code,
			String message, Throwable exception) {
		super(severity, pluginId, code, message, exception);
	}
	
	public AssertionStatus(int severity, String pluginId, int code,
			String message, Throwable exception, int lineNumber) {
		this(severity, pluginId, code, message, exception);
		this.lineNumber = lineNumber;
	}


	/**
	 * @param severity
	 * @param pluginId
	 * @param code
	 * @param message
	 * @param exception
	 */
	public AssertionStatus(String message, Throwable exception) {
		super(IStatus.WARNING, RegistryActivator.PLUGIN_ID, CODE_MAY, message,
				exception);
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber){
		this.lineNumber = lineNumber;
	}

	@Override
	public String toString() {
		final StringBuffer buf = new StringBuffer();
		String title = null;
		switch (getSeverity()) {
		case IStatus.WARNING:
			if (getCode() == AssertionStatus.CODE_MAY)
				title = "May Fix Issue: ";
			else
				title = "Should Fix Issue: ";
			break;
		case IStatus.ERROR:
			title = "Must Fix Issue: ";
			break;
		}
		
		buf.append(title);
		buf.append(getMessage());
		return buf.toString();
	}
}
