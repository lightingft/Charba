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

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.ArrayStringList;
import org.pepstock.charba.client.commons.Key;

import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;

/**
 * This dataset is managing some common properties of Bar and Bubble datasets where every property can be set as a single value
 * or an array.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class HovingFlexDataset extends Dataset {

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
	 * Returns default background color value based on type of chart.
	 * 
	 * @return default background color value based on type of chart.
	 */
	abstract String getDefaultBackgroundColorAsString();

	/**
	 * Returns default border color value based on type of chart.
	 * 
	 * @return default border color value based on type of chart.
	 */
	abstract String getDefaultBorderColorAsString();

	/**
	 * Returns default border width value based on type of chart.
	 * 
	 * @return default border width value based on type of chart.
	 */
	abstract int getDefaultBorderWidth();

	/**
	 * Sets the fill colors of the elements.
	 * 
	 * @param backgroundColor the fill colors of the elements.
	 */
	public void setBackgroundColor(IsColor... backgroundColor) {
		setValueOrArray(Property.backgroundColor, backgroundColor);
		// removes previous configuration to other containers
		resetBeingColors(Property.backgroundColor);
	}

	/**
	 * Sets the fill colors of the elements.
	 * 
	 * @param backgroundColor the fill colors of the elements.
	 */
	public void setBackgroundColor(String... backgroundColor) {
		setValueOrArray(Property.backgroundColor, backgroundColor);
		// removes previous configuration to other containers
		resetBeingColors(Property.backgroundColor);
	}

	/**
	 * Sets the fill pattern of the elements.
	 * 
	 * @param backgroundColor the fill pattern of element.
	 */
	public void setBackgroundColor(Pattern... backgroundColor) {
		// sets value to patterns
		getPatternsContainer().setObjects(Property.backgroundColor, ArrayObject.of(backgroundColor));
		// removes previous configuration to other containers
		resetBeingPatterns(Property.backgroundColor);
	}

	/**
	 * Sets the fill gradient of the elements.
	 * 
	 * @param backgroundColor the fill gradient of the elements.
	 */
	public void setBackgroundColor(Gradient... backgroundColor) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.backgroundColor, ArrayObject.of(backgroundColor));
		// removes previous configuration to other containers
		resetBeingGradients(Property.backgroundColor);
	}

	/**
	 * Returns the fill colors of the elements. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill colors of the elements. If property is missing or not a color, returns an empty list.
	 */
	public List<String> getBackgroundColorAsString() {
		// checks if the property is not a color (therefore a pattern or gradient)
		if (hasColors(Property.backgroundColor)) {
			// returns list of colors
			ArrayString array = getValueOrArray(Property.backgroundColor, getDefaultBackgroundColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a object
			// or the property is missing or not a color
			// returns empty list
			return new ArrayStringList();
		}
	}

	/**
	 * Returns the fill colors of the elements. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill colors of the elements. If property is missing or not a color, returns an empty list.
	 */
	public List<IsColor> getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the fill patters of elements. If property is missing or not a pattern, returns an empty list.
	 * 
	 * @return list of the fill patterns of elements. If property is missing or not a pattern, returns an empty list.
	 */
	public List<Pattern> getBackgroundColorAsPatterns() {
		// checks if the property is not a pattern (therefore a color)
		if (hasPatterns(Property.backgroundColor)) {
			return getPatternsContainer().getObjects(Property.backgroundColor);
		} else {
			// if here, the property is not a object
			// or the property is missing or not a pattern
			// returns empty list
			return new ArrayObjectContainerList<Pattern>();
		}
	}

	/**
	 * Returns the fill gradient of elements. If property is missing or not a gradient, returns an empty list.
	 * 
	 * @return the fill gradient of elements. If property is missing or not a gradient, returns an empty list.
	 */
	public List<Gradient> getBackgroundColorAsGradients() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Property.backgroundColor)) {
			return getGradientsContainer().getObjects(Property.backgroundColor);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns empty list
			return new ArrayObjectContainerList<Gradient>();
		}
	}

	/**
	 * Sets the color of the bar border
	 * 
	 * @param borderColor the color of the bar border
	 */
	public void setBorderColor(IsColor... borderColor) {
		setValueOrArray(Property.borderColor, borderColor);
		// removes previous configuration to other containers
		resetBeingColors(Property.borderColor);
	}

	/**
	 * Sets the color of the bar border
	 * 
	 * @param borderColor the color of the bar border
	 */
	public void setBorderColor(String... borderColor) {
		setValueOrArray(Property.borderColor, borderColor);
		// removes previous configuration to other containers
		resetBeingColors(Property.borderColor);
	}

	/**
	 * Sets the gradient of the bar border as gradient.
	 * 
	 * @param borderColor the gradient of the bar border as gradient.
	 */
	public void setBorderColor(Gradient... borderColor) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.borderColor, ArrayObject.of(borderColor));
		// removes previous configuration to other containers
		resetBeingGradients(Property.borderColor);
	}

	/**
	 * Returns the color of the bar border
	 * 
	 * @return list of the color of the bar border
	 */
	public List<String> getBorderColorAsString() {
		// checks if the property is not a color (therefore a gradient)
		if (hasColors(Property.borderColor)) {
			ArrayString array = getValueOrArray(Property.borderColor, getDefaultBorderColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a string
			// or the property is missing or a gradient
			// returns empty list
			return new ArrayStringList();
		}
	}

	/**
	 * Returns the color of the bar border
	 * 
	 * @return list of the color of the bar border
	 */
	public List<IsColor> getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Returns the gradient of the bar border. If property is missing or not a gradient, returns an empty list.
	 * 
	 * @return the gradient of the bar border. If property is missing or not a gradient, returns an empty list.
	 */
	public List<Gradient> getBorderColorAsGradients() {
		// checks if the property is not a gradient (therefore a color)
		if (hasGradients(Property.borderColor)) {
			return getGradientsContainer().getObjects(Property.borderColor);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns empty list
			return new ArrayObjectContainerList<Gradient>();
		}
	}

	/**
	 * Sets the stroke width of the bar in pixels.
	 * 
	 * @param borderWidth the stroke width of the bar in pixels.
	 */
	public void setBorderWidth(int... borderWidth) {
		setValueOrArray(Property.borderWidth, borderWidth);
	}

	/**
	 * Returns the stroke width of the bar in pixels.
	 * 
	 * @return list of the stroke width of the bar in pixels.
	 */
	public List<Integer> getBorderWidth() {
		ArrayInteger array = getValueOrArray(Property.borderWidth, getDefaultBorderWidth());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the fill color of the elements when hovered
	 * 
	 * @param colors the fill color of the elements when hovered
	 */
	public void setHoverBackgroundColor(IsColor... colors) {
		setValueOrArray(Property.hoverBackgroundColor, colors);
		// removes the flag because default is string color
		resetBeingColors(Property.hoverBackgroundColor);
	}

	/**
	 * Sets the fill color of the elements when hovered
	 * 
	 * @param colors the fill color of the elements when hovered
	 */
	public void setHoverBackgroundColor(String... colors) {
		setValueOrArray(Property.hoverBackgroundColor, colors);
		// removes the flag because default is string color
		resetBeingColors(Property.hoverBackgroundColor);
	}

	/**
	 * Sets the fill pattern of the elements when hovered.
	 * 
	 * @param colors the fill pattern of element when hovered.
	 */
	public void setHoverBackgroundColor(Pattern... colors) {
		// sets value to patterns
		getPatternsContainer().setObjects(Property.hoverBackgroundColor, ArrayObject.of(colors));
		// removes the property
		resetBeingPatterns(Property.hoverBackgroundColor);
	}
	
	/**
	 * Sets the fill gradient of the elements when hovered.
	 * 
	 * @param colors the fill gradient of the elements when hovered.
	 */
	public void setHoverBackgroundColor(Gradient... colors) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.hoverBackgroundColor, ArrayObject.of(colors));
		// removes previous configuration to other containers
		resetBeingGradients(Property.hoverBackgroundColor);
	}

	/**
	 * Returns the fill color of the elements when hovered. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill color of the elements when hovered. If property is missing or not a color, returns an empty
	 *         list.
	 */
	public List<String> getHoverBackgroundColorAsString() {
		// checks if the property is not a color (therefore a pattern or gradient)
		if (hasColors(Property.hoverBackgroundColor)) {
			// returns list of colors
			ArrayString array = getValueOrArray(Property.hoverBackgroundColor, getDefaultBackgroundColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a color
			// or the property is missing
			// returns empty list
			return new ArrayStringList();
		}
	}

	/**
	 * Returns the fill color of the elements when hovered. If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill color of the elements when hovered.If property is missing or not a color, returns an empty list.
	 */
	public List<IsColor> getHoverBackgroundColor() {
		return ColorBuilder.parse(getHoverBackgroundColorAsString());
	}

	/**
	 * Returns the fill patters of elements when hovered. If property is missing or not a pattern, returns an empty list.
	 * 
	 * @return list of the fill patterns of elements when hovered. If property is missing or not a pattern, returns an empty
	 *         list.
	 */
	public List<Pattern> getHoverBackgroundColorAsPatterns() {
		// checks if the property is not a pattern (therefore a color)
		if (hasPatterns(Property.hoverBackgroundColor)) {
			return getPatternsContainer().getObjects(Property.hoverBackgroundColor);
		} else {
			// if here, the property is not a color
			// or the property is missing
			// returns empty list
			return new ArrayObjectContainerList<Pattern>();
		}
	}
	
	/**
	 * Returns the fill gradients of elements when hovered. If property is missing or not a gradient, returns an
	 * empty list.
	 * 
	 * @return list of the fill gradients of elements when hovered. If property is missing or not a gradient,
	 *         returns an empty list.
	 */
	public List<Gradient> getHoverBackgroundColorAsGradients() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Property.hoverBackgroundColor)) {
			return getGradientsContainer().getObjects(Property.hoverBackgroundColor);
		} else {
			// if here, the property is not a object
			// or the property is missing or not a gradient
			// returns empty list
			return new ArrayObjectContainerList<Gradient>();
		}
	}

	/**
	 * Sets the stroke color of the elements when hovered
	 * 
	 * @param colors the stroke color of the elements when hovered
	 */
	public void setHoverBorderColor(IsColor... colors) {
		setValueOrArray(Property.hoverBorderColor, colors);
		// removes previous configuration to other containers
		resetBeingColors(Property.hoverBorderColor);
	}

	/**
	 * Sets the stroke color of the elements when hovered
	 * 
	 * @param colors the stroke color of the elements when hovered
	 */
	public void setHoverBorderColor(String... colors) {
		setValueOrArray(Property.hoverBorderColor, colors);
		// removes previous configuration to other containers
		resetBeingColors(Property.hoverBorderColor);
	}

	/**
	 * Sets the stroke gradient of elements when hovered as gradient.
	 * 
	 * @param colors the stroke gradient of elements when hovered as gradient.
	 */
	public void setHoverBorderColor(Gradient... colors) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.hoverBorderColor, ArrayObject.of(colors));
		// removes previous configuration to other containers
		resetBeingGradients(Property.hoverBorderColor);
	}
	
	/**
	 * Returns the stroke color of the elements when hovered.
	 * 
	 * @return list of the stroke color of the elements when hovered.
	 */
	public List<String> getHoverBorderColorAsString() {
		// checks if the property is not a color (therefore a gradient)
		if (hasColors(Property.hoverBorderColor)) {
			// returns list of colors
			ArrayString array = getValueOrArray(Property.hoverBorderColor, getDefaultBorderColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a object
			// or the property is missing or not a color
			// returns empty list
			return new ArrayStringList();
		}
	}

	/**
	 * Returns the stroke color of the elements when hovered
	 * 
	 * @return list of the stroke color of the elements when hovered
	 */
	public List<IsColor> getHoverBorderColor() {
		return ColorBuilder.parse(getHoverBorderColorAsString());
	}

	/**
	 * Returns the stroke gradients of the elements when hovered. If property is missing or not a pattern, returns an
	 * empty list.
	 * 
	 * @return list of the stroke gradients of the elements when hovered. If property is missing or not a pattern,
	 *         returns an empty list.
	 */
	public List<Gradient> getHoverBorderColorAsGradients() {
		// checks if the property is not a gradient (therefore a color)
		if (hasGradients(Property.hoverBorderColor)) {
			return getGradientsContainer().getObjects(Property.hoverBorderColor);
		} else {
			// if here, the property is not a object
			// or the property is missing or not a gradient
			// returns empty list
			return new ArrayObjectContainerList<Gradient>();
		}
	}
	
	/**
	 * Sets the stroke width of the elements when hovered.
	 * 
	 * @param widths the stroke width of the elements when hovered.
	 */
	public void setHoverBorderWidth(int... widths) {
		setValueOrArray(Property.hoverBorderWidth, widths);
	}

	/**
	 * Returns the stroke width of the elements when hovered.
	 * 
	 * @return list of the stroke width of the elements when hovered.
	 */
	public List<Integer> getHoverBorderWidth() {
		ArrayInteger array = getValueOrArray(Property.hoverBorderWidth, getDefaultBorderWidth());
		return ArrayListHelper.list(array);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyPattern(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	void applyPattern(Key key, List<CanvasPattern> canvasPatternsList) {
		setValueOrArray(key, canvasPatternsList.toArray(new CanvasPattern[0]));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyGradient(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	void applyGradient(Key key, List<CanvasGradient> canvasGradientsList) {
		setValueOrArray(key, canvasGradientsList.toArray(new CanvasGradient[0]));
	}

}