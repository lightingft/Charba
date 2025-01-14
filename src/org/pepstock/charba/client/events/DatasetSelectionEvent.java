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
package org.pepstock.charba.client.events;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.items.DatasetItem;

import com.google.gwt.dom.client.NativeEvent;

/**
 * Event which is fired when the user clicks on the chart and selects a dataset.<br>
 * This event doesn't suppress the click event on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DatasetSelectionEvent extends AbstractEvent<DatasetSelectionEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<DatasetSelectionEventHandler> TYPE = new Type<>();
	// item with dataset metadata related to the click
	private final DatasetItem item;
	// item with dataset metadata related to the click
	private final IsChart chart;

	/**
	 * Creates the event with dataset metadata item related to the click
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param item dataset metadata item related to the click
	 */
	public DatasetSelectionEvent(NativeEvent nativeEvent, DatasetItem item) {
		super(nativeEvent);
		this.item = item;
		// sets null because already present in native event as source
		this.chart = null;
	}

	/**
	 * Creates the event with dataset metadata item related to the click and the chart instance
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param chart chart instance
	 * @param item dataset metadata item related to the click
	 */
	public DatasetSelectionEvent(NativeEvent nativeEvent, IsChart chart, DatasetItem item) {
		super(nativeEvent);
		this.item = item;
		this.chart = chart;
	}

	/**
	 * Returns the item with dataset metadata related to the click
	 * 
	 * @return the item with dataset metadata related to the click
	 */
	public DatasetItem getItem() {
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.AbstractEvent#getChart()
	 */
	@Override
	public IsChart getChart() {
		// override the method
		// if chart has passed as argument of constructor
		if (chart != null) {
			return chart;
		}
		// if here, chart is not set
		// then call the native object where chart is stored as
		// source
		return super.getChart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<DatasetSelectionEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(DatasetSelectionEventHandler handler) {
		handler.onSelect(this);
	}

}