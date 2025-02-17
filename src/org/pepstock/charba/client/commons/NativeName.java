/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUString WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.commons;

/**
 * Constants with java script object name to use into {@link jsinterop.annotations.JsType} native objects.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class NativeName {

	/**
	 * Constant to bind java script object as object with name <b>{@value OBJECT}</b>.
	 */
	public static final String OBJECT = "Object";

	/**
	 * Constant to bind java script array with name <b>{@value ARRAY}</b>.
	 */
	public static final String ARRAY = "Array";

	/**
	 * Constant to bind java script object as JsHelper with name <b>{@value JSHELPER}</b>, (internal of Charba).
	 */
	public static final String JSHELPER = "CharbaJsHelper";

	/**
	 * Constant to bind java script object as JsControllerHelper with name <b>{@value JSCONTROLLERHELPER}</b>, (internal of
	 * Charba).
	 */
	public static final String JSCONTROLLERHELPER = "CharbaJsControllerHelper";

	/**
	 * Constant to bind java script object as JsWindowHelper with name <b>{@value JSWINDOWHELPER}</b>, (internal of Charba).
	 */
	public static final String JSWINDOWHELPER = "CharbaJsWindowHelper";

	/**
	 * Constant to bind java script object as JsPositionerHelper with name <b>{@value JSPOSITIONERHELPER}</b>, (internal of
	 * Charba).
	 */
	public static final String JSPOSITIONERHELPER = "CharbaJsPositionerHelper";

	/**
	 * Constant to bind java script object as chart with name <b>{@value CHART}</b>, (internal of CHART.JS).
	 */
	public static final String CHART = "Chart";

	/**
	 * Constant to bind java script object as window with name <b>{@value WINDOW}</b>.
	 */
	public static final String WINDOW = "window";

	/**
	 * To avoid any instantiation
	 */
	private NativeName() {
		// do nothing
	}

}
