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
package org.pepstock.charba.client.colors;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * Contains the color and its offset to set a gradient.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GradientColor extends NativeObjectContainer {
	// offset 0 defined as default start
	static final double OFFSET_START = 0D;
	// offset 1 defined as default stop
	static final double OFFSET_STOP = 1D;
	// default offset is the start one
	private static final double DEFAULT_OFFSET = OFFSET_START;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		_charbaGradientColorOffset,
		_charbaGradientColor
	}

	/**
	 * Creates a stopping gradient with its offset.
	 * 
	 * @param offset offset of color
	 * @param color color instance
	 */
	public GradientColor(double offset, IsColor color) {
		this(offset, color.toRGBA());
	}

	/**
	 * Creates a stopping gradient with its offset.
	 * 
	 * @param offset offset of color
	 * @param color color instance
	 */
	public GradientColor(double offset, String color) {
		checkOffsetWithinBounds(offset);
		setValue(Property._charbaGradientColorOffset, offset);
		setValue(Property._charbaGradientColor, color);
	}

	/**
	 * Creates a stopping gradient starting from existing native object.
	 * 
	 * @param nativeObject native object instance with all field to define a stopping gradient color.
	 */
	GradientColor(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the defined offset for stopping gradient color.
	 * 
	 * @return the defined offset for stopping gradient color. Default is 0.
	 */
	public double getOffset() {
		return getValue(Property._charbaGradientColorOffset, DEFAULT_OFFSET);
	}

	/**
	 * Returns the color of the gradient.
	 * 
	 * @return the color of the gradient.
	 */
	public String getColorAsString() {
		return getValue(Property._charbaGradientColor, Defaults.get().getGlobal().getElements().getLine().getBackgroundColorAsString());
	}

	/**
	 * Returns the color of the gradient.
	 * 
	 * @return the color of the gradient.
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Any double between 0.0d and 1.0d (inclusive) is valid.
	 * 
	 * @param offset value between 0 and 1 for where the color stop is located.
	 * @exception if the channel is nor within bounds
	 */
	static void checkOffsetWithinBounds(double offset) {
		if (offset < OFFSET_START || offset > OFFSET_STOP) {
			throw new IllegalArgumentException("Offset argument is not within bounds (0D-1D)");
		}
	}

	/**
	 * Inner class to create pattern by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	static final class GradientColorFactory implements NativeObjectContainerFactory<GradientColor> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.
		 * NativeObject)
		 */
		@Override
		public GradientColor create(NativeObject nativeObject) {
			return new GradientColor(nativeObject);
		}
	}

}
