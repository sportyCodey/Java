import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class BeanMachineGame extends Application {
	@Override
	public void start(Stage stage) {
		BeanMachine machine = new BeanMachine();

		StackPane center = new StackPane(machine);

		Scene scene = new Scene(center, 500, 500);
		stage.setTitle("BeanMachineGame");
		stage.setScene(scene);
		stage.show();
	}//end start

	public static void main(String[] args) {
		launch(args);
    }
}//end class