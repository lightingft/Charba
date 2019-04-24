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
package org.pepstock.charba.client;

import org.pepstock.charba.client.configuration.TimeSeriesLineOptions;
import org.pepstock.charba.client.data.TimeSeriesLineDataset;

/**
 * LINE chart implementation for time series.<br>
 * A line chart is a way of plotting data points on a line.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class TimeSeriesLineChart extends AbstractChart<TimeSeriesLineDataset> {

	private final TimeSeriesLineOptions options;

	/**
	 * Builds the object.
	 */
	public TimeSeriesLineChart() {
		options = new TimeSeriesLineOptions(this, getDefaultChartOptions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getType()
	 */
	@Override
	public Type getType() {
		return ChartType.LINE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public TimeSeriesLineOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#newDataset()
	 */
	@Override
	public TimeSeriesLineDataset newDataset() {
		return new TimeSeriesLineDataset(getDefaultChartOptions());
	}

}