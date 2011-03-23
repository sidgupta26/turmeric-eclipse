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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.ebayopensource.turmeric.eclipse.errorlibrary.properties.Activator;
import org.ebayopensource.turmeric.eclipse.errorlibrary.providers.IErrorDomainCreator;
import org.ebayopensource.turmeric.eclipse.errorlibrary.providers.IErrorLibraryCreator;
import org.ebayopensource.turmeric.eclipse.errorlibrary.providers.IErrorLibraryProvider;
import org.ebayopensource.turmeric.eclipse.errorlibrary.providers.IErrorRegistryViewProvider;
import org.ebayopensource.turmeric.eclipse.errorlibrary.providers.IErrorTypeCreator;
import org.ebayopensource.turmeric.eclipse.errorlibrary.providers.ISOAErrorLibraryWizardPageProvider;
import org.ebayopensource.turmeric.eclipse.errorlibrary.views.ISOAError;
import org.ebayopensource.turmeric.eclipse.resources.model.ISOAProject;
import org.ebayopensource.turmeric.eclipse.utils.plugin.JDTUtil;
import org.eclipse.core.resources.IProject;


/**
 * @author yayu
 *
 */
public class PropertiesContentSOAErrorLibraryProvider implements
		IErrorLibraryProvider {
	public static final String PROVIDER_ID;
	
	static {
		String id = "PropertiesContentErrorLibraryProvider";
		try {
			String data = JDTUtil.getPluginProperties(Activator.getDefault().getBundle())
			.getString("ErrorLib.Provider.ID.Properties");
			if (StringUtils.isNotBlank(data)) {
				id = data;
			}
		} catch (IOException e) {
			//ignore
		}
		PROVIDER_ID = id;
	}
		
	
	private ISOAErrorLibraryWizardPageProvider errorLibWizardProvider = null;
	private ISOAErrorLibraryWizardPageProvider errorDomainWizardProvider = null;
	private ISOAErrorLibraryWizardPageProvider errorWizardProvider = null;
	private IErrorLibraryCreator errorCreator = null;
	private IErrorDomainCreator domainCreator = null;
	private IErrorTypeCreator typeCreator = null;
	private IErrorRegistryViewProvider viewProvider = null;

	/**
	 * 
	 */
	public PropertiesContentSOAErrorLibraryProvider() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.eclipse.errorlibrary.providers.IErrorLibraryProvider#getErrorLibraryCreationWizardpage()
	 */
	public ISOAErrorLibraryWizardPageProvider getErrorLibraryWizardPageProvider() {
		if (errorLibWizardProvider == null) {
			errorLibWizardProvider = new PropertiesErrorLibraryWizardPageProvider();
		}
		return errorLibWizardProvider;
	}

	public ISOAErrorLibraryWizardPageProvider getErrorWizardPageProvider() {
		if (errorWizardProvider == null) {
			errorWizardProvider = new PropertiesErrorWizardPageProvider();
		}
		return errorWizardProvider;
	}

	public ISOAErrorLibraryWizardPageProvider getErrorDomainWizardPageProvider() {
		if (errorDomainWizardProvider == null) {
			errorDomainWizardProvider = new PropertiesDomainWizardPageProvider();
		}
		return errorDomainWizardProvider;
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.eclipse.errorlibrary.providers.IErrorLibraryProvider#getProvider()
	 */
	public String getProviderID() {
		return PROVIDER_ID;
	}

	public IErrorLibraryCreator getErrorLibraryCreator() {
		if (errorCreator == null) {
			errorCreator = new PropertiesContentErrorLibraryCreator();
		}
		return errorCreator;
	}

	public IErrorDomainCreator getErrorDomainCreator() {
		if (domainCreator == null) {
			domainCreator = new PropertiesContentErrorDomainCreator();
		}
		return domainCreator;
	}

	public IErrorTypeCreator getErrorTypeCreator() {
		if (typeCreator == null) {
			typeCreator = new PropertiesContentErrorTypeCreator();
		}
		return typeCreator;
	}

	public IErrorRegistryViewProvider getErrorRegistryViewProvider() {
		if (viewProvider == null) {
			viewProvider = new PropertiesErrorRegistryViewProvider();
		}
		return viewProvider;
	}

	public List<String> getSourceSubFolders(ISOAProject project) {
		List<String> folders = new ArrayList<String>();
		folders.add(PropertiesSOAConstants.FOLDER_ERROR_DOMAIN + "/" + project.getProjectName());
		return folders;
	}

	public String getImportErrorSource(IProject project, ISOAError error) {
		String retCode = PropertiesSOAConstants.IMPORT_CODE_ERROR_CONSTR;
		retCode = StringUtils.replace(retCode, "$error", error.getName().toUpperCase());
//		retCode = StringUtils.replace(retCode, "$domain", error.getDomain().getName().toUpperCase());
		return retCode;
	}

}
