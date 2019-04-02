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
package org.pepstock.charba.client.labels;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.labels.enums.Position;
import org.pepstock.charba.client.labels.enums.Render;

/**
 * It wraps default global options if there are and provides all default values for LABELS plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class LabelsDefaultsOptions extends NativeObjectContainer {

	/**
	 * Creates an empty options without any default global options. it will use the constants as default of plugin properties.
	 */
	LabelsDefaultsOptions() {
		super();
	}

	/**
	 * Creates the object wrapping the default global options if there are.
	 * 
	 * @param nativeObject native object which maps default global options.
	 */
	LabelsDefaultsOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns what data must be showed.
	 * 
	 * @return what data must be showed.
	 */
	Render getRender() {
		return getValue(LabelsOptions.Property.render, Render.class, LabelsOptions.DEFAULT_RENDER);
	}

	/**
	 * Returns the precision for percentage.
	 * 
	 * @return the precision for percentage.
	 */
	int getPrecision() {
		return getValue(LabelsOptions.Property.precision, LabelsOptions.DEFAULT_PRECISION);
	}

	/**
	 * Returns whether or not labels of value 0 are displayed.
	 * 
	 * @return whether or not labels of value 0 are displayed.
	 */
	boolean isShowZero() {
		return getValue(LabelsOptions.Property.showZero, LabelsOptions.DEFAULT_SHOWZERO);
	}

	/**
	 * Returns the the font size.
	 * 
	 * @return the font size..
	 */
	int getFontSize() {
		return getValue(LabelsOptions.Property.fontSize, Defaults.get().getGlobal().getDefaultFontSize());
	}

	/**
	 * Returns the the font color as string.
	 * 
	 * @return the font color.
	 */
	String getFontColorAsString() {
		return getValue(LabelsOptions.Property.fontColor, Defaults.get().getGlobal().getDefaultFontColorAsString());
	}

	/**
	 * Returns the font style.
	 * 
	 * @return the font style.
	 */
	FontStyle getFontStyle() {
		return getValue(LabelsOptions.Property.fontStyle, FontStyle.class, Defaults.get().getGlobal().getDefaultFontStyle());
	}

	/**
	 * Returns the font family.
	 * 
	 * @return the font family.
	 */
	String getFontFamily() {
		return getValue(LabelsOptions.Property.fontFamily, Defaults.get().getGlobal().getDefaultFontFamily());
	}

	/**
	 * Returns if draws text shadows under labels.
	 * 
	 * @return <code>true</code> if draws text shadows under labels.
	 */
	boolean isTextShadow() {
		return getValue(LabelsOptions.Property.textShadow, LabelsOptions.DEFAULT_TEXTSHADOW);
	}

	/**
	 * Returns the text shadow intensity.
	 * 
	 * @return the text shadow intensity.
	 */
	int getShadowBlur() {
		return getValue(LabelsOptions.Property.shadowBlur, LabelsOptions.DEFAULT_SHADOWBLUR);
	}

	/**
	 * Returns the text shadow X offset.
	 * 
	 * @return the text shadow X offset.
	 */
	int getShadowOffsetX() {
		return getValue(LabelsOptions.Property.shadowOffsetX, LabelsOptions.DEFAULT_SHADOWOFFSETX);
	}

	/**
	 * Returns the text shadow Y offset.
	 * 
	 * @return the text shadow Y offset.
	 */
	int getShadowOffsetY() {
		return getValue(LabelsOptions.Property.shadowOffsetY, LabelsOptions.DEFAULT_SHADOWOFFSETY);
	}

	/**
	 * Returns the text shadow color as string.
	 * 
	 * @return the text shadow color as string. 
	 */
	String getShadowColorAsString() {
		return getValue(LabelsOptions.Property.shadowColor, LabelsOptions.DEFAULT_SHADOWCOLOR);
	}

	/**
	 * Returns if draws label in arc.
	 * 
	 * @return <code>true</code> if draws label in arc. 
	 */
	boolean isArc() {
		return getValue(LabelsOptions.Property.arc, LabelsOptions.DEFAULT_ARC);
	}

	/**
	 * Returns the position to draw label.
	 * 
	 * @return the position to draw label.
	 */
	String getPositionAsString() {
		return getValue(LabelsOptions.Property.position, LabelsOptions.DEFAULT_POSITION.getValue());
	}

	/**
	 * Returns if draws label even it's overlap.
	 * 
	 * @return <code>true</code>if draws label even it's overlap.
	 */
	boolean isOverlap() {
		return getValue(LabelsOptions.Property.overlap, LabelsOptions.DEFAULT_OVERLAP);
	}

	/**
	 * Returns if shows the real calculated percentages from the values and don't apply the additional logic to fit the
	 * percentages to 100 in total.
	 * 
	 * @return <code>true</code>if shows the real calculated percentages from the values and don't apply the additional logic to
	 *         fit the percentages to 100 in total.
	 */
	boolean isShowActualPercentages() {
		return getValue(LabelsOptions.Property.showActualPercentages, LabelsOptions.DEFAULT_SHOWACTUALPERCENTAGES);
	}

	/**
	 * Returns the padding when position is {@link Position#outside}.
	 * 
	 * @return the padding when position is {@link Position#outside}.
	 */
	int getOutsidePadding() {
		return getValue(LabelsOptions.Property.outsidePadding, LabelsOptions.DEFAULT_OUTSIDEPADDING);
	}

	/**
	 * Returns the margin of text when position is {@link Position#outside} or {@link Position#border}.
	 * 
	 * @return the margin of text when position is {@link Position#outside} or {@link Position#border}. 
	 */
	int getTextMargin() {
		return getValue(LabelsOptions.Property.textMargin, LabelsOptions.DEFAULT_TEXTMARGIN);
	}
}
