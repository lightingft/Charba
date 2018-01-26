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

import org.pepstock.charba.client.data.MeterDataset;
import org.pepstock.charba.client.options.MeterOptions;

/**
 * METER chart implementation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class MeterChart extends BaseMeterChart<MeterOptions, MeterDataset> {

	private final MeterOptions options;
	
	/**
	 * Builds the object.
	 */
	public MeterChart() {
		super();
		options = new MeterOptions(this);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Chart#getType()
	 */
	@Override
	public Type getType() {
		return Type.doughnut;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Chart#getOptions()
	 */
	@Override
	public MeterOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Chart#newDataset()
	 */
	@Override
	public MeterDataset newDataset() {
		return newDataset(DEFAULT_MAX);
	}

	@Override
	public MeterDataset newDataset(double max) {
		return new MeterDataset(max);
	}

}