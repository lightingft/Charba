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

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Java native object which is wrapping a CHARBA java script object implementation with some utilities to act on java script projects.<br>
 * This script will be injected with CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
@JsType(isNative = true, name = NativeName.JSHELPER, namespace = JsPackage.GLOBAL)
final class NativeJsHelper {

	/**
	 * To avoid any instantiation
	 */
	NativeJsHelper() {
		// do nothing
	}

	/**
	 * 
	 * @param object
	 * @param key
	 * @return
	 */
	static native String type(Object object, String key);

	/**
	 * 
	 * @param object
	 * @param key
	 * @return
	 */
	static native boolean isArray(Object object, String key);

	/**
	 * Creates new proxy for callback which will pass <code>this</code> environment of java script as first argument of callback method.
	 * @return new proxy for callback.
	 */
	static native <T> CallbackProxy<T> newCallbackProxy();
	
	/**
	 * Removes a property from a java script object.
	 * @param object object to be modified.
	 * @param key property key to be removed.
	 */
	static native void remove(NativeObject object, String key);

	/**
	 * Returns a property of java script object as integer.
	 * @param object object to be queried
	 * @param key property key
	 * @return integer value
	 */
	static native int propertyAsInt(Object object, String key);
	
	/**
	 * Returns a property of java script object as double.
	 * @param object object to be queried
	 * @param key property key
	 * @return double value
	 */
	static native double propertyAsDouble(Object object, String key);

	/**
	 * Returns a property of java script object as string.
	 * @param object object to be queried
	 * @param key property key
	 * @return string value
	 */
	static native String propertyAsString(Object object, String key);

}
