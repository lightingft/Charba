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
package org.pepstock.charba.client.jsinterop.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event which is fired when new event handler has been added to the chart.<br>
 * This event should use only for use internal only to manage internally all handlers.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AddHandlerEvent extends GwtEvent<AddHandlerEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<AddHandlerEventHandler> TYPE = new Type<AddHandlerEventHandler>();

	// type of new handler added to the chart
	private final Type<? extends EventHandler> type;

	/**
	 * Creates the event with the type of new handler.
	 * 
	 * @param type the type of new handler.
	 */
	public AddHandlerEvent(Type<? extends EventHandler> type) {
		this.type = type;
	}

	/**
	 * Returns the type of new handler.
	 * 
	 * @return the type of new handler.
	 */
	public Type<? extends EventHandler> getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<AddHandlerEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(AddHandlerEventHandler handler) {
		handler.onAdd(this);
	}

}