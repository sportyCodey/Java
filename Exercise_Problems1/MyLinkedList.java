import java.util.Collection;

public class MyLinkedList<E> extends MyAbstractList<E> {
  private Node<E> head, tail;

  /** Create a default list */
  public MyLinkedList() {
  }

  /** Create a list from an array of objects */
  public MyLinkedList(E[] objects) {
    super(objects);
  }

  /** Return the head element in the list */
  public E getFirst() {
    if (size == 0) {
      return null;
    }
    else {
      return head.element;
    }
  }

  /** Return the last element in the list */
  public E getLast() {
    if (size == 0) {
      return null;
    }
    else {
      return tail.element;
    }
  }

  /** Add an element to the beginning of the list */
  public void addFirst(E e) {
    Node<E> newNode = new Node<E>(e); // Create a new node
    newNode.next = head; // link the new node with the head
    head = newNode; // head points to the new node
    size++; // Increase list size

    if (tail == null) // the new node is the only node in list
      tail = head;
  }

  /** Add an element to the end of the list */
  public void addLast(E e) {
    Node<E> newNode = new Node<E>(e); // Create a new for element e

    if (tail == null) {
      head = tail = newNode; // The new node is the only node in list
    }
    else {
      tail.next = newNode; // Link the new with the last node
      tail = tail.next; // tail now points to the last node
    }
    size++; // Increase size
  }


  @Override /** Add a new element at the specified index
   * in this list. The index of the head element is 0 */
  public void add(int index, E e) {
    if (index == 0) {
      addFirst(e);
    }
    else if (index >= size) {
      addLast(e);
    }
    else {
      Node<E> current = head;
      for (int i = 1; i < index; i++) {
        current = current.next;
      }
      Node<E> temp = current.next;
      current.next = new Node<E>(e);
      (current.next).next = temp;
      size++;
    }
  }

  /** Remove the head node and
   *  return the object that is contained in the removed node. */
  public E removeFirst() {
    if (size == 0) {
      return null;
    }
    else {
      Node<E> temp = head;
      head = head.next;
      size--;
      if (head == null) {
        tail = null;
      }
      return temp.element;
    }
  }

  /** Remove the last node and
   * return the object that is contained in the removed node. */
  public E removeLast() {
    if (size == 0) {
      return null;
    }
    else if (size == 1) {
      Node<E> temp = head;
      head = tail = null;
      size = 0;
      return temp.element;
    }
    else {
      Node<E> current = head;

      for (int i = 0; i < size - 2; i++) {
        current = current.next;
      }

      Node<E> temp = tail;
      tail = current;
      tail.next = null;
      size--;
      return temp.element;
    }
  }

  @Override /** Remove the element at the specified position in this
   *  list. Return the element that was removed from the list. */
  public E remove(int index) {
    if (index < 0 || index >= size) {
      return null;
    }
    else if (index == 0) {
      return removeFirst();
    }
    else if (index == size - 1) {
      return removeLast();
    }
    else {
      Node<E> previous = head;

      for (int i = 1; i < index; i++) {
        previous = previous.next;
      }

      Node<E> current = previous.next;
      previous.next = current.next;
      size--;
      return current.element;
    }
  }

  @Override /** Override toString() to return elements in the list */
  public String toString() {
    StringBuilder result = new StringBuilder("[");

    Node<E> current = head;
    if (size == 0)
    	return "[]";
    for (int i = 0; i < size; i++) {
      result.append(current.element);
      current = current.next;
      if (current != null) {
        result.append(", "); // Separate two elements with a comma
      }
      else {
        result.append("]"); // Insert the closing ] in the string
      }
    }

    return result.toString();
  }

  @Override /** Clear the list */
  public void clear() {
    size = 0;
    head = tail = null;
  }

  @Override /** Return true if this list contains the element e */
  public boolean contains(Object e) {
	  Node<E> current = head;
	  while (current!= null) {
		  if (current.element.equals(e))
		  	return true;
		  current = current.next;
	  }
	  return false;
  }

  @Override /** Return the element at the specified index */
  public E get(int index) {
    if (index < 0 || index >= size) return null;
    else {
		Node<E> current = head;
		int count = 0;
		while (current!= null) {
			if (count == index)
				return current.element;
			count++;
			current = current.next;
		}
	}
    return null;
  }

  @Override /** Return the index of the head matching element in
   *  this list. Return -1 if no match. */
  public int indexOf(Object e) {
  	Node<E> current = head;
    int count = 0;
	while (current!= null) {
		if (current.element.equals(e))
			return count;
		count++;
		current = current.next;
	}
	return -1;
  }

  @Override /** Return the index of the last matching element in
   *  this list. Return -1 if no match. */
  public int lastIndexOf(E e) {
   	Node<E> current = head;
   	int count = 0;
   	int index = 0;
   	boolean found = false;

   	while (current!= null) {
   		if (current.element.equals(e)) {
			found = true;
   			index = count;
		}
   		count++;
   		current = current.next;
   	}
   	if (found)
   		return index;
   	else
		return -1;
  }

  @Override /** Replace the element at the specified position
   *  in this list with the specified element. */
  public E set(int index, E e) {
	 if (index < 0 || index >= size) return null;
	 Node<E> replace = head;

	 for (int i = 0; i < index; i++) {
		replace = replace.next;
     }

     Node<E> temp = replace;
	 E s = temp.element;
	 Node<E> newNode = replace;
	 newNode.element = e;

	 return s;
  }

  @Override /** Override iterator() defined in Iterable */
  public java.util.Iterator<E> iterator() {
    return new LinkedListIterator();
  }

  /* Not used, but handy */
  private void checkIndex(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException
        ("Index: " + index + ", Size: " + size);
  }

  @Override
  public Object[] toArray() {
	  java.util.Iterator<E> iterator = iterator();
	  Object[] arr = new Object[10];
	  for (int i = 0; i < size; i++) {
		  if (size >= arr.length)
		  	arr = expandArray(arr);
		  arr[i] = iterator.next();
	  }
	  return arr;
  }

  @Override
  public <T> T[] toArray(T[] array) {
  	  java.util.Iterator<E> iterator = iterator();
	  int index = 0;
	  while (iterator.hasNext()) {
	 	 if (size >= array.length) {
	  		array = java.util.Arrays.copyOf(array, array.length + 10);
		}
	  	array[index] = (T)iterator.next(); //casting to T
	  	index++;
	  }
	  return array;
  }

  /* made this generic, but could replace with Object */
  private <T> T[] expandArray(T[] arr) {
  	T[] newArray = (T[])new Object[size * 2 + 1];
	System.arraycopy(arr, 0, newArray, 0, arr.length);
	arr = newArray;
	return arr;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
	  if (c.isEmpty())
	  	return true;
	  for (Object o: c) {
			if (!contains(o))
				return false;
	  }
	  for (E e: this) {
			if (!c.contains(e))
				return false;
	  }
	   return true;
  }//end containsAll

  @Override
  public boolean addAll(Collection<? extends E> c) {
	  for (Object element: c) {
		  this.addLast((E)element);
	  }
	  return true;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    for (Object o: c) {
	  if (this.contains(o)) {
	    remove(o);
	  }
	}
	return true;
  }

  @Override
   public boolean retainAll(Collection<?> c) {
	   for (E element: this) {
		  if (!c.contains(element))
		  	remove(element);
	   }
	   return true;
   }

  private class LinkedListIterator
      implements java.util.Iterator<E> {
    private Node<E> current = head; // Current index
	private Node<E> previous; //index of previous index

    @Override
    public boolean hasNext() {
      return (current != null);
    }

    @Override
    public E next() {
      E e = current.element;
      previous = current;
      current = current.next;
      return e;
    }

    @Override
    public void remove() {
		if (current == head) // next() has not been called yet
        throw new IllegalStateException();
		MyLinkedList.this.remove(previous.element); //remove(Object e) is implmenented in abstract class
    }
  }//end inner class LinkedListIterator

  private static class Node<E> {
    E element;
    Node<E> next;

    public Node(E element) {
      this.element = element;
    }
  }//end inner class Node
}//end class MyLinkedList

