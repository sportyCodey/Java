//This program displays three cards randomly selected from a deck of 52.

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class displayThreeCards extends Application {
	ArrayList<Integer> list = new ArrayList<>();
	@Override
	public void start(Stage primaryStage) {
		for(int i = 1; i <= 52; i++) {
			list.add(i);
		}
		java.util.Collections.shuffle(list);//method to shuffle the numbers

		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(5, 5, 5, 5));

		//creates the first image
		Image image1 = new Image("image/card/"+list.get(0)+ ".png");
		ImageView firstImageView = new ImageView (image1);
		gridPane.add(firstImageView, 0, 0);

		//creates the second image
		Image image2 = new Image("image/card/"+list.get(1)+ ".png");
		ImageView secondImageView = new ImageView(image2);
		gridPane.add(secondImageView, 1, 0);

		//creates the third image
		Image image3 = new Image("image/card/"+list.get(3)+ ".png");
		ImageView thirdImageView = new ImageView(image3);
		gridPane.add(thirdImageView, 2, 0);

		Scene scene = new Scene(gridPane);
		primaryStage.setTitle("displayThreeCards");
		primaryStage.setScene(scene);
    	primaryStage.show();

	}

	public static void main(String[] args) {
	      launch(args);
    }
}//end class displayThreeCards
