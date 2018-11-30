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
package org.pepstock.charba.client.jsinterop.commons;

import org.pepstock.charba.client.Injector;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CHARBA java script object implementation with some utilities to act on java script pbjects.<br>
 * This wrapper is necessary to ensure that script is injected with CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public final class JsHelper {
	
	private static final JsHelper INSTANCE = new JsHelper();

	/**
	 * To avoid any instantiation
	 */
	JsHelper() {
		// to be sure that CHARBA java script object is injected
		Injector.ensureInjected();
	}
	
	/**
	 * Singleton object to get the helper instance
	 * @return jsHelper instance.
	 */
	public static JsHelper get() {
		return INSTANCE;
	}

	/**
	 * 
	 * @param object
	 * @param key
	 * @return
	 */
	public final ObjectType typeOf(Object object, String key) {
		return ObjectType.getType(NativeJsHelper.type(object, key), NativeJsHelper.isArray(object, key)); 
	}

	/**
	 * Creates new proxy for callback which will pass <code>this</code> environment of java script as first argument of callback method.
	 * @return new proxy for callback.
	 */
	public <T> CallbackProxy<T> newCallbackProxy(){
		return NativeJsHelper.newCallbackProxy();
	}
	
	/**
	 * Removes a property from a java script object.
	 * @param object object to be modified.
	 * @param key property key to be removed.
	 */
	public void remove(NativeObject object, String key) {
		NativeJsHelper.remove(object, key);
	}

	/**
	 * Returns a property of java script object as integer.
	 * @param object object to be queried
	 * @param key property key
	 * @return integer value
	 */
	public int propertyAsInt(Object object, String key) {
		return NativeJsHelper.propertyAsInt(object, key);
	}
	
	/**
	 * Returns a property of java script object as double.
	 * @param object object to be queried
	 * @param key property key
	 * @return double value
	 */
	public double propertyAsDouble(Object object, String key) {
		return NativeJsHelper.propertyAsDouble(object, key);
	}

	/**
	 * Returns a property of java script object as string.
	 * @param object object to be queried
	 * @param key property key
	 * @return string value
	 */
	public String propertyAsString(Object object, String key) {
		return NativeJsHelper.propertyAsString(object, key);
	}

}
