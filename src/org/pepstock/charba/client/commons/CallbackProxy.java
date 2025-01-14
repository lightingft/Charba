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

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This is a native object which wraps a java script object created by {@link org.pepstock.charba.client.commons.JsHelper}.<br>
 * This object is used to get a proxy instance which is able to call a java script function passing laso <code>this</code> java
 * script value, to maintain the environment when required.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class CallbackProxy<T> {

	/**
	 * It's a java script function which maps the function <code>proxy</code> implemented into additional java script source.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	public interface Proxy {
		/**
		 * Call method the function <code>proxy</code> implemented into additional java script source.
		 */
		void call();
	}

	/**
	 * Gets the function <code>proxy</code> implemented into additional java script source.
	 * 
	 * @return the proxy function <code>proxy</code> implemented into additional java script source.
	 */
	@JsProperty
	public native Proxy getProxy();

	/**
	 * Returns the function <code>callback</code> implemented into additional java script source.
	 * 
	 * @return the function <code>callback</code> implemented into additional java script source.
	 */
	@JsProperty
	public native T getCallback();

	/**
	 * Sets the function <code>callback</code> implemented into additional java script source.
	 * 
	 * @param callback the function <code>callback</code> implemented into additional java script source.
	 */
	@JsProperty
	public native void setCallback(T callback);

}
