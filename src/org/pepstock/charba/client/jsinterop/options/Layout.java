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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions;

/**
 * The layout configuration is needed to set the padding.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Layout extends BaseModel<Options, IsDefaultOptions, NativeLayout> {

	private final Padding padding;
	
	Layout(Options options, IsDefaultOptions defaultOptions, NativeLayout delegated) {
		super(options, defaultOptions, delegated == null ? new NativeLayout() : delegated);
		padding = new Padding(this, defaultOptions.getPadding(), getDelegated().getPadding());
	}

	/**
	 * @return the padding
	 */
	public Padding getPadding() {
		return padding;
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getLayout() == null) {
			getParent().getDelegated().setLayout(getDelegated());
		}
	}
}