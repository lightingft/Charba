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
package org.pepstock.charba.client.commons;

import org.pepstock.charba.client.events.ChartNativeEvent;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.Element;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Java native object which is wrapping a CHARBA java script object implementation with some utilities to act on java script
 * objects.
 * 
 * @author Andrea "Stock" Stocchero
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
	 * Returns an undefined reference
	 * 
	 * @return undefined reference
	 */
	static native Object undefined();

	/**
	 * Returns the java script object type of object.
	 * 
	 * @param object the object to get type.
	 * @return the object type
	 */
	static native String typeOf(Object object);

	/**
	 * Returns the java script object type of a property.
	 * 
	 * @param object the object on which to search the property.
	 * @param key the string name of the property to test.
	 * @return the object type
	 */
	static native String type(Object object, String key);

	/**
	 * This method determines whether the passed property of passed object is an Array.
	 * 
	 * @param object the object on which to test the property.
	 * @param key the string name of the property to test.
	 * @return <code>true</code> if the value is an Array; otherwise, <code>false</code>.
	 */
	static native boolean isArray(Object object, String key);

	/**
	 * Creates new proxy for callback which will pass <code>this</code> environment of java script as first argument of callback
	 * method.
	 * 
	 * @return new proxy for callback.
	 */
	static native <T> CallbackProxy<T> newCallbackProxy();

	/**
	 * Removes a property from a java script object.
	 * 
	 * @param object the object on which to remove the property.
	 * @param key the string name of the property to remove.
	 */
	static native void remove(NativeObject object, String key);

	/**
	 * Returns a property of java script object as integer.
	 * 
	 * @param object the object on which to define the property.
	 * @param key the string name of the property to be defined or modified..
	 * @return integer value
	 */
	static native int propertyAsInt(Object object, String key);

	/**
	 * Returns a property of java script object as double.
	 * 
	 * @param object the object on which to define the property.
	 * @param key the string name of the property to be defined or modified..
	 * @return double value
	 */
	static native double propertyAsDouble(Object object, String key);

	/**
	 * Returns a property of java script object as string.
	 * 
	 * @param object the object on which to define the property.
	 * @param key the string name of the property to be defined or modified..
	 * @return string value
	 */
	static native String propertyAsString(Object object, String key);

	/**
	 * Sets the line dash pattern used when stroking lines. It uses an array of values that specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param context context of canvas
	 * @param object array of values that specify alternating lengths of lines and gaps which describe the pattern
	 */
	static native void setLineDash(Context2d context, ArrayInteger object);

	/**
	 * Returns an array of strings with element attributes.
	 * 
	 * @param element DOM element to scan
	 * @return an array of strings with element attributes
	 */
	static native ArrayString elementAttributes(Element element);

	/**
	 * Returns a chart native event from CHART.JS event.
	 * 
	 * @param event CHART.JS event
	 * @param key key of java script object
	 * @return a chart native event
	 */
	static native ChartNativeEvent nativeEvent(NativeObject event, String key);

}
