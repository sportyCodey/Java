public class LinkedQueue<E> implements Queue<E> {
  private ListNode<E> front;
  private ListNode<E> back;
  private int size;

  public LinkedQueue() {
    front = null;
    back = null;
    size = 0;
  }
 
  public void add(E target) {
    ListNode<E> node = new ListNode<E>(target);
    if (isEmpty()) {
      front = node;
      back = node;
    } else {        
        back.setNext(node);
        back = node;
      }
      size++;
  }

  public E peek() {
    if (isEmpty())
      throw new EmptyStructureException();
      E result = back.getItem();
      return result;
  }

  public boolean isEmpty() {
    return front == null;
  }

  public int getSize() {
    return size;
  }

  public E remove() {
    if (isEmpty()) {
      throw new EmptyStructureException();
    }
    E result = front.getItem();
    front = front.getNext();
    size--;
    return result;
  }
}
