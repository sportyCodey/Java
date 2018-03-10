//GUI app that lets user choose a shape and fill it with a color

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;
import javafx.scene.Node;

public class SelectGeometricFigure extends Application {
	@Override
	public void start(Stage stage) {
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: white");

		StackPane center = new StackPane();
		center.setStyle("-fx-border-color: black");

		RadioButton circle = new RadioButton("Circle");
		RadioButton rectangle = new RadioButton("Rectangle");
		RadioButton ellipse = new RadioButton("Ellipse");

		ToggleGroup group = new ToggleGroup();
		circle.setToggleGroup(group);
		rectangle.setToggleGroup(group);
		ellipse.setToggleGroup(group);

		CheckBox isFill = new CheckBox("Fill");

		HBox user = new HBox(5, circle, rectangle, ellipse, isFill);
		user.setAlignment(javafx.geometry.Pos.CENTER);

		pane.setCenter(center);
		pane.setBottom(user);

		Circle c = new Circle(100);
		Rectangle r = new Rectangle(200, 130);
		Ellipse el = new Ellipse(100, 60);

		Scene scene = new Scene(pane, 500, 500);
		stage.setTitle("SelectGeometricFigure");
		stage.setScene(scene);
		stage.show();

		circle.setOnAction(e -> {
			if (circle.isSelected()) {
				center.getChildren().clear();
				//c.setId("Circle");
				if (isFill.isSelected())
					c.setFill(Color.BLUE);
				else
					c.setFill(Color.WHITE);
				c.setStroke(Color.BLACK);
				c.setStrokeWidth(5);
				center.getChildren().add(c);
			}
		});

		rectangle.setOnAction(e -> {
			if (rectangle.isSelected()) {
				center.getChildren().clear();
				//r.setId("Rectangle");
				if (isFill.isSelected())
					r.setFill(Color.BLUE);
				else
					r.setFill(Color.WHITE);
				r.setStroke(Color.BLACK);
				r.setStrokeWidth(5);
				center.getChildren().add(r);
			}
		});

		ellipse.setOnAction(e -> {
			if (ellipse.isSelected()) {
				center.getChildren().clear();
				//el.setId("Ellipse");
				if (isFill.isSelected())
					el.setFill(Color.BLUE);
				else
					el.setFill(Color.WHITE);
				el.setStroke(Color.BLACK);
				el.setStrokeWidth(5);
				center.getChildren().add(el);
			}
		});

		isFill.setOnAction(e -> {
			if (isFill.isSelected()) {
				c.setFill(Color.BLUE);
				r.setFill(Color.BLUE);
				el.setFill(Color.BLUE);
			}
			else {
				c.setFill(Color.WHITE);
				r.setFill(Color.WHITE);
				el.setFill(Color.WHITE);
			}
		});




			/*
			if (isFill.isSelected()) {
				if (center.getChildren().size() > 0) {
					for (Node node: center.getChildren()) {
						switch (node.getId()) {
							case "Circle": c.setFill(Color.BLUE); break;
							case "Rectangle": r.setFill(Color.BLUE); break;
							case "Ellipse": el.setFill(Color.BLUE); break;
						}
					}
				}
			}
			else {
				if (center.getChildren().size() > 0) {
					for (Node node: center.getChildren()) {
						switch (node.getId()) {
							case "Circle": c.setFill(Color.WHITE); break;
							case "Rectangle": r.setFill(Color.WHITE); break;
							case "Ellipse": el.setFill(Color.WHITE); break;
						}
					}
				}
			}
		});
	    */
	}//end start

	public static void main(String[] args) {
		launch(args);
	}
}//end class
