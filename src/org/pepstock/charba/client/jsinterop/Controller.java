/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License", JavaScriptObject options);
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.controllers.Context;
import org.pepstock.charba.client.jsinterop.controllers.ControllerType;
import org.pepstock.charba.client.jsinterop.controllers.StyleElement;

/**
 * This interface enables the capability to create a custom chart.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public interface Controller {

	/**
	 * Controller must define a unique id in order to be configurable.<br>
	 * Returns the controller id.
	 * 
	 * @return the controller id.
	 */
	ControllerType getType();
	
	/**
	 * Initializes the controller.
	 * 
	 * @param jsThis context of controller 
	 * @param chart chart instance
	 * @param datasetIndex dataset index
	 */
	void initialize(Context jsThis, AbstractChart<?, ?> chart, int datasetIndex);

	/**
	 * Create elements for each piece of data in the dataset. Store elements in an array on the dataset.
	 * 
	 * @param jsThis context of controller
	 * @param chart chart instance
	 */
	void addElements(Context jsThis, AbstractChart<?, ?> chart);

	/**
	 * Create a single element for the data at the given index and reset its state.
	 * 
	 * @param jsThis context of controller
	 * @param chart chart instance
	 * @param index dataset index
	 */
	void addElementAndReset(Context jsThis, AbstractChart<?, ?> chart, int index);

	/**
	 * Draw the representation of the dataset.
	 * 
	 * @param jsThis context of controller
	 * @param chart chart instance
	 * @param ease if specified, this number represents how far to transition elements.
	 */
	void draw(Context jsThis, AbstractChart<?, ?> chart, double ease);

	/**
	 * Remove hover styling from the given element.
	 * 
	 * @param jsThis context of controller
	 * @param chart chart instance
	 * @param element element to be removed.
	 */
	void removeHoverStyle(Context jsThis, AbstractChart<?, ?> chart, StyleElement element);

	/**
	 * Add hover styling to the given element.
	 * 
	 * @param jsThis context of controller
	 * @param chart chart instance
	 * @param element element to be set.
	 */
	void setHoverStyle(Context jsThis, AbstractChart<?, ?> chart, StyleElement element);

	/**
	 * Update the elements in response to new data.
	 * 
	 * @param jsThis context of controller
	 * @param chart chart instance
	 * @param reset if true, put the elements into a reset state so they can animate to their final values
	 */
	void update(Context jsThis, AbstractChart<?, ?> chart, boolean reset);

}