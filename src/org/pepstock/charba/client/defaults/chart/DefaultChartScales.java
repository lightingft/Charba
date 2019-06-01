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

import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.defaults.IsDefaultScales;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.options.ScalesOptions;

/**
 * Defaults for scalesOptions/axes option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartScales implements IsDefaultScales {

	private final ScalesOptions scalesOptions;

	private final IsDefaultScale xAxis;

	private final IsDefaultScale yAxis;

	/**
	 * Creates the object by scalesOptions option element instance.
	 * 
	 * @param scalesOptions scalesOptions option element instance.
	 */
	public DefaultChartScales(ScalesOptions scalesOptions) {
		this.scalesOptions = scalesOptions;
		// checks if there is any x axes
		if (!scalesOptions.getXAxes().isEmpty()) {
			// uses the first one as default
			xAxis = scalesOptions.getXAxes().get(0);
		} else {
			// uses the default
			xAxis = DefaultsBuilder.get().getScaledOptions().getScale();
		}
		// checks if there is any y axes
		if (!scalesOptions.getYAxes().isEmpty()) {
			// uses the first one as default
			yAxis = scalesOptions.getYAxes().get(0);
		} else {
			// uses the default
			yAxis = DefaultsBuilder.get().getScaledOptions().getScale();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScales#getDisplay()
	 */
	@Override
	public Display getDisplay() {
		return scalesOptions.getDisplay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScales#getXAxis()
	 */
	@Override
	public IsDefaultScale getXAxis() {
		return xAxis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScales#getYAxis()
	 */
	@Override
	public IsDefaultScale getYAxis() {
		return yAxis;
	}

}
