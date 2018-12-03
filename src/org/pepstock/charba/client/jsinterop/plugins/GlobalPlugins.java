/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.jsinterop.plugins;

import java.util.HashSet;
import java.util.Set;

import org.pepstock.charba.client.jsinterop.Plugin;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;

/**
 * Glabal configuration to set plugins at global level.<br>
 * It maps the CHART.JS object of default, <code>chart.plugins</code>.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GlobalPlugins {

	// list of global plugins set by user (not OOTB)
	private final Set<String> pluginIds = new HashSet<String>();
	
	private final NativePlugins plugins;

	/**
	 * @param plugins
	 */
	public GlobalPlugins(NativePlugins plugins) {
		this.plugins = plugins;
	}

	/**
	 * Registers a plugin as global, to apply to all charts. 
	 * @param plugin plugin instance
	 * @return <code>true</code> if registered, otherwise <code>false</code> if the plugin is already registered with the plugin id of plugin instance.
	 * @throws InvalidPluginIdException  if the plugin id is not correct.
	 */
	public boolean register(Plugin plugin) throws InvalidPluginIdException{
		// checks the plugin id
		PluginIdChecker.check(plugin.getId());
		// checks if ID is already registered
		if (getIds().contains(plugin.getId())){
			return false;
		}
		// creates a java script object, wrapper of the plugin
		GlobalPlugin wPlugin = new GlobalPlugin(plugin);
		plugins.register(wPlugin.getNativeObject());
		// stores the id into a set
		pluginIds.add(plugin.getId());
		return true;
	}

	/**
	 * Unregisters a global plugin. THis is possible ONLY for plugins added as custom ones. 
	 * @param pluginId plugin instance
	 * @return <code>true</code> if unregistered, otherwise <code>false</code> if the plugin is not a custom one.
	 * @throws InvalidPluginIdException if the plugin id is not correct.
	 */
	public boolean unregister(String pluginId) throws InvalidPluginIdException{
		// checks the plugin id
		PluginIdChecker.check(pluginId);
		// checks if ID is already registered on custom one
		if (!pluginIds.contains(pluginId)){
			return false;
		}
		// gets plugins ids requesting to CHART.JS.
		ArrayObject existingPlugins = plugins.getAll();
		// scans ids
		for (int i=0; i<existingPlugins.length(); i++){
			PluginReference reference = new PluginReference(existingPlugins.get(i));
			if (reference.getId() != null && reference.getId().equalsIgnoreCase(pluginId)) {
				plugins.unregister(reference);
				// stores the id into a set
				pluginIds.remove(pluginId);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets all global registered plugins ids.
	 * @return all global registered plugins ids.
	 */
	public Set<String> getIds(){
		// gets plugins ids requesting to CHART.JS.
		ArrayObject existingPlugins = plugins.getAll();
		// creates a set of strings
		final Set<String> pluginsIds = new HashSet<String>();
		// checks teh result from CHART.JS
		if (existingPlugins != null && existingPlugins.length() > 0){
			// scans ids
			for (int i=0; i<existingPlugins.length(); i++){
				PluginReference reference = new PluginReference(existingPlugins.get(i));
				pluginsIds.add(reference.getId());
			}
		}
		return pluginsIds;
	}
}