//interface for a List

import java.util.Collection;

public interface MyList<E> extends Collection<E> {
  /** Add a new element at the specified index in this list */
  public void add(int index, E e);

  /** Return the element from this list at the specified index */
  public E get(int index);

  /** Return the index of the first matching element in this list.
   *  Return -1 if no match. */
  public int indexOf(Object e);

  /** Return the index of the last matching element in this list
   *  Return -1 if no match. */
  public int lastIndexOf(E e);

  /** Remove the element at the specified position in this list
   *  Shift any subsequent elements to the left.
   *  Return the element that was removed from the list. */
  public E remove(int index);

  /** Replace the element at the specified position in this list
   *  with the specified element and returns the new set. */
  public E set(int index, E e);

  /** Add a new element at the end of this list. METHOD INHERITED FROM COLLECTION
  * default, so Override from Collection. We could put in abstract class if preferable*/
  @Override
  public default boolean add(E e) {
	add(size(), e);
    return true;
  }

  /* I put the other methods inherited from Collection to show what methods we are implementing */

  /** Return true if this list contains no elements. METHOD INHERITED FROM COLLECTION
  * Technically we don't have to have it here */
  public boolean isEmpty();

   /** Remove the first occurrence of the element e
   *  from this list. Shift any subsequent elements to the left.
   *  Return true if the element is removed. METHOD INHERITED FROM COLLECTION
   * Technically we don't have to have it here */
  public boolean remove(Object o);

  /* METHOD INHERITED FROM COLLECTION
   * Technically we don't have to have it here  */
  public boolean containsAll(Collection<?> c);

  /* METHOD INHERITED FROM COLLECTION
   * Technically we don't have to have it here  */
  public boolean addAll(Collection<? extends E> c);

  /* METHOD INHERITED FROM COLLECTION
   * Technically we don't have to have it here  */
  public boolean removeAll(Collection<?> c);

  /* METHOD INHERITED FROM COLLECTION
   * Technically we don't have to have it here  */
  public boolean retainAll(Collection<?> c);

  /* METHOD INHERITED FROM COLLECTION
   * Technically we don't have to have it here */
  public Object[] toArray();

  /* METHOD INHERITED FROM COLLECTION
   * Technically we don't have to have it here
   One use for it: copies content into other array */
  public <T> T[] toArray(T[] array);
}//end interface MyList

