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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.options.scales.CartesianCategoryAxis;
import org.pepstock.charba.client.options.scales.CartesianLinearAxis;
import org.pepstock.charba.client.options.scales.StackedScales;

/**
 * Configuration of chart which could be stacked.<br>
 * It uses Category axis for X axis and Linear for Y axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class StackedOptions extends BaseOptions {

	private final StackedScales scales;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		scales
	}

	/**
	 * Builds the object storing the chart instance and if only Y axis is scaled.
	 * 
	 * @param chart chart instance
	 * @param onlyYScaled <code>true</code> if only Y axis is scaled.
	 */
	public StackedOptions(AbstractChart<?, ?> chart, boolean onlyYScaled) {
		super(chart);
		// creates scales for stacked chart
		scales = new StackedScales(chart);
		// sets if only Y scaled
		scales.setOnlyYAxis(onlyYScaled);
		// creates the axes
		CartesianCategoryAxis axis1 = new CartesianCategoryAxis();
		scales.setXAxes(axis1);
		CartesianLinearAxis axis2 = new CartesianLinearAxis();
		scales.setYAxes(axis2);
		// sets java script property
		setValue(Property.scales, scales);
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	public StackedOptions(AbstractChart<?, ?> chart) {
		this(chart, false);
	}

	/**
	 * @return the scales
	 */
	public Scales getScales() {
		return scales;
	}
}