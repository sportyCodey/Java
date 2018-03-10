/* press G to grab sword and Enter to swing sword
* press arrow keys to move */
//play game here (see issues)

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import java.io.*;
import javafx.scene.media.AudioClip;

public class Conquer extends Application{
	/* sword */
	private Circle symbol;
	private Line left;
	private Line right;
	private Line middle;
	private Line hold;

	/* player */
	private Circle head;
	private Circle eye;
	private Line mouth1;
	private Line mouth2;
	private Line body;
	private Line arm;
	private Line foot1;
	private Line foot2;
	private Line hair1;
	private Line hair2;
	private Line hair3;
	private Line hair4;

	private Group sword;
	private Group player;
	private Pane pane;

	private int swing = 0;

	private boolean hasGrab = false;

	@Override
	public void start(Stage stage) {
		//String music = "";
		//AudioClip sound = new AudioClip(new File(music).toURI().toString());

		Image conquer= new Image("conquer.jpg");
		ImageView iv = new ImageView(conquer);

		Button begin = new Button("Let's Conquer...");
		begin.setStyle("-fx-background-color: gray; -fx-border-color: red; " +
		"-fx-font-size: 50; -fx-border-width: 10; -fx-text-fill: blue");

		Pane menu = new Pane(iv, begin);

		iv.fitWidthProperty().bind(menu.widthProperty());
		iv.fitHeightProperty().bind(menu.heightProperty());

		begin.setTranslateX(400);
		begin.setTranslateY(500);

		Scene startMenu = new Scene(menu, 500, 500);
		stage.setTitle("Menu");
		stage.setScene(startMenu);
		stage.show();
		stage.setMaximized(true);
		//sound.setCycleCount(Integer.MAX_VALUE);
		//sound.play();

		begin.setOnAction( e -> {
			Scene game = new Scene(getPane(), 500, 500);
			stage.setTitle("Conquer");
			stage.setScene(game);
			stage.show();
			playGame();
		});
	}//end start

	public void playGame() {
		player.setOnKeyPressed( e -> {
			switch(e.getCode()) {
				case LEFT: checkBounds(); head.setCenterX(head.getCenterX() - 10); break;
				case RIGHT: checkBounds(); head.setCenterX(head.getCenterX() + 10);; break;
				case UP: checkBounds(); head.setCenterY(head.getCenterY() - 10); break;
				case DOWN: checkBounds(); head.setCenterY(head.getCenterY() + 10); break;
				case ENTER: swing(); break;
				case G: grab(); break;
			}
		});
		player.requestFocus();
	}

	public void grab() {
		if (arm.contains(hold.getEndX(), hold.getEndY())) {
			hasGrab = true;
			symbol.centerXProperty().bind(arm.endXProperty());
			symbol.centerYProperty().bind(arm.endYProperty().subtract(symbol.getRadius()).subtract(20));
		}
	}

	public void swing() {
		if (hasGrab) {
			swing++;
			if (swing == 1) {
				sword.setRotate(90);
				sword.setTranslateX(90);
				sword.setTranslateY(95);
			}
			else {
				sword.setRotate(0);
				sword.setTranslateX(3);
				sword.setTranslateY(-5);
				swing = 0;
			}

		}
	}

	public Pane getPane() {
		/* create sword */
		sword = new Group();

		symbol = new Circle(800, 300, 20);
		symbol.setFill(Color.GREEN);

		left = new Line(symbol.getCenterX() - symbol.getRadius(), symbol.getCenterY(), 290, 150);
		left.setStroke(Color.PURPLE);
		left.setStrokeWidth(2);

		right = new Line(symbol.getCenterX() + symbol.getRadius(), symbol.getCenterY(), 305, 150);
		right.setStroke(Color.PURPLE);
		right.setStrokeWidth(2);

		middle = new Line(symbol.getCenterX(), symbol.getCenterY() - symbol.getRadius(), 297.5, 150);
		middle.setStroke(Color.WHITE);

		hold = new Line(symbol.getCenterX(), symbol.getCenterY() + symbol.getRadius() + 2, symbol.getCenterX(), symbol.getCenterY() + symbol.getRadius() + 25);
		hold.setStroke(Color.GOLD);
		hold.setStrokeWidth(5);

		sword.getChildren().addAll(symbol, left, right, middle, hold);

		symbol.toFront();
		BindSword();

		/* create player */
		player = new Group();

		head = new Circle(100, 100, 45);
		head.setFill(Color.TRANSPARENT);
		head.setStroke(Color.WHITE);
		head.setStrokeWidth(5);

		eye = new Circle(head.getCenterX() + (head.getRadius() / 2), head.getCenterY(), 5);
		eye.setFill(Color.BLUE);

		body = new Line(head.getCenterX(), head.getCenterY() + head.getRadius(), head.getCenterX(), head.getCenterY() + 300);
		body.setStrokeWidth(5);
		body.setStroke(Color.WHITE);

		mouth1 = new Line(head.getCenterX() + (head.getRadius() - 5), head.getCenterY() + 10, head.getCenterX() + head.getRadius() - 20, head.getCenterY() + 20);
		mouth1.setStroke(Color.WHITE);

		mouth2 = new Line(head.getCenterX() + head.getRadius() - 20, head.getCenterY() + 20, head.getCenterX() + 10, head.getCenterY() + 20);
		mouth2.setStroke(Color.WHITE);

		arm = new Line(body.getStartX(), head.getCenterY() + head.getRadius() + 140, head.getCenterX() + head.getRadius() + 75,
			  head.getCenterY() + head.getRadius() + 90);
		arm.setStrokeWidth(5);
		arm.setStroke(Color.WHITE);

		foot1 = new Line(head.getCenterX(), body.getEndY(), body.getEndX() + 30, body.getEndY() + 20);
		foot1.setStrokeWidth(5);
		foot1.setStroke(Color.WHITE);

		foot2 = new Line(head.getCenterX(), body.getEndY(), body.getEndX() - 30, body.getEndY() + 20);
		foot2.setStrokeWidth(5);
		foot2.setStroke(Color.WHITE);

		hair1 = new Line(head.getCenterX(), head.getCenterY() - head.getRadius(), head.getCenterX() - 50, head.getCenterY() - 50);
		hair1.setStroke(Color.BROWN);
		hair1.setStrokeWidth(3);

		hair2 = new Line(head.getCenterX(), head.getCenterY() - head.getRadius(), head.getCenterX() - 50, head.getCenterY() - 60);
		hair2.setStroke(Color.BROWN);
		hair2.setStrokeWidth(3);

		hair3 = new Line(head.getCenterX(), head.getCenterY() - head.getRadius(), head.getCenterX() - 50, head.getCenterY() - 70);
		hair3.setStroke(Color.BROWN);
		hair3.setStrokeWidth(3);

		hair4 = new Line(head.getCenterX(), head.getCenterY() - head.getRadius(), head.getCenterX() - 50, head.getCenterY() - 80);
		hair4.setStroke(Color.BROWN);
		hair4.setStrokeWidth(3);

		player.getChildren().addAll(head, eye, mouth1, mouth2, body, arm, foot1, foot2, hair1, hair2, hair3, hair4);

		BindPlayer();

		Image image = new Image("background.png");
		ImageView iv = new ImageView(image);

		pane = new Pane(iv, sword, player);

		iv.fitWidthProperty().bind(pane.widthProperty());
   		iv.fitHeightProperty().bind(pane.heightProperty());

		return pane;
	}

	public void checkBounds() {
		if (arm.getEndX() >= pane.getWidth())
			head.setCenterX(head.getCenterX() -15);
		else if (hair1.getEndX() <= 10)
			head.setCenterX(70);
		else if (hair4.getEndY() <= 10)
			head.setCenterY(100);
		else if (foot1.getEndY() >= pane.getHeight())
			head.setCenterY(pane.getHeight() - head.getCenterY());
	}

	public void BindSword() {
		left.startXProperty().bind(symbol.centerXProperty().subtract(symbol.radiusProperty()));
		left.startYProperty().bind(symbol.centerYProperty());
		left.endXProperty().bind(symbol.centerXProperty().subtract(7));
		left.endYProperty().bind(left.startYProperty().subtract(150));

		right.startXProperty().bind(symbol.centerXProperty().add(symbol.radiusProperty()));
		right.startYProperty().bind(symbol.centerYProperty());
		right.endXProperty().bind(symbol.centerXProperty().add(7));
		right.endYProperty().bind(left.startYProperty().subtract(150));

		middle.startXProperty().bind(symbol.centerXProperty());
		middle.startYProperty().bind(symbol.centerYProperty().subtract(symbol.radiusProperty()));
		middle.endXProperty().bind(symbol.centerXProperty());
		middle.endYProperty().bind(middle.startYProperty().subtract(130));

		hold.startXProperty().bind(symbol.centerXProperty());
		hold.startYProperty().bind(symbol.centerYProperty().add(2));
		hold.endXProperty().bind(symbol.centerXProperty());
		hold.endYProperty().bind(symbol.centerYProperty().add(symbol.radiusProperty().add(25)));
	}

	public void BindPlayer() {
		eye.centerXProperty().bind(head.centerXProperty().add(head.radiusProperty().divide(2)));
		eye.centerYProperty().bind(head.centerYProperty());

		mouth1.startXProperty().bind(head.centerXProperty().add(head.radiusProperty().subtract(5)));
		mouth1.startYProperty().bind(head.centerYProperty().add(10));
		mouth1.endXProperty().bind(head.centerXProperty().add(head.radiusProperty().subtract(20)));
		mouth1.endYProperty().bind(head.centerYProperty().add(20));

		mouth2.startXProperty().bind(head.centerXProperty().add(head.radiusProperty().subtract(20)));
		mouth2.startYProperty().bind(head.centerYProperty().add(20));
		mouth2.endXProperty().bind(head.centerXProperty().add(10));
		mouth2.endYProperty().bind(head.centerYProperty().add(20));

		body.startXProperty().bind(head.centerXProperty());
		body.startYProperty().bind(head.centerYProperty().add(head.radiusProperty()));
		body.endXProperty().bind(head.centerXProperty());
		body.endYProperty().bind(head.centerYProperty().add(300));

		arm.startXProperty().bind(body.startXProperty());
		arm.startYProperty().bind(head.centerYProperty().add(head.radiusProperty()).add(140));
		arm.endXProperty().bind(head.centerXProperty().add(head.radiusProperty()).add(75));
		arm.endYProperty().bind(head.centerYProperty().add(head.radiusProperty()).add(90));

		foot1.startXProperty().bind(head.centerXProperty());
		foot1.startYProperty().bind(body.endYProperty());
		foot1.endXProperty().bind(body.endXProperty().add(30));
		foot1.endYProperty().bind(body.endYProperty().add(20));

		foot2.startXProperty().bind(head.centerXProperty());
		foot2.startYProperty().bind(body.endYProperty());
		foot2.endXProperty().bind(body.endXProperty().subtract(30));
		foot2.endYProperty().bind(body.endYProperty().add(20));

		hair1.startXProperty().bind(head.centerXProperty());
		hair1.startYProperty().bind(head.centerYProperty().subtract(head.radiusProperty()));
		hair1.endXProperty().bind(head.centerXProperty().subtract(50));
		hair1.endYProperty().bind(head.centerYProperty().subtract(50));

		hair2.startXProperty().bind(head.centerXProperty());
		hair2.startYProperty().bind(head.centerYProperty().subtract(head.radiusProperty()));
		hair2.endXProperty().bind(head.centerXProperty().subtract(50));
		hair2.endYProperty().bind(head.centerYProperty().subtract(60));

		hair3.startXProperty().bind(head.centerXProperty());
		hair3.startYProperty().bind(head.centerYProperty().subtract(head.radiusProperty()));
		hair3.endXProperty().bind(head.centerXProperty().subtract(50));
		hair3.endYProperty().bind(head.centerYProperty().subtract(70));

		hair4.startXProperty().bind(head.centerXProperty());
		hair4.startYProperty().bind(head.centerYProperty().subtract(head.radiusProperty()));
		hair4.endXProperty().bind(head.centerXProperty().subtract(50));
		hair4.endYProperty().bind(head.centerYProperty().subtract(80));
	}

	public static void main(String[] args) {
		launch(args);
	}

}//end class
