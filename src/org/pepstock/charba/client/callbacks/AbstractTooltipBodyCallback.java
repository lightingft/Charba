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
package org.pepstock.charba.client.callbacks;

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.items.TooltipItem;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Abstract implementation of tooltip body callback in order to help who will implement it to override ONLY needed methods and
 * use the default for the others.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractTooltipBodyCallback implements TooltipBodyCallback {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.TooltipBodyCallback#onBeforeBody(org.pepstock.charba.client.IsChart,
	 * java.util.List)
	 */
	@Override
	public String[] onBeforeBody(IsChart chart, List<TooltipItem> items) {
		return Utilities.EMPTY_ARRAY_STRING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.TooltipBodyCallback#onAfterBody(org.pepstock.charba.client.IsChart,
	 * java.util.List)
	 */
	@Override
	public String[] onAfterBody(IsChart chart, List<TooltipItem> items) {
		return Utilities.EMPTY_ARRAY_STRING;
	}

}
