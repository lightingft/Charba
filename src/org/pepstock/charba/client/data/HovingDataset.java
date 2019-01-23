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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayPattern;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.ArrayStringList;
import org.pepstock.charba.client.commons.Key;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * This class collects a set of common field for Pie and Polar charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class HovingDataset extends Dataset {

	// internal cache for patterns for property background
	private final List<Pattern> patternsForBackground = new LinkedList<>();

	// internal cache for patterns for property hover background
	private final List<Pattern> patternsForHoverBackground = new LinkedList<>();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		backgroundColor,
		borderColor,
		borderWidth,
		hoverBackgroundColor,
		hoverBorderColor,
		hoverBorderWidth
	}

	/**
	 * Sets the fill color of the arcs in the dataset.
	 * 
	 * @param backgroundColor the fill color of the arcs in the dataset.
	 */
	public void setBackgroundColor(IsColor... backgroundColor) {
		setArrayValue(Property.backgroundColor, ArrayString.of(backgroundColor));
		// clear previous pattern stored if there are
		patternsForBackground.clear();
	}

	/**
	 * Sets the fill color of the arcs in the dataset.
	 * 
	 * @param backgroundColor the fill color of the arcs in the dataset.
	 */
	public void setBackgroundColor(String... backgroundColor) {
		setArrayValue(Property.backgroundColor, ArrayString.of(backgroundColor));
		// clear previous pattern stored if there are
		patternsForBackground.clear();
	}

	/**
	 * Sets the fill pattern of the arcs in the dataset.
	 * 
	 * @param backgroundColor the fill pattern of the arcs in the dataset.
	 */
	public void setBackgroundColor(Pattern... backgroundColor) {
		setArrayValue(Property.backgroundColor, ArrayPattern.of(backgroundColor));
		// clear previous pattern stored if there are
		patternsForBackground.clear();
		patternsForBackground.addAll(Arrays.asList(backgroundColor));
	}

	/**
	 * Returns the fill color of the arcs in the dataset as string. If property is missing or not a color, returns an empty
	 * list.
	 * 
	 * @return list of the fill color of the arcs in the dataset as string. If property is missing or not a color, returns an
	 *         empty list.
	 */
	public List<String> getBackgroundColorAsString() {
		// checks if the list of pattern is empty
		// if not, means the property is pattern and not colors
		if (patternsForBackground.isEmpty()) {
			// returns list of colors
			ArrayString array = getArrayValue(Property.backgroundColor);
			return ArrayListHelper.list(array);
		} else {
			// the property is colors
			// therefore returns an empty list
			return new ArrayStringList();
		}
	}

	/**
	 * Returns the fill color of the arcs in the dataset. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill color of the arcs in the dataset. If property is missing or not a color, returns an empty list.
	 */
	public List<IsColor> getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the fill patters of the arcs in the dataset. If property is missing or not a pattern, returns an empty list.
	 * 
	 * @return list of the fill patterns of the arcs in the dataset. If property is missing or not a pattern, returns an empty
	 *         list.
	 */
	public List<Pattern> getBackgroundColorAsPatterns() {
		return patternsForBackground;
	}

	/**
	 * Sets the border color of the arcs in the dataset.
	 * 
	 * @param borderColor the border color of the arcs in the dataset.
	 */
	public void setBorderColor(IsColor... borderColor) {
		setArrayValue(Property.borderColor, ArrayString.of(borderColor));
	}

	/**
	 * Sets the border color of the arcs in the dataset as string.
	 * 
	 * @param borderColor the border color of the arcs in the dataset as string.
	 */
	public void setBorderColor(String... borderColor) {
		setArrayValue(Property.borderColor, ArrayString.of(borderColor));
	}

	/**
	 * Returns the border color of the arcs in the dataset as string.
	 * 
	 * @return list of the border color of the arcs in the dataset as string.
	 */
	public List<String> getBorderColorAsString() {
		ArrayString array = getArrayValue(Property.borderColor);
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns the border color of the arcs in the dataset.
	 * 
	 * @return list of the border color of the arcs in the dataset.
	 */
	public List<IsColor> getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Sets the border width of the arcs in the dataset.
	 * 
	 * @param borderWidth the border width of the arcs in the dataset.
	 */
	public void setBorderWidth(int... borderWidth) {
		setArrayValue(Property.borderWidth, ArrayInteger.of(borderWidth));
	}

	/**
	 * Returns the border width of the arcs in the dataset.
	 * 
	 * @return list of the border width of the arcs in the dataset.
	 */
	public List<Integer> getBorderWidth() {
		ArrayInteger array = getArrayValue(Property.borderWidth);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the fill color of the arcs when hovered
	 * 
	 * @param colors the fill color of the arcs when hovered
	 */
	public void setHoverBackgroundColor(IsColor... colors) {
		setArrayValue(Property.hoverBackgroundColor, ArrayString.of(colors));
		// clear previous pattern stored if there are
		patternsForHoverBackground.clear();
	}

	/**
	 * Sets the fill color of the arcs when hovered as string
	 * 
	 * @param colors the fill color of the arcs when hovered as string
	 */
	public void setHoverBackgroundColor(String... colors) {
		setArrayValue(Property.hoverBackgroundColor, ArrayString.of(colors));
		// clear previous pattern stored if there are
		patternsForHoverBackground.clear();
	}

	/**
	 * Sets the fill pattern of the arcs in the dataset when hovered.
	 * 
	 * @param colors the fill pattern of the arcs in the dataset when hovered.
	 */
	public void setHoverBackgroundColor(Pattern... colors) {
		setArrayValue(Property.hoverBackgroundColor, ArrayPattern.of(colors));
		// clear previous pattern stored if there are
		patternsForHoverBackground.clear();
		patternsForHoverBackground.addAll(Arrays.asList(colors));
	}

	/**
	 * Returns the fill color of the arcs when hovered as string. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill color of the arcs when hovered as string. If property is missing or not a color, returns an
	 *         empty list.
	 */
	public List<String> getHoverBackgroundColorAsString() {
		// checks if the list of pattern is empty
		// if not, means the property is pattern and not colors
		if (patternsForHoverBackground.isEmpty()) {
			// returns list of colors
			ArrayString array = getArrayValue(Property.hoverBackgroundColor);
			return ArrayListHelper.list(array);
		} else {
			// the property is colors
			// therefore returns an empty list
			return new ArrayStringList();
		}
	}

	/**
	 * Returns the fill color of the arcs when hovered. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill color of the arcs when hovered. If property is missing or not a color, returns an empty list.
	 */
	public List<IsColor> getHoverBackgroundColor() {
		return ColorBuilder.parse(getHoverBackgroundColorAsString());
	}

	/**
	 * Returns the fill patters of the arcs in the dataset when hovered. If property is missing or not a pattern, returns an
	 * empty list.
	 * 
	 * @return list of the fill patterns of the arcs in the dataset when hovered. If property is missing or not a pattern,
	 *         returns an empty list.
	 */
	public List<Pattern> getHoverBackgroundColorAsPatterns() {
		return patternsForHoverBackground;
	}

	/**
	 * Sets the stroke color of the arcs when hovered as string.
	 * 
	 * @param colors the stroke color of the arcs when hovered as string.
	 */
	public void setHoverBorderColor(IsColor... colors) {
		setArrayValue(Property.hoverBorderColor, ArrayString.of(colors));
	}

	/**
	 * Sets the stroke color of the arcs when hovered as string.
	 * 
	 * @param colors the stroke color of the arcs when hovered as string.
	 */
	public void setHoverBorderColor(String... colors) {
		setArrayValue(Property.hoverBorderColor, ArrayString.of(colors));
	}

	/**
	 * Returns the stroke color of the arcs when hovered.
	 * 
	 * @return list of the stroke color of the arcs when hovered.
	 */
	public List<String> getHoverBorderColorAsString() {
		ArrayString array = getArrayValue(Property.hoverBorderColor);
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns the stroke color of the arcs when hovered.
	 * 
	 * @return list of the stroke color of the arcs when hovered.
	 */
	public List<IsColor> getHoverBorderColor() {
		return ColorBuilder.parse(getHoverBorderColorAsString());
	}

	/**
	 * Sets the stroke width of the arcs when hovered.
	 * 
	 * @param widths the stroke width of the arcs when hovered.
	 */
	public void setHoverBorderWidth(int... widths) {
		setArrayValue(Property.hoverBorderWidth, ArrayInteger.of(widths));
	}

	/**
	 * Returns the stroke width of the arcs when hovered.
	 * 
	 * @return list of the stroke width of the arcs when hovered.
	 */
	public List<Integer> getHoverBorderWidth() {
		ArrayInteger array = getArrayValue(Property.hoverBorderWidth);
		return ArrayListHelper.list(array);
	}

}