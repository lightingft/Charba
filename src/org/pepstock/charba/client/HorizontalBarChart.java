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
package org.pepstock.charba.client;

/**
 * HORIZONTAL BAR chart implementation.<br>
 * A horizontal bar chart is a variation on a bar chart.<br>
 * It is sometimes used to show trend data, and the comparison of multiple data sets side by side.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class HorizontalBarChart extends BarChart {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.BarChart#getType()
	 */
	@Override
	public Type getType() {
		return ChartType.HORIZONTAL_BAR;
	}

}