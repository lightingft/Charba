package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Arcs are used in the polar area, doughnut and pie charts.<br>
 * While chart types provide settings to configure the styling of each dataset, you sometimes want to style all datasets the
 * same way.<br>
 * Options can be configured for four different types of elements: arc, lines, points, and rectangles.<br>
 * When set, these options apply to all objects of that type unless specifically overridden by the configuration attached to a
 * dataset.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name=NativeName.OBJECT)
class NativeArc extends NativeObject {

	/**
	 * Sets the background color.
	 * 
	 * @param backgroundColor the background color.
	 */
	@JsProperty
	native void setBackgroundColor(String backgroundColor);

	/**
	 * Returns the background color.
	 * 
	 * @return the background color. 
	 */
	@JsProperty
	native String getBackgroundColor();

	/**
	 * Sets the border width.
	 * 
	 * @param borderWidth the border width.
	 */
	@JsProperty
	native void setBorderWidth(int borderWidth);

	/**
	 * Returns the border width.
	 * 
	 * @return the border width. 
	 */
	@JsProperty
	native int getBorderWidth();

	/**
	 * Sets the border color.
	 * 
	 * @param borderColor the border color.
	 */
	@JsProperty
	native void setBorderColor(String borderColor);

	/**
	 * Returns the border color.
	 * 
	 * @return the border color. 
	 */
	@JsProperty
	native String getBorderColor();
}
