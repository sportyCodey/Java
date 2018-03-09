import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.util.Scanner;

public class InsidePolygon extends Application {
	Scanner input = new Scanner(System.in);
	@Override
	public void start(Stage stage) {
		Pane pane = new Pane();

		Points one = new Points();
		Points two = new Points();
		Points three = new Points();
		Points four = new Points();

		System.out.print("Enter polygon coordinates and location of dot: ");
		one.set();
		two.set();
		three.set();
		four.set();
		int locationX = input.nextInt();
		int locationY = input.nextInt();

		Polygon shape = new Polygon(one.getD1(), one.getD2(), two.getD1(), two.getD2(), three.getD1(), three.getD2(), four.getD1(), four.getD2());
		shape.setFill(Color.TRANSPARENT);
		shape.setStroke(Color.BLACK);

		Circle dot = new Circle(locationX, locationY, 5);

		Label text = new Label();
		if (shape.contains(locationX, locationY))
			text.setText("The point is inside the polygon");
		else
			text.setText("The point is not inside the polygon");
		text.setTranslateX(300);
		text.setTranslateY(300);

		pane.getChildren().addAll(shape, dot);

		Scene scene = new Scene(pane, 500, 500);
		stage.setTitle("InsidePolygon");
		stage.setScene(scene);
		stage.show();
	}//end start

	class Points {
		private double d1;
		private double d2;

		public Points() {
			d1 = 0;
			d2 = 0;
		}

		public void set() {
			d1 = input.nextDouble();
			d2 = input.nextDouble();
		}

		public double getD1() {
			return d1;
		}

		public double getD2() {
			return d2;
		}
	}

	public static void main(String[] args) {
	      launch(args);
  	}

}//end class