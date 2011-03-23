/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.eclipse.logging;

import java.io.IOException;
import java.util.PropertyResourceBundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 * @author Yang Yu(yayu@ebay.com)
 */
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.ebayopensource.turmeric.eclipse.logging";
	
	public final static String MY_PROPERTIES = "plugin.properties";
	private static BundleContext context = null;
	private static SOALogger soaLogger = SOALogger.getLogger();

    protected PropertyResourceBundle pluginProperties;

    public PropertyResourceBundle getPluginProperties(){
    	if (pluginProperties == null){
    		try {
    			pluginProperties = new PropertyResourceBundle(
    					FileLocator.openStream(getBundle() == null ? context.getBundle() : getBundle(),
    							new Path(MY_PROPERTIES),false));
    		} catch (IOException e) {
    			soaLogger.error(e);
    		}
    	}
    	return pluginProperties;
    }
	
	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		Activator.context = context;
		plugin = this;
		super.start(context);
		/*try {
			SOAAdoptionTrackingActivator.start();
		}
		catch (Exception e)
		{
			Logger.getLogger( SOALogger.GLOBAL_LOGGER_ID_PLUGIN ).throwing(Activator.class.getName(), "start", e);
		}*/
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
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
	public static Activator getDefault() {
		return plugin;
	}

}
