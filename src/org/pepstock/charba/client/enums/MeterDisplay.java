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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Determines how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
 * (degenerate segments with zero lengths, whose specified endpoints and control points are exactly at the same position, are
 * skipped).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum MeterDisplay implements Key
{
	/**
	 * 
	 */
	value,
	/**
	 * 
	 */
	percentage,
	/**
	 * 
	 */
	label,
	/**
	 * 
	 */
	valueAndLabel,
	/**
	 * 
	 */
	percentageAndLabel

}