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
package org.pepstock.charba.client.options.scales;

import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.JsStringArrayList;
import org.pepstock.charba.client.commons.Key;

/**
 * The category scale provides the following options for configuring tick marks.<br>
 * The labels are drawn from one of the label arrays included in the chart data.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CartesianCategoryTick extends CartesianTick {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		labels,
		min,
		max
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	CartesianCategoryTick(AbstractChart<?, ?> chart) {
		super(chart);
	}

	/**
	 * Sets an array of labels to display.
	 * 
	 * @param labels An array of labels to display.
	 */
	public void setLabels(String... labels) {
		setInternalLabels(ArrayListHelper.build(labels));
	}

	/**
	 * Sets an array of labels to display.
	 * 
	 * @param labels An array of labels to display.
	 */
	public void setLabels(List<String> labels) {
		// if the list is already a java script object list
		if (labels instanceof JsStringArrayList) {
			// sets directly
			setInternalLabels((JsStringArrayList) labels);
		} else {
			// creates a new java script object list
			JsStringArrayList list = new JsStringArrayList();
			// loads values
			list.addAll(labels);
			// sets list
			setInternalLabels(list);
		}
	}

	/**
	 * Sets the list of labels to java script object
	 * 
	 * @param labels An array of labels to display.
	 */
	private void setInternalLabels(JsStringArrayList labels) {
		setStringArray(Property.labels, labels);
	}

	/**
	 * Returns the array of labels to display.
	 * 
	 * @return the array of labels to display.
	 */
	public List<String> getLabels() {
		return getStringArray(Property.labels);
	}

	/**
	 * Sets the minimum item to display.
	 * 
	 * @param min The minimum item to display
	 */
	public void setMin(String min) {
		setValue(Property.min, min);
	}

	/**
	 * Returns the minimum item to display
	 * 
	 * @return The minimum item to display
	 */
	public String getMin() {
		return getValue(Property.min, String.valueOf(getAxis().getScale().getTicks().getMin()));
	}

	/**
	 * Sets the maximum item to display.
	 * 
	 * @param max the maximum item to display.
	 */
	public void setMax(String max) {
		setValue(Property.max, max);
	}

	/**
	 * Returns the maximum item to display.
	 * 
	 * @return the maximum item to display.
	 */
	public String getMax() {
		return getValue(Property.max,String.valueOf(getAxis().getScale().getTicks().getMax()));
	}

}