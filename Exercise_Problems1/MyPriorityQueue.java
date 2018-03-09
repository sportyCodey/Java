import java.util.Comparator;

public class MyPriorityQueue<E> {
  private HeapWithComparator<E> heap;

  public MyPriorityQueue(Comparator<? super E> comparator) {
	  heap = new HeapWithComparator<>(comparator);
  }

  public void enqueue(E newObject) {
    heap.add(newObject);
  }

  public E dequeue() {
    return heap.remove();
  }

  public int getSize() {
    return heap.getSize();
  }
}

