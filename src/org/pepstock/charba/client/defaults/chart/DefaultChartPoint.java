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

import org.pepstock.charba.client.defaults.IsDefaultPoint;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.options.PointOptions;

/**
 * Defaults for pointOptions option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartPoint implements IsDefaultPoint {

	private final PointOptions pointOptions;

	/**
	 * Creates the object by pointOptions option element instance.
	 * 
	 * @param pointOptions pointOptions option element instance.
	 */
	DefaultChartPoint(PointOptions pointOptions) {
		this.pointOptions = pointOptions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getBackgroundColorAsString()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return pointOptions.getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return pointOptions.getBorderWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getBorderColorAsString()
	 */
	@Override
	public String getBorderColorAsString() {
		return pointOptions.getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPoint#getRadius()
	 */
	@Override
	public double getRadius() {
		return pointOptions.getRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPoint#getPointStyle()
	 */
	@Override
	public PointStyle getPointStyle() {
		return pointOptions.getPointStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPoint#getHitRadius()
	 */
	@Override
	public double getHitRadius() {
		return pointOptions.getHitRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPoint#getHoverRadius()
	 */
	@Override
	public double getHoverRadius() {
		return pointOptions.getHoverRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPoint#getHoverBorderWidth()
	 */
	@Override
	public int getHoverBorderWidth() {
		return pointOptions.getHoverBorderWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPoint#getRotation()
	 */
	@Override
	public double getRotation() {
		return pointOptions.getRotation();
	}
}
