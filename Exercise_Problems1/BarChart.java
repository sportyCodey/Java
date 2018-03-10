/* displays a bar char */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BarChart extends Application {
	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();

		Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE};
		int[] height = {200, 100, 300, 400};
		int[] y = {300, 400, 200, 100};
		String[] words = {"Project -- 20%", "Quiz -- 10%", "Midterm -- 30%", "Final -- 40%"};

		Rectangle bar;
		Text text;

		int x = 200;
		for (int i = 0; i < 4; i++, x += 100) {
			bar = new Rectangle(x, y[i], 80, height[i]);
			bar.setFill(colors[i]);
			text = new Text(x, y[i] - 15, words[i]);
			pane.getChildren().addAll(bar, text);
		}

		Scene scene = new Scene(pane, 500, 500);
		primaryStage.setTitle("BarChart");
		primaryStage.setScene(scene);
		primaryStage.show();
	}//end start

	public static void main(String[] args) {
	      launch(args);
    }
}//end class
