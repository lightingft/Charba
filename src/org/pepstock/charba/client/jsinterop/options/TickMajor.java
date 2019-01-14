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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem;

/**
 * It defines options for the major tick marks that are generated by the axis.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public final class TickMajor extends AbstractTick<Ticks, IsDefaultFontItem> {

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param ticks ticks of axis.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	TickMajor(Ticks ticks, Key childKey, IsDefaultFontItem defaultValues, NativeObject nativeObject) {
		super(ticks, childKey, defaultValues, nativeObject);
	}
	

}