//class that creates the bean machine and logic

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.geometry.Point2D;

public class BeanMachine extends Group {
	private Timeline animation = new Timeline(new KeyFrame(Duration.millis(100), e -> dropBalls()));

	private java.util.ArrayList<Circle> list = new java.util.ArrayList<>();

	private Circle[] balls = new Circle[10];

	private boolean[] allDone = new boolean[10];

	public BeanMachine() {
		makeMachine();
		makeBalls();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
	}

	public void makeMachine() {
		Polyline body = new Polyline(300, 100, 300, 150, 100, 500, 100, 600, 550, 600, 550, 500, 350, 150, 350, 100);
		getChildren().add(body);

		int counter = 1;
		int locationX = 200;
		int locationY = 200;
		for (int i = 0; i < 7; i++) {
			switch(i) {
				case 0: locationX = 325; break;
				case 1: locationX = 300; break;
				case 2: locationX = 275; break;
				case 3: locationX = 250; break;
				case 4: locationX = 225; break;
				case 5: locationX = 200; break;
				case 6: locationX = 175; break;
			}
			for (int j = 0; j < counter; j++) {
				Circle c = new Circle(locationX, locationY, 10);
				list.add(c);
				getChildren().add(c);
				c.toFront();
				locationX += 50;
			}
			counter++;
			locationY += 50;
		}

		locationX = 175;
		for (int i = 0; i < 7; i++) {
			Line line = new Line(locationX, 500, locationX, 600);
			line.setStrokeWidth(3);
			locationX += 50;
			getChildren().add(line);
		}
	}//end makeMachine

	public void makeBalls() {
		int y = 130;
		for (int i = 0; i < balls.length; i++) {
			balls[i] = new Circle(325, y, 5);
			balls[i].setFill(Color.GRAY);
			balls[i].setStroke(Color.BLACK);
			getChildren().add(balls[i]);
			y -= 50;
		}
	}

	public void dropBalls() {
		for (int i = 0; i < balls.length; i++) {
			balls[i].setCenterY(balls[i].getCenterY() + 10);
			for (Circle shape: list) {
				if (balls[i].getCenterY() + balls[i].getRadius() >= 600) {
					balls[i].setCenterY(balls[i].getCenterY() - 2);
					allDone[i] = true;
				}
				checkBoundaries(shape, i);
			}
		}

		for (int i = 0; i < balls.length; i++) {
			for (int j = 0; j < balls.length; j++) {
				if (i == j)
					continue;
				if (circleIntersects(balls[j], balls[i])) {
					balls[j].setCenterY(balls[j].getCenterY() - 10);
					allDone[j] = true;
					break;
				}
			}
		}
		check();
	}

	private void checkBoundaries(Circle shape, int i) {
		int random = random();
		if (circleIntersects(shape, balls[i])) {
			if (random == 0) {
				balls[i].setCenterX(balls[i].getCenterX() - 25);
			}
			else {
				balls[i].setCenterX(balls[i].getCenterX() + 25);
			}
		}
	}

	private boolean circleIntersects(Circle shape, Circle ball) {
		Point2D p1 = new Point2D(shape.getCenterX(), shape.getCenterY());
		Point2D p2 = new Point2D(ball.getCenterX(), ball.getCenterY());

		double distance = p2.distance(p1);

		if (distance <= shape.getRadius() + ball.getRadius())
			return true;
		return false;
	}

	private int random() {
		return (int)(Math.random() * 2);
	}

	private void check() {
		int count = 0;
		for (int i = 0; i < allDone.length; i++) {
			if (allDone[i] == true) count++;
		}
		if (count == allDone.length) animation.stop();
	}
}//end class
