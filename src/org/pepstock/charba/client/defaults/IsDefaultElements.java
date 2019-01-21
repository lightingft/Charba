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
package org.pepstock.charba.client.defaults;

/**
 * Interface to define elements object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultElements {

	/**
	 * Returns ARC object defaults.
	 * 
	 * @return ARC object defaults.
	 */
	IsDefaultArc getArc();

	/**
	 * Returns LINE object defaults.
	 * 
	 * @return LINE object defaults.
	 */
	IsDefaultLine getLine();

	/**
	 * Returns POINT object defaults.
	 * 
	 * @return POINT object defaults.
	 */
	IsDefaultPoint getPoint();

	/**
	 * Returns RECTANGLE object defaults.
	 * 
	 * @return RECTANGLE object defaults.
	 */
	IsDefaultRectangle getRectangle();

}
