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
package org.pepstock.charba.client;

import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.items.TooltipModel;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * This interface is defining the extension hook for Chart.JS plugin implementation (both for <i>inline</i> and <i>global</i> plugins).<br>
 * Plugins are the most efficient way to customize or change the default behavior of a chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface Plugin {

	/**
	 * Plugins must define a unique id in order to be configurable.<br>
	 * Returns the plugin id. A plugin id <br>
	 * <ul>
	 * <li>can not start with a dot or an underscore
	 * <li>can not contain any non-URL-safe characters
	 * <li>cannot contain uppercase letters
	 * <li>should be something short, but also reasonably descriptive
	 * </ul>
	 * 
	 * @return the plugin id.
	 */
	String getId();

	/**
	 * Called before initializing 'chart'.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 */
	void onBeforeInit(AbstractChart<?, ?> chart, JavaScriptObject options);

	/**
	 * Called after 'chart' has been initialized and before the first update.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 */
	void onAfterInit(AbstractChart<?, ?> chart, JavaScriptObject options);

	/**
	 * Called before updating 'chart'. If any plugin returns <code>false</code>, the update is cancelled (and thus subsequent
	 * render(s)) until another 'update' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the chart update.
	 */
	boolean onBeforeUpdate(AbstractChart<?, ?> chart, JavaScriptObject options);

	/**
	 * Called after 'chart' has been updated and before rendering. Note that this hook will not be called if the chart update
	 * has been previously cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 */
	void onAfterUpdate(AbstractChart<?, ?> chart, JavaScriptObject options);

	/**
	 * Called before laying out 'chart'. If any plugin returns <code>false</code>, the layout update is cancelled until another
	 * 'update' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the chart layout.
	 */
	boolean onBeforeLayout(AbstractChart<?, ?> chart, JavaScriptObject options);

	/**
	 * Called after the 'chart' has been layed out. Note that this hook will not be called if the layout update has been
	 * previously cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 */
	void onAfterLayout(AbstractChart<?, ?> chart, JavaScriptObject options);

	/**
	 * Called before updating the 'chart' datasets. If any plugin returns <code>false</code>, the datasets update is cancelled
	 * until another 'update' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the datasets update.
	 */
	boolean onBeforeDatasetsUpdate(AbstractChart<?, ?> chart, JavaScriptObject options);

	/**
	 * Called after the 'chart' datasets have been updated. Note that this hook will not be called if the datasets update has
	 * been previously cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 */
	void onAfterDatasetsUpdate(AbstractChart<?, ?> chart, JavaScriptObject options);

	/**
	 * Called before updating the 'chart' dataset at the given 'args.index'. If any plugin returns <code>false</code>, the
	 * datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param datasetIndex The dataset index.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	boolean onBeforeDatasetUpdate(AbstractChart<?, ?> chart, int datasetIndex, JavaScriptObject options);

	/**
	 * Called after the 'chart' datasets at the given 'args.index' has been updated. Note that this hook will not be called if
	 * the datasets update has been previously cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param datasetIndex The dataset index.
	 * @param options plugin options set by user into chart options.
	 */
	void onAfterDatasetUpdate(AbstractChart<?, ?> chart, int datasetIndex, JavaScriptObject options);

	/**
	 * Called before rendering 'chart'. If any plugin returns <code>false</code>, the rendering is cancelled until another
	 * 'render' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the chart rendering.
	 */
	boolean onBeforeRender(AbstractChart<?, ?> chart, JavaScriptObject options);

	/**
	 * Called after the 'chart' has been fully rendered (and animation completed). Note that this hook will not be called if the
	 * rendering has been previously cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 */
	void onAfterRender(AbstractChart<?, ?> chart, JavaScriptObject options);

	/**
	 * Called before drawing 'chart' at every animation frame specified by the given easing value. If any plugin returns
	 * <code>false</code>, the frame drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the chart drawing.
	 */
	boolean onBeforeDraw(AbstractChart<?, ?> chart, double easing, JavaScriptObject options);

	/**
	 * Called after the 'chart' has been drawn for the specific easing value. Note that this hook will not be called if the
	 * drawing has been previously cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @param options plugin options set by user into chart options.
	 */
	void onAfterDraw(AbstractChart<?, ?> chart, double easing, JavaScriptObject options);

	/**
	 * Called before drawing the 'chart' datasets. If any plugin returns <code>false</code>, the datasets drawing is cancelled
	 * until another 'render' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	boolean onBeforeDatasetsDraw(AbstractChart<?, ?> chart, double easing, JavaScriptObject options);

	/**
	 * Called after the 'chart' datasets have been drawn. Note that this hook will not be called if the datasets drawing has
	 * been previously cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @param options plugin options set by user into chart options.
	 */
	void onAfterDatasetsDraw(AbstractChart<?, ?> chart, double easing, JavaScriptObject options);

	/**
	 * Called before drawing the 'chart' dataset at the given 'args.index' (datasets are drawn in the reverse order). If any
	 * plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param datasetIndex The dataset index.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	boolean onBeforeDatasetDraw(AbstractChart<?, ?> chart, int datasetIndex, double easing, JavaScriptObject options);

	/**
	 * Called after the 'chart' datasets at the given 'args.index' have been drawn (datasets are drawn in the reverse order).
	 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param datasetIndex The dataset index.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @param options plugin options set by user into chart options.
	 */
	void onAfterDatasetDraw(AbstractChart<?, ?> chart, int datasetIndex, double easing, JavaScriptObject options);

	/**
	 * Called before drawing the 'tooltip'. If any plugin returns <code>false</code>, the tooltip drawing is cancelled until
	 * another 'render' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param tooltip The tooltip instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the chart tooltip drawing.
	 */
	boolean onBeforeTooltipDraw(AbstractChart<?, ?> chart, TooltipModel tooltip, double easing, JavaScriptObject options);

	/**
	 * Called after drawing the 'tooltip'. Note that this hook will not be called if the tooltip drawing has been previously
	 * cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param tooltip The tooltip instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @param options plugin options set by user into chart options.
	 */
	void onAfterTooltipDraw(AbstractChart<?, ?> chart, TooltipModel tooltip, double easing, JavaScriptObject options);

	/**
	 * Called before processing the specified 'event'. If any plugin returns <code>false</code>, the event will be discarded.
	 * 
	 * @param chart The chart instance.
	 * @param event The event object.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to discard the event.
	 */
	boolean onBeforeEvent(AbstractChart<?, ?> chart, ChartNativeEvent event, JavaScriptObject options);

	/**
	 * Called after the 'event' has been consumed. Note that this hook will not be called if the 'event' has been previously
	 * discarded.
	 * 
	 * @param chart The chart instance.
	 * @param event The event object.
	 * @param options plugin options set by user into chart options.
	 */
	void onAfterEvent(AbstractChart<?, ?> chart, ChartNativeEvent event, JavaScriptObject options);

	/**
	 * Called after the chart as been resized.
	 * 
	 * @param chart The chart instance.
	 * @param size The new canvas display size (eq. canvas.style width and height).
	 * @param options plugin options set by user into chart options.
	 */
	void onResize(AbstractChart<?, ?> chart, SizeItem size, JavaScriptObject options);

	/**
	 * Called after the chart as been destroyed.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 */
	void onDestroy(AbstractChart<?, ?> chart, JavaScriptObject options);
}
