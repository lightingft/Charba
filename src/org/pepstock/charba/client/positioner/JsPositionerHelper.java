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
package org.pepstock.charba.client.positioner;

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.CallbackProxy.Proxy;
import org.pepstock.charba.client.enums.IsTooltipPosition;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Internal utility of positioner to invoke java script methods to activate and manage custom positioner.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class JsPositionerHelper {

	// static instance for singleton
	private static final JsPositionerHelper INSTANCE = new JsPositionerHelper();

	/**
	 * To avoid any instantiation
	 */
	private JsPositionerHelper() {
		// to be sure that CHART.JS java script object is injected
		// some methods are calling CHART.JS for this reason is mandatory
		// to include also chart.js
		Injector.ensureInjected(ResourcesType.getClientBundle().chartJs());
		// to be sure that CHARBA java script object is injected
		Injector.ensureInjected(ResourcesType.getClientBundle().charbaHelper());
	}

	/**
	 * Singleton object to get the helper instance
	 * 
	 * @return jsControllerHelper instance.
	 */
	static JsPositionerHelper get() {
		return INSTANCE;
	}

	/**
	 * Register the tooltips positioner to CHART.JS.
	 * 
	 * @param position new position for tooltips
	 * @param object callback to invoke the tolltip positioner
	 */
	void register(IsTooltipPosition position, Proxy object) {
		NativeJsPositionerHelper.register(position.value(), object);
	}

	/**
	 * Unregister the tooltips positioner from CHART.JS.
	 * 
	 * @param position position of tooltips to be removed
	 */
	void unregister(IsTooltipPosition position) {
		NativeJsPositionerHelper.unregister(position.value());
	}

	/**
	 * Invokes an existing positioner to get the point.
	 * 
	 * @param position position of tooltips to be invoked
	 * @param context context Value of <code>this</code> to the execution context of function.
	 * @param datasetItems list of dataset items
	 * @param eventPosition point on the canvas where the event occurred.
	 * @return the point calculated by positioner or <code>null</code> if positioner does not exist
	 */
	Point invoke(IsTooltipPosition position, PositionerContext context, ArrayObject datasetItems, Point eventPoint) {
		return NativeJsPositionerHelper.invoke(position.value(), context, datasetItems, eventPoint);
	}
}
