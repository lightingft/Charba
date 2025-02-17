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

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.colors.IsColor;

import com.google.gwt.dom.client.ImageElement;

/**
 * Utility to create array list objects from java script arrays.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ArrayListHelper {

	/**
	 * To avoid any instantiation
	 */
	private ArrayListHelper() {
		// nothing
	}

	/**
	 * Creates a array list of doubles by a java script array of doubles.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of doubles instance
	 */
	public static ArrayDoubleList list(ArrayDouble values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayDoubleList(values);
	}

	/**
	 * Creates a array list of integers by a java script array of integers.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of integers instance
	 */
	public static ArrayIntegerList list(ArrayInteger values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayIntegerList(values);
	}

	/**
	 * Creates a array list of strings by a java script array of strings.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance
	 */
	public static ArrayStringList list(ArrayString values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayStringList(values);
	}

	/**
	 * Creates a array list of images by a java script array of images.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of images instance
	 */
	public static ArrayImageList list(ArrayImage values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayImageList(values);
	}

	/**
	 * Creates a array list of strings by an array of colors (instance of {@link org.pepstock.charba.client.colors.IsColor}).
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance
	 */
	public static ArrayStringList list(IsColor... values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayStringList(ArrayString.fromOrNull(values));
	}

	/**
	 * Creates a array list of enumeration values (instance of {@link org.pepstock.charba.client.commons.Key}).
	 * 
	 * @param clazz enumeration class with all possible values of enumeration
	 * @param values array of elements to load when the list is creating.
	 * @param <E> type of key
	 * @return a array list of values
	 */
	public static <E extends Key> ArrayEnumList<E> list(Class<E> clazz, E[] values) {
		// creates the list
		ArrayEnumList<E> result = new ArrayEnumList<>(clazz);
		// checks if array is null
		if (values != null) {
			// adds all elements
			result.addAll(values);
		}
		// returns the list
		return result;
	}

	/**
	 * Creates a array list of enumeration values by an java script array of strings.
	 * 
	 * @param clazz enumeration class with all possible values of enumeration
	 * @param array array of strings to load when the list is creating.
	 * @param <E> type of key
	 * @return a array list of values.
	 */
	public static <E extends Key> ArrayEnumList<E> list(Class<E> clazz, ArrayString array) {
		// returns the list adding the string array list to initialize it
		// PAY ATTENTION: no checks if the values of strings are
		// consistent with the enumeration values
		return new ArrayEnumList<>(clazz, array);
	}

	/**
	 * Creates a array list of generic java script objects by a java script array.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance
	 */
	public static ArrayObjectList list(ArrayObject values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayObjectList(values);
	}

	/**
	 * Creates a array list of java script native object container by a java script array and a factory.
	 * 
	 * @param array array of elements to load when the list is creating.
	 * @param factory factory implementation to create containers by a single native object of the array.
	 * @param <E> type of native object container
	 * @return the instance of updated list
	 */
	public static <E extends NativeObjectContainer> ArrayObjectContainerList<E> list(ArrayObject array, NativeObjectContainerFactory<E> factory) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayObjectContainerList<>(array, factory);
	}

	/**
	 * Creates an unmodifiable array list of doubles by a java script array of doubles.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of doubles instance
	 */
	public static List<Double> unmodifiableList(ArrayDouble values) {
		return Collections.unmodifiableList(list(values));
	}

	/**
	 * Creates an unmodifiable array list of integers by a java script array of integers.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of integers instance
	 */
	public static List<Integer> unmodifiableList(ArrayInteger values) {
		return Collections.unmodifiableList(list(values));
	}

	/**
	 * Creates a array list of strings by a java script array of strings.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance
	 */
	public static List<String> unmodifiableList(ArrayString values) {
		return Collections.unmodifiableList(list(values));
	}

	/**
	 * Creates a array list of images by a java script array of images.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance
	 */
	public static List<ImageElement> unmodifiableList(ArrayImage values) {
		return Collections.unmodifiableList(list(values));
	}

	/**
	 * Creates an unmodifiable array list of enumeration values (instance of {@link org.pepstock.charba.client.commons.Key}).
	 * 
	 * @param clazz enumeration class with all possible values of enumeration
	 * @param values array of elements to load when the list is creating.
	 * @param <E> type of key
	 * @return a array list of values
	 */
	public static <E extends Key> List<E> unmodifiableList(Class<E> clazz, E[] values) {
		return Collections.unmodifiableList(list(clazz, values));
	}

	/**
	 * Creates an unmodifiable array list of enumeration values by an java script array of strings.
	 * 
	 * @param clazz enumeration class with all possible values of enumeration
	 * @param array array of strings to load when the list is creating.
	 * @param <E> type of key
	 * @return a array list of values
	 */
	public static <E extends Key> List<E> unmodifiableList(Class<E> clazz, ArrayString array) {
		return Collections.unmodifiableList(list(clazz, array));
	}

	/**
	 * Creates an unmodifiable array list of generic java script objects by a java script array.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance
	 */
	public static List<NativeObject> unmodifiableList(ArrayObject values) {
		return Collections.unmodifiableList(list(values));
	}

	/**
	 * Creates an unmodifiable array list of java script native object container by a java script array and a factory.
	 * 
	 * @param array array of elements to load when the list is creating.
	 * @param factory factory implementation to create containers by a single native object of the array.
	 * @param <E> type of native object container
	 * @return the instance of updated list
	 */
	public static <E extends NativeObjectContainer> List<E> unmodifiableList(ArrayObject array, NativeObjectContainerFactory<E> factory) {
		return Collections.unmodifiableList(list(array, factory));
	}

}