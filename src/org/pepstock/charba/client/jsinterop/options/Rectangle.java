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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultRectangle;

/**
 * Rectangle elements are used to represent the bars in a bar chart.<br>
 * While chart types provide settings to configure the styling of each dataset, you sometimes want to style all datasets the
 * same way.<br>
 * Options can be configured for four different types of elements: arc, lines, points, and rectangles.<br>
 * When set, these options apply to all objects of that type unless specifically overridden by the configuration attached to a
 * dataset.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Rectangle extends AbstractElement<Elements, IsDefaultRectangle> implements IsDefaultRectangle{
	
	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		borderSkipped
	}

	Rectangle(Elements elements, Key childKey, IsDefaultRectangle defaultValues, NativeObject nativeObject) {
		super(elements, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderSkipped the edge to skip drawing the border for.
	 * @see  org.pepstock.charba.client.enums.Position
	 */
	public void setBorderSkipped(Position borderSkipped) {
		setValue(Property.borderSkipped, borderSkipped);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for. Default is {@link  org.pepstock.charba.client.enums.Position#bottom}.
	 * @see  org.pepstock.charba.client.enums.Position
	 */
	public Position getBorderSkipped() {
		return getValue(Property.borderSkipped, Position.class, getDefaultValues().getBorderSkipped());
	}

}