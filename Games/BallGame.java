/* move with arrow keys.
* press space to shoot */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class BallGame extends Application {
	@Override
	public void start(Stage primaryStage) {
		welcomePane startPane = new welcomePane();
		Stage welcomeStage = new Stage();

		Scene welcomeScene = new Scene(startPane);
		welcomeStage.setTitle("Welcome!");
		welcomeStage.setScene(welcomeScene);
		welcomeStage.setMaximized(true);
		welcomeStage.setResizable(false);
		welcomeStage.show();

		customizeBall customize = new customizeBall();
		Stage customizeStage = new Stage();

		Scene customizeScene = new Scene(customize);
		customizeStage.setTitle("Customize!");
		customizeStage.setScene(customizeScene);
		customizeStage.setMaximized(true);
		customizeStage.setResizable(false);

		startPane.getButton().setOnAction(e -> {
			welcomeStage.close();
			startPane.stop();
			customizeStage.show();
		});

		customize.getButton().setOnAction(e -> {
			Ball ball = new Ball();
			Scene scene = new Scene(ball);
			primaryStage.setTitle("Ball Game");
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.setResizable(false);

			ball.requestFocus();

			customizeStage.close();

			primaryStage.show();
		});
	}//end start method

	public static void main(String[] args) {
		launch(args);
	}
}//end class BallGame
