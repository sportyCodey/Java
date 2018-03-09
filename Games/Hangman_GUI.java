import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class Hangman_GUI extends Application {

	public static int order = 0;

	public static int win = 0;

	public static int length = 0;

	public static int gameCount = 0;

	public static int index = 0;

	public static int random = -1;

	public static int counter = 0;

	public static String word = "";

	public static String star_Text = "";

	public static String missed = "";

	public static String[] words = {"Hello", "My", "Name", "Is", "Drew"};

	public static String[] tokens;

	public static String[] stars;

	public static String[] guesses = new String[10];

	public static int[] num_Words = new int[words.length];

	public static boolean correct = false;

	public static boolean duplicate = false;

	public static boolean moveOn = true;

	public static boolean end = false;

	public static boolean discl = false;

	public static Label disclaimer;

	@Override
	public void start(Stage primaryStage) {

		reset();

		Pane pane = new Pane();

		ArrayList<Shape> hangman = new ArrayList<>();

		Arc base = new Arc(150, 150, 100, 100, 30, 120);
		base.setFill(Color.TRANSPARENT);
		base.setStroke(Color.BLACK);
		base.setStrokeWidth(4);
		base.setType(ArcType.OPEN);
		base.setTranslateY(500);
		base.setTranslateX(10);

		Line pole = new Line(160, 100, 160, 550);
		pole.setStroke(Color.BLACK);
		pole.setStrokeWidth(4);

		Line heading = new Line(160, 100, 400, 100);
		heading.setStroke(Color.BLACK);
		heading.setStrokeWidth(4);

		Line hang = new Line(400, 100, 400, 125);
		hang.setStroke(Color.BLACK);
		hang.setStrokeWidth(4);

		pane.getChildren().addAll(base, pole, heading, hang);

		Circle head = new Circle(400, 175, 50);
		head.setFill(Color.TRANSPARENT);
		head.setStroke(Color.BLACK);
		head.setStrokeWidth(4);

		Line left_Arm = new Line(400, 228, 300, 315);
		left_Arm.setFill(Color.WHITE);
		left_Arm.setStroke(Color.BLACK);
		left_Arm.setStrokeWidth(4);

		Line right_Arm = new Line(400, 228, 500, 315);
		right_Arm.setFill(Color.WHITE);
		right_Arm.setStroke(Color.BLACK);
		right_Arm.setStrokeWidth(4);

		Line body = new Line(400, 225, 400, 460);
		body.setFill(Color.WHITE);
		body.setStroke(Color.BLACK);
		body.setStrokeWidth(4);

		Line left_Leg = new Line(400, 460, 300, 544);
		left_Leg.setFill(Color.WHITE);
		left_Leg.setStroke(Color.BLACK);
		left_Leg.setStrokeWidth(4);

		Line right_Leg = new Line(400, 460, 500, 544);
		right_Leg.setFill(Color.WHITE);
		right_Leg.setStroke(Color.BLACK);
		right_Leg.setStrokeWidth(4);

		pane.getChildren().addAll(head, left_Arm, right_Arm, body, left_Leg, right_Leg);
		head.setVisible(false);
		left_Arm.setVisible(false);
		right_Arm.setVisible(false);
		body.setVisible(false);
		left_Leg.setVisible(false);
		right_Leg.setVisible(false);

		hangman.add(head);
		hangman.add(left_Arm);
		hangman.add(right_Arm);
		hangman.add(body);
		hangman.add(left_Leg);
		hangman.add(right_Leg);

		Label guess_Word = new Label("Guess a letter and press enter: ");
		Label missed_Letters = new Label("Missed Letters: ");

		guess_Word.setTranslateX(650);
		guess_Word.setTranslateY(500);
		guess_Word.setStyle("-fx-font-size: 25");

		missed_Letters.setTranslateX(650);
		missed_Letters.setTranslateY(560);
		missed_Letters.setStyle("-fx-font-size: 25");

		TextField input = new TextField();
		input.setTranslateX(1000);
		input.setTranslateY(507);

		Label missed_Info = new Label();
		missed_Info.setTranslateX(830);
		missed_Info.setTranslateY(560);
		missed_Info.setStyle("-fx-font-size: 25");

		Label preview = new Label(star_Text);
		preview.setTranslateX(650);
		preview.setTranslateY(400);
		preview.setStyle("-fx-font-size: 35");

		Label error_1 = new Label("You can only guess 1 character \n at a time. Guess again.");
		error_1.setTranslateX(500);
		error_1.setTranslateY(100);
		error_1.setStyle("-fx-font-size: 40");

		Label error_2 = new Label("You have already guessed that.\n Guess again.");
		error_2.setTranslateX(500);
		error_2.setTranslateY(100);
		error_2.setStyle("-fx-font-size: 40");

		Label corr = new Label("Correct!");
		corr.setTranslateX(600);
		corr.setTranslateY(200);
		corr.setStyle("-fx-font-size: 50");

		Label not_Corr=  new Label("You did not guess correctly.");
		not_Corr.setTranslateX(550);
		not_Corr.setTranslateY(200);
		not_Corr.setStyle("-fx-font-size: 40");

		Label winner = new Label("You win! \nPress the button to play again!");
		winner.setTranslateX(510);
		winner.setTranslateY(200);
		winner.setStyle("-fx-font-size: 50");

		disclaimer = new Label("DISCLAIMER. You have played through \n all the words available");
		disclaimer.setTranslateX(500);
		disclaimer.setTranslateY(100);
		disclaimer.setStyle("-fx-font-size: 40");

		Label lose = new Label("You lose! \n Press the button to player again!");
		lose.setTranslateX(510);
		lose.setTranslateY(200);
		lose.setStyle("-fx-font-size: 50");

		Button play = new Button("Play");
		play.setTranslateX(800);
		play.setTranslateY(115);
		play.setStyle("-fx-font-size: 50");

		pane.getChildren().addAll(guess_Word, missed_Letters, input, missed_Info, preview, error_1, error_2, corr, not_Corr, winner, play,
		disclaimer, lose);

		error_1.setVisible(false);
		error_2.setVisible(false);
		corr.setVisible(false);
		not_Corr.setVisible(false);
		winner.setVisible(false);
		play.setVisible(false);
		disclaimer.setVisible(false);
		lose.setVisible(false);

		input.setOnKeyPressed( e -> {
			switch (e.getCode()) {
				case ENTER:	if (!end) {
									disclaimer.setVisible(false);
									if (input.getText().length() != 1) {
									input.setText("");
									error_1.setVisible(true);
									error_2.setVisible(false);
									corr.setVisible(false);
									not_Corr.setVisible(false);
									moveOn = false;
								}

								if (moveOn) {

									error_1.setVisible(false);

									guesses[index] = input.getText().toUpperCase();

									if (isDuplicate(guesses, index)) {
										input.setText("");
										error_2.setVisible(true);
										corr.setVisible(false);
										not_Corr.setVisible(false);
										duplicate = true;
										missed_Info.setText(missed);
									}

									if (!duplicate) {

										error_2.setVisible(false);

										if (index % 10 == 0)
											guesses = extendArray(guesses);

										for (int i = 0; i < tokens.length; i++) {
											if (input.getText().equalsIgnoreCase(tokens[i])) {
												stars[i] = input.getText().toUpperCase();
												correct = true;
												win++;
											}
										}

										input.setText("");

										if (correct) {
											not_Corr.setVisible(false);
											corr.setVisible(true);
											star_Text = "";
											for (int i = 0; i < stars.length; i++)
												star_Text += stars[i] + "    ";
											preview.setText(star_Text);
										}
										else {
											missed += guesses[index].toUpperCase() + " ";
											missed_Info.setText(missed);
											not_Corr.setVisible(true);
											corr.setVisible(false);
											hangman.get(order).setVisible(true);
											order++;
											if (order == 6) {
												end = true;
												lose.setVisible(true);
												play.setVisible(true);
												not_Corr.setVisible(false);
												corr.setVisible(false);

												gameCount++;
											}
										}

										index++;

										correct = false;

										if (win == stars.length) {
											end = true;
											winner.setVisible(true);
											play.setVisible(true);
											not_Corr.setVisible(false);
											corr.setVisible(false);

											gameCount++;
										}
									}//end duplicate
									duplicate = false;
								}//end moveOn
								moveOn = true;
							}//end !end
							if (end)
								input.setEditable(false);
			}
		});

		play.setOnAction( e -> {
			reset();
			for (int i = 0; i < index + 1; i++)
				guesses[i] = null;
			index = 0;
			preview.setText(star_Text);
			missed_Info.setText(missed);
			winner.setVisible(false);
			play.setVisible(false);
			lose.setVisible(false);

			head.setVisible(false);
			left_Arm.setVisible(false);
			right_Arm.setVisible(false);
			body.setVisible(false);
			left_Leg.setVisible(false);
			right_Leg.setVisible(false);

			input.setEditable(true);
		});

		Scene scene = new Scene(pane,500,500);
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Hangman");
		primaryStage.setScene(scene);
		primaryStage.show();

	}//end start

	public static String insertBlanks(String s) {
		String result = "";

		for (int i = 0; i < s.length(); i++) {
			result += s.charAt(i) + " ";
		}
		return result;
	}

	public static void reset() {

		win = 0;

		order = 0;

		random = (int)(Math.random() * words.length);

		if (gameCount == words.length) {
				disclaimer.setVisible(true);
				discl = true;
		}

		if (!discl) {

			num_Words[counter] = random;

			if (isDuplicate(num_Words, counter)) {
				while(!isDuplicate(num_Words, counter)) {
						random = (int)(Math.random() * words.length);
						num_Words[counter] = random;
				}
			}

			counter++;
		}

		length = words[random].length();
		word = insertBlanks(words[random]);

		tokens = word.split(" ");

		stars = new String[length];

		star_Text = "";

		missed = "";

		end = false;

		for (int i = 0; i < stars.length; i++) {
				stars[i] = "*";
				star_Text += stars[i] + "    ";
		}
	}

	public static String[] extendArray(String array[]) {
		int oldSize = array.length;
		String newArray[] = new String[oldSize + 10];

		for (int i = 0; i < oldSize; i++) {
		 	newArray[i] = array[i];
		}

		for (int i = 0; i < newArray.length; i++) {
			array = Arrays.copyOf(newArray, i + 2);
		}
		return array;
	}

	public static boolean isDuplicate(String array[], int index) {
		for (int i = 0; i < index + 1; i++) {
			for (int j = i + 1; j < index + 1; j++) {
				if (array[i].equals(array[j])) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isDuplicate(int array[], int index) {
		for (int i = 0; i < index + 1; i++) {
			for (int j = i + 1; j < index + 1; j++) {
				if (array[i] == array[j]) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
	      launch(args);
    }

}//end class
