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
package org.pepstock.charba.client.jsinterop.items;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;

/**
 * This item contains the new size of the chart after it has been resized.<br>
 * This object has been created ONLY when a resize event occurs.
 * 
 * @author Andrea "Stock" Stocchero

 */
public class SizeItem extends NativeObjectContainer {
	
	/**
	 * Name of fields of JavaScript object.
	 */
	protected enum Property implements Key
	{
		width,
		height
	}

	/**
	 * @param nativeObject
	 */
	public SizeItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the width of the chart item in pixel.
	 * 
	 * @return the width of the chart item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public final int getWidth() {
		return getValue(Property.width, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the height of the chart item in pixel.
	 * 
	 * @return the height of the chart item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public final int getHeight() {
		return getValue(Property.height, UndefinedValues.INTEGER);
	}


}