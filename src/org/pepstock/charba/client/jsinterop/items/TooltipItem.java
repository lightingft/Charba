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
package org.pepstock.charba.client.jsinterop.items;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;

/**
 * Contains all info for every item of tooltip.<br>
 * Created and passed by CHART.JS.<br>
 * It uses into the tooltips callbacks.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.callbacks.TooltipLabelCallback
 * @see org.pepstock.charba.client.callbacks.TooltipBodyCallback
 * @see org.pepstock.charba.client.callbacks.TooltipItemSortHandler
 * @see org.pepstock.charba.client.callbacks.TooltipFilterHandler
 * @see org.pepstock.charba.client.callbacks.TooltipFooterCallback
 * @see org.pepstock.charba.client.callbacks.TooltipTitleCallback
 *
 */
public final class TooltipItem extends NativeObjectContainer {
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		xLabel,
		yLabel,
		datasetIndex,
		index,
		x,
		y
	}

	/**
	 * @param nativeObject
	 */
	public TooltipItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the X location of label.
	 * 
	 * @return the X location of label. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	public String getXLabel() {
		return getValue(Property.xLabel, UndefinedValues.STRING);
	}

	/**
	 * Returns the Y location of label.
	 * 
	 * @return the Y location of label. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	public String getYLabel() {
		return getValue(Property.yLabel, UndefinedValues.STRING);
	}

	/**
	 * Returns the dataset index of the chart
	 * 
	 * @return the dataset index of the chart. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 * @see org.pepstock.charba.client.data.Data#getDatasets()
	 */
	public int getDatasetIndex() {
		return getValue(Property.datasetIndex, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 * @see org.pepstock.charba.client.data.Dataset#getData()
	 * @see org.pepstock.charba.client.data.Data#getLabels()
	 */
	public int getIndex() {
		return getValue(Property.index, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the X location of tooltip item.
	 * 
	 * @return the X location of tooltip item. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getX() {
		return getValue(Property.x, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the Y location of tooltip item.
	 * 
	 * @return the Y location of tooltip item. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getY() {
		return getValue(Property.y, UndefinedValues.INTEGER);
	}
	
	public static class TooltipItemFactory implements Factory<TooltipItem>{
		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory#create(org.pepstock.charba.client.jsinterop.commons.NativeObject)
		 */
		@Override
		public TooltipItem create(NativeObject nativeObject) {
			return new TooltipItem(nativeObject);
		}
	}
}