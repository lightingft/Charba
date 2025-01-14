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
package org.pepstock.charba.client.plugins;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.items.DatasetPluginItem;
import org.pepstock.charba.client.items.EventPluginItem;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.items.TooltipPluginItem;

/**
 * Wraps a plugin, delegating the execution of all hooks to it.<br>
 * The wrapper is mandatory to able to catch all hooks of chart even if the plugin implements just a part of the hooks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class WrapperPlugin {
	// user plugin implementation
	private final Plugin delegation;
	// native object which will be added to chart.js
	private final NativePlugin nativeObject;

	/**
	 * Builds the object with plugin instance
	 * 
	 * @param delegation plugin instance
	 */
	WrapperPlugin(Plugin delegation) {
		// stores the plugin
		this.delegation = delegation;
		// sets the plugin ID
		nativeObject = new NativePlugin(this);
		nativeObject.setId(delegation.getId());
	}

	/**
	 * Returns the plugin id.
	 * 
	 * @return the plugin id.
	 */
	String getId() {
		return delegation.getId();
	}

	/**
	 * Returns the native java script object.
	 * 
	 * @return the nativeObject
	 */
	NativePlugin getNativeObject() {
		return nativeObject;
	}

	/**
	 * Called before creation of 'chart' java script.
	 * 
	 * @param chart chart instance.
	 */
	void onConfigure(IsChart chart) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onConfigure(chart);
		}
	}

	/**
	 * Called before initializing 'chart'.
	 * 
	 * @param chartId chart id.
	 */
	void onBeforeInit(IsChart chart) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onBeforeInit(chart);
		}
	}

	/**
	 * Called after 'chart' has been initialized and before the first update.
	 * 
	 * @param chartId chart id.
	 */
	void onAfterInit(IsChart chart) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterInit(chart);
		}
	}

	/**
	 * Called before updating 'chart'. If any plugin returns <code>false</code>, the update is cancelled (and thus subsequent
	 * render(s)) until another 'update' is triggered.
	 * 
	 * @param chartId chart id.
	 * @return <code>false</code> to cancel the chart update.
	 */
	boolean onBeforeUpdate(IsChart chart) {
		// if consistent, calls plugin
		if (chart != null) {
			return delegation.onBeforeUpdate(chart);
		}
		return true;
	}

	/**
	 * Called after 'chart' has been updated and before rendering. Note that this hook will not be called if the chart update
	 * has been previously cancelled.
	 * 
	 * @param chartId chart id.
	 */
	void onAfterUpdate(IsChart chart) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterUpdate(chart);
		}
	}

	/**
	 * Called before laying out 'chart'. If any plugin returns <code>false</code>, the layout update is cancelled until another
	 * 'update' is triggered.
	 * 
	 * @param chartId chart id.
	 * @return <code>false</code> to cancel the chart layout.
	 */
	boolean onBeforeLayout(IsChart chart) {
		// if consistent
		if (chart != null) {
			// calls plugin
			return delegation.onBeforeLayout(chart);
		}
		return true;
	}

	/**
	 * Called after the 'chart' has been layed out. Note that this hook will not be called if the layout update has been
	 * previously cancelled.
	 * 
	 * @param chartId chart id.
	 */
	void onAfterLayout(IsChart chart) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterLayout(chart);
		}
	}

	/**
	 * Called before updating the 'chart' datasets. If any plugin returns <code>false</code>, the datasets update is cancelled
	 * until another 'update' is triggered.
	 * 
	 * @param chartId chart id.
	 * @return <code>false</code> to cancel the datasets update.
	 */
	boolean onBeforeDatasetsUpdate(IsChart chart) {
		// if consistent, calls plugin
		if (chart != null) {
			return delegation.onBeforeDatasetsUpdate(chart);
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets have been updated. Note that this hook will not be called if the datasets update has
	 * been previously cancelled.
	 * 
	 * @param chartId chart id.
	 */
	void onAfterDatasetsUpdate(IsChart chart) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterDatasetsUpdate(chart);
		}
	}

	/**
	 * Called before updating the 'chart' dataset at the given 'args.index'. If any plugin returns <code>false</code>, the
	 * datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param chartId chart id.
	 * @param datasetIndex The dataset index.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	boolean onBeforeDatasetUpdate(IsChart chart, DatasetPluginItem item) {
		// if consistent, calls plugin
		if (chart != null) {
			return delegation.onBeforeDatasetUpdate(chart, item);
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets at the given 'args.index' has been updated. Note that this hook will not be called if
	 * the datasets update has been previously cancelled.
	 * 
	 * @param chartId chart id.
	 * @param datasetIndex The dataset index.
	 */
	void onAfterDatasetUpdate(IsChart chart, DatasetPluginItem item) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterDatasetUpdate(chart, item);
		}
	}

	/**
	 * Called before rendering 'chart'. If any plugin returns <code>false</code>, the rendering is cancelled until another
	 * 'render' is triggered.
	 * 
	 * @param chartId chart id.
	 * @return <code>false</code> to cancel the chart rendering.
	 */
	boolean onBeforeRender(IsChart chart) {
		// if consistent, calls plugin
		if (chart != null) {
			return delegation.onBeforeRender(chart);
		}
		return true;
	}

	/**
	 * Called after the 'chart' has been fully rendered (and animation completed). Note that this hook will not be called if the
	 * rendering has been previously cancelled.
	 * 
	 * @param chartId chart id.
	 */
	void onAfterRender(IsChart chart) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterRender(chart);
		}
	}

	/**
	 * Called before drawing 'chart' at every animation frame specified by the given easing value. If any plugin returns
	 * <code>false</code>, the frame drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chartId chart id.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart drawing.
	 */
	boolean onBeforeDraw(IsChart chart, double easing) {
		// if consistent, calls plugin
		if (chart != null) {
			return delegation.onBeforeDraw(chart, easing);
		}
		return true;
	}

	/**
	 * Called after the 'chart' has been drawn for the specific easing value. Note that this hook will not be called if the
	 * drawing has been previously cancelled.
	 * 
	 * @param chartId chart id.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	void onAfterDraw(IsChart chart, double easing) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterDraw(chart, easing);
		}
	}

	/**
	 * Called before drawing the 'chart' datasets. If any plugin returns <code>false</code>, the datasets drawing is cancelled
	 * until another 'render' is triggered.
	 * 
	 * @param chartId chart id.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	boolean onBeforeDatasetsDraw(IsChart chart, double easing) {
		// if consistent, calls plugin
		if (chart != null) {
			return delegation.onBeforeDatasetsDraw(chart, easing);
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets have been drawn. Note that this hook will not be called if the datasets drawing has
	 * been previously cancelled.
	 * 
	 * @param chartId chart id.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	void onAfterDatasetsDraw(IsChart chart, double easing) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterDatasetsDraw(chart, easing);
		}
	}

	/**
	 * Called before drawing the 'chart' dataset at the given 'args.index' (datasets are drawn in the reverse order). If any
	 * plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chartId chart id.
	 * @param datasetIndex The dataset index.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	boolean onBeforeDatasetDraw(IsChart chart, DatasetPluginItem item) {
		// if consistent, calls plugin
		if (chart != null) {
			return delegation.onBeforeDatasetDraw(chart, item);
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets at the given 'args.index' have been drawn (datasets are drawn in the reverse order).
	 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @param chartId chart id.
	 * @param datasetIndex The dataset index.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	void onAfterDatasetDraw(IsChart chart, DatasetPluginItem item) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterDatasetDraw(chart, item);
		}
	}

	/**
	 * Called before drawing the 'tooltip'. If any plugin returns <code>false</code>, the tooltip drawing is cancelled until
	 * another 'render' is triggered.
	 * 
	 * @param chartId chart id.
	 * @param object The tooltip model instance as java script object.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart tooltip drawing.
	 */
	boolean onBeforeTooltipDraw(IsChart chart, TooltipPluginItem item) {
		// if consistent, calls plugin
		if (chart != null) {
			return delegation.onBeforeTooltipDraw(chart, item);
		}
		return true;
	}

	/**
	 * Called after drawing the 'tooltip'. Note that this hook will not be called if the tooltip drawing has been previously
	 * cancelled.
	 * 
	 * @param chartId chart id.
	 * @param object The tooltip model instance as java script object.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	void onAfterTooltipDraw(IsChart chart, TooltipPluginItem item) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterTooltipDraw(chart, item);
		}
	}

	/**
	 * Called before processing the specified 'event'. If any plugin returns <code>false</code>, the event will be discarded.
	 * 
	 * @param chartId chart id.
	 * @param event The event object wrapper.
	 * @return <code>false</code> to discard the event.
	 */
	boolean onBeforeEvent(IsChart chart, EventPluginItem event) {
		// if consistent, both chart and event, calls plugin
		if (chart != null && event.getEvent() != null) {
			return delegation.onBeforeEvent(chart, event.getEvent());
		}
		return true;
	}

	/**
	 * Called after the 'event' has been consumed. Note that this hook will not be called if the 'event' has been previously
	 * discarded.
	 * 
	 * @param chartId chart id.
	 * @param event The event object wrapper.
	 */
	void onAfterEvent(IsChart chart, EventPluginItem event) {
		// if consistent, both chart and event, calls plugin
		if (chart != null && event.getEvent() != null) {
			delegation.onAfterEvent(chart, event.getEvent());
		}
	}

	/**
	 * Called after the chart as been resized.
	 * 
	 * @param chartId chart id.
	 * @param item The new canvas display size (eq. canvas.style width & height).
	 */
	void onResize(IsChart chart, SizeItem item) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onResize(chart, item);
		}
	}

	/**
	 * Called after the chart as been destroyed.
	 * 
	 * @param chartId chart id.
	 */
	void onDestroy(IsChart chart) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onDestroy(chart);
		}
	}

}
