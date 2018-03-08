//Andrew Hudson

public interface Queue<E> {

  public void add(E target);

  public boolean isEmpty();

  public E remove();

  public E peek();

  public int getSize();

}
