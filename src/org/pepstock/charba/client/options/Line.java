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
package org.pepstock.charba.client.options;

import java.util.List;

import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultLine;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.CubicInterpolationMode;
import org.pepstock.charba.client.enums.Filler;
import org.pepstock.charba.client.enums.HasFill;
import org.pepstock.charba.client.enums.IsFill;
import org.pepstock.charba.client.enums.JoinStyle;

/**
 * Line elements are used to represent the line in a line chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Line extends AbstractElement<IsDefaultLine> implements IsDefaultLine, HasFill {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TENSION("tension"),
		BORDER_CAP_STYLE("borderCapStyle"),
		BORDER_DASH("borderDash"),
		BORDER_DASH_OFFSET("borderDashOffset"),
		BORDER_JOIN_STYLE("borderJoinStyle"),
		CUBIC_INTERPOLATION_MODE("cubicInterpolationMode"),
		CAP_BEZIER_POINTS("capBezierPoints"),
		STEPPED("stepped");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	// instance of filler
	private final Filler filler;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param elements parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Line(Elements elements, Key childKey, IsDefaultLine defaultValues, NativeObject nativeObject) {
		super(elements, childKey, defaultValues, nativeObject);
		// creates filler instance
		filler = new Filler(getNativeObject(), getDefaultValues().getFill());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.enums.IsFillable#getFiller()
	 */
	@Override
	public Filler getFiller() {
		return filler;
	}

	/**
	 * Returns the Bezier curve tension (0 for no Bezier curves).
	 * 
	 * @param tension the Bezier curve tension (0 for no Bezier curves).
	 */
	public void setTension(double tension) {
		setValue(Property.TENSION, tension);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the Bezier curve tension (0 for no Bezier curves).
	 * 
	 * @return the Bezier curve tension (0 for no Bezier curves).
	 */
	public double getTension() {
		return getValue(Property.TENSION, getDefaultValues().getTension());
	}

	/**
	 * Sets how the end points of every line are drawn. There are three possible values for this property and those are: butt,
	 * round and square.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn.
	 */
	public void setBorderCapStyle(CapStyle borderCapStyle) {
		setValue(Property.BORDER_CAP_STYLE, borderCapStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns how the end points of every line are drawn. There are three possible values for this property and those are:
	 * butt, round and square. By default this property is set to butt.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	public CapStyle getBorderCapStyle() {
		return getValue(Property.BORDER_CAP_STYLE, CapStyle.class, getDefaultValues().getBorderCapStyle());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating
	 *            lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		setArrayValue(Property.BORDER_DASH, ArrayInteger.fromOrNull(borderDash));
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 *         lines and gaps which describe the pattern.
	 */
	public List<Integer> getBorderDash() {
		ArrayInteger array = getArrayValue(Property.BORDER_DASH);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset the line dash pattern offset or "phase".
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		setValue(Property.BORDER_DASH_OFFSET, borderDashOffset);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return the line dash pattern offset or "phase".
	 */
	public int getBorderDashOffset() {
		return getValue(Property.BORDER_DASH_OFFSET, getDefaultValues().getBorderDashOffset());
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 * (degenerate segments with zero lengths, whose specified end points and control points are exactly at the same position,
	 * are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @param borderJoinStyle there are three possible values for this property: round, bevel and miter.
	 */
	public void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		setValue(Property.BORDER_JOIN_STYLE, borderJoinStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 * (degenerate segments with zero lengths, whose specified end points and control points are exactly at the same position,
	 * are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @return there are three possible values for this property: round, bevel and miter.
	 */
	public JoinStyle getBorderJoinStyle() {
		return getValue(Property.BORDER_JOIN_STYLE, JoinStyle.class, getDefaultValues().getBorderJoinStyle());
	}

	/**
	 * Sets <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @param capBezierPoints <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 */
	public void setCapBezierPoints(boolean capBezierPoints) {
		setValue(Property.CAP_BEZIER_POINTS, capBezierPoints);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @return <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 */
	public boolean isCapBezierPoints() {
		return getValue(Property.CAP_BEZIER_POINTS, getDefaultValues().isCapBezierPoints());
	}

	/**
	 * Sets algorithm used to interpolate a smooth curve from the discrete data points.<br>
	 * The following interpolation modes are supported:<br>
	 * <br>
	 * 
	 * <pre>
	 * 'default'
	 * 'monotone'
	 * </pre>
	 * 
	 * <br>
	 * The 'default' algorithm uses a custom weighted cubic interpolation, which produces pleasant curves for all types of
	 * datasets.<br>
	 * The 'monotone' algorithm is more suited to y = f(x) datasets : it preserves monotonicity (or piecewise monotonicity) of
	 * the dataset being interpolated, and ensures local extremums (if any) stay at input data points.
	 * 
	 * @param mode algorithm used to interpolate a smooth curve from the discrete data points
	 */
	public void setCubicInterpolationMode(CubicInterpolationMode mode) {
		setValue(Property.CUBIC_INTERPOLATION_MODE, mode);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns algorithm used to interpolate a smooth curve from the discrete data points.
	 * 
	 * @return algorithm used to interpolate a smooth curve from the discrete data points. Default is <code>'default'</code>.
	 */
	public CubicInterpolationMode getCubicInterpolationMode() {
		return getValue(Property.CUBIC_INTERPOLATION_MODE, CubicInterpolationMode.class, getDefaultValues().getCubicInterpolationMode());
	}

	/**
	 * Sets how to fill the area under the line.
	 *
	 * @param fill <code>true</code> to fill, otherwise <code>false</code>.
	 */
	@Override
	public void setFill(boolean fill) {
		// calls the default method into interface
		HasFill.super.setFill(fill);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets how to fill the area under the line.
	 *
	 * @param fill how to fill the area under the line.
	 */
	@Override
	public void setFill(IsFill fill) {
		// calls the default method into interface
		HasFill.super.setFill(fill);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns how to fill the area under the line.
	 *
	 * @return how to fill the area under the line.
	 */
	@Override
	public IsFill getFill() {
		// calls the default method into interface
		return HasFill.super.getFill();
	}

	/**
	 * Sets <code>true</code> to show the line as a stepped line (tension will be ignored).
	 * 
	 * @param stepped <code>true</code> to show the line as a stepped line (tension will be ignored).
	 */
	public void setStepped(boolean stepped) {
		setValue(Property.STEPPED, stepped);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns <code>true</code> to show the line as a stepped line (tension will be ignored).
	 * 
	 * @return <code>true</code> to show the line as a stepped line (tension will be ignored).
	 */
	public boolean isStepped() {
		return getValue(Property.STEPPED, getDefaultValues().isStepped());
	}
}