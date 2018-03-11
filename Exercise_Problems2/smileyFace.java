//GUI app that displays a face

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class smileyFace extends Application{
	@Override
	public void start(Stage primaryStage){
		Pane pane = new Pane();

		//creates the head
		Circle head = new Circle(100, 100, 70);
		head.setFill(Color.WHITE);
		head.setStroke(Color.BLACK);

		//creates eye1
		Circle eye1 = new Circle(80, 70, 10);
		eye1.setFill(Color.WHITE);
		eye1.setStroke(Color.BLACK);

		//creates eye2
		Circle eye2 = new Circle(120, 70, 10);
		eye2.setFill(Color.WHITE);
		eye2.setStroke(Color.BLACK);

		//creates nose
		Circle nose = new Circle(100, 100, 5);
		nose.setFill(Color.WHITE);
		nose.setStroke(Color.BLACK);

		//creates mouth
		Circle mouth = new Circle(100, 140, 20);
		mouth.setFill(Color.WHITE);
		mouth.setStroke(Color.BLACK);

		//adds the shapes to the pane
		pane.getChildren().addAll(head, eye1, eye2, nose, mouth);

		Scene scene = new Scene(pane);
		primaryStage.setTitle("smileyFace");
		primaryStage.setScene(scene);
    		primaryStage.show();
	}

	public static void main(String[] args) {
	      launch(args);
    }
}//end class smileyFace
