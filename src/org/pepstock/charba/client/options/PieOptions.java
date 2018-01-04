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

/**
 * Specific options for PIE chart. It contains all properties for this kind of chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class PieOptions extends BaseOptions {

	private static final double DEFAULT_CUTOUT_PERCENTAGE = 0D;

	private static final double DEFAULT_ROTATION = -0.5 * Math.PI;

	private static final double DEFAULT_CIRCUMFERENCE = 2 * Math.PI;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		cutoutPercentage,
		rotation,
		circumference
	}

	/**
	 * Builds the object storing the chart instance.<br>
	 * Sets also the internal parts of options.
	 * 
	 * @param chart chart instance
	 */
	public PieOptions(AbstractChart<?, ?> chart) {
		super(chart);
		// forces to 0
		setCutoutPercentage(0);
		// sets rotate and animation scale
		getAnimation().setAnimateRotate(true);
		getAnimation().setAnimateScale(true);
	}

	/**
	 * Sets the percentage of the chart that is cut out of the middle.
	 * 
	 * @param cutoutPercentage the percentage of the chart that is cut out of the middle.
	 */
	public void setCutoutPercentage(double cutoutPercentage) {
		setValue(Property.cutoutPercentage, cutoutPercentage);
	}

	/**
	 * Returns the the percentage of the chart that is cut out of the middle.
	 * 
	 * @return the percentage of the chart that is cut out of the middle. Default is 0.
	 */
	public double getCutoutPercentage() {
		return getValue(Property.cutoutPercentage, DEFAULT_CUTOUT_PERCENTAGE);
	}

	/**
	 * Sets the starting angle to draw arcs from.
	 * 
	 * @param rotation starting angle to draw arcs from.
	 */
	public void setRotation(double rotation) {
		setValue(Property.rotation, rotation);
	}

	/**
	 * returns the starting angle to draw arcs from.
	 * 
	 * @return starting angle to draw arcs from. Default is <code>-0.5 * Math.PI</code>.
	 */
	public double getRotation() {
		return getValue(Property.rotation, DEFAULT_ROTATION);
	}

	/**
	 * Sets the sweep to allow arcs to cover.
	 * 
	 * @param circumference the sweep to allow arcs to cover.
	 */
	public void setCircumference(double circumference) {
		setValue(Property.circumference, circumference);
	}

	/**
	 * Returns the the sweep to allow arcs to cover.
	 * 
	 * @return the sweep to allow arcs to cover. Default is <code>2 * Math.PI</code>.
	 */
	public double getCircumference() {
		return getValue(Property.circumference, DEFAULT_CIRCUMFERENCE);
	}

}