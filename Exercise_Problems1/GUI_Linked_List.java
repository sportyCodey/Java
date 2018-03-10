//GUI app that manipulates data using methods from a LinkedList

import java.util.*;
import java.util.Scanner;
import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class GUI_Linked_List extends Application {

	private TextField answer;
	private Button sort;
	private Button shuffle;
	private Button reverse;

	public static String input = "";
	public static int index = 0;
	public static int check[] = new int[10];

	@Override
	public void start(Stage primaryStage) {
		BorderPane pane = new BorderPane();

		LinkedList<Integer> linkedList = new LinkedList<>();
		ListIterator<Integer> listIterator = linkedList.listIterator();

		TextField tf = new TextField();
		tf.setMinSize(100, 200);
		tf.setEditable(false);

		pane.setBottom(getButtons());
		pane.setTop(getInfo());
		pane.setCenter(tf);

		answer.setOnMouseClicked( e -> {
			while (listIterator.hasNext()) {
				linkedList.remove();
			}
		});

		answer.setOnKeyPressed( e -> {
			switch (e.getCode()) {
			case ENTER: check[index] = Integer.parseInt(answer.getText());
						if (index % 10 == 0)
							check = extendArray(check);
						if (isDuplicate(check, index))
							System.out.println("There can be no duplicates. Enter another number.");
						else {
							linkedList.add(Integer.parseInt(answer.getText()));
							input += answer.getText() + " ";
							tf.setText(input);
							index++; break;
						}
			}
		});

		sort.setOnAction( e -> {
			Collections.sort(linkedList);
			tf.setText(String.valueOf(linkedList));
			for (int i = 0; i < check.length; i++) {
				check[i] = 0;
			}
			input = "";
			index = 0;
		});

		shuffle.setOnAction( e -> {
			Collections.shuffle(linkedList);
			tf.setText(String.valueOf(linkedList));
			for (int i = 0; i < check.length; i++) {
				check[i] = 0;
			}
			input = "";
			index = 0;
		});

		reverse.setOnAction( e -> {
			Collections.sort(linkedList, Collections.reverseOrder());
			tf.setText(String.valueOf(linkedList));
			for (int i = 0; i < check.length; i++) {
				check[i] = 0;
			}
			input = "";
			index = 0;
		});

		Scene scene = new Scene(pane, 700, 400);
		primaryStage.setTitle("GUI_Linked_List");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private HBox getButtons() {
		HBox hbox = new HBox(10);
		sort = new Button("Sort");
		reverse = new Button("Reverse");
		shuffle = new Button("Shuffle");
		hbox.getChildren().addAll(sort, reverse, shuffle);
		hbox.setAlignment(Pos.CENTER);

		return hbox;
	}

	private HBox getInfo() {
		HBox hbox = new HBox();
		hbox.getChildren().add(new Label("Enter a number and press Enter: "));

		answer = new TextField();
		hbox.getChildren().add(answer);
		hbox.setAlignment(Pos.CENTER);

		return hbox;
	}

	public static boolean isDuplicate(int array[], int index) {
		for (int i = 0; i < index + 1; i++) {
			for (int j = i + 1; j < index + 1; j++) {
				if (array[i] == array[j]) {
					return true;
				}
			}
		}
		return false;
	}

	public static int[] extendArray(int array[]) {
		int oldSize = array.length;
		int newArray[] = new int[oldSize + 10];

		for (int i = 0; i < oldSize; i++) {
		 	newArray[i] = array[i];
		}

		for (int i = 0; i < newArray.length; i++) {
			array = Arrays.copyOf(newArray, i + 2);
		}
		return array;
	}

	public static void main(String[] args) {
	      launch(args);
    }

}//end class GUI_Linked_List

	//The following is implemenation on the console:

	/*

	public static int[] extendArray(int array[]) {
		int oldSize = array.length;
		int newArray[] = new int[oldSize + 10];

		for (int i = 0; i < oldSize; i++) {
		 	newArray[i] = array[i];
		}

		for (int i = 0; i < newArray.length; i++) {
			array = Arrays.copyOf(newArray, i + 2);
		}
		return array;
	}

	public static boolean isDuplicate(int array[], int index) {
		for (int i = 0; i < index + 1; i++) {
			for (int j = i + 1; j < index + 1; j++) {
				if (array[i] == array[j]) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int num = -1;
		int index = 0;

		LinkedList<Integer> linkedList = new LinkedList<>();
		int check[] = new int[10];

		while (num != 0) {
			System.out.print("Enter numbers: ");
			num = input.nextInt();
			check[index] = num;
			if (index % 10 == 0)
				check = extendArray(check);
			if (isDuplicate(check, index))
				System.out.println("There can be no duplicates. Enter another number.");
			else {
				linkedList.add(num);
				index++;
			}
		}

		linkedList.removeLast();

		System.out.println("Sorted ");
		Collections.sort(linkedList);
		System.out.println(linkedList);

		System.out.println("Reverse");
		Collections.sort(linkedList, Collections.reverseOrder());
		System.out.println(linkedList);

		System.out.println("Shuffled");
		Collections.shuffle(linkedList);
		System.out.println(linkedList);

	}//end main
	*/
