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
package org.pepstock.charba.client.jsinterop.items;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;

/**
 * Wrapper of tooltip node of CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TooltipNode extends NativeObjectContainer {
	
	private final TooltipModel model;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		_model
	}
	
	/**
	 * @param nativeObject
	 */
	public TooltipNode(NativeObject nativeObject) {
		super(nativeObject);
		model = new TooltipModel(getValue(Property._model));
	}


	/**
	 * Returns the tooltip model
	 * 
	 * @return the model
	 */
	public TooltipModel getModel() {
		return model;
	}	

}