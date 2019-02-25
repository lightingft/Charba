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
package org.pepstock.charba.client.impl.plugins;

import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.ChartNode;
import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.ScaleItem;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Manages the selection on canvas, drawing selection area and implementing mouse listeners for canvas.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 1.8
 */
final class SelectionHandler implements MouseDownHandler, MouseUpHandler, MouseMoveHandler {

	// chart instance
	private final AbstractChart<?, ?> chart;
	// plugin options
	private final DatasetsItemsSelectorOptions options;
	// current selection area
	private final SelectionArea area = new SelectionArea();
	// current selection dataset items
	private final SelectionDatasetItems items = new SelectionDatasetItems();
	// current mouse track of selection
	private SelectionTrack track = null;
	// status if selected
	private SelectionStatus status = SelectionStatus.ready;
	// copy of chart canvas as image to apply when is drwaing into canvas
	private ImageElement snapshot = null;
	// amount of datasets items
	private int datasetsItemsCount = 0;
	// event handler registration
	private HandlerRegistration mouseDown = null;
	// event handler registration
	private HandlerRegistration mouseUp = null;
	// event handler registration
	private HandlerRegistration mouseMove = null;
	// previous chart area
	private String previousChartAreaAsString = null;
	// previous data URL chart as png
	private String previousDataURL = null;
	// flag if do not send any event after refresh
	private boolean skipNextFireEvent = false;

	/**
	 * Creates the selection handler with chart instance and the options (if exist) into chart options.
	 * 
	 * @param chart chart instance
	 * @param options plugin options
	 */
	SelectionHandler(AbstractChart<?, ?> chart, DatasetsItemsSelectorOptions options) {
		this.chart = chart;
		this.options = options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.dom.client.MouseDownHandler#onMouseDown(com.google.gwt.event.dom.client.MouseDownEvent)
	 */
	@Override
	public void onMouseDown(MouseDownEvent event) {
		// removes the default behavior of mouse down on canvas
		// this removes the canvas selection
		event.preventDefault();
		// flag to know if there is at least a dataset visible
		boolean areDatasetsVisible = false;
		// checks how many dataset are visible
		for (int i=0; i<chart.getData().getDatasets().size(); i++) {
			// checks if dataset is visible
			if (chart.isDatasetVisible(i)) {
				// sets the flag
				areDatasetsVisible = true;
				// exits from for cycle
				break;
			}
		}
		// if the mouse down event points
		// are in chart area and has got datasets items
		if (datasetsItemsCount > 0 && areDatasetsVisible && isEventInChartArea(event)) {
			chart.getCanvas().getElement().getStyle().setCursor(Cursor.CROSSHAIR);
			// then start selection with X coordinate
			startSelection(event.getX());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.dom.client.MouseMoveHandler#onMouseMove(com.google.gwt.event.dom.client.MouseMoveEvent)
	 */
	@Override
	public void onMouseMove(MouseMoveEvent event) {
		// removes the default behavior of mouse down on canvas
		// this removes the canvas selection
		event.preventDefault();
		// if the status of is in selecting
		// means that mouse down is already done
		if (getStatus().equals(SelectionStatus.selecting)) {
			// if the mouse move event points
			// are out of chart area
			if (!isEventInChartArea(event)) {
				// figures out as an end of selection
				endSelection(event.getNativeEvent());
				return;
			}
			// updates the selection into canvas
			updateSelection(event.getX(), false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.dom.client.MouseUpHandler#onMouseUp(com.google.gwt.event.dom.client.MouseUpEvent)
	 */
	@Override
	public void onMouseUp(MouseUpEvent event) {
		// removes the default behavior of mouse down on canvas
		// this removes the canvas selection
		event.preventDefault();
		// this could be the end of selection
		// therefore will apply the logic of end selection
		// only if current status is selecting and selected
		if (getStatus().equals(SelectionStatus.selecting)) {
			// sets the cursor
			chart.getCanvas().getElement().getStyle().setCursor(Cursor.DEFAULT);
			endSelection(event.getNativeEvent());
		}
	}

	/**
	 * Returns the status of selection.
	 * 
	 * @return the status
	 */
	SelectionStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status of selection.
	 * 
	 * @param status the status to set
	 */
	private void setStatus(SelectionStatus status) {
		this.status = status;
	}

	/**
	 * Sets if next event firing should be skipped.
	 * 
	 * @param skipNextFireEvent the skipNextFireEvent to set
	 */
	void setSkipNextFireEvent(boolean skipNextFireEvent) {
		this.skipNextFireEvent = skipNextFireEvent;
	}

	/**
	 * Returns the image which is snapshot of chart.
	 * 
	 * @return the snapshot
	 */
	ImageElement getSnapshot() {
		return snapshot;
	}

	/**
	 * @param snapshot the snapshot to set
	 */
	void setSnapshot(ImageElement snapshot) {
		this.snapshot = snapshot;
	}

	/**
	 * Returns the calculated dataset items count.
	 * 
	 * @return the datasetsItemsCount
	 */
	int getDatasetsItemsCount() {
		return datasetsItemsCount;
	}

	/**
	 * Sets the calculated dataset items count.
	 * 
	 * @param datasetsItemsCount the datasetsItemsCount to set
	 */
	void setDatasetsItemsCount(int datasetsItemsCount) {
		this.datasetsItemsCount = datasetsItemsCount;
	}

	/**
	 * Returns the mouse down handler registration.
	 * 
	 * @return the mouse down handler registration
	 */
	HandlerRegistration getMouseDown() {
		return mouseDown;
	}

	/**
	 * Sets the mouse down handler registration.
	 * 
	 * @param mouseDown the mouse down handler registration to set
	 */
	void setMouseDown(HandlerRegistration mouseDown) {
		this.mouseDown = mouseDown;
	}

	/**
	 * Returns the mouse up handler registration.
	 * 
	 * @return the mouse up handler registration
	 */
	HandlerRegistration getMouseUp() {
		return mouseUp;
	}

	/**
	 * Sets the mouse up handler registration.
	 * 
	 * @param mouseUp the mouse up handler registration to set
	 */
	void setMouseUp(HandlerRegistration mouseUp) {
		this.mouseUp = mouseUp;
	}

	/**
	 * Returns the mouse move handler registration.
	 * 
	 * @return the mouse move handler registration
	 */
	HandlerRegistration getMouseMove() {
		return mouseMove;
	}

	/**
	 * Sets the mouse move handler registration.
	 * 
	 * @param mouseMove the mouse move to set
	 */
	void setMouseMove(HandlerRegistration mouseMove) {
		this.mouseMove = mouseMove;
	}
	
	/**
	 * Returns the minimum amount of datasets, selectable based on chart type.
	 *  
	 * @param chart chart instance
	 * @return the minimum amount of datasets
	 */
	int getMinimumDatasetsItemsCount(AbstractChart<?, ?> chart) {
		// gets chart AREA
		ChartNode node = chart.getNode();
		// gets the scale element of chart
		// using the X axis id of plugin options
		ScaleItem scaleItem = node.getScales().getItems().get(options.getXAxisID());
		// if chart is line or axis time is equals to 2
		// else if bar chart is equals to 1
		return chart.getType().equals(ChartType.line) ? 2 : AxisType.time.equals(scaleItem.getType()) ? 2 : 1;
	}


	/**
	 * Start selection on canvas by the starting X coordinate.<br>
	 * Can be invokes by mouse down or refresh of chart (like resizing).
	 * 
	 * @param x the starting X coordinate.
	 */
	void startSelection(int x) {
		// sets status
		setStatus(SelectionStatus.selecting);
		// gets chart AREA
		ChartNode node = chart.getNode();
		ChartAreaNode chartArea = node.getChartArea();
		// sets TOP and BOTTOM
		// always related to area dimension
		area.setTop(chartArea.getTop());
		area.setBottom(chartArea.getBottom());
		// initializes the mouse track
		track = new SelectionTrack(x);
	}

	/**
	 * Update an existing selection on canvas by new X coordinate.<br>
	 * Can be invokes by mouse move or refresh of chart (like resizing).
	 * 
	 * @param x new X coordinate.
	 * @param refresh if <code>true</code> the chart is refreshing therefore it doesn't clear the canvas
	 */
	void updateSelection(int x, boolean refresh) {
		// gets context
		Context2d ctx = chart.getCanvas().getContext2d();
		// save context
		ctx.save();
		// the snapshot is the image of chart (without any selection).
		// Every time the selection is updating, it removes the previous
		// selection putting the original chart (image snapshot) and then
		// draws new selection
		if (snapshot != null) {
			// checks if is doing a refresh
			// in case of refresh, it doesn't clear the canvas
			if (!refresh) {
				// clears the canvas because the chart could have a transparent background color therefore
				// before to apply the image/snapshot, must clear the canvas (related to issue #26)
				ctx.clearRect(0, 0, chart.getCanvas().getOffsetWidth(), chart.getCanvas().getOffsetHeight());
			}
			// draws a scaled image setting width and height
			ctx.drawImage(snapshot, 0, 0, chart.getCanvas().getOffsetWidth(), chart.getCanvas().getOffsetHeight());
		}
		// gets chart AREA
		ChartNode node = chart.getNode();
		ChartAreaNode chartArea = node.getChartArea();
		// checks if X coordinate is inside of
		// chart area
		if (x < chartArea.getLeft()) {
			// if less then area, used left as current track point
			track.setCurrent(chartArea.getLeft());
		} else if (x > chartArea.getRight()) {
			// if great then area, used right as current track point
			track.setCurrent(chartArea.getRight());
		} else {
			// otherwise use X coordinate passed as current point
			track.setCurrent(x);
		}
		// gets the scale element of chart
		// using the X axis id of plugin options
		ScaleItem scaleItem = node.getScales().getItems().get(options.getXAxisID());
		// calculates the amount of sections into chart based on
		// amount of dataset items
		// in case of time axis, it must be reduce by1 because the dataset items
		// are always located in line with tick
		int areaCount = chart.getType().equals(ChartType.line) ? getDatasetsItemsCount() - 1 : AxisType.time.equals(scaleItem.getType()) ? getDatasetsItemsCount() - 1 : getDatasetsItemsCount();
		// gets the left of chart area as starting point
		double scaleTickX = chartArea.getLeft();
		// calculates the section size for every dataset item
		// PAY attention to use DOUBLE because there is a problem
		// if rounds the values (does not select exactly the right section)
		double scaleTickLength = (double) scaleItem.getWidth() / (double) areaCount;
		// scans all sections
		for (int i = 0; i <= areaCount; i++) {
			// calculates the Y coordinate of section
			// adding to starting point the section size (always DOUBLE)
			double scaleTickY = scaleTickX + scaleTickLength;
			// checks if the X coordinate of track is inside of section
			if (track.getStart() >= scaleTickX && track.getStart() <= scaleTickY) {
				// sets the start dataset item index
				items.setStart(i);
				// sets the left part of selection area
				area.setLeft(scaleTickX);
			}
			// checks if the Y coordinate of track is inside of section
			if (track.getEnd() >= scaleTickX && track.getStart() <= scaleTickY) {
				// sets the end dataset item index
				items.setEnd(i);
				// sets the right part of selection area, max must be right of chart area
				area.setRight(Math.min(scaleTickY, chartArea.getRight()));
			}
			// increments the starting point of section
			scaleTickX = scaleTickX + scaleTickLength;
		}
		// sets the selecting color into canvas
		ctx.setFillStyle(options.getColorAsString());
		// draws the rectangle of area selection
		ctx.fillRect(area.getLeft(), area.getTop(), area.getRight() - area.getLeft(), area.getBottom() - area.getTop());
		// borders
		if (options.getBorderWidth() > 0) {
			ctx.setLineWidth(options.getBorderWidth());
			List<Integer> borderDash = options.getBorderDash();
			// sets the selecting color into canvas
			ctx.setStrokeStyle(options.getBorderColorAsString());
			if (!borderDash.isEmpty()) {
				JsHelper.get().setLineDash(ctx, options.getBorderDashAsJavaScriptObject());
			}
			ctx.strokeRect(area.getLeft(), area.getTop(), area.getRight() - area.getLeft(), area.getBottom() - area.getTop());
		}
		// restore context
		ctx.restore();
	}

	/**
	 * Complete an existing selection on canvas by an event.<br>
	 * Can be invokes by mouse up or refresh of chart (like resizing).
	 * 
	 * @param event event which will complete the selection
	 */
	void endSelection(NativeEvent event) {
		endSelection(event, false);
	}

	/**
	 * Complete an existing selection on canvas by an event.<br>
	 * Can be invokes by mouse up or refresh of chart (like resizing).
	 * 
	 * @param event event which will complete the selection
	 * @param skipNextFireEvent if <code>true</code>, does not send any event
	 */
	void endSelection(NativeEvent event, boolean skipNextFireEvent) {
		// sets status
		setStatus(SelectionStatus.selected);
		// checks if it must send event
		if (!skipNextFireEvent) {
			// gets chart node
			ChartNode node = chart.getNode();
			// gets the scale element of chart
			// using the X axis id of plugin options
			ScaleItem scaleItem = node.getScales().getItems().get(options.getXAxisID());
			// checks the type of chart and scale
			// LINE and axis TIME must be added by 1 end of datasets
			if (chart.getType().equals(ChartType.line) || AxisType.time.equals(scaleItem.getType())) {
				// fires the event that dataset items selection
				chart.fireEvent(new DatasetRangeSelectionEvent(event, items.getStart(), items.getEnd() + 1));
			} else if (chart.getType().equals(ChartType.bar)) {
				// fires the event that dataset items selection
				chart.fireEvent(new DatasetRangeSelectionEvent(event, items.getStart(), items.getEnd()));
			}
		}
	}

	/**
	 * Recalculates the selection area and track and draws the area when a chart is updated or resized.
	 */
	void refresh() {
		// gets chart AREA
		ChartNode node = chart.getNode();
		ChartAreaNode chartArea = node.getChartArea();
		// gets the scale element of chart
		// using the X axis id of plugin options
		ScaleItem scaleItem = node.getScales().getItems().get(options.getXAxisID());
		// calculates the amount of sections into chart based on
		// amount of dataset items
		int areaCount = chart.getType().equals(ChartType.line) ? getDatasetsItemsCount() - 1 : AxisType.time.equals(scaleItem.getType()) ? getDatasetsItemsCount() - 1 : getDatasetsItemsCount();
		// gets the left of chart area as starting point
		double scaleTickX = chartArea.getLeft();
		// calculates the section size for every dataset item
		// PAY attention to use DOUBLE because there is a problem
		// if rounds the values (does not select exactly the right section)
		double scaleTickLength = (double) scaleItem.getWidth() / (double) areaCount;
		// scans all sections
		for (int i = 0; i <= areaCount; i++) {
			// when the section index is equals of start of dataset item index
			if (items.getStart() == i) {
				// this is new start selection point
				startSelection((int) Math.ceil(scaleTickX));
			}
			// when the section index is equals of end of dataset item index
			if (items.getEnd() == i) {
				double middle = scaleTickX + scaleTickLength / 2;
				// this is new end selection point
				updateSelection((int) middle, true);
			}
			// increments the starting point of section
			scaleTickX = scaleTickX + scaleTickLength;
		}
		// when here, the area has been draw
		// then complete the selection
		endSelection(Document.get().createChangeEvent(), skipNextFireEvent);
		skipNextFireEvent = false;
	}

	/**
	 * Checks if the chart is changed.<br>
	 * It checks:<br>
	 * <ul>
	 * <li>the dimension of chart
	 * <li>data image of chart
	 * </ul>
	 * 
	 * @param dataUrl data image for chart
	 * @return <code>true</code> if chart is changed, otherwise <code>false</code>.
	 */
	boolean isChartChanged(String dataUrl) {
		// gets the chart area in json format
		String chartAreaAsString = chart.getNode().getChartArea().toString();
		// checks if previous values are null (first round)
		if (previousDataURL == null && previousChartAreaAsString == null) {
			// saves the current data image and dimensions of chart
			previousDataURL = dataUrl;
			previousChartAreaAsString = chartAreaAsString;
			return true;
		}
		// checks if dimension of chart is changed
		if (!chartAreaAsString.equalsIgnoreCase(previousChartAreaAsString)) {
			// saves the current data image and dimensions of chart
			previousDataURL = dataUrl;
			previousChartAreaAsString = chartAreaAsString;
			return true;
		}
		// checks if data image of chart is changed
		if (!dataUrl.equalsIgnoreCase(previousDataURL)) {
			// saves the current data image and dimensions of chart
			previousDataURL = dataUrl;
			previousChartAreaAsString = chartAreaAsString;
			return true;
		}
		// if here the chart is NOT changed
		return false;
	}

	/**
	 * Checks if the coordinate of event is inside the chart area.
	 * 
	 * @param event event to be checked.
	 * @return <code>true</code> if inside the area, otherwise <code>false</code>.
	 */
	private boolean isEventInChartArea(MouseEvent<?> event) {
		// gets chart AREA
		ChartNode node = chart.getNode();
		ChartAreaNode area = node.getChartArea();
		// checks if inside
		boolean isX = event.getX() >= area.getLeft() && event.getX() <= area.getRight();
		boolean isY = event.getY() >= area.getTop() && event.getY() <= area.getBottom();
		return isX && isY;
	}

}
