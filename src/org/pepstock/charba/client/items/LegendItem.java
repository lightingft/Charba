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
package org.pepstock.charba.client.items;

import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.PointStyle;

/**
 * This object is created by CHART.JS and passed to all callbacks and events handlers related to legend entity.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LegendItem extends BaseItem {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		datasetIndex,
		text,
		fillStyle,
		hidden,
		lineCap,
		lineDash,
		lineDashOffset,
		lineJoin,
		lineWidth,
		strokeStyle,
		pointStyle
	}

	/**
	 * Needed for GWt injection
	 */
	protected LegendItem() {
		// do nothing
	}

	/**
	 * Returns the dataset index of the chart
	 * 
	 * @return the dataset index of the chart
	 * @see org.pepstock.charba.client.data.Data#getDatasets()
	 */
	public final int getDatasetIndex() {
		return getInt(Property.datasetIndex.name());
	}

	/**
	 * Returns the label that will be displayed
	 * 
	 * @return the label that will be displayed
	 */
	public final String getText() {
		return getString(Property.text.name());
	}

	/**
	 * Returns the fill style of the legend box
	 * 
	 * @return the fill style of the legend box
	 */
	public final String getFillStyle() {
		return getString(Property.fillStyle.name());
	}

	/**
	 * Returns true if this item represents a hidden dataset. Label will be rendered with a strike-through effect
	 * 
	 * @return <code>true</code> if this item represents a hidden dataset. Label will be rendered with a strike-through effect
	 */
	public final boolean isHidden() {
		return getBoolean(Property.hidden.name());
	}

	/**
	 * Returns how the end points of every box border are drawn. There are three possible values for this property and those
	 * are: butt, round and square.
	 * 
	 * @return how the end points of every box border are drawn.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	public final CapStyle getLineCap() {
		return getValue(Property.fillStyle, CapStyle.class, CapStyle.butt);
	}

	/**
	 * Returns the box border dash pattern used when stroking lines, using an array of values which specify alternating lengths
	 * of lines and gaps which describe the pattern.
	 * 
	 * @return the box border dash pattern used when stroking lines, using an array of values which specify alternating lengths
	 *         of lines and gaps which describe the pattern.
	 */
	public final List<Integer> getLineDash() {
		return getIntegerArray(Property.lineDash.name());
	}

	/**
	 * Returns the box border dash pattern offset or "phase".
	 * 
	 * @return the box border dash pattern offset or "phase".
	 */
	public final int getLineDashOffset() {
		return getInt(Property.lineDashOffset.name());
	}

	/**
	 * Returns how two connecting segments (of box border) with non-zero lengths in a shape are joined together (degenerate
	 * segments with zero lengths, whose specified endpoints and control points are exactly at the same position, are
	 * skipped).<br>
	 * There are three possible values for this property: round, bevel and miter.
	 * 
	 * @return There are three possible values for this property: round, bevel and miter.
	 * @see org.pepstock.charba.client.enums.JoinStyle
	 */
	public final JoinStyle getLineJoin() {
		return getValue(Property.lineJoin, JoinStyle.class, JoinStyle.miter);
	}

	/**
	 * Returns the width of box border in pixels.
	 * 
	 * @return the width of box border in pixels.
	 */
	public final int getLineWidth() {
		return getInt(Property.lineWidth.name());
	}

	/**
	 * Returns the stroke style of the legend box
	 * 
	 * @return the stroke style of the legend box
	 */
	public final String getStrokeStyle() {
		return getString(Property.strokeStyle.name());
	}

	/**
	 * Returns the style of the legend box (only used if usePointStyle is true)
	 * 
	 * @return the style of the legend box
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public final PointStyle getPointStyle() {
		return getValue(Property.lineJoin, PointStyle.class, PointStyle.circle);
	}
	
}