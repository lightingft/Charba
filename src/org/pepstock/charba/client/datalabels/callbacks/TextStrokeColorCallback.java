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
package org.pepstock.charba.client.datalabels.callbacks;

import org.pepstock.charba.client.callbacks.Scriptable;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.datalabels.DataLabelsPlugin;

import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;

/**
 * Callback interface of {@link DataLabelsPlugin#ID} plugin to set <code>textStrokeColor</code> property at runtime, using the chart instance and
 * the plugin context.<br>
 * <b>PAY ATTENTION</b> that with the current release of {@link DataLabelsPlugin#ID} plugin, the GRADIENT coordinates are relative the the label
 * coordinates (local), not relative to the canvas coordinates (global), therefore NOT USE GRADIENT Charba object, waiting for
 * {@link DataLabelsPlugin#ID} enhancement.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @see IsColor
 * @see Pattern
 * @see Gradient
 * @see CanvasPattern
 * @see CanvasGradient
 */
public interface TextStrokeColorCallback extends Scriptable<Object> {

}
