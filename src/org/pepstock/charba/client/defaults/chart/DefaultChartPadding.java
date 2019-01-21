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

import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.options.Padding;

/**
 * Defaults for padding option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartPadding implements IsDefaultPadding {

	private final Padding padding;

	/**
	 * Creates the object by padding option element instance.
	 * 
	 * @param padding padding option element instance.
	 */
	DefaultChartPadding(Padding padding) {
		this.padding = padding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding#getLeft()
	 */
	@Override
	public int getLeft() {
		return padding.getLeft();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding#getRight()
	 */
	@Override
	public int getRight() {
		return padding.getRight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding#getTop()
	 */
	@Override
	public int getTop() {
		return padding.getTop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding#getBottom()
	 */
	@Override
	public int getBottom() {
		return padding.getBottom();
	}

}
