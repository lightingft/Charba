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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.Defaults;

/**
 * Implements options for chart with lines to be showed and with span gaps.
 *
 * @author Andrea "Stock" Stocchero
 */
interface HasLineOptions {

	/**
	 * Returns a multi scale options which will implements show lines and span gaps.
	 * 
	 * @return a multi scale options instance
	 */
	MultiScalesOptions getOptions();

	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @param showLine If false, the lines between points are not drawn.
	 */
	default void setShowLines(boolean showLine) {
		// checks if options is consistent
		if (getOptions() != null) {
			getOptions().getConfiguration().setShowLines(showLine);
		}
	}

	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @return If false, the lines between points are not drawn.
	 */
	default boolean isShowLines() {
		// checks if options is consistent
		if (getOptions() != null) {
			return getOptions().getConfiguration().isShowLines();
		}
		// if here, options is not consistent
		return Defaults.get().getGlobal().isShowLines();
	}

	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @param spanGaps If false, NaN data causes a break in the line.
	 */
	default void setSpanGaps(boolean spanGaps) {
		// checks if options is consistent
		if (getOptions() != null) {
			getOptions().getConfiguration().setSpanGaps(spanGaps);
		}
	}

	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @return If false, NaN data causes a break in the line.
	 */
	default boolean isSpanGaps() {
		// checks if options is consistent
		if (getOptions() != null) {
			return getOptions().getConfiguration().isSpanGaps();
		}
		// if here, options is not consistent
		return Defaults.get().getGlobal().isSpanGaps();
	}

}