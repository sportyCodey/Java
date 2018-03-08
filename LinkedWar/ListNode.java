//Andrew Hudson

public class ListNode<E> {
  private E item;
  private ListNode<E> next;

  public ListNode(E item) {
    this.item = item;
    next = null;
  }

  public ListNode(E item, ListNode<E> next) {
    this.item = item;
    this.next = next;
  }

  public E getItem() {
    return item;
  }

  public ListNode<E> getNext() {
    return next;
  }

  public void setItem(E item) {
    this.item = item;
  }

  public void setNext(ListNode<E> next) {
    this.next = next;
  }
}
