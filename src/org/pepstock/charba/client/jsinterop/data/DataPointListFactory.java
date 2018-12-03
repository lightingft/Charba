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
package org.pepstock.charba.client.jsinterop.data;

import org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

/**
 * Factory to create a datapoint from a native object, used for arra container lists.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
class DataPointListFactory implements Factory<DataPoint> {

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory#create(org.pepstock.charba.client.jsinterop.commons.NativeObject)
	 */
	@Override
	public DataPoint create(NativeObject nativeObject) {
		return new DataPoint(nativeObject);
	}

}