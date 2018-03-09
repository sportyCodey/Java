import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.*;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class SelectAFont extends Application {
	@Override
	public void start(Stage stage) {
		BorderPane pane = new BorderPane();

		CheckBox bold = new CheckBox("Bold");
		CheckBox italic = new CheckBox("Italic");

		HBox checkBoxes = new HBox(10, bold, italic);
		checkBoxes.setAlignment(Pos.CENTER);

		Text text = new Text("Programming is fun");
		text.setFont(Font.font("Agency FB", 20));
		StackPane center = new StackPane(text);

		/* create font combo box */
		ComboBox<String> fontNames = new ComboBox<>();
		java.util.List<String> fonts = Font.getFamilies();
		ObservableList<String> items = FXCollections.observableArrayList(fonts);
		fontNames.getItems().addAll(items);
		fontNames.setValue("Agency FB");

		/* create size combo box */
		ComboBox<Integer> fontSizes = new ComboBox<>();
		Integer[] sizes = new Integer[100];
		for (int i = 0; i < sizes.length; i++) {
			sizes[i] = i + 1;
		}
		ObservableList<Integer> items2 = FXCollections.observableArrayList(sizes);
		fontSizes.getItems().addAll(items2);
		fontSizes.setValue(20);

		HBox top = new HBox(5, new Label("Font Name"), fontNames, new Label("Font Size"), fontSizes);
		top.setAlignment(Pos.CENTER);

		pane.setBottom(checkBoxes);
		pane.setCenter(center);
		pane.setTop(top);

		Scene scene = new Scene(pane, 500, 500);
		stage.setTitle("SelectAFont");
		stage.setScene(scene);
		stage.show();

		/* everything below is from book. I was using lamba expressions */
		EventHandler<ActionEvent> handler = e -> {
			if (bold.isSelected() && italic.isSelected()) {
				text.setFont(Font.font(fontNames.getValue(), FontWeight.BOLD, FontPosture.ITALIC, fontSizes.getValue()));
			}
			else if (bold.isSelected()) {
				text.setFont(Font.font(fontNames.getValue(), FontWeight.BOLD, fontSizes.getValue()));
			}
			else if (italic.isSelected()) {
				text.setFont(Font.font(fontNames.getValue(), FontPosture.ITALIC, fontSizes.getValue()));
			}
			else {
				text.setFont(Font.font(fontNames.getValue(), FontWeight.NORMAL, FontPosture.REGULAR, fontSizes.getValue()));
			}
		};

		fontNames.setOnAction(handler);
		fontSizes.setOnAction(handler);
		bold.setOnAction(handler);
		italic.setOnAction(handler);
	}//end start

	public static void main(String[] args) {
		launch(args);
	}
}//end class