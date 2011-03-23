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
package org.ebayopensource.turmeric.eclipse.errorlibrary.properties.providers;

import java.util.ArrayList;
import java.util.List;

import org.ebayopensource.turmeric.eclipse.errorlibrary.properties.providers.wizards.pages.NewPropertiesContentErrorWizardPage;
import org.ebayopensource.turmeric.eclipse.errorlibrary.providers.ISOAErrorLibraryWizardPageProvider;
import org.ebayopensource.turmeric.eclipse.errorlibrary.ui.model.ErrorParamModel;
import org.ebayopensource.turmeric.eclipse.logging.SOALogger;
import org.ebayopensource.turmeric.eclipse.resources.ui.model.BaseServiceParamModel;
import org.ebayopensource.turmeric.eclipse.ui.SOABasePage;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;


/**
 * @author yayu
 * 
 */
public final class PropertiesErrorWizardPageProvider implements
		ISOAErrorLibraryWizardPageProvider {
	private NewPropertiesContentErrorWizardPage wizardPage = null;
	private static final SOALogger logger = SOALogger.getLogger();

	/**
	 * 
	 */
	public PropertiesErrorWizardPageProvider() {
		super();
	}

	public IStatus preValidate() {
		return Status.OK_STATUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ebayopensource.turmeric.eclipse.errorlibrary.providers.ISOAErrorLibraryWizardPageProvider#getWizardpages()
	 */
	public List<SOABasePage> getWizardpages(IStructuredSelection selection) {
		final List<SOABasePage> pages = new ArrayList<SOABasePage>(1);
		wizardPage = new NewPropertiesContentErrorWizardPage(selection);
		pages.add(wizardPage);
		return pages;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ebayopensource.turmeric.eclipse.errorlibrary.providers.ISOAErrorLibraryWizardPageProvider#performFinish()
	 */
	public BaseServiceParamModel performFinish() throws Exception {
		if (SOALogger.DEBUG)
			logger.entering();

		final ErrorParamModel model = new ErrorParamModel();
		long nId = wizardPage.getErrorID();
		model.setNID(nId);
		model.setErrorID("" + nId);
		model.setErrorLibrary(wizardPage.getErrorLibrary());
		model.setCategory(wizardPage.getCategory());
		model.setName(wizardPage.getErrorName());
		model.setOrganization(wizardPage.getOrganization());
		model.setSeverity(wizardPage.getSeverity());
		model.setMessage(wizardPage.getContentErrorMessage());
		model.setResolution(wizardPage.getResolution());
		model.setSubdomain(wizardPage.getSubDomain());
		model.setDomain(wizardPage.getDomain());

		if (SOALogger.DEBUG)
			logger.exiting(model);
		return model;
	}

}
