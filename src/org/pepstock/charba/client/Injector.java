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
package org.pepstock.charba.client;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.resources.client.ResourcePrototype;
import com.google.gwt.resources.client.TextResource;

/**
 * This utility injects ChartJS java script and CHARBA custom java script implementation (for some utilities) into the web page
 * of GWT.<br>
 * It enables also to inject other script into web page, necessary when you want to use some Chart.JS plugins.<br>
 * It tracks the resources which have been injected using as key their name and class name to avoid that however will inject own
 * resources will use the same name of already injected resources.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public final class Injector {

	// key separator
	private static final String KEY_CLASS_AND_METHOD_SEPARATOR = "_";
	// Prefix ID of injected script elements
	private static final String CHARBA_PREFIX_SCRIPT_ELEMENT_ID = "_charba_";
	// contains all script object injected
	private static final Set<String> ELEMENTS_INJECTED = new HashSet<>();

	/**
	 * To avoid any instantiation
	 */
	private Injector() {
		// do nothing
	}

	/**
	 * Injects a script resource if not injected yet.
	 * 
	 * @param resource script resource
	 */
	public static void ensureInjected(ResourcePrototype resource) {
		// checks if resource is consistent
		if (resource != null) {
			// creates a unique key for the resource
			// to use to understand if is already injected
			String resourceKey = createKey(resource);
			// checks if already injected
			if (!ELEMENTS_INJECTED.contains(resourceKey)) {
				if (resource instanceof TextResource) {
					TextResource textResource = (TextResource) resource;
					// creates a script element
					ScriptElement scriptElement = Document.get().createScriptElement();
					// sets ID
					scriptElement.setId(CHARBA_PREFIX_SCRIPT_ELEMENT_ID + resource.getName());
					// sets the script content source
					scriptElement.setInnerText(textResource.getText());
					// appends to the body
					Document.get().getBody().appendChild(scriptElement);
				}
				ELEMENTS_INJECTED.add(resourceKey);
			}
		}
	}

	/**
	 * Creates a unique key for every single resource type to be injected.<br>
	 * The key is: [resource class name]_[resource name].
	 * 
	 * @param resource resource instance to to create the key
	 * @return a unique key for every single resource type
	 */
	private static final String createKey(ResourcePrototype resource) {
		return resource.getClass().getName() + KEY_CLASS_AND_METHOD_SEPARATOR + resource.getName();
	}
}