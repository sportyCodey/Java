import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.animation.PathTransition;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.io.*;
import javafx.scene.media.AudioClip;
import javafx.geometry.Bounds;

public class Ball extends Pane {

	private Timeline hitAnimation;
	private Timeline oAnimation;

	private static final double x = 15;
	private static final double y = 15;
	private static final double r = 75;
	private Circle playBall = new Circle(x, y, r);
	private Circle opponent = new Circle(x, y, r);

	private EventHandler<ActionEvent> oAway = e -> {
		Bounds b = opponent.localToScene(opponent.getBoundsInLocal());
		Line away = new Line((b.getMinX() + b.getMaxX()) / 2, (b.getMinY() + b.getMaxY()) / 2,
		opponent.getCenterX() + 100, opponent.getCenterY() + 100);

		PathTransition leave = new PathTransition(Duration.millis(2500), away, opponent);
		leave.setCycleCount(1);
		leave.play();
	};

	health oHealth = new health(opponent, 100);
	health pHealth = new health(playBall, 100);

	private customizeBall ball = new customizeBall();

	public Ball() {
		if (getColor().equals("Blue")) {
			playBall.setFill(Color.BLUE);
		}
		else if (getColor().equals("Red")) {
			playBall.setFill(Color.RED);
		}
		else if (getColor().equals("Orange")) {;
			playBall.setFill(Color.ORANGE);
		}
		else if (getColor().equals("Yellow")) {
			playBall.setFill(Color.YELLOW);
		}
		else if (getColor().equals("Purple")) {
			playBall.setFill(Color.PURPLE);
		}
		else if (getColor().equals("White")) {
			playBall.setFill(Color.WHITE);
		}
		else if (getColor().equals("Random")) {
			if (getRandomColor().equals("Blue")) {
				playBall.setFill(Color.BLUE);
			}
			else if (getRandomColor().equals("Red")) {
				playBall.setFill(Color.RED);
			}
			else if (getRandomColor().equals("Orange")) {;
				playBall.setFill(Color.ORANGE);
			}
			else if (getRandomColor().equals("Yellow")) {
				playBall.setFill(Color.YELLOW);
			}
			else if (getRandomColor().equals("Purple")) {
				playBall.setFill(Color.PURPLE);
			}
			else if (getRandomColor().equals("White")) {
				playBall.setFill(Color.WHITE);
			}
		}
		else {
			playBall.setFill(Color.BLACK);
			playBall.setStroke(Color.WHITE);
			playBall.setStrokeWidth(10);
		}

		getChildren().add(playBall);

		setUpOpponents();

		oMoving();
		oAttack();

		setStyle("-fx-background-color: black");

		go();

	}

	public String getColor() {
		return ball.getChoice();
	}

	public String getRandomColor() {
		return ball.getRandomChoice();
	}

	public void goRight() {
		playBall.setCenterX(playBall.getCenterX() + 20);
	}

	public void goLeft() {
		playBall.setCenterX(playBall.getCenterX() - 20);
	}

	public void goUp() {
		playBall.setCenterY(playBall.getCenterY() - 20);
	}

	public void goDown() {
		playBall.setCenterY(playBall.getCenterY() + 20);
	}

	public void shoot() {
		//gunSound();
		Line bullet = new Line(playBall.getCenterX() + playBall.getRadius(), playBall.getCenterY(),
		playBall.getCenterX() + playBall.getRadius() + 50, playBall.getCenterY());
		bullet.setStrokeWidth(8);
		bullet.setStroke(Color.RED);
		getChildren().add(bullet);

		Line shoot = new Line(playBall.getCenterX() + playBall.getRadius(), playBall.getCenterY(),
		playBall.getCenterX() + playBall.getRadius() + 1000, playBall.getCenterY());

		PathTransition shooting = new PathTransition(Duration.millis(500), shoot, bullet);
		shooting.setCycleCount(1);
		shooting.play();

		EventHandler<ActionEvent> pEventHandler = e -> {

			if (shoot.intersects(opponent.getBoundsInLocal())) {
					getChildren().remove(bullet);
					oHealth.setHealth(oHealth.getHealth() - 1);
			}
/*
			if (shoot.contains(opponent.getCenterX(), opponent.getCenterY())) {
					getChildren().remove(bullet);
					oHealth.setHealth(oHealth.getHealth() - 1);
				}
				*/


			if (oHealth.getHealth() <= 0) {
						getChildren().remove(opponent);
						oAnimation.stop();
			}
		};

		Timeline pAnimation = new Timeline(new KeyFrame(Duration.millis(500), pEventHandler));
		pAnimation.setCycleCount(1);
		pAnimation.play();

		EventHandler<ActionEvent> dissapearEventHandler = e -> {
			if (bullet.isVisible()) {
				getChildren().remove(bullet);
			}
		};

		Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), dissapearEventHandler));
		animation.setCycleCount(1);
		animation.play();
	}

	public void setUpOpponents() {
		opponent.setFill(Color.BLUE);
		opponent.setCenterX(1200);
		opponent.setCenterY(100);
		getChildren().add(opponent);
	}

	public void oAttackMove() {
		Bounds b = opponent.localToScene(opponent.getBoundsInLocal());

		Line oBullet = new Line((b.getMinX() + b.getMaxX()) / 2, (b.getMinY() + b.getMaxY()) / 2,
		(((b.getMinX() + b.getMaxX()) / 2) - opponent.getRadius()) + 50, (b.getMinY() + b.getMaxY()) / 2);

		oBullet.setStrokeWidth(8);
		oBullet.setStroke(Color.BLUE);
		getChildren().add(oBullet);

		//Line shoot = new Line((b.getMinX() + b.getMaxX()) / 2, (b.getMinY() + b.getMaxY()) / 2,
		//(((b.getMinX() + b.getMaxX()) / 2) - opponent.getRadius()) - 1000, (b.getMinY() + b.getMaxY()) / 2);

		Line shoot = new Line((b.getMinX() + b.getMaxX()) / 2, (b.getMinY() + b.getMaxY()) / 2,
		playBall.getCenterX(), playBall.getCenterY());

		PathTransition shooting = new PathTransition(Duration.millis(500), shoot, oBullet);
		shooting.setCycleCount(1);
		shooting.play();
		gunSound();

		EventHandler<ActionEvent> hitEventHandler = e -> {
			if (shoot.intersects(playBall.getBoundsInLocal())) {
					getChildren().remove(oBullet);
					pHealth.setHealth(pHealth.getHealth() - 1);
					if (pHealth.getHealth() <= 0) {
						getChildren().remove(playBall);
						hitAnimation.stop();
						oAnimation.stop();
					}
				}
			};

		Timeline hitAnimation = new Timeline(new KeyFrame(Duration.millis(500), hitEventHandler));
		hitAnimation.setCycleCount(1);
		hitAnimation.play();

		EventHandler<ActionEvent> dissapearEventHandler = e -> {
			if (oBullet.isVisible()) {
				getChildren().remove(oBullet);
			}
		};

		Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), dissapearEventHandler));
		animation.setCycleCount(1);
		animation.play();
	}

	public void oAttack() {
		EventHandler<ActionEvent> oAttackEventHandler = e -> {
			if (oHealth.getHealth() <= 0) {
				hitAnimation.stop();
			}
				oAttackMove();
		};

		hitAnimation = new Timeline(new KeyFrame(Duration.millis(2000), oAttackEventHandler));
		hitAnimation.setCycleCount(Timeline.INDEFINITE);
		hitAnimation.play();
	}

	public void oMoving() {
		EventHandler<ActionEvent> oMAttackEventHandler = e -> {
			Bounds b = opponent.localToScene(opponent.getBoundsInLocal());

			Line go = new Line((b.getMinX() + b.getMaxX()) / 2, (b.getMinY() + b.getMaxY()) / 2,
			playBall.getCenterX() + 50, playBall.getCenterY() + 50);


			PathTransition move = new PathTransition(Duration.millis(2500), go, opponent);
			move.setCycleCount(1);
			move.play();
			move.setOnFinished(oAway);
		};

		oAnimation = new Timeline(new KeyFrame(Duration.millis(3000), oMAttackEventHandler));
		oAnimation.setCycleCount(Timeline.INDEFINITE);
		oAnimation.play();
	}

	public void sPause() {
		EventHandler<ActionEvent> pause = e -> {
			System.out.println("Hi");
		};

		Timeline p = new Timeline(new KeyFrame(Duration.millis(5000), pause));
		p.setCycleCount(2);
		p.play();
	}

	public void go() {
		setOnKeyPressed(e -> {
			switch(e.getCode()) {
			case RIGHT: goRight(); break;
			case LEFT:  goLeft(); break;
			case UP:	goUp(); break;
			case DOWN: 	goDown(); break;
			case SPACE: check();
						shoot();
						//gunSound();
						//sPause();
						break;
			}
		});
	}

	public void check() {

		if (pHealth.getHealth() <= 0) {
			setOnKeyPressed(e -> {
				switch(e.getCode()) {}
			});
		}

	}

	public void gunSound() {
		// String musicFile = "";
		// AudioClip sound = new AudioClip(new File(musicFile).toURI().toString());

		//sound.play();
	}

}//end class Ball




