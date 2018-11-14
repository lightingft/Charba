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

import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.items.TooltipItem;

/**
 * Allows sorting of tooltip items.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.jsinterop.items.TooltipItem
 * @since 2.0
 */
public interface TooltipItemSortCallback {

	/**
	 * Allows sorting of tooltip items.
	 * 
	 * @param chart chart instance
	 * @param item1 the first object to be compared.
	 * @param item2 the second object to be compared.
	 * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the
	 *         second.
	 */
	int onItemSort(AbstractChart<?, ?> chart, TooltipItem item1, TooltipItem item2);

}