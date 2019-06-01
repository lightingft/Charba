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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.IsDefaultLegend;
import org.pepstock.charba.client.defaults.IsDefaultLegendLabels;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.options.LegendOptions;

/**
 * Defaults for legendOptions option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartLegend implements IsDefaultLegend {

	private final LegendOptions legendOptions;

	private final DefaultChartLegendLabels labels;

	/**
	 * Creates the object by legendOptions option element instance.
	 * 
	 * @param legendOptions legendOptions option element instance.
	 */
	DefaultChartLegend(LegendOptions legendOptions) {
		this.legendOptions = legendOptions;
		// creates sub element
		labels = new DefaultChartLegendLabels(legendOptions.getLabels());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#getLabels()
	 */
	@Override
	public IsDefaultLegendLabels getLabels() {
		return labels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return legendOptions.isDisplay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#isFullWidth()
	 */
	@Override
	public boolean isFullWidth() {
		return legendOptions.isFullWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#isReverse()
	 */
	@Override
	public boolean isReverse() {
		return legendOptions.isReverse();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#getPosition()
	 */
	@Override
	public Position getPosition() {
		return legendOptions.getPosition();
	}

}
