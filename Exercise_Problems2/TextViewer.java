//GUI app that lets user display text in a file inside the GUI

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.text.*;
import java.util.Scanner;
import java.io.*;

public class TextViewer extends Application {
	@Override
	public void start(Stage stage) {
		BorderPane pane = new BorderPane();

		TextField tf = new TextField();

		Button view = new Button("View");

		HBox bottom = new HBox(10, new Label("Filename: "), tf, view);
		bottom.setAlignment(Pos.CENTER);

		Label welcome = new Label("Welcome to Text Viewer!\n\n");
		welcome.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		HBox top = new HBox(welcome);
		top.setAlignment(Pos.CENTER);

		TextArea ta = new TextArea();
		ta.setWrapText(true);
		ta.setEditable(false);

		pane.setBottom(bottom);
		pane.setCenter(ta);
		pane.setTop(top);

		Scene scene = new Scene(pane, 500, 500);
		stage.setTitle("TextViewer");
		stage.setScene(scene);
		stage.show();

		view.setOnAction(e -> {
			if (!tf.getText().isEmpty()) {
				try {
					readData(tf, ta);
				}
				catch (IOException ex) {

				}
			}
		});

	}//end start

	public void readData(TextField tf, TextArea ta) throws IOException {
		File file = new File(tf.getText());
		if (!file.exists()) {
			ta.setText("File: " + tf.getText() + " does not exist. Try again.");
		}
		else {
			Scanner input = new Scanner(file);
			String text = "";
			while (input.hasNext()) {
				ta.appendText(input.nextLine() + '\n');
			}

			input.close();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}//end class
