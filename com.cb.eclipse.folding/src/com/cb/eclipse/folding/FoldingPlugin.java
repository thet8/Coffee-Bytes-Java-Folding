/*******************************************************************************
 * Copyright (c) 2004 Coffee-Bytes.com and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.opensource.org/licenses/cpl.php
 * 
 * Contributors:
 *     Coffee-Bytes.com - initial API and implementation
 *******************************************************************************/
package com.cb.eclipse.folding;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.cb.eclipse.folding.java.JavaSettings;
import com.cb.eclipse.folding.java.preferences.Defaults;
import com.cb.eclipse.folding.theme.Images;

/**
 * The main plugin class for the FoldingPlugin.
 */
public class FoldingPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.cb.eclipse.folding";

	// The shared instance.
	private static FoldingPlugin plugin;

	private ResourceBundle resourceBundle;

	private Images images;

	private JavaSettings javaDomain;

	/**
	 * The constructor.
	 */
	public FoldingPlugin() {
		plugin = this;
	}

	public static void restoreToDefaults() {
		Defaults.restoreDefaults(getPrefs());
	}

	public static void logError(Throwable t, String message) {
		FoldingPlugin.getDefault().getLog().log(
				new Status(Status.ERROR, PLUGIN_ID, 0, message, t));
	}

	/**
	 * Returns the shared instance.
	 */
	public static FoldingPlugin getDefault() {
		return plugin;
	}

	public static Images getImages() {
		if (plugin.images == null) {
			plugin.getImageRegistry();
		}
		return plugin.images;
	}

	public static JavaSettings getJavaDomain() {
		return getDefault().javaDomain;
	}

	public static IPreferenceStore getPrefs() {
		return getDefault().getPreferenceStore();
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 */
	public static String getMessage(String key) {
		ResourceBundle bundle = FoldingPlugin.getDefault().getResourceBundle();
		try {
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return key;
		}
	}

	public static boolean getBoolean(String key) {
		boolean result = FoldingPlugin.getPrefs().getBoolean(key);
		return result;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		resourceBundle = Platform.getResourceBundle(getBundle());
		Defaults.applyDefaults(getPreferenceStore());
		javaDomain = new JavaSettings(getPreferenceStore());
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		resourceBundle = null;
		plugin = null;
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

    /* (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeImageRegistry(org.eclipse.jface.resource.ImageRegistry)
     */
    protected void initializeImageRegistry(ImageRegistry reg) {
		images = new Images(reg);
    }

}