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

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.options.Scale;

/**
 * Default global scale (maps the java script object chart.defaults.scale).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class GlobalScale extends Scale {

	/**
	 * Creates the object with the native object which maps the java script object chart.defaults.scale.
	 * 
	 * @param nativeObject native object which maps the java script object chart.defaults.scale
	 */
	GlobalScale(NativeObject nativeObject) {
		// uses the CHART.JS scale of default options as default one
		super(DefaultsBuilder.get().getScaledOptions().getScale(), nativeObject);
	}

}
