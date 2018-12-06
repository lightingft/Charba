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

import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This is native java script object, passed by CHART.JS during the animation events, which contains the animation item.<br>
 * This is the CHART.JS item with all needed info.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name=NativeName.OBJECT)
public final class AnimationObject {
	
	/**
	 * To avoid any user creation
	 */
	protected AnimationObject() {}

	/**
	 * Returns the native property of java script object related to animation item.
	 * 
	 * @return java script object related to animation item.
	 */
	@JsProperty
	native NativeObject getAnimationObject();
	
	/**
	 * Returns the animation item, by the native java script object.
	 * 
	 * @return the animation item, by the native java script object.
	 */
	@JsOverlay
	public final AnimationItem getAnimationItem() {
		return new AnimationItem(getAnimationObject());
	}

}