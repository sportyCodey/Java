//GUI app that lets user change the color of the square

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SquareChangerStudent extends Application {
  private SquarePane squarePane = new SquarePane();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Hold two buttons in an HBox
    HBox hBox = new HBox();
    hBox.setSpacing(10);
    Button btRed = new Button("Red");
    Button btBlue = new Button("Blue");
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().add(btRed);
    hBox.getChildren().add(btBlue);

    // Create and register the handlers for each button.
    btRed.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			squarePane.redColor();
		}

	});

	btBlue.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			squarePane.blueColor();
		}

	});
    // You need a handler for btRed and another handler for btBlue

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(squarePane);
    borderPane.setBottom(hBox);
    BorderPane.setAlignment(hBox, Pos.CENTER);

    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 200, 150);
    primaryStage.setTitle("Square Color Changer"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  public static void main(String[] args) {
    launch(args);
  }
}

class SquarePane extends StackPane {
  private Rectangle rectangle = new Rectangle(20,20,50,50);

  public SquarePane() {
    getChildren().add(rectangle);
    rectangle.setStroke(Color.BLACK);
    rectangle.setFill(Color.WHITE);
  }

  public void changeColor(Color c) {
    rectangle.setFill(c);
  }

  public void redColor(){
	  rectangle.setFill(Color.RED);
  }

  public void blueColor() {
	  rectangle.setFill(Color.BLUE);
  }

}
