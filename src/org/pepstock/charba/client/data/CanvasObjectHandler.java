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
package org.pepstock.charba.client.data;

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.plugins.AbstractPlugin;

/**
 * Internal plugin, set by data object before a chart is initializing.<br>
 * This plugin is added to the chart ONLY if the dataset is configured to have patterns and gradients.<br>
 * This is mandatory because gradients and pattern must be created using the canvas and its context of chart, therefore must be
 * set ONLY when the dimension of chart/canvas are available.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class CanvasObjectHandler extends AbstractPlugin {

	// plugin ID
	static final String ID = "canvasobjecthandler";
	// maintains the data object of chart status
	private String dataToJson = null;

	/**
	 * To avoid any instantiation
	 */
	CanvasObjectHandler() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#getId()
	 */
	@Override
	public String getId() {
		return ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onBeforeDatasetsDraw(org.pepstock.charba.client.IsChart, double)
	 */
	@Override
	public boolean onBeforeDatasetsDraw(IsChart chart, double easing) {
		// gets data
		Data data = chart.getData();
		// gets data json
		String currentDataToJson = data.getDatasetsAsString();
		// gets list of datasets
		List<Dataset> datasets = data.getDatasets();
		// checks if the amount of datasets is changed
		if (dataToJson == null || !dataToJson.equalsIgnoreCase(currentDataToJson)) {
			// data have been changed
			dataChanged(chart, datasets);
		}
		// stores the data chart JSON representation
		dataToJson = currentDataToJson;
		// checks if chart must be updated
		// when you creates new patterns or gradient and
		// set them to dataset configuration in this point of
		// time is MANDATORY to update chart because CHART.JS
		// must applied new patterns and gradients
		if (arePatternOrGradientsChanged(chart, datasets)) {
			// updates the chart
			chart.update();
			// informs CHART.JS to stop the current drawing
			// because is not needed due to chart update is called
			return false;
		}
		// informs CHART.JS to draw the chart
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onResize(org.pepstock.charba.client.IsChart,
	 * org.pepstock.charba.client.items.SizeItem)
	 */
	@Override
	public void onResize(IsChart chart, SizeItem size) {
		// Due to gradients are created based on dimension of
		// canvas or chart area, every time a resize is occurring
		// gradients must be recreated
		// sets true to changed status in order that
		// during the drawing after resize
		// all gradients will be applied and recreated
		for (Dataset dataset : chart.getData().getDatasets()) {
			dataset.getGradientsContainer().setChanged(true);
		}
		// because gradients must be recreated
		// the cache of gradients must be clear
		DatasetCanvasObjectFactory.get().resetGradients(chart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onDestroy(IsChart chart) {
		// because chart is destroy
		// clears the cache of patterns and gradients of the chart
		DatasetCanvasObjectFactory.get().clear(chart);
	}

	/**
	 * The data of chart has been changed therefore checks if the gradients container must be reset.
	 * 
	 * @param chart chart instance
	 * @param datasets list of datasets of chart
	 */
	private void dataChanged(IsChart chart, List<Dataset> datasets) {
		// flags to know if the gradients must be reset
		boolean mustBeReset = false;
		// scans datasets
		for (Dataset dataset : datasets) {
			// if there is at least 1 gradient
			if (!dataset.getGradientsContainer().isEmpty()) {
				// forces the flag to be changed
				// because adding a dataset
				// the RADIAL gradient could change
				dataset.getGradientsContainer().setChanged(true);
				// set the flag to reset
				mustBeReset = true;
			}
		}
		// checks if gradients must be reset
		if (mustBeReset) {
			// because amount of datasets is changed
			// the cache of gradients must be clear
			// and gradients recalculated
			DatasetCanvasObjectFactory.get().resetGradients(chart);
		}
	}

	/**
	 * Returns <code>true</code> if gradients or patterns has been created or changed, otherwise <code>false</code>.
	 * 
	 * @param chart chart instance
	 * @param datasets list of datasets of chart
	 * @return <code>true</code> if gradients or patterns has been created or changed, otherwise <code>false</code>
	 */
	private boolean arePatternOrGradientsChanged(IsChart chart, List<Dataset> datasets) {
		// flags to know if the chart must be updated because some patterns or
		// gradients are recreated.
		boolean updated = false;
		// dataset index
		int datasetIndex = 0;
		// scans all datasets
		for (Dataset dataset : datasets) {
			// checks if the dataset is visible
			if (chart.isDatasetVisible(datasetIndex)) {
				// checks if the patterns container has been changed
				// if true, means that new patterns are set or old patterns
				// are removed
				updated = updated || checkPatterns(chart, dataset);
				// checks if the gradients container has been changed
				// if true, means that new gradients are set or old gradients
				// are removed
				updated = updated || checkGradients(chart, dataset, datasetIndex);
			}
			// increments of index
			datasetIndex++;
		}
		return updated;
	}

	/**
	 * Returns <code>true</code> if patterns has been created or changed, otherwise <code>false</code>.
	 * 
	 * @param chart chart instance
	 * @param dataset dataset instance
	 * @return <code>true</code> if patterns has been created or changed, otherwise <code>false</code>
	 */
	private boolean checkPatterns(IsChart chart, Dataset dataset) {
		// checks if the patterns container has been changed
		// if true, means that new patterns are set or old patterns
		// are removed
		if (dataset.getPatternsContainer().isChanged()) {
			// asks to dataset to creates patterns
			dataset.applyPatterns(chart);
			// reset the changed status of pattern container
			dataset.getPatternsContainer().setChanged(false);
			// sets the flag to update chart
			return true;
		}
		return false;
	}

	/**
	 * Returns <code>true</code> if gradients has been created or changed, otherwise <code>false</code>.
	 * 
	 * @param chart chart instance
	 * @param dataset dataset instance
	 * @param datasetIndex dataset index
	 * @return <code>true</code> if gradients has been created or changed, otherwise <code>false</code>
	 */
	private boolean checkGradients(IsChart chart, Dataset dataset, int datasetIndex) {
		// checks if the gradients container has been changed
		// if true, means that new gradients are set or old gradients
		// are removed
		if (dataset.getGradientsContainer().isChanged()) {
			// asks to dataset to creates gradients
			dataset.applyGradients(chart, datasetIndex);
			// reset the changed status of gradients container
			dataset.getGradientsContainer().setChanged(false);
			// sets the flag to update chart
			return true;
		}
		return false;
	}

}
