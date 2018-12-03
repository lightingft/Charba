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
package org.pepstock.charba.client.jsinterop.callbacks;

import java.util.List;

import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.items.TooltipItem;

/**
 * The tooltip label configuration is nested below the tooltip configuration using the callbacks key.<br>
 * The tooltip has the following callbacks for providing text.<br>
 * All functions must return either a string or an array of strings. Arrays of strings are treated as multiple lines of
 * text.<br>
 * This interface takes care about labels to apply to the footer.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.jsinterop.items.TooltipItem
 * @since 2.0
 */
public interface TooltipFooterCallback {

	/**
	 * Returns text to render before the footer section.
	 * 
	 * @param chart chart instance
	 * @param items list of all tooltip items
	 * @return an array of labels to apply to the footer. If returns <code>null</code>, it will be ignored.
	 */
	String[] onBeforeFooter(AbstractChart<?, ?> chart, List<TooltipItem> items);

	/**
	 * Returns text to render as the footer of the tooltip.
	 * 
	 * @param chart chart instance
	 * @param items list of all tooltip items
	 * @return an array of labels to apply to the footer. If returns <code>null</code>, it will be ignored.
	 */
	String[] onFooter(AbstractChart<?, ?> chart, List<TooltipItem> items);

	/**
	 * Text to render after the footer section.
	 * 
	 * @param chart chart instance
	 * @param items list of all tooltip items
	 * @return an array of labels to apply to the footer. If returns <code>null</code>, it will be ignored.
	 */
	String[] onAfterFooter(AbstractChart<?, ?> chart, List<TooltipItem> items);

}