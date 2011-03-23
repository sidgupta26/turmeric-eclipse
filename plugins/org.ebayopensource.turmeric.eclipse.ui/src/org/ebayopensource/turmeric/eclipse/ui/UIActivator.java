/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.eclipse.ui;

import java.io.IOException;
import java.util.PropertyResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.ebayopensource.turmeric.eclipse.logging.SOALogger;
import org.ebayopensource.turmeric.eclipse.utils.plugin.JDTUtil;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle
 */
public class UIActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.ebayopensource.turmeric.eclipse.ui";
	public static final String ICON_PATH = "icons/";

	// The shared instance
	private static UIActivator plugin;
	private static SOALogger logger = SOALogger.getLogger();

	protected PropertyResourceBundle pluginProperties;

	public PropertyResourceBundle getPluginProperties() {
		if (pluginProperties == null) {
			try {
				pluginProperties = JDTUtil.getPluginProperties(getBundle());
			} catch (IOException e) {
				logger.error(e);
			}
		}
		return pluginProperties;
	}

	/**
	 * The constructor
	 */
	public UIActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		StringBuffer buf = new StringBuffer();
		buf.append("SOAPlugin.start - ");
		buf.append(JDTUtil.getBundleInfo(context.getBundle(), SOALogger.DEBUG));
		SOALogger.getLogger().info(buf);
//		initImageRegistry(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static UIActivator getDefault() {
		return plugin;
	}

	/*private void initImageRegistry(final BundleContext context) {
		final ImageRegistry registry = getImageRegistry();
		try {
			final URL url = FileLocator.resolve(context.getBundle()
					.getResource(ICON_PATH));

			final File location = new File(url.getPath());
			if (location != null && location.exists()) {
				for (String file : location.list(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						return name.endsWith(".gif") || name.endsWith(".png");
					}
				})) {
					ImageDescriptor image = imageDescriptorFromPlugin(
							PLUGIN_ID, ICON_PATH + file);
					if (image != null) {
						registry.put(ICON_PATH + file, image);
					}
				}
			}
		} catch (Exception e) {
			logger.warning("Image Registry Initialization Failed!", e);
		}
	}*/
	
	public static Image getImageFromRegistry(AbstractUIPlugin uiPlugin, 
			String iconPathRoot, String path) {
		if (path == null)
			return null;
		path = StringUtils.replaceChars(path, '\\', '/');
		final String iconPath = path.startsWith(iconPathRoot) ? path : iconPathRoot
				+ path;
		
		Image image = uiPlugin.getImageRegistry().get(iconPath);
		if (image == null) {

			final ImageDescriptor descriptor = imageDescriptorFromPlugin(
					uiPlugin.getBundle().getSymbolicName(), iconPath);
			if (descriptor != null) {
				uiPlugin.getImageRegistry().put(iconPath, descriptor);
				image = uiPlugin.getImageRegistry().get(iconPath);
			}
		}
		return image;
		
	}

	public static Image getImageFromRegistry(String path) {
		return getImageFromRegistry(getDefault(), ICON_PATH, path);
	}
	
	public static ImageDescriptor getImageDescriptor(String pluginID, 
			final String path) {
		ImageDescriptor descriptor = imageDescriptorFromPlugin(pluginID, path);
		return descriptor;
	}

	public static ImageDescriptor getImageDescriptor(final String path) {
		return getImageDescriptor(PLUGIN_ID, path);
	}
}
