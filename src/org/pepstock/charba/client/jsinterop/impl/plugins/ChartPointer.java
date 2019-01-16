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
package org.pepstock.charba.client.jsinterop.impl.plugins;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.events.ChartNativeEvent;
import org.pepstock.charba.client.jsinterop.items.DatasetItem;
import org.pepstock.charba.client.jsinterop.plugins.AbstractPlugin;
import org.pepstock.charba.client.jsinterop.plugins.InvalidPluginIdException;

/**
 * This plugin is changing the cursor when mouse over on dataset on canvas if a dataset selection handler has been.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public final class ChartPointer extends AbstractPlugin {
	
	private final ChartPointerOptionsFactory factory = new ChartPointerOptionsFactory();
	
	private static final Map<String, ChartPointerOptions> OPTIONS = new HashMap<>();

	/**
	 * Plugin ID
	 */
	public static final String ID = "cursorpointer";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.Plugin#getId()
	 */
	@Override
	public String getId() {
		return ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.plugins.AbstractPlugin#onAfterEvent(org.pepstock.charba.client.jsinterop.
	 * AbstractChart, org.pepstock.charba.client.jsinterop.events.ChartNativeEvent)
	 */
	@Override
	public void onAfterEvent(AbstractChart<?, ?> chart, ChartNativeEvent event) {
		// checks if chart has got any dataset selection handler
		if (chart.getOptions().hasDatasetSelectionHandlers()) {
			ChartPointerOptions pOptions = null;
			if (!OPTIONS.containsKey(chart.getId())) {
				try {
					// creates the plugin options using the java script object
					// passing also the default color set at constructor.
					if (chart.getOptions().getPlugins().hasOptions(ID)) {
						pOptions = chart.getOptions().getPlugins().getOptions(ID, factory);
					} else {
						pOptions = new ChartPointerOptions();
					}
				} catch (InvalidPluginIdException e) {
					// ignore message
					// and use the default
					pOptions = new ChartPointerOptions();
				}
				OPTIONS.put(chart.getId(), pOptions);
			} else {
				pOptions = OPTIONS.get(chart.getId());
			}
			// if yes, asks the dataset item by event
			DatasetItem item = chart.getElementAtEvent(event);
			// checks item
			if (item == null) {
				// if null, sets the default cursor
				chart.getElement().getStyle().setCursor(pOptions.getCursorDefault());
			} else {
				// otherwise sets the pointer
				chart.getElement().getStyle().setCursor(pOptions.getCursorPointer());
			}
		}
	}

}
