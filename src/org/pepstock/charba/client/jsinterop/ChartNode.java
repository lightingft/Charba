package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.commons.ObjectType;
import org.pepstock.charba.client.jsinterop.items.ChartAreaNode;
import org.pepstock.charba.client.jsinterop.items.LegendNode;
import org.pepstock.charba.client.jsinterop.items.OptionsNode;
import org.pepstock.charba.client.jsinterop.items.ScalesNode;
import org.pepstock.charba.client.jsinterop.items.TitleNode;
import org.pepstock.charba.client.jsinterop.items.TooltipNode;
import org.pepstock.charba.client.jsinterop.items.UndefinedValues;

public final class ChartNode{
	
	private final Chart chart;
	
	private final OptionsNode options;
	
	private final LegendNode legend;
	
	private final ScalesNode scales;
	
	private final ChartAreaNode chartArea;
	
	private final TitleNode title;
	
	private final TooltipNode tooltip;
	
	private final boolean initialized;
	
	/**
	 * @param chart
	 */
	ChartNode(Chart chart) {
		this.chart = chart;
		options = new OptionsNode(chart != null ? chart.getOptions() : null);
		legend = new LegendNode(chart != null ? chart.getLegend() : null);
		scales = new ScalesNode(chart != null ? chart.getScales() : null);
		chartArea = new ChartAreaNode(chart != null ? chart.getChartArea() : null);
		title = new TitleNode(chart != null ? chart.getTitleBlock() : null);
		tooltip = new TooltipNode(chart != null ? chart.getTooltip() : null);
		initialized = getId() != UndefinedValues.INTEGER;
	}
	
	/**
	 * @return the initialized
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * Returns the options item.
	 * 
	 * @return the options item.
	 */
	public OptionsNode getOptions() {
		return options;
	}

	/**
	 * Returns the legend item.
	 * 
	 * @return the legend item.
	 */
	public LegendNode getLegend() {
		return legend;
	}

	/**
	 * Returns the scales item.
	 * 
	 * @return the scales item.
	 */
	public ScalesNode getScales() {
		return scales;
	}

	/**
	 * Returns the chart area item.
	 * 
	 * @return the chart area item.
	 */
	public ChartAreaNode getChartArea() {
		return chartArea;
	}

	/**
	 * Returns the title item.
	 * 
	 * @return the title item.
	 */
	public TitleNode getTitle() {
		return title;
	}

	/**
	 * Returns the tooltip item.
	 * 
	 * @return the tooltip item.
	 */
	public TooltipNode getTooltip() {
		return tooltip;
	}

	/**
	 * Returns the CHART JS chart ID.
	 * 
	 * @return the CHART JS chart ID. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getId() {
		return check(chart.getId(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the width in pixel.
	 * 
	 * @return the width in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getWidth() {
		return check(chart.getWidth(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the height in pixel.
	 * 
	 * @return the height in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getHeight() {
		return check(chart.getHeight(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the aspect ratio.
	 * 
	 * @return the aspect ratio. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getAspectRatio() {
		return check(chart.getAspectRatio(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the current device pixel ratio.
	 * 
	 * @return the current device pixel ratio. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getCurrentDevicePixelRatio() {
		return check(chart.getCurrentDevicePixelRatio(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns if the chart is animating or not.
	 * 
	 * @return if the chart is animating or not. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	public boolean isAnimating() {
		return check(chart.isAnimating(), UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the border width value.
	 * 
	 * @return the border width value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getBorderWidth() {
		return check(chart.getBorderWidth(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the outer radius value.
	 * 
	 * @return the outer radius value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getOuterRadius() {
		return check(chart.getOuterRadius(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the inner radius value.
	 * 
	 * @return the inner radius value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getInnerRadius() {
		return check(chart.getInnerRadius(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the radius length value.
	 * 
	 * @return the radius length value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getRadiusLength() {
		return check(chart.getRadiusLength(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the offset X value.
	 * 
	 * @return the offset X value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getOffsetX() {
		return check(chart.getOffsetX(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the offset Y value.
	 * 
	 * @return the offset Y value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getOffsetY() {
		return check(chart.getOffsetY(), UndefinedValues.INTEGER);
	}
	
	/**
	 * Checks 2 booleans and returns the no-null one.
	 * @param value original value
	 * @param defaultValue default value
	 * @return returns the no-null one.
	 */
	private boolean check(boolean value, boolean defaultValue) {
		// transforms the value into string because a boolean can not be null
		String stringValue = String.valueOf(value);
		// by java script, if value is null, to string you have "undefined"
		return ObjectType.Undefined.name().equalsIgnoreCase(stringValue) ? defaultValue : value;
	}

	/**
	 * Checks 2 integers and returns the no-null one.
	 * @param value original value
	 * @param defaultValue default value
	 * @return returns the no-null one.
	 */
	private int check(int value, int defaultValue) {
		return Double.isNaN(value) ? defaultValue : value;
	}

	/**
	 * Checks 2 doubles and returns the no-null one.
	 * @param value original value
	 * @param defaultValue default value
	 * @return returns the no-null one.
	 */
	private double check(double value, double defaultValue) {
		return Double.isNaN(value) ? defaultValue : value;
	}

}