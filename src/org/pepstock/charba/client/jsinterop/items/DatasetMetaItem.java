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

import java.util.List;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This object is just a proxy object, created from JavaScript side, to wrap an JavaScript array.<br>
 * Created and passed by CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name=NativeName.OBJECT)
public final class DatasetMetaItem extends NativeObject {

	@JsProperty(name = "type")
	native String getNativeType();
	
	@JsProperty(name = "hidden")
	native boolean isNativeHidden();
	
	@JsProperty(name = "hidden")
	native void setNativeHidden(boolean hidden);

	@JsProperty(name = "yAxisID")
	native String getNativeYAxisID();

	@JsProperty(name = "xAxisID")
	native String getNativeXAxisID();
	
	@JsProperty(name = "data")
	native ArrayObject<DatasetItem> getNativeData();

	
	/**
	 * Returns the type of dataset. If not set, the default is <code>bar</code>.
	 * 
	 * @return the type of dataset
	 * @see org.pepstock.charba.client.Type
	 */
	@JsOverlay
	public final Type getType() {
		// gets string value from java script object
		String value = Checker.check(getNativeType(), ChartType.bar.name());
		// checks if consistent with out of the box chart types
		Type type = ChartType.get(value);
		// if not, creates new type being a controller.
		return type == null ? new ControllerType(value) : type;
	}

	/**
	 * Returns if the dataset is hidden.
	 * 
	 * @return <code>true</code> if the dataset is hidden, otherwise is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	@JsOverlay
	public final boolean isHidden() {
		return Checker.check(isNativeHidden(), UndefinedValues.BOOLEAN);
	}

	/**
	 * Sets if the dataset must be hidden.
	 * 
	 * @param hidden <code>true</code> if the dataset must be hidden, otherwise is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	@JsOverlay
	public final void setHidden(boolean hidden) {
		setNativeHidden(hidden);
	}
	
	/**
	 * Returns the Y axis ID. 
	 * 
	 * @return the Y axis ID. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	@JsOverlay
	public final String getYAxisID() {
		// FIXME
		return Checker.check(getNativeYAxisID(), UndefinedValues.STRING);
	}
	
	/**
	 * Returns the X axis ID. 
	 * 
	 * @return the X axis ID. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	@JsOverlay
	public final String getXAxisID() {
		// FIXME
		return Checker.check(getNativeXAxisID(), UndefinedValues.STRING);
	}

	/**
	 * Returns a list of dataset metadata items.
	 * 
	 * @return a list of dataset metadata items.
	 * @see org.pepstock.charba.client.items.DatasetItem
	 */
	@JsOverlay
	public final List<DatasetItem> getDatasets() {
		return ArrayListHelper.unmodifiableList(getNativeData());
	}

}