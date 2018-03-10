/* click on balls to delete them. scroll the scroll bar to increase/decrease speed
* the rest is self-explanatory */
//this GUI app simulates bouncing balls that combine when they collide

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.util.*;

public class CombineBouncingBalls extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    MultipleBallPane ballPane = new MultipleBallPane();
    ballPane.setStyle("-fx-border-color: yellow");

    Button btAdd = new Button("+");
    Button btSubtract = new Button("-");
    Button suspend = new Button("Suspend");
    Button resume = new Button("Resume");
    HBox hBox = new HBox(10);
    hBox.getChildren().addAll(suspend, resume, btAdd, btSubtract);
    hBox.setAlignment(Pos.CENTER);

    // Add or remove a ball
    btAdd.setOnAction(e -> ballPane.add());
    btSubtract.setOnAction(e -> ballPane.subtract());

    // Pause and resume animation
   	suspend.setOnMousePressed(e -> ballPane.pause());
    resume.setOnMouseReleased(e -> ballPane.play());

    // Use a scroll bar to control animation speed
    ScrollBar sbSpeed = new ScrollBar();
    sbSpeed.setMax(20);
    sbSpeed.setValue(10);
    ballPane.rateProperty().bind(sbSpeed.valueProperty());

    BorderPane pane = new BorderPane();
    pane.setCenter(ballPane);
    pane.setTop(sbSpeed);
    pane.setBottom(hBox);

    // Create a scene and place the pane in the stage
    Scene scene = new Scene(pane, 250, 150);
    primaryStage.setTitle("MultipleBounceBall"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  private class MultipleBallPane extends Pane {
    private Timeline animation;

    public MultipleBallPane() {
      // Create an animation for moving the ball
      animation = new Timeline(
        new KeyFrame(Duration.millis(50), e -> moveBall()));
      animation.setCycleCount(Timeline.INDEFINITE);
      animation.play(); // Start animation
    }

    public void add() {
      Color color = new Color(Math.random(),
        Math.random(), Math.random(), 0.5);
      Ball ball = new Ball(30, 30, 20, color);
      ball.setOnMouseClicked(e-> getChildren().remove(ball));
      getChildren().add(ball);
    }

    public void subtract() {
      if (getChildren().size() > 0) {
        getChildren().remove(getChildren().size() - 1);
      }
    }

    public void play() {
      animation.play();
    }

    public void pause() {
      animation.pause();
    }

    public void increaseSpeed() {
      animation.setRate(animation.getRate() + 0.1);
    }

    public void decreaseSpeed() {
      animation.setRate(
        animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }

    public DoubleProperty rateProperty() {
      return animation.rateProperty();
    }

    protected void moveBall() {
      for (int i = 0; i < this.getChildren().size(); i++) {
        Ball ball = (Ball)this.getChildren().get(i);

        // Check boundaries
        if (ball.getCenterX() < ball.getRadius() ||
            ball.getCenterX() > getWidth() - ball.getRadius()) {
          ball.dx *= -1; // Change ball move direction
        }
        if (ball.getCenterY() < ball.getRadius() ||
            ball.getCenterY() > getHeight() - ball.getRadius()) {
          ball.dy *= -1; // Change ball move direction
        }

        // Adjust ball position
        ball.setCenterX(ball.dx + ball.getCenterX());
        ball.setCenterY(ball.dy + ball.getCenterY());

        for (int j = i + 1; j < this.getChildren().size(); j++) {
			Ball temp = (Ball)this.getChildren().get(j);
			if (ball.contains(temp.getCenterX(), temp.getCenterY())) {
				this.getChildren().remove(temp);
				ball.setRadius(temp.getRadius() + ball.getRadius());
			}
			/* even better 
			if (ball.intersects(temp.getBoundsInLocal())) {

			}
			*/
	    }
      }
    }
  }

  class Ball extends Circle {
    private double dx = 1, dy = 1;

    Ball(double x, double y, double radius, Color color) {
      super(x, y, radius);
      setFill(color); // Set ball color
    }
  }

  public static void main(String[] args) {
      launch(args);
  }
}//end class CombineBouncingBalls
