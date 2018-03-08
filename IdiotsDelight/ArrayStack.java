//Andrew Hudson
//700656832
//ash68320
//a4
//This program creates the class ArrayStack<E>

public class ArrayStack<E> implements Stack<E> {

  private E[] data;
  private int size;

  public ArrayStack() {
    data = (E[])(new Object[1]);
    size = 0;
  }

  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  protected boolean isFull() {
    return size == data.length;
  }
  
  public E peek() {
    if (isEmpty()) {
      throw new EmptyStructureException();
    }
    return data[size - 1];
  }

  public E pop() {
    if (isEmpty()) {
      throw new EmptyStructureException();
    } 
    size--;
    return data[size];
  }
  
  public void push(E target) {
    if (isFull()) {
      stretch();
    }
    data[size] = target;
    size++;
  }

  protected void stretch() {
    E[] newData = (E[])(new Object[data.length * 2]);
    for (int i = 0; i < data.length; i++) {
      newData[i] = data[i];
    }
    data = newData;
  }

}

