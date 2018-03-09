//This program displays four images in a grid pane.

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class displayImages extends Application{
	@Override
	public void start(Stage primaryStage){
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

		//creates first image
        Image firstImage = new Image("image/flag5.gif");
		ImageView firstImageView = new ImageView(firstImage);
		firstImageView.setFitHeight(100);
		firstImageView.setFitWidth(100);
		gridPane.add(firstImageView, 0, 0);

		//creates second image
		Image secondImage = new Image("image/illinoisMap.gif");
		ImageView secondImageView = new ImageView(secondImage);
		secondImageView.setFitHeight(100);
		secondImageView.setFitWidth(100);
		gridPane.add(secondImageView, 0, 1);

		//creates third image
		Image thirdImage = new Image("image/flag3.gif");
		ImageView thirdImageView = new ImageView(thirdImage);
		thirdImageView.setFitHeight(100);
		thirdImageView.setFitWidth(100);
		gridPane.add(thirdImageView, 1, 0);

		//creates fourth image
		Image fourthImage = new Image("image/ca.gif");
		ImageView fourthImageView = new ImageView(fourthImage);
		fourthImageView.setFitHeight(100);
		fourthImageView.setFitWidth(100);
		gridPane.add(fourthImageView, 1, 1);

    	Scene scene = new Scene(gridPane);
    	primaryStage.setTitle("displayImages");
    	primaryStage.setScene(scene);
    	primaryStage.show();

	}

	public static void main(String[] args) {
	      launch(args);
    }
}//end class displayImages

