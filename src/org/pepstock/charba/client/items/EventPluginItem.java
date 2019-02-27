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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.events.ChartNativeEvent;

/**
 * This is a wrapper of java script object which represents a event.<br>
 * This object is used in the plugins methods of CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EventPluginItem extends NativeObjectContainer {

	// "native" is a keyword of Java therefore
	// it can not add the key into enum.
	private static final String NATIVE_KEY = "native";

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		type,
		x,
		y
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public EventPluginItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the native event into the CHART.JS event.
	 * 
	 * @return the native event into the CHART.JS event or <code>null</code> if doen't not exist.
	 */
	public ChartNativeEvent getEvent() {
		return JsHelper.get().nativeEvent(getNativeObject(), NATIVE_KEY);
	}

	/**
	 * Returns X value of event.
	 * 
	 * @return X value of event. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getX() {
		return getValue(Property.x, UndefinedValues.INTEGER);
	}

	/**
	 * Returns Y value of event.
	 * 
	 * @return Y value of event. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getY() {
		return getValue(Property.y, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the event type a string.
	 * 
	 * @return the event type a string. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	public String getType() {
		return getValue(Property.type, UndefinedValues.STRING);
	}
}