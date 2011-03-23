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
package org.ebayopensource.turmeric.eclipse.exception.resources;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * @author yayu
 * 
 */
public class SOAActionExecutionFailedException extends CoreException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 201L;

	/**
	 * @param status
	 */
	public SOAActionExecutionFailedException(IStatus status) {
		super(status);
	}

	/**
	 * @param cause
	 */
	public SOAActionExecutionFailedException(Throwable cause) {
		this(cause.toString(), cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SOAActionExecutionFailedException(String message, Throwable cause) {
		super(new Status(IStatus.ERROR, "org.ebayopensource.turmeric.eclipse", 0,
				message == null || "".equals(message.trim()) ? "Unknown Error"
						: message, cause == null ? new Exception(message)
						: cause));
	}

}
