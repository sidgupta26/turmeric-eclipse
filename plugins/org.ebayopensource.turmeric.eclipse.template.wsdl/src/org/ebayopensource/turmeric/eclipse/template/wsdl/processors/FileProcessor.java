/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.eclipse.template.wsdl.processors;

import java.io.IOException;

import org.ebayopensource.turmeric.eclipse.core.ICommand;
import org.ebayopensource.turmeric.eclipse.exception.core.CommandFailedException;
import org.ebayopensource.turmeric.eclipse.typelibrary.ui.TypeLibraryUIActivator;
import org.ebayopensource.turmeric.eclipse.utils.io.IOUtil;
import org.ebayopensource.turmeric.eclipse.utils.plugin.ProgressUtil;
import org.ebayopensource.turmeric.eclipse.utils.xml.JDOMUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.wst.wsdl.Definition;


/**
 * This processor writes the WSDL model back to a file. The contents are
 * formatted before writing it. It uses the JDom Utility to convert the XML to
 * String and using the IOUtil saves the file. This processor is part of the
 * WSDL templating stack and is the last one. From the use case point of view
 * this is a dumb class as far as WSDL know how is concerned. Basically this
 * class is "what you get is what you save" class.
 * 
 * @author smathew
 * 
 * 
 */
public class FileProcessor implements ICommand {

	/**
	 * This is where the save logic resides. The parameter should be the file
	 * processor parameter for this API to react.
	 *
	 * @param object the object
	 * @param monitor the monitor
	 * @return true, if successful
	 * @throws CommandFailedException the command failed exception
	 * @see org.ebayopensource.turmeric.eclipse.core.ICommand#execute(java.lang.Object,
	 * org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public boolean execute(Object object, IProgressMonitor monitor)
			throws CommandFailedException {
		if (object == null || !(object instanceof FileProcessorParam)) {
			throw new CommandFailedException("Input corrupted");
		}
		FileProcessorParam fileProcessorParam = (FileProcessorParam) object;
		String contents;
		try {
			contents = TypeLibraryUIActivator.formatContents(JDOMUtil
					.convertXMLToString(JDOMUtil
							.convertToJDom(fileProcessorParam.getInputWSDL()
									.getDocument())));
			ProgressUtil.progressOneStep(monitor);

			IOUtil.writeTo(contents, fileProcessorParam.getOutputFile(),
					monitor);
		} catch (CoreException e) {
			throw new CommandFailedException(e);
		} catch (IOException e) {
			throw new CommandFailedException(e);
		}
		return true;
	}

	/**
	 * The File processor parameter is the communication object for the
	 * enclosing File Processor. It contains the WSDL definition as the input
	 * definition and output file as the final file(to be saved).
	 * 
	 * @author smathew
	 * 
	 */
	public static class FileProcessorParam {
		private Definition inputWSDLDef;
		private IFile outputFile;

		/**
		 * Gets the input wsdl.
		 *
		 * @return the input wsdl
		 */
		public Definition getInputWSDL() {
			return inputWSDLDef;
		}

		/**
		 * Sets the input schema.
		 *
		 * @param inputWSDL the new input schema
		 */
		public void setInputSchema(Definition inputWSDL) {
			this.inputWSDLDef = inputWSDL;
		}

		/**
		 * Gets the output file.
		 *
		 * @return the output file
		 */
		public IFile getOutputFile() {
			return outputFile;
		}

		/**
		 * Sets the output file.
		 *
		 * @param outputFile the new output file
		 */
		public void setOutputFile(IFile outputFile) {
			this.outputFile = outputFile;
		}

		/**
		 * Instantiates a new file processor param.
		 *
		 * @param inputWSDL the input wsdl
		 * @param outputFile the output file
		 */
		public FileProcessorParam(Definition inputWSDL, IFile outputFile) {
			super();
			this.inputWSDLDef = inputWSDL;
			this.outputFile = outputFile;
		}

	}

}
