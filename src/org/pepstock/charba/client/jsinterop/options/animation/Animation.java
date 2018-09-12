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
package org.pepstock.charba.client.jsinterop.options.animation;

import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.commons.IsDelegated;

/**
 * It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it
 * takes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Animation implements IsDelegated<NativeAnimation> {

	private final NativeAnimation delegated;
	
	private final IsDefaultAnimation defaultValues;
	
	public Animation(IsDefaultAnimation defaultValues) {
		this(new NativeAnimation(), defaultValues);
	}

	public Animation(NativeAnimation delegated, IsDefaultAnimation defaultValues) {
		this.delegated = delegated;
		this.defaultValues = defaultValues;
	}

	/**
	 * Sets the animation easing.
	 * 
	 * @param easing animation easing.
	 * @see org.pepstock.charba.client.enums.Easing
	 */
	public void setEasing(Easing easing) {
		delegated.setEasing(easing.name());
	}

	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing. Default value is {@link org.pepstock.charba.client.enums.Easing#easeOutQuart}.
	 * @see org.pepstock.charba.client.enums.Easing
	 */
	public Easing getEasing() {
		return AssignHelper.deserialize(AssignHelper.check(delegated.getEasing(), defaultValues.getEasing()), Easing.class, Easing.easeOutQuart);
	}

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	public void setDuration(int milliseconds) {
		delegated.setDuration(milliseconds);
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes. Default is 1000 (1 second).
	 */
	public int getDuration() {
		return AssignHelper.check(delegated.getDuration(), defaultValues.getDuration());
	}
	
	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @param animateRotate If true, the chart will animate in with a rotation animation.
	 */
	public void setAnimateRotate(boolean animateRotate) {
		delegated.setAnimateRotate(animateRotate);
	}

	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @return If true, the chart will animate in with a rotation animation. Default is true.
	 */
	public boolean isAnimateRotate() {
		return AssignHelper.check(delegated.isAnimateRotate(), defaultValues.isAnimateRotate());
	}

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If true, will animate scaling the chart from the center outwards.
	 */
	public void setAnimateScale(boolean animateScale) {
		delegated.setAnimateScale(animateScale);
	}

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @return If true, will animate scaling the chart from the center outwards. Default is false.
	 */
	public boolean isAnimateScale() {
		return AssignHelper.check(delegated.isAnimateScale(), defaultValues.isAnimateScale());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.commons.IsDelegated#getDelegated()
	 */
	@Override
	public NativeAnimation getDelegated() {
		return delegated;
	}

}