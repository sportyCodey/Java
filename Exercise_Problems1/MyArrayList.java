import java.util.Collection;

public class MyArrayList<E> extends MyAbstractList<E> {
  public static final int INITIAL_CAPACITY = 3;
  private E[] data = (E[])new Object[INITIAL_CAPACITY];
  private int size = 0; // Number of elements in the list

  /** Create an empty list */
  public MyArrayList() {
  }

  /** Create a list from an array of objects */
  public MyArrayList(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      add(objects[i]); // Warning: don’t use super(objects)!
  }

  @Override /** Add a new element at the specified index */
  public void add(int index, E e) {
    // Ensure the index is in the right range
    if (index < 0 || index > size)
      throw new IndexOutOfBoundsException
        ("Index: " + index + ", Size: " + size);

    ensureCapacity();

    // Move the elements to the right after the specified index
    for (int i = size - 1; i >= index; i--)
      data[i + 1] = data[i];

    // Insert new element to data[index]
    data[index] = e;

    // Increase size by 1
    size++;
  }

  /** Create a new larger array, double the current size + 1 */
  private void ensureCapacity() {
    if (size >= data.length) {
      E[] newData = (E[])(new Object[size * 2 + 1]);
      System.arraycopy(data, 0, newData, 0, size);
      data = newData;
    }
  }

  @Override /** Clear the list */
  public void clear() {
    data = (E[])new Object[INITIAL_CAPACITY];
    size = 0;
  }

  @Override /** Return true if this list contains the element */
  public boolean contains(Object e) {
    for (int i = 0; i < size; i++)
      if (e.equals(data[i])) return true;

    return false;
  }

  @Override /** Return the element at the specified index */
  public E get(int index) {
    checkIndex(index);
    return data[index];
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException
        ("Index: " + index + ", Size: " + size);
  }

  @Override /** Return the index of the first matching element
   *  in this list. Return -1 if no match. */
  public int indexOf(Object e) {
    for (int i = 0; i < size; i++)
      if (e.equals(data[i])) return i;

    return -1;
  }

  @Override /** Return the index of the last matching element
   *  in this list. Return -1 if no match. */
  public int lastIndexOf(E e) {
    for (int i = size - 1; i >= 0; i--)
      if (e.equals(data[i])) return i;

    return -1;
  }

  @Override /** Remove the element at the specified position
   *  in this list. Shift any subsequent elements to the left.
   *  Return the element that was removed from the list. */
  public E remove(int index) {
    checkIndex(index);

    E e = data[index];

    // Shift data to the left
    for (int j = index; j < size - 1; j++)
      data[j] = data[j + 1];

    data[size - 1] = null; // This element is now null

    // Decrement size
    size--;

    return e;
  }

  @Override /** Replace the element at the specified position
   *  in this list with the specified element. */
  public E set(int index, E e) {
    checkIndex(index);
    E old = data[index];
    data[index] = e;
    return old;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("[");

    for (int i = 0; i < size; i++) {
      result.append(data[i]);
      if (i < size - 1) result.append(", ");
    }

    return result.toString() + "]";
  }

  /** Trims the capacity to current size */
  public void trimToSize() {
    if (size != data.length) {
      E[] newData = (E[])(new Object[size]);
      System.arraycopy(data, 0, newData, 0, size);
      data = newData;
    } // If size == capacity, no need to trim
  }

  @Override
  public boolean containsAll(Collection<?> c) {
	if (c.isEmpty())
		return true;
    for (E data: this) {
		if (!c.contains(data))
			return false;
	}
	for (Object o: c) {
		if(!this.contains(o))
			return false;
	}
    return true;
  }

   @Override
   public Object[] toArray() {
  	 Object[] arr = new Object[10];
	 for (int i = 0; i < size; i++) {
	   if (size >= arr.length)
	     arr = expandArray(arr);
	   arr[i] = data[i];
	  }
  	  return arr;
  }

  private Object[] expandArray(Object[] arr) {
    Object[] newArray = new Object[size * 2 + 1];
  	System.arraycopy(arr, 0, newArray, 0, arr.length);
  	arr = newArray;
  	return arr;
  }

  @Override
  public <T> T[] toArray(T[] array) {
	  for (int i = 0; i < size; i++) {
		  if (size >= array.length)
		  	array = java.util.Arrays.copyOf(array, array.length + 10);
		  array[i] = (T)data[i]; //cast to T
	  }
	  return array;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
	  for (int i = 0; i < size; i++) {
		  if (!c.contains(data[i]))
		    remove(data[i]);
		    retainAll(c);
	  }
	  return true;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
	for (int i = 0; i < data.length; i++) {
		if (data[i] == null)
			break;
		if (c.contains(data[i])) {
			remove(data[i]);
			removeAll(c); //recursively call removeAll because elements are shifted to the left after each removal
		}
	}
	return true;
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
	  for (Object element: c) {
		  this.add(size, (E)element);
	  }
	  return true;
  }

  @Override /** Return the number of elements in this list */
  public int size() {
    return size;
  }

  @Override /** Override iterator() defined in Iterable */
  public java.util.Iterator<E> iterator() {
    return new ArrayListIterator();
  }

  private class ArrayListIterator
      implements java.util.Iterator<E> {
    private int current = 0; // Current index

    @Override
    public boolean hasNext() {
      return current < size;
    }

    @Override
    public E next() {
      return data[current++];
    }

    @Override // Remove the element returned by the last next()
    public void remove() {
      if (current == 0) // next() has not been called yet
      throw new IllegalStateException();
      MyArrayList.this.remove(--current);
    }
  }//end inner class ArrayListIterator
}//end class MyArrayList

