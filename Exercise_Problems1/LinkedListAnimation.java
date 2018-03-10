/* this file has the interface, abstract class, and implementation file for convenience,
* but there are separate files with more functionality posted */
//GUI app that simulates a LinkedList

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.text.*;

public class LinkedListAnimation extends Application {

	private MyLinkedList<String> list = new MyLinkedList<>();

	private Button search;
	private Button insert;
	private Button delete;

	private TextField v_User;
	private TextField i_User;

	public Pane getShapes() {
		Pane shapes = new Pane();

		java.util.Iterator<String> loop = list.iterator();

		int locationX = 100;

		if (list.isEmpty()) {
			Label head = new Label("Head: null");
			head.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
			head.setTranslateX(100 - 25);
			head.setTranslateY(235);

			Label tail = new Label("Tail: null");
			tail.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
			tail.setTranslateX(locationX + 60);
			tail.setTranslateY(235);

			shapes.getChildren().addAll(head, tail);
		}

		for (int i = 0; i < list.size(); i++) {

			Label head = new Label("Head");
			head.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
			head.setTranslateX(100 - 25);
			head.setTranslateY(235);

			Label tail = new Label("Tail");
			tail.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
			tail.setTranslateX(locationX + 40);
			tail.setTranslateY(235);

			Line h_Spine = new Line(100 - 5, 265, 100 + 10, 300);
			Line h_Left = new Line(100 + 10, 300, 100 - 5, 290);
			Line h_Right = new Line(100 + 10, 300, 100 + 20, 290);

			Line t_Spine = new Line(locationX + 50, 265,  locationX + 35, 300);
			Line t_Left = new Line(locationX + 35, 300, locationX + 25, 290);
			Line t_Right = new Line(locationX + 35, 300, locationX + 55, 290);

			if (list.size() - 1 == i) {
				t_Spine.setVisible(true);
				t_Left.setVisible(true);
				t_Right.setVisible(true);
				tail.setVisible(true);
			}
			else {
				t_Spine.setVisible(false);
				t_Left.setVisible(false);
				t_Right.setVisible(false);
				tail.setVisible(false);
			}

			Rectangle big_Box = new Rectangle(60, 25);
			big_Box.setFill(Color.TRANSPARENT);
			big_Box.setStroke(Color.BLACK);
			big_Box.setTranslateX(locationX);
			big_Box.setTranslateY(300);

			Rectangle small_Box = new Rectangle(25, 25);
			small_Box.setFill(Color.TRANSPARENT);
			small_Box.setStroke(Color.GRAY);
			small_Box.setTranslateX(locationX + 60);
			small_Box.setTranslateY(300);

			Line p_Spine = new Line(locationX + 74, 314, locationX + 120, 314);
			Line p_Left = new Line(locationX + 120, 314, locationX + 104, 300);
			Line p_Right = new Line(locationX + 120, 314, locationX + 105, 325);

			if (i == list.size() - 1) {
				p_Spine.setVisible(false);
				p_Left.setVisible(false);
				p_Right.setVisible(false);
			}

			Label text = new Label(loop.next());
			text.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
			text.setTranslateX(locationX + 25);
			text.setTranslateY(300);

			shapes.getChildren().addAll(big_Box, small_Box, p_Spine, p_Left, p_Right, text, t_Spine, t_Left, t_Right, h_Spine, h_Left, h_Right, head, tail);

			locationX += 121;
		}
		return shapes;
	}//end getShapes

	public HBox getBottom() {
		HBox bottom = new HBox(20);

		search = new Button("Search");
		insert = new Button("Insert");
		delete = new Button("Delete");

		Label value = new Label("Enter a value: ");
		value.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));

		Label index = new Label("Enter an index: ");
		index.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));

		v_User=  new TextField();
		i_User = new TextField();

		bottom.getChildren().addAll(value, v_User, index, i_User, search, insert, delete);
		bottom.setAlignment(Pos.CENTER);

		return bottom;
	}

	@Override
	public void start(Stage primaryStage) {
		BorderPane user = new BorderPane();

		user.setBottom(getBottom());
		user.setCenter(getShapes());

		search.setOnAction( e -> {
			Label info = new Label();

			Pane pane = new HBox();

			if (!v_User.getText().equals("")) {
				if (list.indexOf(v_User.getText()) == -1)
					info.setText(v_User.getText() + " was not found");
				else
					info.setText(v_User.getText() + " is at " + list.indexOf(v_User.getText()));
				}
			info.setFont(Font.font("Time New Roman", FontWeight.BOLD, 25));
			info.setTranslateX(-100);
			info.setTranslateY(400);

			pane.getChildren().add(info);

			user.setRight(pane);
		});

		insert.setOnAction( e -> {
			if (!i_User.getText().equals("") && !v_User.getText().equals("")) {
				list.add(Integer.parseInt(i_User.getText()), v_User.getText());
				i_User.setText("");
			}
			else if (!v_User.getText().equals(""))
					list.add(v_User.getText());
				user.setCenter(getShapes());
		});

		delete.setOnAction( e -> {
			if (!i_User.getText().equals("")) {
				list.remove(Integer.parseInt(i_User.getText()));
				user.setCenter(getShapes());
				i_User.setText("");
			}
			else if(!v_User.getText().equals("")) {
				list.remove(v_User.getText());
				user.setCenter(getShapes());
			}
		});

		Scene scene = new Scene(user, 500, 500);
		primaryStage.setTitle("LinkedListAnimation");
		primaryStage.setScene(scene);
		primaryStage.show();

	}//end start

	public abstract class MyAbstractList<E> implements MyList<E> {
	  protected int size = 0; // The size of the list

	  /** Create a default list */
	  protected MyAbstractList() {
	  }

	  /** Create a list from an array of objects */
	  protected MyAbstractList(E[] objects) {
	    for (int i = 0; i < objects.length; i++)
	      add(objects[i]);
	  }

	  /** Add a new element at the end of this list */
	  public void add(E e) {
	    add(size, e);
	  }

	  /** Return true if this list contains no elements */
	  public boolean isEmpty() {
	    return size == 0;
	  }

	  /** Return the number of elements in this list */
	  public int size() {
	    return size;
	  }

	  /** Remove the first occurrence of the element o from this list.
	   *  Shift any subsequent elements to the left.
	   *  Return true if the element is removed. */
	  public boolean remove(E e) {
	    if (indexOf(e) >= 0) {
	      remove(indexOf(e));
	      return true;
	    }
	    else
	      return false;
	  }
	}

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
	   	int index = -1;

	   	while (current!= null) {
	   		if (current.element.equals(e))
	   			index = count;
	   		count++;
	   		current = current.next;
	   		if (current == null)
	   			return index;
	   	}
		return -1;
	  }

	  @Override /** Replace the element at the specified position
	   *  in this list with the specified element. */
	  public E set(int index, E e) {
		 Node<E> replacement = head;

		if (index == 0) {
			Node<E> temp = head;
			E r = head.element;
			replacement.element = e;
		 	return r;
		}
		else {
			for (int i = 1; i < index; i++) {
			 	replacement = replacement.next;
			}

			Node<E> temp = replacement.next;
			E r = temp.element;
			Node<E> newNode = replacement.next;
			newNode.element = e;

			return r;
		}
	  }

	 // @Override /** Override iterator() defined in Iterable */
	  public java.util.Iterator<E> iterator() {
	    return new LinkedListIterator();
	  }

	  private void checkIndex(int index) {
	    if (index < 0 || index >= size)
	      throw new IndexOutOfBoundsException
	        ("Index: " + index + ", Size: " + size);
	  }

	  public class LinkedListIterator
	      implements java.util.Iterator<E> {
	    private Node<E> current = head; // Current index

	    @Override
	    public boolean hasNext() {
	      return (current != null);
	    }

	    @Override
	    public E next() {
	      E e = current.element;
	      current = current.next;
	      return e;
	    }

	    @Override
	    public void remove() {
	     // System.out.println("Implementation left as an exercise");
	    }
	  }

	  class Node<E> {
	    E element;
	    Node<E> next;

	    public Node(E element) {
	      this.element = element;
	    }
	  }
	}
	public interface MyList<E> {
	    /** Add a new element at the end of this list */
	    public void add(E e);

	    /** Add a new element at the specified index in this list */
	    public void add(int index, E e);

	    /** Clear the list */
	    public void clear();

	    /** Return true if this list contains the element */
	    public boolean contains(E e);

	    /** Return the element from this list at the specified index */
	    public E get(int index);

	    /** Return the index of the first matching element in this list.
	     *  Return -1 if no match. */
	    public int indexOf(E e);

	    /** Return true if this list contains no elements */
	    public boolean isEmpty();

	    /** Return the index of the last matching element in this list
	     *  Return -1 if no match. */
	    public int lastIndexOf(E e);

	    /** Remove the first occurrence of the element o from this list.
	     *  Shift any subsequent elements to the left.
	     *  Return true if the element is removed. */
	    public boolean remove(E e);

	    /** Remove the element at the specified position in this list
	     *  Shift any subsequent elements to the left.
	     *  Return the element that was removed from the list. */
	    public E remove(int index);

	    /** Replace the element at the specified position in this list
	     *  with the specified element and returns the new set. */
	    public Object set(int index, E e);

	    /** Return the number of elements in this list */
	    public int size();
	  }

	  public static void main(String[] args) {
	      launch(args);
  	  }
}//end class
