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
package org.pepstock.charba.client.colors;

import org.pepstock.charba.client.commons.Key;

/**
 * A gradient must have a type.<br>
 * The possible types are:<br>
 * <ul>
 * <li>LINEAR</li>
 * <li>RADIAL</li>
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum GradientType implements Key
{

	/**
	 * Creates an image consisting of a progressive transition between two or more colors along a straight line.<br>
	 * Here is the<a href="https://developer.mozilla.org/en-US/docs/Web/CSS/linear-gradient"> definition</a>.
	 */
	LINEAR("linear"),
	/**
	 * Creates an image consisting of a progressive transition between two or more colors that radiate from an origin. Its shape
	 * may be a circle or an ellipse.<br>
	 * Here is the<a href="https://developer.mozilla.org/en-US/docs/Web/CSS/radial-gradient"> definition</a>.
	 */
	RADIAL("radial");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 */
	private GradientType(String value) {
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
