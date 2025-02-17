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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.GwtMaterialColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.impl.plugins.DatasetsItemsSelectorOptionsFactory.DatasetsItemsSelectorDefaultsOptionsFactory;
import org.pepstock.charba.client.options.Scales;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * Configuration options of {@link DatasetsItemsSelector#ID} plugin.<br>
 * It is managing:<br>
 * <ul>
 * <li>the X axis ID
 * <li>the selection color
 * <li>the border color
 * <li>the border width
 * <li>the border dash
 * <li>if firing event on clear selection
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DatasetsItemsSelectorOptions extends AbstractPluginOptions {

	/**
	 * Default alpha of selecting/selection colors, <b>{@value DEFAULT_ALPHA}</b>.
	 */
	public static final double DEFAULT_ALPHA = 0.3D;

	/**
	 * Default color for area, {@link GwtMaterialColor#ORANGE_LIGHTEN_3}, alpha <b>{@value DEFAULT_ALPHA}</b>.
	 */
	public static final IsColor DEFAULT_COLOR = GwtMaterialColor.ORANGE_LIGHTEN_3.alpha(DEFAULT_ALPHA);

	/**
	 * Default X axis id, {@link Scales#DEFAULT_X_AXIS_ID}.
	 */
	public static final String DEFAULT_AXIS_ID = Scales.DEFAULT_X_AXIS_ID;

	/**
	 * Default border width of selection area, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 0;

	/**
	 * Default border color for area, {@link GwtMaterialColor#GREY_DARKEN_2}.
	 */
	public static final IsColor DEFAULT_BORDER_COLOR = GwtMaterialColor.GREY_DARKEN_2;

	/**
	 * Default flag if fire event after clear selection, <b>{@value DEFAULT_FIRE_EVENT_ON_CLEAR_SELECTION}</b>.
	 */
	public static final boolean DEFAULT_FIRE_EVENT_ON_CLEAR_SELECTION = false;

	// defaults global options instance
	private DatasetsItemsSelectorDefaultsOptions defaultsOptions;
	// defaults global options factory
	private final DatasetsItemsSelectorDefaultsOptionsFactory defaultsFactory = new DatasetsItemsSelectorDefaultsOptionsFactory();
	// clear selection item
	private final ClearSelection clearSelection;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		COLOR("color"),
		X_AXIS_ID("xAxisID"),
		BORDER_COLOR("borderColor"),
		BORDER_DASH("borderDash"),
		BORDER_WIDTH("borderWidth"),
		FIRE_EVENT_ON_CLEAR_SELECTION("fireEventOnClearSelection"),
		CLEAR_SELECTION("clearSelection");

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

	/**
	 * Builds the object with a new java script object setting the default value of plugin.
	 */
	public DatasetsItemsSelectorOptions() {
		super(DatasetsItemsSelector.ID);
		// this constructor is used by user to set options for plugin
		// both default global or chart one.
		// reads the default default global options
		defaultsOptions = loadGlobalsPluginOptions(defaultsFactory);
		// sets inner elements
		clearSelection = new ClearSelection(defaultsOptions.getClearSelection());
		// stores inner elements
		setValue(Property.CLEAR_SELECTION, clearSelection);
	}

	/**
	 * Builds the object using the java script object of options and the defaults, set by user.<br>
	 * Used internally to call the plugin.
	 * 
	 * @param nativeObject configuration of plugin.
	 * @param defaultsOptions default options, which must be stored into default global.
	 */
	DatasetsItemsSelectorOptions(NativeObject nativeObject, DatasetsItemsSelectorDefaultsOptions defaultsOptions) {
		super(DatasetsItemsSelector.ID, nativeObject);
		this.defaultsOptions = defaultsOptions;
		// sets inner elements
		clearSelection = new ClearSelection(getValue(Property.CLEAR_SELECTION), defaultsOptions.getClearSelection());
	}

	/**
	 * Returns the clear selection element.
	 * 
	 * @return the clear selection element
	 */
	public ClearSelection getClearSelection() {
		return clearSelection;
	}

	/**
	 * Sets the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * 
	 * @param xAxisID the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found
	 *            x axis.
	 */
	public void setXAxisID(String xAxisID) {
		setValue(Property.X_AXIS_ID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x
	 * axis.
	 * 
	 * @return the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x
	 *         axis.
	 */
	public String getXAxisID() {
		return getValue(Property.X_AXIS_ID, defaultsOptions.getXAxisID());
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	public String getColorAsString() {
		return getValue(Property.COLOR, defaultsOptions.getColorAsString());
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color.
	 */
	public void setColor(String color) {
		setValue(Property.COLOR, color);
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color.
	 */
	public void setColor(IsColor color) {
		setColor(checkValue(color));
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	public void setBorderDash(int... borderDash) {
		setArrayValue(Property.BORDER_DASH, ArrayInteger.fromOrNull(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines.
	 */
	public List<Integer> getBorderDash() {
		return ArrayListHelper.list(getBorderDashAsJavaScriptObject());
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines.
	 */
	ArrayInteger getBorderDashAsJavaScriptObject() {
		// creates array instance
		ArrayInteger array = null;
		// checks if there is the property set
		if (has(Property.BORDER_DASH)) {
			// returns array
			array = getArrayValue(Property.BORDER_DASH);
		} else {
			// returns default
			array = defaultsOptions.getBorderDash();
		}
		return array;
	}

	/**
	 * Sets the border width of the selection.
	 * 
	 * @param borderWidth the border width of the selection.
	 */
	public void setBorderWidth(int borderWidth) {
		setValue(Property.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Returns the border width of the selection.
	 * 
	 * @return list of the border width of the selection.
	 */
	public int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, defaultsOptions.getBorderWidth());
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	public String getBorderColorAsString() {
		return getValue(Property.BORDER_COLOR, defaultsOptions.getBorderColorAsString());
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color.
	 */
	public void setBorderColor(String color) {
		setValue(Property.BORDER_COLOR, color);
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color.
	 */
	public void setBorderColor(IsColor color) {
		setBorderColor(checkValue(color));
	}

	/**
	 * Sets <code>true</code> if it will fire event after clear of selection, otherwise <code>false</code>.
	 * 
	 * @param fireEvent <code>true</code> if it will fire event after clear of selection, otherwise <code>false</code>
	 */
	public void setFireEventOnClearSelection(boolean fireEvent) {
		setValue(Property.FIRE_EVENT_ON_CLEAR_SELECTION, fireEvent);
	}

	/**
	 * Returns <code>true</code> if it will fire event after clear of selection, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if it will fire event after clear of selection, otherwise <code>false</code>
	 */
	public boolean isFireEventOnClearSelection() {
		return getValue(Property.FIRE_EVENT_ON_CLEAR_SELECTION, defaultsOptions.isFireEventOnClearSelection());
	}

	/**
	 * Returns the java script object of this options.
	 * 
	 * @return the java script object of this options.
	 */
	public NativeObject getObject() {
		return super.getNativeObject();
	}

}
