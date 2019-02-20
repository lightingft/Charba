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
package org.pepstock.charba.client.utils;

/**
 * Contains the constants used by JSON stringify replacer.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class JSONReplacerConstants {

	// used into JSON stringfy replacer when the object is already passed
	static final String CYCLE_PROPERTY_VALUE = "";
	// used into JSON stringfy replacer when the key of object is the hashcode
	static final String HASHCODE_PROPERTY_KEY = "$H";

	/**
	 * To avoid any instantiation.
	 */
	private JSONReplacerConstants() {
		// do nothing
	}

}
