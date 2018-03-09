import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.*;
import javafx.scene.paint.Color;

public class UseRadioButtons extends Application {
	@Override
	public void start(Stage primaryStage) {
		BorderPane pane = new BorderPane();

		Button left = new Button("<=");
		Button right = new Button("=>");

		HBox buttons = new HBox(5, left, right);
		buttons.setAlignment(javafx.geometry.Pos.CENTER);

		Pane pt = new Pane();
		pt.setStyle("-fx-border-color: black");
		Text text = new Text(500, 350, "Programming is fun");
		text.setFont(Font.font("System", FontWeight.BOLD, 30));
		pt.getChildren().add(text);

		RadioButton rbRed = new RadioButton("Red");
		RadioButton rbYellow = new RadioButton("Yellow");
		RadioButton rbBlack = new RadioButton("Black");
		RadioButton rbOrange = new RadioButton("Orange");
		RadioButton rbGreen = new RadioButton("Green");

		HBox radioButtons = new HBox(5, rbRed, rbYellow, rbBlack, rbOrange, rbGreen);
		radioButtons.setAlignment(javafx.geometry.Pos.CENTER);

		ToggleGroup group = new ToggleGroup();
		rbRed.setToggleGroup(group);
		rbYellow.setToggleGroup(group);
		rbBlack.setToggleGroup(group);
		rbOrange.setToggleGroup(group);
		rbGreen.setToggleGroup(group);

		pane.setBottom(buttons);
		pane.setCenter(pt);
		pane.setTop(radioButtons);

		Scene scene = new Scene(pane, 500, 500);
		primaryStage.setTitle("UseRadioButtons");
		primaryStage.setScene(scene);
		primaryStage.show();

		left.setOnAction(e -> {
			checkBoundaries(text, pt);
			text.setX(text.getX() - 50);
		});

		right.setOnAction(e -> {
			checkBoundaries(text, pt);
			text.setX(text.getX() + 50);
		});

		rbRed.setOnAction(e -> {
			if (rbRed.isSelected())
				text.setFill(Color.RED);
		});

		rbYellow.setOnAction(e -> {
			if (rbYellow.isSelected())
				text.setFill(Color.YELLOW);
		});

		rbBlack.setOnAction(e -> {
			if (rbBlack.isSelected())
				text.setFill(Color.BLACK);
		});

		rbOrange.setOnAction(e -> {
			if (rbOrange.isSelected())
				text.setFill(Color.ORANGE);
		});

		rbGreen.setOnAction(e -> {
			if (rbGreen.isSelected())
				text.setFill(Color.GREEN);
		});
	}//end start

	private void checkBoundaries(Text text, Pane pane) {
		if (text.getX() + 300 >= pane.getWidth()) {
			text.setX(text.getX() - 50);
		}
		else if (text.getX() - 10 <= pane.getMinWidth()) {
			text.setX(text.getX() + 50);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}//end class