/* move with arrow keys */
//GUI app to practice binding

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;

public class PlayWithBinding extends Application{

	private Circle symbol;
	private Line left;
	private Line right;
	private Line middle;
	private Line hold;

	private Pane pane;

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(getPane(), 500, 500);
		stage.setTitle("PlayWithBinding");
		stage.setScene(scene);
		stage.show();

	pane.setOnKeyPressed( e -> {
		switch(e.getCode()) {
			case LEFT: checkBounds(); symbol.setCenterX(symbol.getCenterX() - 10); break;
			case RIGHT: checkBounds(); symbol.setCenterX(symbol.getCenterX() + 10); break;
			case DOWN: checkBounds(); symbol.setCenterY(symbol.getCenterY() + 10); break;
			case UP: checkBounds(); symbol.setCenterY(symbol.getCenterY() - 10); break;
		}
	});

	pane.requestFocus();
	}//end start

	public Pane getPane() {
		pane = new Pane();

		symbol = new Circle(300, 300, 20);
		symbol.setFill(Color.GREEN);

		left = new Line(symbol.getCenterX() - symbol.getRadius(), symbol.getCenterY(), 290, 150);
		left.setStroke(Color.PURPLE);
		left.setStrokeWidth(2);

		right = new Line(symbol.getCenterX() + symbol.getRadius(), symbol.getCenterY(), 305, 150);
		right.setStroke(Color.PURPLE);
		right.setStrokeWidth(2);

		middle = new Line(symbol.getCenterX(), symbol.getCenterY() - symbol.getRadius(), 297.5, 150);

		hold = new Line(symbol.getCenterX(), symbol.getCenterY() + symbol.getRadius() + 2, symbol.getCenterX(), symbol.getCenterY() + symbol.getRadius() + 25);
		hold.setStroke(Color.GOLD);
		hold.setStrokeWidth(5);

		pane.getChildren().addAll(symbol, left, right, middle, hold);

		symbol.toFront();

		bind();

		return pane;
	}

	public void checkBounds() {
		if ((symbol.getCenterX() + symbol.getRadius()) >= pane.getWidth()) {
			symbol.setCenterX(pane.getWidth() - symbol.getRadius() - 10);
		}
		else if ((symbol.getCenterX() - symbol.getRadius()) <= 0)
			symbol.setCenterX(30);
		else if (left.getEndY() <= 10) {
			symbol.setCenterY(160);
		}
		else if (hold.getEndY() >= pane.getHeight())
			symbol.setCenterY(pane.getHeight() - symbol.getRadius() - 30);
	}

	public void bind() {
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

	public static void main(String[] args) {
	      launch(args);
  	}

}//end class
