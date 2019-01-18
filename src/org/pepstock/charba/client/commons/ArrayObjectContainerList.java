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

import java.util.ArrayList;
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
public final class ArrayObjectContainerList<E extends NativeObjectContainer> extends AbstractArrayList<E, ArrayObject> {

	// delegated array to store objects
	private final ArrayObject array;

	// delegated linked list to store Java objects
	private final List<E> delegate = new LinkedList<E>();

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
			throw new UnsupportedOperationException("Unable to create NativeObjectContainer without a factory");
		} else {
			// uses an existing array
			this.array = array;
			// scans the array
			for (int i = 0; i < array.length(); i++) {
				// uses teh factory to creates all elements
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
	 * @see org.pepstock.charba.client.jsinterop.commons.AbstractArrayList#getArray()
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
		// scans all elements
		for (E val : values) {
			// adds
			add(val);
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
	public boolean contains(Object o) {
		return delegate.contains(o);
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 */
	@Override
	public Iterator<E> iterator() {
		return new IteratorImpl<E>(this);
	}

	/**
	 * Appends the specified element to the end of this list
	 */
	@Override
	public boolean add(E e) {
		// adds to linked list
		boolean added = delegate.add(e);
		// if added
		if (added) {
			// adds to JS array
			array.push(e.getNativeObject());
		}
		return added;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if it is present. If this list does not contain the
	 * element, it is unchanged.
	 */
	@Override
	public boolean remove(Object o) {
		// gets index of object
		int index = indexOf(o);
		// if is in the right range
		if (checkRange(index)) {
			// removes by index
			remove(index);
			return true;
		}
		return false;
	}

	/**
	 * Returns true if this list contains all of the elements of the specified collection.
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return delegate.containsAll(c);
	}

	/**
	 * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by
	 * the specified collection's iterator
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		// set modified checking if collection is empty
		boolean modified = !c.isEmpty();
		Iterator<? extends E> e = c.iterator();
		// scans all elements
		while (e.hasNext()) {
			// if adds
			if (add(e.next())) {
				// sets modified
				modified &= true;
			} else {
				// sets false!
				modified = false;
			}
		}
		return modified;
	}

	/**
	 * Removes from this list all of its elements that are contained in the specified collection.
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		// set modified checking if collection is empty
		boolean modified = !c.isEmpty();
		Iterator<?> e = c.iterator();
		// scans all elements
		while (e.hasNext()) {
			// removes and checks if modified
			modified = modified && remove(e.next());
		}
		return modified;
	}

	/**
	 * Retains only the elements in this list that are contained in the specified collection.<br>
	 * In other words, removes from this list all of its elements that are not contained in the specified collection.
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		// set modified checking if collection is empty
		boolean modified = !c.isEmpty();
		if (modified) {
			// creates a copy of elements
			List<E> contained = new ArrayList<E>();
			// scans all current elements
			for (int i = 0; i < size(); i++) {
				E value = get(i);
				// checks if not present into
				// passed collection
				if (!c.contains(get(i))) {
					// adds to temporary list
					contained.add(value);
				}
			}
			// if temporary list is not empty
			if (!contained.isEmpty()) {
				// scans all elements
				for (E toRemove : contained) {
					// removes and checks if modified
					modified = modified && remove(toRemove);
				}
			}
		}
		return modified;
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
		// checks range
		if (checkRange(index)) {
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
		delegate.add(index, element);
		array.insertAt(index, element.getNativeObject());
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
			E elem = delegate.remove(index);
			// removes from JS array
			array.remove(index);
			// returns old value
			return elem;
		}
		return null;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the
	 * element.
	 */
	@Override
	public int indexOf(Object o) {
		return delegate.indexOf(o);
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the
	 * element.
	 */
	@Override
	public int lastIndexOf(Object o) {
		return delegate.lastIndexOf(o);
	}

	/**
	 * Returns a list iterator over the elements in this list
	 */
	@Override
	public ListIterator<E> listIterator() {
		return new ListIteratorImpl<E>(0, this);
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
		return new ListIteratorImpl<E>(index, this);
	}

	/**
	 * Not implemented
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException(UNABLE_COPY_ARRAY_MESSAGE);
	}

}