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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.ChartOptions;
import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.defaults.IsDefaultScales;
import org.pepstock.charba.client.defaults.globals.AbstractDefaultOptions;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * Defaults for options element, based on chart type. THIS IS THE ROOT OF ALL ELEMENTS DEFAULTS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartOptions extends AbstractDefaultOptions implements IsDefaultScaledOptions {

	private final ChartOptions chartOptions;

	private final IsDefaultScale scale;

	private final IsDefaultScales scales;

	/**
	 * Creates the object by options element instance.
	 * 
	 * @param chartOptions chart options instance.
	 */
	public DefaultChartOptions(ChartOptions chartOptions) {
		super(new DefaultChartAnimation(chartOptions.getAnimation()),
				new DefaultChartHover(chartOptions.getHover()),
				new DefaultChartElements(chartOptions.getElements()),
				new DefaultChartLayout(chartOptions.getLayout()),
				new DefaultChartTitle(chartOptions.getTitle()),
				new DefaultChartLegend(chartOptions.getLegend()),
				new DefaultChartTooltips(chartOptions.getTooltips()));
		this.chartOptions = chartOptions;
		// checks if the chart options is related to axes
		// checks if single scale
		if (ScaleType.SINGLE.equals(chartOptions.getType().scaleType())) {
			// gets scale
			scale = new DefaultChartScale(chartOptions.getScale());
		} else {
			// uses the default scale
			scale = DefaultsBuilder.get().getScaledOptions().getScale();
		}
		// checks if multi scale
		if (ScaleType.MULTI.equals(chartOptions.getType().scaleType())) {
			// gets scale
			scales = new DefaultChartScales(chartOptions.getScales());
		} else {
			// uses the default scales
			scales = DefaultsBuilder.get().getScaledOptions().getScales();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getScale()
	 */
	@Override
	public IsDefaultScale getScale() {
		return scale;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaledOptions#getScales()
	 */
	@Override
	public IsDefaultScales getScales() {
		return scales;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#isResponsive()
	 */
	@Override
	public boolean isResponsive() {
		return chartOptions.isResponsive();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getResponsiveAnimationDuration()
	 */
	@Override
	public int getResponsiveAnimationDuration() {
		return chartOptions.getResponsiveAnimationDuration();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#isMaintainAspectRatio()
	 */
	@Override
	public boolean isMaintainAspectRatio() {
		return chartOptions.isMaintainAspectRatio();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getAspectRatio()
	 */
	@Override
	public double getAspectRatio() {
		return chartOptions.getAspectRatio();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getDevicePixelRatio()
	 */
	@Override
	public double getDevicePixelRatio() {
		return chartOptions.getDevicePixelRatio();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getDefaultColorAsString()
	 */
	@Override
	public String getDefaultColorAsString() {
		return chartOptions.getDefaultColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getDefaultFontColorAsString()
	 */
	@Override
	public String getDefaultFontColorAsString() {
		return chartOptions.getDefaultFontColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getDefaultFontSize()
	 */
	@Override
	public int getDefaultFontSize() {
		return chartOptions.getDefaultFontSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getDefaultFontStyle()
	 */
	@Override
	public FontStyle getDefaultFontStyle() {
		return chartOptions.getDefaultFontStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getDefaultFontFamily()
	 */
	@Override
	public String getDefaultFontFamily() {
		return chartOptions.getDefaultFontFamily();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#isShowLines()
	 */
	@Override
	public boolean isShowLines() {
		return chartOptions.isShowLines();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#isSpanGaps()
	 */
	@Override
	public boolean isSpanGaps() {
		return chartOptions.isSpanGaps();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getCutoutPercentage()
	 */
	@Override
	public double getCutoutPercentage() {
		return chartOptions.getCutoutPercentage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getRotation()
	 */
	@Override
	public double getRotation() {
		return chartOptions.getRotation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getCircumference()
	 */
	@Override
	public double getCircumference() {
		return chartOptions.getCircumference();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getStartAngle()
	 */
	@Override
	public double getStartAngle() {
		return chartOptions.getStartAngle();
	}

}
