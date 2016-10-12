/* Kyle Green
 * CSC 364-001
 * Knapsack Problem : program implements a Circular Doubly Linked List similar to that of
 * the Java collections framework. The list contains a series of linked nodes that can be traversed
 * forwards and backwards, with a dummy head node that designates the beginning and end of the list.
 */

import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> extends
MyAbstractSequentialList<E>{
	private Node<E> head;

	/** Create a default list */
	public MyDoublyLinkedList() {
		head  = new Node<E>();
		head.prev = head;
		head.next = head;
	}

	/** Create a list from an array of objects */
	public MyDoublyLinkedList(E[] list) {
		super(list);
	}

	@Override /** Clear the list */
	public void clear() {
		size = 0;
		head = head.prev = null;
	}

	@Override /** Return true if this list contains the element e */
	public boolean contains(E e) {
		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			if (e == null ? current.element == null : e.equals(current.element))
				return true;
			current = current.next;
		}
		return false;
	}

	@Override /** Return the index of the head matching element in 
	 *  this list. Return -1 if no match. */
	public int indexOf(E e) {
		Node<E> current = head;
		for (int i = 0; i <= size; i++, current = current.next)
			if (e == null ? current.element == null  && current != head : e.equals(current.element))
				return i - 1;
		return -1;
	}

	@Override /** Return the index of the last matching element in 
	 *  this list. Return -1 if no match. */
	public int lastIndexOf(E e) {
		int lastMatch = -1;
		Node<E> current = head;

		for (int i = 0; i < size; i++) {    	
			if (e == null ? current.element == null : e.equals(current.element))
				lastMatch = i - 1;
			current = current.next;
		}
		return lastMatch;
	}

	@Override /** Override toString() to return elements in the list */
	public String toString() {
		StringBuilder result = new StringBuilder("[");

		Node<E> current = head.next;
		for (int i = 0; i < size; i++) {
			result.append(current.element);
			current = current.next;
			if (current != null && current != head) {
				result.append(", "); // Separate two elements with a comma
			}
			else {
				result.append("]"); // Insert the closing ] in the string
			}
		}

		return result.toString();
	}

	@Override /** Replace the element at the specified position 
	 *  in this list with the specified element. */
	public Object set(int index, E e) {
		checkIndex(index);

		Node<E> current = head;
		for (int i = 0; i <= index; i++)
			current = current.next;    
		E oldElement = current.element;
		current.element = e;
		return oldElement;
	}

	@Override /** Return the element at the specified index */
	public E get(int index) {
		checkIndex(index);

		Node<E> current = head;
		for (int i = 0; i <= index; i++)
			current = current.next;    
		return current.element;
	}

	@Override /** Return the head element in the list */
	public E getFirst() {
		if (size == 0) {
			return null;
		}
		else {
			return head.next.element;
		}
	}

	@Override /** Return the last element in the list */
	public E getLast() {
		if (size == 0) {
			return null;
		}
		else {
			return head.prev.element;
		}
	}

	public void add(E e) {
		size++;
		if (size == 1)
			addFirst(e);
		else
			addLast(e);
	}

	/** Add an element to a particular index */
	@Override
	public void add(int index, E e) {
		size++;
		checkIndex(index);

		if (index == 0) {
			addFirst(e);
		}
		else if (index == size - 1) {
			addLast(e);
		}
		else {
			Node<E> current = head;
			Node<E> newNode = new Node<E>(e);
			for (int i = 0; i < index; i++)
				current = current.next;

			newNode.prev = current;
			newNode.next = current.next;
			current.next.prev = newNode;
			current.next = newNode;
		}
	}

	@Override /** Add an element to the beginning of the list */
	public void addFirst(E e) {
		Node<E> newNode = new Node<E>(e); // Create a new node

		if(size == 1) {
			newNode.next = head;
			newNode.prev = head;
			head.next = newNode;
			head.prev = newNode;
		}

		else {
			newNode.next = head.next;
			newNode.prev = head;
			head.next.prev = newNode;
			head.next = newNode;
		}
	}

	@Override  /** Add an element to the end of the list */
	public void addLast(E e) { 
		Node<E> newNode = new Node<E>(e); // Create a new Node for element e

		if(head.prev == head.next) {
			newNode.next = head;
			newNode.prev = head.next;
			head.prev = newNode;
			head.next.next = newNode;
		}
		else {
			newNode.next = head;
			newNode.prev = head.prev;
			head.prev.next = newNode;
			head.prev = newNode;
		}
	}

	@Override /** Remove the element at the specified position in this 
	 *  list. Return the element that was removed from the list. */
	public boolean remove(E e) {
		if (contains(e)) {
			remove(indexOf(e));
			return true;
		}

		else
			return false;
	}

	@Override /** Remove the element at the specified position in this 
	 *  list. Return the element that was removed from the list. */
	public E remove(int index) {
		checkIndex(index);

		if (index == 0) {
			return removeFirst();
		}
		else if (index == size) {
			return removeLast();
		}
		else {
			Node<E> current = head;

			if(index > size / 2)
				for (int i = 0; i < size - index; i++)
					current = current.prev;

			else
				for (int i = 0; i <= index; i++)
					current = current.next;

			current.prev.next = current.next;
			current.next.prev = current.prev;

			size--;
			return current.element;
		}
	}

	@Override  /** Remove the head node and
	 *  return the object that is contained in the removed node. */
	public E removeFirst() {
		if (size == 0) {
			throw new NoSuchElementException
			("The list is empty.");
		}
		else {
			Node<E> temp = head.next;
			head.next = head.next.next;
			head.next.prev = head;
			size--;
			return temp.element;
		}
	}

	@Override /** Removes the last element in the list */
	public E removeLast() {
		Node<E> temp;

		if (size == 0)
			throw new NoSuchElementException
			("The list is empty.");
		else if (size == 1) {
			temp = head.prev;
			head.prev = head;
			head.next = head;
		}
		else {
			temp = head.prev;
			head.prev = head.prev.prev;
			head.prev.next = head;
		}

		size--;
		return temp.element;
	}


	/** Helper method for checking illegal indices */
	private void checkIndex(int index) {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException
			("Index: " + index + ", Size: " + size);
	}

	@Override /** Override iterator() defined in Iterable */
	public java.util.ListIterator<E> listIterator(int index) {
		return new ListIterator(index);
	}

	private class ListIterator
	implements java.util.ListIterator<E> {
		private Node<E> current = head.next;
		private Node<E> toBeRemovedOrSet = null;
		private int index = 0;
		
		public ListIterator ()
		{
		}
		
		public ListIterator (int index)
		{
			for(int i = 1; i <= index; i++)
				next();
		}

		// Returns the element at current and sets canRemove to true
		@Override
		public E next() {

			E e = current.element;

			if(e == null) {
				throw new java.util.NoSuchElementException();
			}
			else {
				toBeRemovedOrSet = current;
				current = current.next;
				index++;
				return e;
			}
		}

		// Returns true if there are elements left in the list
		@Override
		public boolean hasNext() {
			return (current.element == null ? false : true);
		}

		@Override
		public int nextIndex() {
			if (current == null)
				throw new java.util.NoSuchElementException();
			else
				return index;
		}

		@Override
		public E previous() {

			if(current.prev.element == null) {
				throw new java.util.NoSuchElementException();
			}
			else {
				current = current.prev;
				toBeRemovedOrSet = current;
				index--;
				return current.element;
			}
		}

		@Override
		public boolean hasPrevious() {
			return (current.prev.element == null ? false : true);
		}

		@Override
		public int previousIndex() {
			if (current == null)
				throw new java.util.NoSuchElementException();
			else
				return index - 1;
		}

		// Removes the Node of the last returned element from the list.
		@Override
		public void remove() {
			if (toBeRemovedOrSet == null)
				throw new IllegalStateException
				("There is no element to remove.");

			toBeRemovedOrSet.prev.next = toBeRemovedOrSet.next;
			toBeRemovedOrSet.next.prev = toBeRemovedOrSet.prev;
			if(current == toBeRemovedOrSet)
				current = current.next;
			toBeRemovedOrSet = null;

			size--;
			toBeRemovedOrSet = null;
		}

		@Override  /**
		 * Inserts the specified element into the list. The element is inserted
		 * immediately before the element that would be returned by next(), if
		 * any, and after the element that would be returned by previous(), if
		 * any. (If the list contains no elements, the new element becomes the
		 * sole element on the list.) The new element is inserted before the
		 * implicit cursor: a subsequent call to next would be unaffected, and a
		 * subsequent call to previous would return the new element. (This call
		 * increases by one the value that would be returned by a call to
		 * nextIndex or previousIndex.)
		 */
		public void add(E e) {
			Node<E> newNode = new Node<E>(e);
			
			if(toBeRemovedOrSet != null) {
			newNode.next = toBeRemovedOrSet.next;
			newNode.prev = toBeRemovedOrSet;
			toBeRemovedOrSet.next.prev = newNode;
			toBeRemovedOrSet.next = newNode;
			}
			
			else {
				newNode.next = head;
				newNode.prev = head;
				head.next = newNode;
				head.prev = newNode;
			}

			index++;
			size++;
		}

		@Override /**
		 * Replaces the last element returned by next() or previous() with the
		 * specified element. This call can be made only if neither remove() nor
		 * add(E) have been called after the last call to next or previous.
		 * throws IllegalStateException if neither next nor previous have been
		 * called, or remove or add have been called after the last call to next
		 * or previous.
		 */
		public void set(E e) {
			if (toBeRemovedOrSet == null)
				throw new IllegalStateException
				("There is no element to set.");

			Node<E> newNode = new Node<E>(e);

			newNode.prev = toBeRemovedOrSet.prev;
			newNode.next = toBeRemovedOrSet.next;
			toBeRemovedOrSet = newNode;
			toBeRemovedOrSet.next.prev = toBeRemovedOrSet;
			toBeRemovedOrSet.prev.next = toBeRemovedOrSet;
		}
	}

	private static class Node<E> {
		E element;
		Node<E> next, prev;

		public Node(E element) {
			this.element = element;
		}

		public Node() {
		}
	}
}