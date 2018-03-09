import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.text.*;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;
import javafx.util.Duration;

public class CountUpStopWatch extends Application {
	private Timeline countUp;

	private Text time;

	private int second = 0;
	private int minute = 0;
	private int hour = 0;

	private boolean initial = true;

	@Override
	public void start(Stage stage) {
		BorderPane pane = new BorderPane();

		Button start = new Button("Start");
		Button clear = new Button("Clear");

		start.setPrefHeight(100);
		start.setPrefWidth(100);
		start.setStyle("-fx-font-size: 25");

		clear.setPrefHeight(100);
		clear.setPrefWidth(100);
		clear.setStyle("-fx-font-size: 25");


		HBox buttons = new HBox(10, start, clear);
		buttons.setAlignment(javafx.geometry.Pos.CENTER);

		time = new Text("00:00:00");
		time.setFont(Font.font("Regular", FontWeight.BOLD, 70));

		pane.setBottom(buttons);
		pane.setCenter(time);

		Scene scene = new Scene(pane, 500, 500);
		stage.setTitle("CountUpStopWatch");
		stage.setScene(scene);
		stage.show();

		start.setOnAction(e -> {
			if (initial)
				goUp();
			if (start.getText().equals("Start")) {
				initial = false;
				countUp = new Timeline(new KeyFrame(Duration.millis(1000), event -> goUp()));
				countUp.setCycleCount(Timeline.INDEFINITE);
				countUp.play();
				start.setText("Pause");
			}
			else {
				countUp.pause();
				start.setText("Start");
			}
		});

		clear.setOnAction(e -> {
			second = 0;
			minute = 0;
			hour = 0;
			String output = String.format("%02d:%02d:%02d", hour, minute, second);
			time.setText(output);
			initial = true;
		});

	}//end start

	private void goUp() {
		if (second >= 60) {
			minute++;
			second = 0;
		}
		if (minute >= 60) {
			hour++;
			minute = 0;
		}
		String output = String.format("%02d:%02d:%02d", hour, minute, second);
		time.setText(output);
		second++;
	}

	public static void main(String[] args) {
		launch(args);
    }
}//end class