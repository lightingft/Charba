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
package org.pepstock.charba.client.commons;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * An ordered collection (also known as a sequence). The user of this interface has precise control over where in the list each
 * element is inserted. <br>
 * The user can access elements by their integer index (position in the list), and search for elements in the list.<br>
 * This implementation uses a java script array as back-end to store objects (native object containers).<br>
 * Elements are instances of {@link org.pepstock.charba.client.commons.NativeObjectContainer}.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <E> extension of {@link org.pepstock.charba.client.commons.NativeObjectContainer}
 * 
 */
public final class ArrayObjectContainerList<E extends NativeObjectContainer> extends AbstractArrayContainerList<E, ArrayObject> {

	// delegated array to store objects
	private final ArrayObject array;

	// delegated linked list to store Java objects
	private final List<E> delegate = new LinkedList<>();

	/**
	 * Internal constructor used to set an array instance as back-end of the list.
	 * 
	 * @param array java script array instance. If <code>null</code>, new empty array has been created
	 * @param factory factory instance to create the object from a native one.
	 */
	ArrayObjectContainerList(ArrayObject array, NativeObjectContainerFactory<E> factory) {
		// if null, creates a new array
		if (array == null) {
			this.array = new ArrayObject();
		} else if (factory == null) {
			// factory is not consistent and array is consistent EXCEPTION
			// factory is mandatory to initialize the list creating the elements from native object
			throw new IllegalArgumentException("Unable to create NativeObjectContainer without a factory which is null");
		} else {
			// uses an existing array
			this.array = array;
			// scans the array
			for (int i = 0; i < array.length(); i++) {
				// uses the factory to creates all elements
				delegate.add(factory.create(array.get(i)));
			}
		}
	}

	/**
	 * Creates an empty list
	 */
	public ArrayObjectContainerList() {
		this(null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.AbstractArrayList#getArray()
	 */
	@Override
	ArrayObject getArray() {
		return array;
	}

	/**
	 * Loads an array of elements into the list
	 * 
	 * @param values an array of elements to be loaded
	 */
	public void addAll(E[] values) {
		// checks if arguments are consistent
		if (values != null && values.length > 0) {
			// scans all elements
			for (E val : values) {
				// adds
				add(val);
			}
		}
	}

	/**
	 * Returns the number of elements in this list.
	 */
	@Override
	public int size() {
		return delegate.size();
	}

	/**
	 * Returns true if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	/**
	 * Returns true if this list contains the specified element.
	 */
	@Override
	public boolean contains(Object object) {
		return delegate.contains(object);
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 */
	@Override
	public Iterator<E> iterator() {
		return new IteratorImpl<>(this);
	}

	/**
	 * Appends the specified element to the end of this list
	 */
	@Override
	public boolean add(E element) {
		// checks if element is consistent
		if (element != null) {
			// adds to linked list
			boolean added = delegate.add(element);
			// if added
			if (added) {
				// adds to JS array
				array.push(element.getNativeObject());
			}
			return added;
		}
		// if here, element is not consistent
		// and not added
		return false;
	}

	/**
	 * Returns true if this list contains all of the elements of the specified collection.
	 */
	@Override
	public boolean containsAll(Collection<?> collection) {
		// checks if argument is consistent
		if (collection != null) {
			return delegate.containsAll(collection);
		}
		// if here, collection is not consistent
		return false;
	}

	/**
	 * Removes all of the elements from this list. The list will be empty after this call returns.
	 */
	@Override
	public void clear() {
		delegate.clear();
		array.clear();
	}

	/**
	 * Returns the element at the specified position in this list. If index out of range, returns null
	 */
	@Override
	public E get(int index) {
		// checks range
		if (checkRange(index)) {
			return delegate.get(index);
		}
		return null;
	}

	/**
	 * Replaces the element at the specified position in this list with the specified element. If index out of range, returns
	 * null
	 */
	@Override
	public E set(int index, E element) {
		// checks element is consistent and in range
		if (element != null && checkRange(index)) {
			// sets to linked list
			E old = delegate.set(index, element);
			// sets on JS array
			array.set(index, element.getNativeObject());
			// returns old value
			return old;
		}
		return null;
	}

	/**
	 * Inserts the specified element at the specified position in this list.<br>
	 * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their
	 * indices).
	 */
	@Override
	public void add(int index, E element) {
		// checks if element is consistent
		if (element != null) {
			// adds element
			delegate.add(index, element);
			array.insertAt(index, element.getNativeObject());
		}
	}

	/**
	 * Removes the element at the specified position in this list.<br>
	 * Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from
	 * the list.
	 */
	@Override
	public E remove(int index) {
		// checks range
		if (checkRange(index)) {
			// removes from LinkedList
			E old = delegate.remove(index);
			// removes from JS array
			array.remove(index);
			// returns old value
			return old;
		}
		return null;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the
	 * element.
	 */
	@Override
	public int indexOf(Object object) {
		// checks if argument is consistent
		if (object != null) {
			return delegate.indexOf(object);
		}
		// if here, element is not consistent
		return AbstractArrayList.NOT_FOUND;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the
	 * element.
	 */
	@Override
	public int lastIndexOf(Object object) {
		// checks if argument is consistent
		if (object != null) {
			return delegate.lastIndexOf(object);
		}
		// if here, element is not consistent
		return AbstractArrayList.NOT_FOUND;
	}

	/**
	 * Returns a list iterator over the elements in this list
	 */
	@Override
	public ListIterator<E> listIterator() {
		return new ListIteratorImpl<>(0, this);
	}

	/**
	 * Returns a list iterator over the elements in this list (in proper sequence), starting at the specified position in the
	 * list.<br>
	 * The specified index indicates the first element that would be returned by an initial call to next.<br>
	 * An initial call to previous would return the element with the specified index minus one.
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		// if index is out of range, EXCEPTION
		if (!checkRange(index)) {
			throw new IndexOutOfBoundsException("Index: " + index);
		}
		return new ListIteratorImpl<>(index, this);
	}

	/**
	 * Not implemented
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException(UNABLE_COPY_ARRAY_MESSAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray() {
		return delegate.toArray();
	}

}