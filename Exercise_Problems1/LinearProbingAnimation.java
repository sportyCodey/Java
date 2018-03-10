//GUI app that simulates linear probing hashing

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
import javafx.geometry.*;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class LinearProbingAnimation extends Application {
	int capacity = 11;

	java.util.ArrayList<Integer> valueForX = new java.util.ArrayList<>();

	MyHashMapWithLinearProbing<Integer, Integer> map = new MyHashMapWithLinearProbing<>(capacity);

	final static int MAXIMUM_CAPACITY = 1 << 30;

	double loadFactor = 0.0;

	boolean initial = false;

	Label warning = new Label();

	@Override
	public void start(Stage stage) {
		initializeValueForX();

		TextField tfInput = new TextField();
		tfInput.setPrefColumnCount(2);

		TextField vInput = new TextField();
		vInput.setPrefColumnCount(2);

		Button insert = new Button("Insert");
		Button delete = new Button("Delete");
		Button removeAll = new Button("Remove All");
		Button reset = new Button("Reset");

		TextField search = new TextField();
		search.setPrefColumnCount(2);

		HBox ui = new HBox(20, new Label("Enter initial table size. Press Enter: "), tfInput, new Label("Enter a value: "), vInput,
		insert, delete, removeAll, new Label("Search a key. Press Enter: "), search, reset);
		ui.setAlignment(Pos.CENTER);
		ui.setStyle("-fx-border-color: red; -fx-padding: 2");

		BorderPane pane = new BorderPane();
		pane.setCenter(new ScrollPane(getRectangle()));
		pane.setTop(getLabels());
		pane.setBottom(ui);

		Scene scene = new Scene(pane, 500, 500);
		stage.setTitle("LinearProbingAnimation");
		stage.setScene(scene);
		stage.show();


		tfInput.setOnKeyPressed(e -> {
			switch(e.getCode()) {
				case ENTER: if (tfInput.getText().equals("")) {
								setText("     No value inputted");
							}
							else {
								if (initial) {
									setText("     Please press reset first");
									vInput.setText("");
									tfInput.setText("");
									search.setText("");
								}
								else {
									try {
										capacity = Math.abs(Integer.parseInt(tfInput.getText()));
										pane.setCenter(new ScrollPane(getRectangle()));
										initial = true;
									}
									catch (NumberFormatException ex) {
										setText("     Enter integers only");
									}
									tfInput.setText("");
									vInput.setText("");
									search.setText("");
								}
							}
			}
		});

		search.setOnKeyPressed(e -> {
			switch(e.getCode()) {
				case ENTER: if (search.getText().equals("")) {
								setText("     No value inputted");
							}
							else {
								try {
									int value = Math.abs(Integer.parseInt(search.getText()));
									if (map.containsKey(value)) {
										setText("     " + Integer.toString(value) + " is in the hash set");
									}
									else {
										setText("     " + Integer.toString(value) + " is not in the hash set");
									}
								}
								catch (NumberFormatException ex) {
									setText("     Enter integers only");
								}
								vInput.setText("");
								tfInput.setText("");
								search.setText("");
							}
			}
		});

		reset.setOnAction(e -> {
			initial = false;
			capacity = 11;
			map = new MyHashMapWithLinearProbing<>(capacity);
			valueForX = new java.util.ArrayList<>();
			initializeValueForX();
			pane.setCenter(new ScrollPane(getRectangle()));
			pane.setTop(getLabels());
			vInput.setText("");
			tfInput.setText("");
			search.setText("");
		});

		insert.setOnAction(e -> {
			if (vInput.getText().equals("")) {
				setText("     No value inputted");
			}
			else {
				try {
					int value = Math.abs(Integer.parseInt(vInput.getText()));
					if (map.containsKey(value)) {
						setText("     key " + Integer.toString(value) + " is already in the hash set");
						vInput.setText("");
						tfInput.setText("");
						search.setText("");
						return;
					}
					int hashCode = map.putAndGetHashCode(value, -1);
					if (valueForX.contains(hashCode)) {
						valueForX.remove(hashCode);
						valueForX.add(hashCode, -1);
					}
					calculateLoadFactor();
					if (loadFactor > 0.5) {
						if (capacity == MAXIMUM_CAPACITY)
							throw new RuntimeException("Exceeding maximum capacity");
						capacity *= 2;
						calculateLoadFactor();
						initializeValueForX();
					}
					pane.setCenter(new ScrollPane(getRectangle()));
					pane.setTop(getLabels());
					initial = true;
				}
				catch (NumberFormatException ex) {
					setText("     Enter integers only");
				}
				vInput.setText("");
				tfInput.setText("");
				search.setText("");
			}
		});

		delete.setOnAction(e -> {
			if (vInput.getText().equals("")) {
				setText("     No value inputted");
			}
			else {
				try {
					int value = Math.abs(Integer.parseInt(vInput.getText()));
					if (map.containsKey(value)) {
						int hashCode = map.removeAndGetHashCode(value);
						int index = -1;
						valueForX.add(hashCode, hashCode);
						for (int i = hashCode + 1; i < valueForX.size(); i++) {
							if (valueForX.get(i) == -1) {
								index = i;
								break;
							}
						}
						if (index != -1) valueForX.remove(index);
						pane.setCenter(new ScrollPane(getRectangle()));
						calculateLoadFactor();
						pane.setTop(getLabels());
					}
					else
						setText("     key " + Integer.toString(value) + " is not in the hash set");
				}
				catch (NumberFormatException ex) {
					setText("     Enter integers only");
				}
				vInput.setText("");
				tfInput.setText("");
				search.setText("");
			}
		});

		removeAll.setOnAction(e -> {
			map.clear();
			initializeValueForX();
			pane.setCenter(new ScrollPane(getRectangle()));
			calculateLoadFactor();
			pane.setTop(getLabels());
			vInput.setText("");
			tfInput.setText("");
			search.setText("");
		});
	}//end start

	private void initializeValueForX() {
		for (int i = 0; i < capacity; i++) {
			valueForX.add(i, -1);
		}
	}

	private void setText(String text) {
		warning.setText(text);
		warning.setFont(Font.font("System", FontWeight.BOLD, 30));
		playAnimation();
	}

	private void calculateLoadFactor() {
		loadFactor = map.size() / (double)capacity;
	}

	private void playAnimation() {
		FadeTransition ft = new FadeTransition(Duration.millis(8000), warning);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
	}

	public Pane getRectangle() {
		Pane pane = new Pane();

		pane.getChildren().clear();

		Rectangle r;

		int location = 35;

		for (int i = 0; i < capacity; i++, location += 35) {
			/* create rectangles */
			r = new Rectangle(65, 35, Color.TRANSPARENT);
			r.setStroke(Color.BLACK);
			r.setStrokeWidth(3);
			r.setTranslateX(50);
			r.setTranslateY(location);

			/* create numbers on side */
			Text number = new Text(10, location + (r.getHeight() / 2) + 5, "[" + Integer.toString(i) + "]");
			number.setFont(new Font(20));

			pane.getChildren().addAll(r, number);
		}

		location = 35;

		/* everything below is very dirty way to program */
		java.util.ArrayList<Object> list = map.getNullList();

		Text text;
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).equals(-1)) {
				Integer key = (Integer)list.get(i);
				text = new Text(75, location + 25, Integer.toString(key));
				text.setFont(new Font(25));
				pane.getChildren().add(text);
			}
			if (valueForX.get(i) == i) {
				text = new Text(75, location + 25, "X");
				text.setFont(new Font(25));
				pane.getChildren().add(text);
			}
			location += 35;
		}
		return pane;
	}

	public VBox getLabels() {
		VBox pane = new VBox();

		pane.getChildren().clear();

		Label sizeAndKeys = new Label("\tTable size = " + capacity + ". Number of keys = " + map.size());
		sizeAndKeys.setFont(new Font(20));

		String s = String.format("%.2f", loadFactor);
		Label lf_lft = new Label("\tLoad factor = " + s + ". Load factor threshhold = 0.50");
		lf_lft.setFont(new Font(20));

		warning.setText("");
		warning.setFont(Font.font("System", FontWeight.BOLD, 30));

		pane.getChildren().addAll(sizeAndKeys, lf_lft, warning);

		return pane;
	}

	public static void main(String[] args) {
		launch(args);
    }
}//end class
