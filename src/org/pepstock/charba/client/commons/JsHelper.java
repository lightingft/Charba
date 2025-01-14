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

import java.util.List;

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.resources.ResourcesType;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.Element;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CHARBA java script object implementation with some
 * utilities to act on java script objects.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class JsHelper {
	// static instance for singleton
	private static final JsHelper INSTANCE = new JsHelper();

	/**
	 * To avoid any instantiation
	 */
	private JsHelper() {
		// to be sure that CHARBA java script object is injected
		Injector.ensureInjected(ResourcesType.getClientBundle().charbaHelper());
	}

	/**
	 * Singleton object to get the helper instance
	 * 
	 * @return jsHelper instance.
	 */
	public static JsHelper get() {
		return INSTANCE;
	}

	/**
	 * Returns an undefined reference
	 * 
	 * @return undefined reference
	 */
	public Object undefined() {
		return NativeJsHelper.undefined();
	}

	/**
	 * Returns a list of strings with element attributes.
	 * 
	 * @param element DOM element to scan
	 * @return a list of strings with element attributes
	 */
	public List<String> elementAttributes(Element element) {
		// checks if arguments is consistent
		// if not, uses a null element for array string
		ArrayString array = element != null ? NativeJsHelper.elementAttributes(element) : null;
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the java script object type of object.
	 * 
	 * @param object the object to get type.
	 * @return the object type
	 */
	public ObjectType typeOf(Object object) {
		// checks consistency of arguments
		if (object != null) {
			// gets the object type by javascript type and checking if is an array
			return ObjectType.getType(NativeJsHelper.typeOf(object), Array.isArray(object));
		}
		// if here the arguments are not consistent
		return ObjectType.UNDEFINED;
	}

	/**
	 * Returns the java script object type of a property.
	 * 
	 * @param object the object on which to search the property.
	 * @param key the string name of the property to test.
	 * @return the object type
	 */
	public ObjectType typeOf(Object object, String key) {
		// checks consistency of arguments
		if (object != null && key != null) {
			// gets the object type by javascript type and checking if is an array
			return ObjectType.getType(NativeJsHelper.type(object, key), NativeJsHelper.isArray(object, key));
		}
		// if here the arguments are not consistent
		return ObjectType.UNDEFINED;
	}

	/**
	 * Creates new proxy for callback which will pass <code>this</code> environment of java script as first argument of callback
	 * method.
	 * 
	 * @param <T> type of callback wrapped by proxy
	 * @return new proxy for callback.
	 */
	public <T> CallbackProxy<T> newCallbackProxy() {
		return NativeJsHelper.newCallbackProxy();
	}

	/**
	 * Removes a property from a java script object.
	 * 
	 * @param object the object on which to remove the property.
	 * @param key the string name of the property to remove.
	 */
	void remove(NativeObject object, String key) {
		NativeJsHelper.remove(object, key);
	}

	/**
	 * Returns a property of java script object as integer.
	 * 
	 * @param object the object on which to define the property.
	 * @param key the string name of the property to be defined or modified..
	 * @return integer value or {@link UndefinedValues#INTEGER} if arguments are not consistent
	 */
	public int propertyAsInt(Object object, String key) {
		// checks consistency of arguments
		if (object != null && key != null) {
			return NativeJsHelper.propertyAsInt(object, key);
		}
		// if here, arguments not consistent
		return UndefinedValues.INTEGER;
	}

	/**
	 * Returns a property of java script object as double.
	 * 
	 * @param object the object on which to define the property.
	 * @param key the string name of the property to be defined or modified..
	 * @return double value or {@link UndefinedValues#DOUBLE} if arguments are not consistent
	 */
	public double propertyAsDouble(Object object, String key) {
		// checks consistency of arguments
		if (object != null && key != null) {
			return NativeJsHelper.propertyAsDouble(object, key);
		}
		// if here, arguments not consistent
		return UndefinedValues.DOUBLE;
	}

	/**
	 * Returns a property of java script object as string.
	 * 
	 * @param object the object on which to define the property.
	 * @param key the string name of the property to be defined or modified..
	 * @return string value or {@link UndefinedValues#STRING} if arguments are not consistent
	 */
	public String propertyAsString(Object object, String key) {
		// checks consistency of arguments
		if (object != null && key != null) {
			return NativeJsHelper.propertyAsString(object, key);
		}
		// if here, arguments not consistent
		return UndefinedValues.STRING;
	}

	/**
	 * Sets the line dash pattern used when stroking lines. It uses an array of values that specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param context context of canvas
	 * @param object array of values that specify alternating lengths of lines and gaps which describe the pattern
	 */
	public void setLineDash(Context2d context, ArrayInteger object) {
		// checks consistency of arguments
		if (context != null && object != null) {
			NativeJsHelper.setLineDash(context, object);
		}
	}

	/**
	 * Returns a chart native event from CHART.JS event.
	 * 
	 * @param event CHART.JS event
	 * @param key key of java script object
	 * @return a chart native event
	 */
	public ChartNativeEvent nativeEvent(NativeObject event, String key) {
		// checks consistency of arguments
		if (event != null && key != null) {
			return NativeJsHelper.nativeEvent(event, key);
		}
		// if here, arguments not consistent
		return null;
	}

}
