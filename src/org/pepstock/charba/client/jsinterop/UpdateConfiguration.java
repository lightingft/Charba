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
package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;
import org.pepstock.charba.client.jsinterop.defaults.globals.DefaultOptions;

/**
 * Object can be provided with additional configuration for the update/render process.<br>
 * This is useful when update is manually called inside an event handler and some different animation is desired.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class UpdateConfiguration extends NativeObjectContainer{
	
	private static final boolean DEFAULT_LAZY = false;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		duration,
		lazy,
		easing
	}

	/**
	 * Sets the animation easing function.
	 * 
	 * @param easing animation easing function.
	 * @see org.pepstock.charba.client.enums.Easing
	 */
	public void setEasing(Easing easing) {
		setValue(Property.easing, easing);
	}

	/**
	 * Returns the animation easing function.
	 * 
	 * @return the animation easing function. Default is {@link org.pepstock.charba.client.enums.Easing#easeOutQuart}.
	 * @see org.pepstock.charba.client.enums.Easing
	 */
	public Easing getEasing() {
		return getValue(Property.easing, Easing.class, DefaultOptions.get().getAnimation().getEasing());
	}

	/**
	 * Sets the time for the animation of the redraw in milliseconds.
	 * 
	 * @param milliseconds time for the animation of the redraw in milliseconds.
	 */
	public void setDuration(int milliseconds) {
		setValue(Property.duration, milliseconds);
	}

	/**
	 * Returns the time for the animation of the redraw in milliseconds.
	 * 
	 * @return time for the animation of the redraw in milliseconds. Default is 1000.
	 */
	public int getDuration() {
		return getValue(Property.duration, DefaultOptions.get().getAnimation().getDuration());
	}

	/**
	 * If true, the animation can be interrupted by other animations.
	 * 
	 * @param intersect if true, the animation can be interrupted by other animations.
	 */
	public void setLazy(boolean intersect) {
		setValue(Property.lazy, intersect);
	}

	/**
	 * If true, the animation can be interrupted by other animations
	 * 
	 * @return if true, the animation can be interrupted by other animations. Default is false.
	 */
	public boolean isLazy() {
		return getValue(Property.lazy, DEFAULT_LAZY);
	}
	
	/**
	 * Wraps the protected method to get the java script object in order to consume it to configure the chart update or render.
	 * 
	 * @return the java script object in order to consume it to configure the chart update or render.
	 */
	NativeObject getObject() {
		return getNativeObject();
	}

}