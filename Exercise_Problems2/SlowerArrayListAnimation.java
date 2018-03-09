import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import java.util.ArrayList;

public class SlowerArrayListAnimation extends Application {
	private ArrayList<String> list = new ArrayList<>();

	private Button search;
	private Button insert;
	private Button delete;
	private Button trim;

	private TextField uValue;
	private TextField uIndex;

	private int capacity = 3;

	public HBox getBottom() {
		HBox bottom = new HBox(20);

		bottom.getChildren().clear();

		Label value = new Label("Enter a value: ");
		uValue = new TextField();
		uValue.setPrefColumnCount(5);

		Label index = new Label("Enter an index: ");
		uIndex = new TextField();
		uIndex.setPrefColumnCount(2);

		search = new Button("Search");
		insert = new Button("Insert");
		delete = new Button("Delete");
		trim = new Button("TrimToSize");

		bottom.getChildren().addAll(value, uValue, index, uIndex, search, insert, delete, trim);

		bottom.setAlignment(Pos.CENTER);

		return bottom;
	}//end getBottom()

	public HBox getTop() {
		HBox top = new HBox(20);

		top.getChildren().clear();

		Label empty = new Label();

		if (list.isEmpty())
			empty.setText("ArrayList is empty");
		else
			empty.setText("ArrayList not empty");

		empty.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));

		Label sizeCapacity = new Label("size = " + Integer.toString(list.size()) + " " + "and capacity is " + Integer.toString(capacity));
		sizeCapacity.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));

		top.getChildren().addAll(empty, sizeCapacity);
		top.setAlignment(Pos.CENTER);

		return top;
	}//end getTop()

	public Pane makeBox(int location, boolean expand, boolean insert, boolean delete, String deletion) {
		Pane shapes = new Pane();

		shapes.getChildren().clear();

		for (int i = 0; i < list.size(); i++, location += 40) {
			if (!list.get(i).equals("")) {
				Rectangle box = new Rectangle(40, 40, 40, 40);
				box.setFill(Color.TRANSPARENT);
				box.setStroke(Color.BLACK);
				box.setStrokeWidth(2);
				Text text = new Text(location + 55, 265, list.get(i));
				text.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
				box.setTranslateX(location);
				box.setTranslateY(200);
				shapes.getChildren().addAll(box, text);

				if (i == list.size() - 1 && insert) {
					Line pathTransition = new Line(location + 60, 150, location + 60, 260);
					PathTransition pt = new PathTransition(Duration.millis(1500), pathTransition, text);
					pt.setCycleCount(1);
					pt.play();
				}
			}
		}

		if (delete) {
			Text text = new Text(location + 55, 265, deletion);
			text.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
			shapes.getChildren().add(text);
			FadeTransition ft = new FadeTransition(Duration.millis(1500), text);
			ft.setFromValue(1.0);
			ft.setToValue(0.0);
			ft.setCycleCount(1);
			ft.play();
		}

		int end = capacity;
		if (expand) {
			end = list.size() * 2 + 1;
			capacity = (list.size() * 2 + 1);
		}

		for (int i = list.size(); i < end; i++, location += 40) {
			Rectangle box = new Rectangle(40, 40, 40, 40);
			box.setFill(Color.TRANSPARENT);
			box.setStroke(Color.BLACK);
			box.setStrokeWidth(2);
			Line line = new Line(box.getX() + box.getWidth(), box.getY(), box.getX(), box.getY() + box.getHeight());
			box.setTranslateX(location);
			box.setTranslateY(200);
			line.setTranslateX(location);
			line.setTranslateY(200);
			shapes.getChildren().addAll(box, line);
		}
		return shapes;
	}//end makeBox()

	public Pane getLabel() {
		Pane pane = new Pane();

		Label index_Show = new Label();

		if (list.indexOf(uIndex.getText()) == -1)
			index_Show.setText("That is not in the ArrayList");
		else
			index_Show.setText(uIndex.getText() +  " is at index " + list.indexOf(uIndex.getText()));

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
		Label sizeCapacity = new Label();

		user.setBottom(getBottom());
		user.setTop(getTop());
		user.setCenter(makeBox(200, false, false, false, null));

		search.setOnAction( e -> {
			if (!uIndex.getText().equals("")) {
				user.setRight(getLabel());
			}
		});

		insert.setOnAction( e -> {
			if (!uValue.getText().equals("") && !uIndex.getText().equals(""))
				list.add(Integer.parseInt(uIndex.getText()), uValue.getText());
			else if (!uValue.getText().equals(""))
				list.add(uValue.getText());
			if (list.size() > capacity) {
				user.setCenter(makeBox(200, true, true, false, null));
				user.setTop(getTop());
			}
			else {
				user.setCenter(makeBox(200, false, true, false, null));
				user.setTop(getTop());
			}
			uIndex.setText("");
		});

		delete.setOnAction( e -> {
			if (!list.isEmpty()) {
				String deletion = "";
				if (!uIndex.getText().equals(""))
					deletion = list.remove(Integer.parseInt(uIndex.getText()));
				else if (!uValue.getText().equals("")) {
					deletion = uValue.getText();
					list.remove(uValue.getText());
				}
				user.setCenter(makeBox(200, false, false, true, deletion));
				user.setTop(getTop());
			}
		});

		trim.setOnAction( e -> {
			list.trimToSize();
			if (!list.isEmpty())
				capacity = list.size();
			user.setCenter(makeBox(200, false, false, false, null));
			user.setTop(getTop());
		});

		Scene scene = new Scene(user, 900, 500);
		primaryStage.setTitle("ArrayList Animation");
		primaryStage.setScene(scene);
		primaryStage.show();
	}//end start

	public static void main(String[] args) {
		launch(args);
	}
}//end class SlowerArrayListAnimation
