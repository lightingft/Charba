package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.callbacks.LegendFilterHandler;
import org.pepstock.charba.client.jsinterop.callbacks.LegendHandler;
import org.pepstock.charba.client.jsinterop.callbacks.LegendLabelsHandler;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipCustomHandler;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipFilterHandler;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipItemSortHandler;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsFactory;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions;
import org.pepstock.charba.client.jsinterop.events.ChartClickCallbackHandler;
import org.pepstock.charba.client.jsinterop.events.ChartHoverCallbackHandler;
import org.pepstock.charba.client.jsinterop.events.ChartNativeEvent;
import org.pepstock.charba.client.jsinterop.events.ChartResizeCallbackHandler;
import org.pepstock.charba.client.jsinterop.items.DatasetItem;
import org.pepstock.charba.client.jsinterop.items.SizeItem;

import jsinterop.annotations.JsFunction;

public final class EventableOptions extends BaseOptions<EventableAnimation,EventableLegend>{
	
	// legend error
	private static final String LEGEND_CALLBACK_ERROR = "Unable to execute LegendCallback";
	
	/**
	 * Called if the event is of type 'mouseup' or 'click'. Called in the context of the chart and passed the event and an array
	 * of active elements.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyChartClickCallback {
		/**
		 * Called if the event is of type 'mouseup' or 'click'. Called in the context of the chart and passed the event and an array
		 * of active elements.
		 * 
		 * @param event event generated by chart.
		 * @param metadata dataset meta data.
		 */
		void call(Object context, ChartNativeEvent event, ArrayObject<DatasetItem> items);
	}

	/**
	 * Called when any of the events fire. Called in the context of the chart and passed the event and an array of active
	 * elements (bars, points, etc).
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyChartHoverCallback {
		
		/**
		 * Called when any of the events fire. Called in the context of the chart and passed the event and an array of active
		 * elements (bars, points, etc).
		 * FIXME
		 * @param event event generated by chart.
		 * @param metadata dataset meta data.
		 */
		void call(Object context, ChartNativeEvent event, ArrayObject<DatasetItem> items);
	}

	/**
	 * Called when a resize occurs. Gets passed the new size.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyChartResizeCallback {
		
		/**
		 * Called when a resize occurs. Gets passed the new size.
		 * 
		 * @param item the new size item.
		 */
		void call(Object context, Object chart, SizeItem size);
	}

	private final EventableAnimation animation;
	
	private final EventableLegend legend;
	
	private final CallbackProxy<ProxyChartResizeCallback> resizeCallbackProxy = JsFactory.newCallbackProxy();

	private final CallbackProxy<ProxyChartClickCallback> clickCallbackProxy = JsFactory.newCallbackProxy();

	private final CallbackProxy<ProxyChartHoverCallback> hoverCallbackProxy = JsFactory.newCallbackProxy();
	
	private final CallbackProxy<ProxyGenerateLegendCallback> legendCallbackProxy = JsFactory.newCallbackProxy();
	
	private ChartClickCallbackHandler clickCallbackHandler = null;
	
	private ChartHoverCallbackHandler hoverCallbackHandler = null;
	
	private ChartResizeCallbackHandler resizeCallbackHandler = null;
	
	private LegendHandler legendHandler = null;
	
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

	public EventableOptions(IsDefaultOptions defaultValues) {
		super(defaultValues);
		animation = new EventableAnimation(this, getDefaultValues().getAnimation(), getDelegated().getAnimation());
		legend = new EventableLegend(this, getDefaultValues().getLegend(),getDelegated().getLegend());
		// events
		clickCallbackProxy.setCallback(new ProxyChartClickCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.EventableOptions.ChartClickCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.events.ChartNativeEvent, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public void call(Object context, ChartNativeEvent event, ArrayObject<DatasetItem> items) {
				if (clickCallbackHandler != null) {
					clickCallbackHandler.onClick(context, event, items);
				}
			}
		});
		
		hoverCallbackProxy.setCallback(new ProxyChartHoverCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.EventableOptions.ProxyChartHoverCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.events.ChartNativeEvent, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public void call(Object context, ChartNativeEvent event, ArrayObject<DatasetItem> items) {
				if (hoverCallbackHandler != null) {
					hoverCallbackHandler.onHover(context, event, items);
				}
			}

		});
		
		resizeCallbackProxy.setCallback(new ProxyChartResizeCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.EventableOptions.ProxyChartResizeCallback#call(java.lang.Object, java.lang.Object, org.pepstock.charba.client.jsinterop.items.SizeItem)
			 */
			@Override
			public void call(Object context, Object chart, SizeItem size) {
				if (resizeCallbackHandler != null) {
					resizeCallbackHandler.onResize(context, chart, size);
				}
			}
			
		});
		
		legendCallbackProxy.setCallback(new ProxyGenerateLegendCallback() {
			
			@Override
			public String call(Object context) {
				return legendHandler != null ? legendHandler.generateLegend(context) : LEGEND_CALLBACK_ERROR;
			}
		});
		

	}
	
	public void setCharbaId(String id) {
		getDelegated().setCharbaId(id);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseOptions#getAnimation()
	 */
	@Override
	public EventableAnimation getAnimation() {
		return animation;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseOptions#getLegend()
	 */
	@Override
	public EventableLegend getLegend() {
		return legend;
	}	

	/**
	 * @return the hoverCallbackHandler
	 */
	public ChartHoverCallbackHandler getHoverCallbackHandler() {
		return hoverCallbackHandler;
	}

	/**
	 * @param hoverCallbackHandler the hoverCallbackHandler to set
	 */
	public void setHoverCallbackHandler(ChartHoverCallbackHandler hoverCallbackHandler) {
		if (hoverCallbackHandler != null) {
			getDelegated().setOnHover(hoverCallbackProxy.getProxy());	
		} else {
			remove(Property.onHover);
		}
		this.hoverCallbackHandler = hoverCallbackHandler;
	}

	/**
	 * @return the resizeCallbackHandler
	 */
	public ChartResizeCallbackHandler getResizeCallbackHandler() {
		return resizeCallbackHandler;
	}

	/**
	 * @param resizeCallbackHandler the resizeCallbackHandler to set
	 */
	public void setResizeCallbackHandler(ChartResizeCallbackHandler resizeCallbackHandler) {
		if (resizeCallbackHandler != null) {
			getDelegated().setOnResize(resizeCallbackProxy.getProxy());	
		} else {
			remove(Property.onResize);
		}
		this.resizeCallbackHandler = resizeCallbackHandler;
	}

	/**
	 * @return the clickCallbackHandler
	 */
	public ChartClickCallbackHandler getClickCallbackHandler() {
		return clickCallbackHandler;
	}

	/**
	 * @param clickCallbackHandler the clickCallbackHandler to set
	 */
	public void setClickCallbackHandler(ChartClickCallbackHandler clickCallbackHandler) {
		if (clickCallbackHandler != null) {
			getDelegated().setOnClick(clickCallbackProxy.getProxy());
		} else {
			remove(Property.onClick);
		}
		this.clickCallbackHandler = clickCallbackHandler;
	}
	
	/**
	 * @return the legendHandler
	 */
	public LegendHandler getLegendHandler() {
		return legendHandler;
	}

	/**
	 * @param legendHandler the legendHandler to set
	 */
	public void setLegendHandler(LegendHandler legendHandler) {
		if (legendHandler == null) {
			getDelegated().setLegendCallback(legendCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.legendCallback);
		}
		this.legendHandler = legendHandler;
	}
	
	/**
	 * @return the filterHandler
	 */
	public LegendFilterHandler getLegendFilterHandler() {
		return getLegend().getLabels().getFilterHandler();
	}

	/**
	 * @param filterHandler the filterHandler to set
	 */
	public void setLegendFilterHandler(LegendFilterHandler filterHandler) {
		getLegend().getLabels().setFilterHandler(filterHandler);
	}
	
	/**
	 * @return the labelsHandler
	 */
	public LegendLabelsHandler getLegendLabelsHandler() {
		return getLegend().getLabels().getLabelsHandler();
	}

	/**
	 * @param labelsHandler the labelsHandler to set
	 */
	public void setLegendLabelsHandler(LegendLabelsHandler labelsHandler) {
		getLegend().getLabels().setLabelsHandler(labelsHandler);
	}
	
	/**
	 * @return the customHandler
	 */
	public TooltipCustomHandler getTooltipCustomHandler() {
		return getTooltips().getCustomHandler();
	}

	/**
	 * @param customHandler the customHandler to set
	 */
	public void setTooltipCustomHandler(TooltipCustomHandler customHandler) {
		getTooltips().setCustomHandler(customHandler);
	}
	
	/**
	 * @return the itemSortHandler
	 */
	public TooltipItemSortHandler getTooltipItemSortHandler() {
		return getTooltips().getItemSortHandler();
	}

	/**
	 * @param itemSortHandler the itemSortHandler to set
	 */
	public void setTooltipItemSortHandler(TooltipItemSortHandler itemSortHandler) {
		getTooltips().setItemSortHandler(itemSortHandler);
	}
	
	/**
	 * @return the filterHandler
	 */
	public TooltipFilterHandler getTooltipFilterHandler() {
		return getTooltips().getFilterHandler();
	}

	/**
	 * @param filterHandler the filterHandler to set
	 */
	public void setTooltipFilterHandler(TooltipFilterHandler filterHandler) {
		getTooltips().setFilterHandler(filterHandler);
	}
	
	// FIXME to be removed
	public NativeOptions getObject() {
		return getDelegated();
	}
}
