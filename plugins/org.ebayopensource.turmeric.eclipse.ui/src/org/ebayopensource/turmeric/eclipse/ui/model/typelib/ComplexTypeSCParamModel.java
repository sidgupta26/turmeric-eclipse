/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.eclipse.ui.model.typelib;

import org.ebayopensource.turmeric.eclipse.core.model.typelibrary.TypeParamModel;
import org.ebayopensource.turmeric.eclipse.ui.wizards.pages.typelib.ComplexTypeWizardAttribPage;

/**
 * UI Model for Complex Type with Simple Content creation
 * @author ramurthy
 *
 */

public class ComplexTypeSCParamModel extends TypeParamModel {

	private ComplexTypeWizardAttribPage.AttribTableModel[] attribTableModel;

	public ComplexTypeWizardAttribPage.AttribTableModel[] getAttribTableModel() {
		return attribTableModel;
	}

	public void setAttribTableModel(ComplexTypeWizardAttribPage.AttribTableModel[] attribTableModel) {
		this.attribTableModel = attribTableModel;
	}	
}