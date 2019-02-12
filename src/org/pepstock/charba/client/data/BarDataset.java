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

import java.util.ArrayList;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.options.Scales;

/**
 * The bar chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * Some properties can be specified as an array. If these are set to an array value, the first value applies to the first bar,
 * the second value to the second bar, and so on.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class BarDataset extends HovingFlexDataset implements HasDataPoints {

	// data point factory
	private final DataPointListFactory factory = new DataPointListFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		xAxisID,
		yAxisID,
		borderSkipped
	}

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public BarDataset() {
		super();
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public BarDataset(IsDefaultOptions defaultValues) {
		super(defaultValues);
	}

	/**
	 * Sets the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * 
	 * @param xAxisID the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found
	 *            x axis.
	 */
	public void setXAxisID(String xAxisID) {
		setValue(Property.xAxisID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x
	 * axis.
	 * 
	 * @return the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x
	 *         axis.
	 */
	public String getXAxisID() {
		return getValue(Property.xAxisID, Scales.DEFAULT_X_AXIS_ID);
	}

	/**
	 * Sets the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 * 
	 * @param yAxisID the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found
	 *            y axis.
	 */
	public void setYAxisID(String yAxisID) {
		setValue(Property.yAxisID, yAxisID);
	}

	/**
	 * Returns the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y
	 * axis.
	 * 
	 * @return the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y
	 *         axis.
	 */
	public String getYAxisID() {
		return getValue(Property.yAxisID, Scales.DEFAULT_Y_AXIS_ID);
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param position the edge to skip drawing the border for.
	 */
	public void setBorderSkipped(Position position) {
		setValue(Property.borderSkipped, position);
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	public Position getBorderSkipped() {
		return getValue(Property.borderSkipped, Position.class, getDefaultValues().getElements().getRectangle().getBorderSkipped());
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints an array of data points
	 */
	@Override
	public void setDataPoints(DataPoint... datapoints) {
		setArrayValue(Dataset.Property.data, ArrayObject.of(datapoints));
		// sets data type
		setValue(Dataset.Property._charbaDataType, DataType.points);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints a list of data points
	 */
	@Override
	public void setDataPoints(List<DataPoint> datapoints) {
		setArrayValue(Dataset.Property.data, ArrayObject.of(datapoints));
		// sets data type
		setValue(Dataset.Property._charbaDataType, DataType.points);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * 
	 * @return a list of data points or an empty list of data points if the data type is not {@link DataType#points}.
	 */
	@Override
	public List<DataPoint> getDataPoints() {
		// checks if is a points data type
		if (DataType.points.equals(getDataType())) {
			// returns points
			ArrayObject array = getArrayValue(Dataset.Property.data);
			return ArrayListHelper.list(array, factory);
		} else {
			// otherwise an empty list
			return new ArrayList<DataPoint>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HovingFlexDataset#getDefaultBackgroundColorAsString()
	 */
	@Override
	String getDefaultBackgroundColorAsString() {
		return getDefaultValues().getElements().getRectangle().getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HovingFlexDataset#getDefaultBorderColorAsString()
	 */
	@Override
	String getDefaultBorderColorAsString() {
		return getDefaultValues().getElements().getRectangle().getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HovingFlexDataset#getDefaultBorderWidth()
	 */
	@Override
	int getDefaultBorderWidth() {
		return getDefaultValues().getElements().getRectangle().getBorderWidth();
	}

}