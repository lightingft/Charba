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
package org.pepstock.charba.client.ext.datalabels;

import org.pepstock.charba.client.AbstractChart;

/**
 * Callback interface of DATALABELS plugin to set <code>clip</code> property at runtime, using the chart instance and the plugin
 * context.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface ClipCallback {

	/**
	 * Returns the <code>clip</code> property at runtime, using the chart instance and the plugin context.
	 * 
	 * @param chart chart instance
	 * @param context DATALABELS plugin context instance
	 * @return the <code>clip</code> value to be applied
	 */
	boolean clip(AbstractChart<?, ?> chart, Context context);

}
