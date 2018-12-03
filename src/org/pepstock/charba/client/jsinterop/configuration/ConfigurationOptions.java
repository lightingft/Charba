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
package org.pepstock.charba.client.jsinterop.configuration;

import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.Chart;
import org.pepstock.charba.client.jsinterop.ChartOptions;
import org.pepstock.charba.client.jsinterop.Configuration;
import org.pepstock.charba.client.jsinterop.ConfigurationElement;
import org.pepstock.charba.client.jsinterop.callbacks.LegendCallback;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.ConfigurationLoader;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.chart.DefaultChartOptions;
import org.pepstock.charba.client.jsinterop.events.ChartClickEvent;
import org.pepstock.charba.client.jsinterop.events.ChartHoverEvent;
import org.pepstock.charba.client.jsinterop.events.ChartNativeEvent;
import org.pepstock.charba.client.jsinterop.events.ChartResizeEvent;
import org.pepstock.charba.client.jsinterop.events.DatasetSelectionEvent;
import org.pepstock.charba.client.jsinterop.items.DatasetItem;
import org.pepstock.charba.client.jsinterop.items.DatasetItem.DatasetItemFactory;
import org.pepstock.charba.client.jsinterop.items.SizeItem;
import org.pepstock.charba.client.jsinterop.options.ExtendedOptions;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import jsinterop.annotations.JsFunction;

/**
 * Base object which maps chart configuration.<br>Charba stores the unique chart ID into CHART.JS chart options using <code>charbaId</code> property key.<br>
 * Important topics to take care:<br>
 * <b> Responsive </b><br>
 * When it comes to change the chart size based on the window size, a major limitation is that the canvas render size
 * (canvas.width and .height) can not be expressed with relative values, contrary to the display size (canvas.style.width and
 * .height). Furthermore, these sizes are independent from each other and thus the canvas render size does not adjust
 * automatically based on the display size, making the rendering inaccurate.<br>
 * It provides a few options to enable responsiveness and control the resize behavior of charts by detecting when the canvas
 * display size changes and update the render size accordingly.<br>
 * <b> Legend </b><br>
 * Sometimes you need a very complex legend. In these cases, it makes sense to generate an HTML legend. Charts provide a
 * generateLegend() method on their prototype that returns an HTML string for the legend. To configure how this legend is
 * generated, you can set the legendCallback.<br>
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 *
 */
public abstract class ConfigurationOptions extends EventProvider<ExtendedOptions> implements ConfigurationElement {
	
	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------
	
	/**
	 * Java script FUNCTION callback when a click event on chart is triggered.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyChartClickCallback {

		/**
		 * Method of function to be called when a click event on chart is triggered.
		 * @param chart  Value of <code>this</code> to the execution context of function. It's the java script chart.
		 * @param event native event
		 * @param items array of chart elements affected by the event
		 */
		void call(Chart chart, ChartNativeEvent event, ArrayObject items);
	}

	/**
	 * Java script FUNCTION callback when a hover event on chart is triggered.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyChartHoverCallback {
		
		/**
		 * Method of function to be called when a hover event on chart is triggered.
		 * @param chart value of <code>this</code> to the execution context of function. It's the java script chart.
		 * @param event native event
		 * @param items array of chart elements affected by the event
		 */
		void call(Chart chart, ChartNativeEvent event, ArrayObject items);
	}

	/**
	 * Java script FUNCTION callback when a resize event on chart is triggered.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyChartResizeCallback {
		
		/**
		 * Method of function to be called when a resize event on chart is triggered.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @param chart java script chart instance
		 * @param size new size of chart
		 */
		void call(NativeObject context, Chart chart, NativeObject size); 
	}
	
	/**
	 * Java script FUNCTION callback when runs it generates an HTML legend.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyGenerateLegendCallback {
		
		/**
		 * Method of function to be called to generate an HTML legend.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @return an HTML string which represents the legend.
		 */
		String call(Object context);
	}
	
	// ---------------------------
	// -- CALLBACKS PROXIES    ---
	// ---------------------------
	
	// callback proxy to invoke the resize function
	private final CallbackProxy<ProxyChartResizeCallback> resizeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the click function
	private final CallbackProxy<ProxyChartClickCallback> clickCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover function
	private final CallbackProxy<ProxyChartHoverCallback> hoverCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the generate legend function
	private final CallbackProxy<ProxyGenerateLegendCallback> legendCallbackProxy = JsHelper.get().newCallbackProxy();
	
	private final Animation animation;

	private final Legend legend;

	private final Title title;

	private final Tooltips tooltips;

	private final Hover hover;

	private final Layout layout;

	private final Elements elements;
	
	private final Plugins plugins;
	
	private LegendCallback legendCallback = null;
	
	// amount of click event handlers
	private int onClickHandlers = 0;
	// amount of hover event handlers
	private int onHoverHandlers = 0;
	// amount of resize event handlers
	private int onResizeHandlers = 0;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		legendCallback,
		onResize,
		onClick,
		onHover
	}

	// factore to transform a native object into a dataset item
	private final DatasetItemFactory datasetItemFactory = new DatasetItemFactory();
	
	/**
	 * Builds the object storing the chart instance and defaults options.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options
	 */
	ConfigurationOptions(AbstractChart<?, ?> chart, ChartOptions defaultValues) {
		// uses the extended option internally (no override)
		super(chart, new ExtendedOptions(new DefaultChartOptions(defaultValues)));
		// creates all sub elements
		animation = new Animation(chart, getConfiguration());
		elements = new Elements(getConfiguration());
		legend = new Legend(chart, getConfiguration());
		title = new Title(getConfiguration());
		layout = new Layout(getConfiguration());
		hover = new Hover(getConfiguration());
		plugins = new Plugins(getConfiguration());
		tooltips = new Tooltips(chart, getConfiguration());
		// sets charba ID
		getConfiguration().setCharbaId(chart.getId());
		
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		clickCallbackProxy.setCallback(new ProxyChartClickCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.ConfigurationOptions.ProxyChartClickCallback#call(org.pepstock.charba.client.jsinterop.Chart, org.pepstock.charba.client.jsinterop.events.ChartNativeEvent, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public void call(Chart chart, ChartNativeEvent event, ArrayObject items) {
				// gets the dataset items by event
				DatasetItem item = getChart().getElementAtEvent(event);
				// if the item is consistent
				if (item != null) {
					// fires the event for dataset selection
					getChart().fireEvent(new DatasetSelectionEvent(event, item));
				}
				// fires the click event on the chart
				getChart().fireEvent(new ChartClickEvent(event, ArrayListHelper.unmodifiableList(items, datasetItemFactory)));			
			}
		});
		hoverCallbackProxy.setCallback(new ProxyChartHoverCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.ConfigurationOptions.ProxyChartHoverCallback#call(org.pepstock.charba.client.jsinterop.Chart, org.pepstock.charba.client.jsinterop.events.ChartNativeEvent, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public void call(Chart chart, ChartNativeEvent event, ArrayObject items) {
				// fires the hover hover on the chart 
				getChart().fireEvent(new ChartHoverEvent(event, ArrayListHelper.unmodifiableList(items, datasetItemFactory)));	
			}

		});
		resizeCallbackProxy.setCallback(new ProxyChartResizeCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.ConfigurationOptions.ProxyChartResizeCallback#call(org.pepstock.charba.client.jsinterop.commons.NativeObject, org.pepstock.charba.client.jsinterop.Chart, org.pepstock.charba.client.jsinterop.commons.NativeObject)
			 */
			@Override
			public void call(NativeObject context, Chart chart, NativeObject size) {
				// creates new native vent
				NativeEvent event = Document.get().createChangeEvent();
				// fires the resize event on chart
				getChart().fireEvent(new ChartResizeEvent(event, new SizeItem(size)));
			}
			
		});
		legendCallbackProxy.setCallback(new ProxyGenerateLegendCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.ConfigurationOptions.ProxyGenerateLegendCallback#call(java.lang.Object)
			 */
			@Override
			public String call(Object context) {
				// creates the safe html to be sure about the right HTML to send back
				SafeHtmlBuilder builder = new SafeHtmlBuilder();
				// checks if callback is consistent
				if (legendCallback != null) {
					// calls callback
					legendCallback.generateLegend(getChart(), builder);
				} 
				return builder.toSafeHtml().asString();
			}
		});
	}

	/**
	 * @return the animation
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * @return the hover
	 */
	public Hover getHover() {
		return hover;
	}

	/**
	 * @return the layout
	 */
	public Layout getLayout() {
		return layout;
	}

	/**
	 * @return the legend
	 */
	public Legend getLegend() {
		return legend;
	}

	/**
	 * @return the title
	 */
	public Title getTitle() {
		return title;
	}

	/**
	 * @return the tooltip
	 */
	public Tooltips getTooltips() {
		return tooltips;
	}

	/**
	 * @return the elements
	 */
	public Elements getElements() {
		return elements;
	}
	
	/**
	 * @return the plugins
	 */
	public Plugins getPlugins() {
		return plugins;
	}

	/**
	 * Sets the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @param events the browser events that the chart should listen to for tooltips and hovering.
	 */
	public void setEvents(Event... events) {
		getConfiguration().setEvents(events);
	}

	/**
	 * Returns the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @return the browser events that the chart should listen to for tooltips and hovering. 
	 */
	public List<Event> getEvents() {
		return getConfiguration().getEvents();
	}

	/**
	 * Sets the resizing of the chart canvas when its container does.
	 * 
	 * @param responsive the resizing of the chart canvas when its container does.
	 */
	public void setResponsive(boolean responsive) {
		getConfiguration().setResponsive(responsive);
	}

	/**
	 * Returns the resizing of the chart canvas when its container does.
	 * 
	 * @return the resizing of the chart canvas when its container does. 
	 */
	public boolean isResponsive() {
		return getConfiguration().isResponsive();
	}

	/**
	 * Sets the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @param milliseconds the duration in milliseconds it takes to animate to new size after a resize event.
	 */
	public void setResponsiveAnimationDuration(int milliseconds) {
		getConfiguration().setResponsiveAnimationDuration(milliseconds);
	}

	/**
	 * Returns the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @return the duration in milliseconds it takes to animate to new size after a resize event. 
	 */
	public int getResponsiveAnimationDuration() {
		return getConfiguration().getResponsiveAnimationDuration();
	}

	/**
	 * Sets the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @param maintainAspectRatio the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 */
	public void setMaintainAspectRatio(boolean maintainAspectRatio) {
		getConfiguration().setMaintainAspectRatio(maintainAspectRatio);
	}

	/**
	 * Returns the the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @return the maintaining of the original canvas aspect ratio (width / height) when resizing. 
	 */
	public boolean isMaintainAspectRatio() {
		return getConfiguration().isMaintainAspectRatio();
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays).
	 * Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * 
	 * @param ratio the pixel ratio.
	 */
	public void setDevicePixelRatio(double ratio) {
		getConfiguration().setDevicePixelRatio(ratio);
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays).
	 * Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * Returns  the pixel ratio.
	 * @return  the pixel ratio..
	 */
	public double getDevicePixelRatio() {
		return getConfiguration().getDevicePixelRatio();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.configuration.EventProvider#addHandler(com.google.gwt.event.shared.GwtEvent.Type)
	 */
	@Override
	protected <H extends EventHandler> void addHandler(Type<H> type) {
		// checks if type of added event handler is dataset selection or click
		if (type.equals(DatasetSelectionEvent.TYPE) || type.equals(ChartClickEvent.TYPE)) {
			// if there is not any click event handler 
			if (onClickHandlers == 0) {
				// sets the callback proxy in order to call the user event interface 
				getConfiguration().setEvent(Property.onClick, clickCallbackProxy.getProxy());
			}
			// increments amount of handlers
			onClickHandlers++;
		} else if (type.equals(ChartHoverEvent.TYPE)) {
			// if there is not any hover event handler 
			if (onHoverHandlers == 0) {
				// sets the callback proxy in order to call the user event interface 
				getConfiguration().setEvent(Property.onHover, hoverCallbackProxy.getProxy());
			}
			// increments amount of handlers
			onHoverHandlers++;
		} else if (type.equals(ChartResizeEvent.TYPE)) {
			// if there is not any resize event handler 
			if (onResizeHandlers == 0) {
				// sets the callback proxy in order to call the user event interface 
				getConfiguration().setEvent(Property.onResize, resizeCallbackProxy.getProxy());
			}
			// increments amount of handlers
			onResizeHandlers++;
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.configuration.EventProvider#removeHandler(com.google.gwt.event.shared.GwtEvent.Type)
	 */
	@Override
	protected <H extends EventHandler> void removeHandler(Type<H> type) {
		// checks if type of removed event handler is dataset selection or click
		if (type.equals(DatasetSelectionEvent.TYPE) || type.equals(ChartClickEvent.TYPE)) {
			// decrements the amount of handlers
			onClickHandlers--;
			// if there is not any handler
			if (onClickHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(Property.onClick, null);
			}
		} else if (type.equals(ChartHoverEvent.TYPE)) {
			// decrements the amount of handlers
			onHoverHandlers--;
			// if there is not any handler
			if (onHoverHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(Property.onHover, null);
			}
		} else if (type.equals(ChartResizeEvent.TYPE)) {
			// decrements the amount of handlers
			onResizeHandlers--;
			// if there is not any handler
			if (onResizeHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(Property.onResize, null);
			}
		}
	}
	
	/**
	 * Returns the legend callback instance
	 * 
	 * @return the legendCallBack
	 */
	public LegendCallback getLegendCallback() {
		return legendCallback;
	}


	/**
	 * Sets the legend callback instance
	 * 
	 * @param legendCallBack the legendCallBack to set
	 */
	public void setLegendCallback(LegendCallback legendCallback) {
		// sets the callback
		this.legendCallback = legendCallback;
		// checks if callback is consistent
		if (legendCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(Property.legendCallback, legendCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(Property.legendCallback, null);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.ConfigurationElement#load(org.pepstock.charba.client.jsinterop.Configuration)
	 */
	@Override
	public final void load(Configuration configuration) {
		// loads the native object into configuration to pass to chart
		ConfigurationLoader.loadOptions(configuration, getConfiguration());
	}
	
}