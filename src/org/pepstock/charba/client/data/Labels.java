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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.commons.Array;
import org.pepstock.charba.client.commons.ArrayMixedObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.ObjectType;

/**
 * Contains the labels of the chart.<br>
 * Is able to manage also multi-line labels.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Labels {

	private static final String SEPARATOR = "\n";

	private final ArrayMixedObject array;

	/**
	 * To avoid any instantiations
	 */
	private Labels(ArrayMixedObject array) {
		this.array = array != null ? array : new ArrayMixedObject();
	}

	/**
	 * Returns the java script array.
	 * 
	 * @return the java script array.
	 */
	ArrayMixedObject getArray() {
		return array;
	}

	/**
	 * Builds new label object
	 * 
	 * @return new label object
	 */
	public static Labels build() {
		return new Labels(new ArrayMixedObject());
	}

	/**
	 * Loads the labels form a native array.
	 * 
	 * @param array native array.
	 * @return a labels instance
	 */
	static Labels load(ArrayMixedObject array) {
		return new Labels(array);
	}

	/**
	 * Loads single line labels.
	 * 
	 * @param values array of labels
	 */
	public void load(String... values) {
		// checks if is a valid array
		if (values != null && values.length > 0) {
			// scans values
			for (String value : values) {
				// checks is not null
				if (value != null) {
					// pushes to JS array
					add(value);
				}
			}
		}
	}

	/**
	 * Adds a single line label
	 * 
	 * @param value a single label
	 */
	public void add(String value) {
		// if consistent
		if (value != null) {
			// checks if contains separator
			if (value.contains(SEPARATOR)) {
				// splits the string by separator
				// and adds to object as array
				array.push(ArrayString.fromOrEmpty(value.split(SEPARATOR)));
			} else {
				// pushes to JS array
				array.push(value);
			}
		}
	}

	/**
	 * Adds a multi line label
	 * 
	 * @param values array of string which represents a multi line label
	 */
	public void add(String... values) {
		// checks if is a valid array
		if (values != null && values.length > 0) {
			// creates new JS array and push it
			array.push(ArrayString.fromOrEmpty(values));
		}
	}

	/**
	 * Removes an item of labels by index
	 * 
	 * @param index index of label
	 */
	public void remove(int index) {
		// checks range
		checkRange(index);
		// creates new JS array
		array.remove(index);
	}

	/**
	 * Returns a label at a specific index. If at index there is multi-line label, returns labels with '\n' as separator. If the
	 * index is out of bounds, throws an exception.
	 * 
	 * @param index index of label
	 * @return a label at a specific index
	 */
	public String getString(int index) {
		ObjectType type = getType(index);
		if (ObjectType.ARRAY.equals(type)) {
			ArrayString internalArray = (ArrayString) array.get(index);
			// creates an string builder
			StringBuilder result = new StringBuilder();
			// scans all values
			for (int i = 0; i < internalArray.length(); i++) {
				// adds separator after 1 element
				if (i > 0) {
					result.append(SEPARATOR);
				}
				// adds to builder
				result.append(internalArray.get(i));
			}
			// returns string
			return result.toString();
		}
		// returns string
		// string can not be null, because checked during loading
		return (String) array.get(index);
	}

	/**
	 * Returns a multi line label at a specific index. An array of strings is returned. If the index is out of bounds, throws an
	 * exception.
	 * 
	 * @param index index of label
	 * @return an array of strings
	 */
	public String[] getStrings(int index) {
		ObjectType type = getType(index);
		if (ObjectType.ARRAY.equals(type)) {
			ArrayString internalArray = (ArrayString) array.get(index);
			// creates an string array
			String[] result = new String[internalArray.length()];
			// scans all values
			for (int i = 0; i < internalArray.length(); i++) {
				// adds to java array
				result[i] = internalArray.get(i);
			}
			// returns array
			return result;
		}
		// returns the array with single item
		// string can not be null, because checked during loading
		return new String[] { (String) array.get(index) };
	}

	/**
	 * Returns the type of a label at specific index.
	 * 
	 * @param index index of label
	 * @return the type of label or null if out of range
	 */
	public ObjectType getType(int index) {
		// checks range
		checkRange(index);
		// gets type of label
		return Array.isArray(array.get(index)) ? ObjectType.ARRAY : ObjectType.STRING;
	}

	/**
	 * Checks if the index is in the right range. If the index is out of bounds, throws an exception.
	 * 
	 * @param index index to be checked
	 */
	private void checkRange(int index) {
		if (index < 0 || index >= array.length()) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bouds [0, " + array.length() + "]");
		}
	}

}