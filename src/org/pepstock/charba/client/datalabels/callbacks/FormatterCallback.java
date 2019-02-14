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
package org.pepstock.charba.client.datalabels.callbacks;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.datalabels.Context;

/**
 * Callback interface of DATALABELS plugin to set <code>formatter</code> property at runtime, using the chart instance and the
 * plugin context.<br>
 * Labels can be displayed on multiple lines by using the newline character <code>\n</code> between each line or by providing an
 * array of strings where each item represents a new line.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface FormatterCallback {

	/**
	 * Returns the <code>formatter</code> property at runtime, using the chart instance and the plugin context.
	 * 
	 * @param chart chart instance
	 * @param value to be formatted
	 * @param context DATALABELS plugin context instance
	 * @return the label value to be showed
	 */
	String format(AbstractChart<?, ?> chart, double value, Context context);

}