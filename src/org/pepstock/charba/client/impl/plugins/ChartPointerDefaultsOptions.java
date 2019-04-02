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
package org.pepstock.charba.client.impl.plugins;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.impl.plugins.enums.PointerElement;

/**
 * Configuration options of pointer plugin. This is mapping the configuration set into default global, used as default of the
 * chart one, if exist.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class ChartPointerDefaultsOptions extends NativeObjectContainer {

	/**
	 * Builds the object with an empty java script object and uses the constants as default.
	 */
	ChartPointerDefaultsOptions() {
		super();
	}

	/**
	 * Builds the object with a java script object stored into options.
	 * 
	 * @param nativeObject native object into options
	 */
	ChartPointerDefaultsOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the cursor type as string when the cursor is over the dataset item.
	 * 
	 * @return cursor type as string
	 */
	String getCursorPointerAsString() {
		return getValue(ChartPointerOptions.Property.cursorPointer, ChartPointerOptions.DEFAULT_CURSOR_POINTER.name());
	}

	/**
	 * Returns the chart elements in scope to "cursorpointer" plugin.
	 * 
	 * @return the chart elements in scope to "cursorpointer" plugin
	 */
	List<PointerElement> getElements() {
		// checks if there is the property
		if (has(ChartPointerOptions.Property.elements)) {
			// reads the property
			ArrayString array = getArrayValue(ChartPointerOptions.Property.elements);
			return ArrayListHelper.list(PointerElement.class, array);
		} else {
			// if here, no property
			// then it uses the default static ones
			// clones them into another list in order to be able to 
			// change them
			List<PointerElement> elements = new LinkedList<>();
			elements.addAll(ChartPointerOptions.DEFAULT_ELEMENTS);
			return elements;
		}
	}
}
