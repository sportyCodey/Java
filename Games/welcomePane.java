import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import java.io.*;
import javafx.scene.media.AudioClip;

public class welcomePane extends BorderPane {

	private BorderPane welcomePane = new BorderPane();
	private Button play = new Button("Play");

	//private String musicFile = "";
	//private AudioClip sound = new AudioClip(new File(musicFile).toURI().toString());

	public welcomePane() {
		setBottom(getPane1());
		setCenter(getPane2());
		setStyle("-fx-background-color: green");
		buttonEnlarge();
		buttonRegular();
		//welcomeSound();
	}

	public HBox getPane1() {
		HBox pane1 = new HBox();
		pane1.setAlignment(Pos.CENTER);
		play.setMaxSize(300,300);
		play.setPrefWidth(235);
		play.setPrefHeight(150);
		play.setStyle("-fx-background-color: red; -fx-border-color: purple; " +
		"-fx-font-size: 50; -fx-border-width: 10; -fx-text-fill: blue");

		pane1.getChildren().add(play);

		return pane1;
	}

	public Button getButton() {
		return play;
	}

	public void buttonEnlarge() {
		play.setOnMouseMoved(e -> {
			play.setPrefWidth(240);
			play.setPrefHeight(155);
		});
	}

	public void buttonRegular() {
		setOnMouseMoved( e -> {
			play.setPrefWidth(235);
			play.setPrefHeight(150);
		});
	}


	public HBox getPane2() {
		HBox pane2 = new HBox();
		pane2.setAlignment(Pos.CENTER);
		Label gameLabel = new Label("Gaming With D-Hud");
		gameLabel.setTextFill(Color.PURPLE);
		gameLabel.setFont(Font.font("Cooper Black", FontWeight.BOLD,
		FontPosture.REGULAR, 75));

		pane2.getChildren().add(gameLabel);

		return pane2;
	}

	public void welcomeSound() {
	//	sound.setCycleCount(Integer.MAX_VALUE);
	//	sound.play();
	}

	public void stop() {
		//sound.stop();
	}

}//end class welcomePane