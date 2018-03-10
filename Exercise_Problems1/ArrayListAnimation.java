/* interface, abstract class, and implementation is in this file
* for convenience, but there are separate files for each with
* more functionality
* this app simulates an arraylist */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class ArrayListAnimation extends Application {

	private MyList<String> list = new MyArrayList<>();

	private Button search;
	private Button insert;
	private Button delete;
	private Button trim;

	private TextField u_Value;
	private TextField u_Index;

	private int location = 200;
	private int capacity = MyArrayList.INITIAL_CAPACITY;

	public void trimToSize() { //for some reason trimToSize() won't work if it's not in interface or abstract class. That's why this is here
		if (capacity != list.size() && list.size() != 0) {
	    	capacity = list.size();
	    } // If size == capacity, no need to trim
  	}

	public HBox getBottom() {
		HBox bottom = new HBox(20);

		bottom.getChildren().clear();

		Label value = new Label("Enter a value: ");
		u_Value = new TextField();

		Label index = new Label("Enter an index: ");
		u_Index = new TextField();

		search = new Button("Search");
		insert = new Button("Insert");
		delete = new Button("Delete");
		trim = new Button("TrimToSize");

		bottom.getChildren().addAll(value, u_Value, index, u_Index, search, insert, delete, trim);

		bottom.setAlignment(Pos.CENTER);

		return bottom;
	}//end getBottom()

	public HBox getTop() {
		HBox top = new HBox(20);

		top.getChildren().clear();

		Label empty = new Label();

		if (list.isEmpty()) {
			empty.setText("ArrayList is Empty");
		}
		else {
			empty.setText("ArrayList is not Empty anymore");
		}

		empty.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));

		Label size_Capacity = new Label("size = " + Integer.toString(list.size()) + " " + "and capacity is " + Integer.toString(capacity));
		size_Capacity.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));

		top.getChildren().addAll(empty, size_Capacity);
		top.setAlignment(Pos.CENTER);

		return top;
	}

	public Pane makeBox() {
		if (list.size() == 0)
			delete.setDisable(true);
		else
			delete.setDisable(false);

		Pane shapes = new Pane();

		shapes.getChildren().clear();

		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).equals("")) {
				Rectangle box = new Rectangle(40, 40, 40, 40);
				box.setFill(Color.TRANSPARENT);
				box.setStroke(Color.BLACK);
				box.setStrokeWidth(2);
				Label text = new Label(list.get(i));
				text.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
				text.setTranslateX(location + 55);
				text.setTranslateY(250);
				box.setTranslateX(location);
				box.setTranslateY(200);
				location += 40;
				shapes.getChildren().addAll(box, text);
			}
		}
		for (int i = list.size(); i < capacity; i++) {
			Rectangle box = new Rectangle(40, 40, 40, 40);
			box.setFill(Color.TRANSPARENT);
			box.setStroke(Color.BLACK);
			box.setStrokeWidth(2);
			Line line = new Line(box.getX() + box.getWidth(), box.getY(), box.getX(), box.getY() + box.getHeight());
			box.setTranslateX(location);
			box.setTranslateY(200);
			line.setTranslateX(location);
			line.setTranslateY(200);
			location += 40;
			shapes.getChildren().addAll(box, line);
		}
		return shapes;
	}//end makeBox

	public Pane expand() {
		if (list.size() == 0)
			delete.setDisable(true);
		else
			delete.setDisable(false);

		Pane shapes = new Pane();

		shapes.getChildren().clear();

		int index = 0;

		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).equals("")) {
				Rectangle box = new Rectangle(40, 40, 40, 40);
				box.setFill(Color.TRANSPARENT);
				box.setStroke(Color.BLACK);
				box.setStrokeWidth(2);
				Label text = new Label(list.get(i));
				text.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
				text.setTranslateX(location + 55);
				text.setTranslateY(250);
				box.setTranslateX(location);
				box.setTranslateY(200);
				location += 40;
				shapes.getChildren().addAll(box, text);
				index++;
			}
		}
		for (int i = index; i < list.size() * 2 + 1; i++) {
			Rectangle box = new Rectangle(40, 40, 40, 40);
			box.setFill(Color.TRANSPARENT);
			box.setStroke(Color.BLACK);
			box.setStrokeWidth(2);
			Line line = new Line(box.getX() + box.getWidth(), box.getY(), box.getX(), box.getY() + box.getHeight());
			line.setTranslateX(location);
			line.setTranslateY(200);
			box.setTranslateX(location);
			box.setTranslateY(200);
			location += 40;
			shapes.getChildren().addAll(box, line);
		}
		capacity = (list.size() * 2 + 1);
		return shapes;
	}//end expand

	public Pane getLabel() {
		Pane pane = new Pane();

		Label index_Show = new Label();

		if (list.indexOf(u_Index.getText()) == -1)
			index_Show.setText("That is not in the ArrayList");
		else
			index_Show.setText(u_Index.getText() +  " is at index " + list.indexOf(u_Index.getText()));

		index_Show.setFont(Font.font("Time New Roman", FontWeight.BOLD, 20));
		index_Show.setTranslateX(0);
		index_Show.setTranslateY(400);

		pane.getChildren().add(index_Show);

		return pane;
	}

	@Override
	public void start(Stage primaryStage) {
		BorderPane user = new BorderPane();

		Label empty = new Label();
		Label size_Capacity = new Label();

		user.setBottom(getBottom());
		user.setTop(getTop());
		user.setCenter(makeBox());

		search.setOnAction( e -> {
			if (!u_Index.getText().equals("")) {
				user.setRight(getLabel());
			}
		});

		insert.setOnAction( e -> {
			String tokens[] = null;
			if (!u_Value.getText().equals("") && !u_Index.getText().equals(""))
				list.add(Integer.parseInt(u_Index.getText()), u_Value.getText());
			else if (!u_Value.getText().equals(""))
				list.add(u_Value.getText());
			if (list.size() > capacity) {
				location = 200;
				user.setCenter(expand());
				user.setTop(getTop());
			}
			else {
				location = 200;
				user.setCenter(makeBox());
				user.setTop(getTop());
			}
			u_Index.setText("");
		});

		delete.setOnAction( e -> {
			if (!u_Index.getText().equals(""))
				list.remove(Integer.parseInt(u_Index.getText()));
			else if (!u_Value.getText().equals(""))
				list.remove(u_Value.getText());
			location = 200;
			user.setCenter(makeBox());
			user.setTop(getTop());
		});

		trim.setOnAction( e -> {
			trimToSize();
			location = 200;
			user.setCenter(makeBox());
			user.setTop(getTop());
		});

		Scene scene = new Scene(user, 900, 500);
		primaryStage.setTitle("ArrayList Animation");
		primaryStage.setScene(scene);
		primaryStage.show();
	}//end start

public class MyArrayList<E> extends MyAbstractList<E> {
    public static final int INITIAL_CAPACITY = 4;
    private E[] data = (E[])new Object[INITIAL_CAPACITY];

    public int getCapacity() {
      return data.length;
    }

    /** Create a default list */
    public MyArrayList() {
    }

    /** Create a list from an array of objects */
    public MyArrayList(E[] objects) {
      for (int i = 0; i < objects.length; i++)
        add(objects[i]);
    }

    /** Add a new element at the specified index in this list */
    public void add(int index, E e) {
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

    /** Clear the list */
    public void clear() {
      data = (E[])new Object[INITIAL_CAPACITY];
      size = 0;
    }

    /** Return true if this list contains the element */
    public boolean contains(E e) {
      for (int i = 0; i < size; i++)
        if (e.equals(data[i])) return true;

      return false;
    }

    /** Return the element from this list at the specified index */
    public E get(int index) {
      return data[index];
    }

    /** Return the index of the first matching element in this list.
     *  Return -1 if no match. */
    public int indexOf(E e) {
      for (int i = 0; i < size; i++)
        if (e.equals(data[i])) return i;

      return -1;
    }

    /** Return the index of the last matching element in this list
     *  Return -1 if no match. */
    public int lastIndexOf(E e) {
      for (int i = size - 1; i >= 0; i--)
        if (e.equals(data[i])) return i;

      return -1;
    }

    /** Remove the element at the specified position in this list
     *  Shift any subsequent elements to the left.
     *  Return the element that was removed from the list. */
    public E remove(int index) {
      E e = data[index];

      // Shift data to the left
      for (int j = index; j < size - 1; j++)
        data[j] = data[j + 1];

      data[size - 1] = null; // This element is now null

      // Decrement size
      size--;

      return e;
    }

    /** Replace the element at the specified position in this list
     *  with the specified element. */
    public E set(int index, E e) {
      E old = data[index];
      data[index] = e;
      return old;
    }

    @Override /** Override toString() to return elements in the list */
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
      if (size != data.length) { // If size == capacity, no need to trim
        E[] newData = (E[])(new Object[size]);
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
      }
    }
  }

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

}//end class ArrayListAnimation
