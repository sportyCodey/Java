//class that lets user customize fighter (ball)

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import java.io.*;
import javafx.scene.media.AudioClip;
import javafx.scene.control.Label;
import javafx.scene.text.*;

public class customizeBall extends BorderPane {

	private BorderPane pane = new BorderPane();
	private String[] colors = {"Blue", "Red", "Orange", "Yellow", "Purple", "White",
	"Random"};

	//private String musicFile = "";
	//private AudioClip sound = new AudioClip(new File(musicFile).toURI().toString());

	private int x, y = 100;
	private int r = 150;
	private Circle ball = new Circle(x, y, r);

	private static String choice = "Black";
	private static String randomChoice;

	private int num = (int)(Math.random() * 6);

	private Circle[] ballColors = {getBlue(), getRed(), getOrange(), getYellow(), getPurple(),
	getWhite(), getRandom()};

	private ListView<String> colorPane = new ListView<>
	(FXCollections.observableArrayList(colors));

	Button go = new Button("Let's Play!");

	private HBox ballPane = new HBox();

	public customizeBall() {
			setCenter(getCBall());
			setRight(getColorPane());
			setBottom(getButtonPane());
			setTop(getLabel());
			setStyle("-fx-background-color: gold");
			buttonEnlarge();
			buttonRegular();
			selection();
	}

	public HBox getCBall() {
		ballPane.getChildren().add(ball);
		ballPane.setTranslateY(75);
		ballPane.setTranslateX(485);
		ball.setId("Play Ball");

		return ballPane;
	}

	public HBox getLabel() {
		HBox lBox = new HBox();
		lBox.setAlignment(Pos.CENTER);
		Label cLabel= new Label("Customize your Fighter");
		cLabel.setTextFill(Color.RED);
		cLabel.setFont(Font.font("Cooper Black", FontWeight.BOLD,
		FontPosture.REGULAR, 75));

		lBox.getChildren().add(cLabel);

		return lBox;

	}

	public void buttonEnlarge() {
		go.setOnMouseMoved(e -> {
			go.setPrefWidth(240);
			go.setPrefHeight(155);
			//buttonSound();
		});
	}

	public void buttonRegular() {
		setOnMouseMoved( e -> {
			go.setPrefWidth(235);
			go.setPrefHeight(150);
			//sound.stop();
		});
	}

	public void buttonSound() {
		//sound.play();
	}


	public Circle getBall() {
		return ball;
	}

	public HBox getButtonPane() {
		HBox buttonPane = new HBox();
		buttonPane.setAlignment(Pos.CENTER);
		go.setMaxSize(300,300);
		go.setPrefWidth(235);
		go.setPrefHeight(150);
		go.setStyle("-fx-background-color: red; -fx-border-color: purple; " +
		"-fx-font-size: 30; -fx-border-width: 10; -fx-text-fill: blue");
		buttonPane.getChildren().add(go);

		return buttonPane;
	}

	public Button getButton() {
		return go;
	}

	public ListView getColorPane() {
		colorPane.setPrefSize(115,800);

		return colorPane;
	}

	public void selection () {
		colorPane.getSelectionModel().selectedItemProperty().addListener (
			ov -> {
				ballPane.getChildren().clear();
				for (Integer i: colorPane.getSelectionModel().getSelectedIndices()) {
					ballPane.getChildren().add(ballColors[i]);
					choice = colors[i];
					if (num == 0) {
						randomChoice = "Blue";
				 	}
					else if (num == 1) {
						randomChoice = "Red";
					}
					else if (num == 2) {
						randomChoice = "Orange";
					}
					else if (num == 3) {
						randomChoice = "Yellow";
					}
					else if (num == 4) {
						randomChoice = "Purple";
					}
					else {
						randomChoice = "White";
			}
				}
			});
	}

	public String getChoice() {
		return choice;
	}

	public Circle getBlue() {
		Circle blueBall = new Circle(x, y, r);
		blueBall.setFill(Color.BLUE);

		return blueBall;
	}

	public Circle getRed() {
			Circle redBall = new Circle(x, y, r);
			redBall.setFill(Color.RED);

			return redBall;
	}

	public Circle getOrange() {
			Circle orangeBall = new Circle(x, y, r);
			orangeBall.setFill(Color.ORANGE);

			return orangeBall;
	}

	public Circle getYellow() {
			Circle yellowBall = new Circle(x, y, r);
			yellowBall.setFill(Color.YELLOW);

			return yellowBall;
	}

	public Circle getPurple() {
			Circle purpleBall = new Circle(x, y, r);
			purpleBall.setFill(Color.PURPLE);

			return purpleBall;
	}

	public Circle getWhite() {
			Circle whiteBall = new Circle(x, y, r);
			whiteBall.setFill(Color.WHITE);

			return whiteBall;
	}

	public Circle getRandom() {
			Circle randomBall = new Circle(x, y, r);
			if (num == 0) {
				randomBall.setFill(Color.BLUE);
			}
			else if (num == 1) {
				randomBall.setFill(Color.RED);
			}
			else if (num == 2) {
				randomBall.setFill(Color.ORANGE);
			}
			else if (num == 3) {
				randomBall.setFill(Color.YELLOW);
			}
			else if (num == 4) {
				randomBall.setFill(Color.PURPLE);
			}
			else {
				randomBall.setFill(Color.WHITE);
			}

			return randomBall;
	}

	public String getRandomChoice() {
			return randomChoice;
	}

}//end class customizeBall




