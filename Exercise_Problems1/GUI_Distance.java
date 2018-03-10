//GUI app that calculates the distance between two points

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.geometry.Point2D;

public class GUI_Distance extends Application {
	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();

		Circle one = new Circle(getRandom(), getRandom(), 15);
		Circle two = new Circle(getRandom(), getRandom(), 15);

		Line line = new Line(one.getCenterX(), one.getCenterY(), two.getCenterX(), two.getCenterY());
		line.setStrokeWidth(3);

		Point2D p1 = new Point2D(one.getCenterX(), one.getCenterY());
		Point2D p2 = new Point2D(two.getCenterX(), two.getCenterY());

		Text text = new Text(((line.getStartX() + line.getEndX()) / 2) + 10, ((line.getStartY() + line.getEndY()) / 2) + 10, Double.toString(p1.distance(p2)));

		pane.getChildren().addAll(one, two, line, text);

		Scene scene = new Scene(pane, 500, 500);
		primaryStage.setTitle("LineDistance");
		primaryStage.setScene(scene);
		primaryStage.show();
	}//end start

	public static int getRandom() {
		int random = (int)(Math.random() * 400);
		return random;
	}

	public static void main(String[] args) {
	      launch(args);
    }

}//end class
