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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.defaults.IsDefaultArc;
import org.pepstock.charba.client.enums.BorderAlign;

/**
 * CHART.JS default values for ARC element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultArc extends AbstractDefaultOptionsElement implements IsDefaultArc {
	
	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.1)";

	private static final int DEFAULT_BORDER_WIDTH = 2;

	private static final String DEFAULT_BORDER_COLOR = "#fff";

	private static final double DEFAULT_WEIGHT = 1D;
	
	/**
	 * Creates a defualt arc
	 */
	public DefaultArc() {
		super(DEFAULT_BACKGROUND_COLOR, DEFAULT_BORDER_COLOR, DEFAULT_BORDER_WIDTH);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getBorderAlign()
	 */
	@Override
	public BorderAlign getBorderAlign() {
		return BorderAlign.CENTER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getWeight()
	 */
	@Override
	public double getWeight() {
		return DEFAULT_WEIGHT;
	}

}
