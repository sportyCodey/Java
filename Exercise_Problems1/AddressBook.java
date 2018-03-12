//GUI app to let user put in addresses
//if you're putting in a new address, click the "Add" button 
//if you want to update an address, click the "Update" button
//as of now, there is a flaw if you hit the "Update" button when you want to add a new entry,
//so if you want to add a new entry, then hit the "Add" button, not the "Update"
//this app destroys the info after each time the user closes the app
//this can very easily be changed by commenting out addressBook.setLength(0);
//I haven't changed this for testing purposes

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.geometry.*;
import javafx.scene.Node;
import java.io.*;

public class AddressBook extends Application {
	private RandomAccessFile addressBook;

	private AddressBookPane pane = new AddressBookPane();

	private boolean firstEntry = true;

	private int entries = 0;

	private int pointer = 0;

	@Override
	public void start(Stage stage) throws IOException {
		addressBook = new RandomAccessFile("MyAddressBook.dat", "rw");

		addressBook.setLength(0);

		pane.add.setOnAction(e -> add());
		pane.first.setOnAction(e -> getFirst());
		pane.last.setOnAction(e -> getLast());
		pane.update.setOnAction(e -> update());
		pane.next.setOnAction(e -> next());
		pane.previous.setOnAction(e -> previous());

		Scene scene = new Scene(pane, 330, 200);
		stage.setTitle("AddressBook");
		stage.setScene(scene);
		stage.show();

		stage.setOnCloseRequest(e -> {
			try {
				addressBook.close();
				System.out.println("addressBook closed successfully");
			}
			catch (IOException ex) {}
		});
	}//end start

	public void add() {
		try {
			if (!isEmpty()) {
				int difference = 0;

				addressBook.seek(addressBook.length());
				addressBook.writeChars(pane.name.getText());

				difference = 32 - (pane.name.getText().length() * 2);

				addressBook.setLength(addressBook.length() + difference);

				if (firstEntry)
					addressBook.seek(addressBook.length());
				else
					addressBook.seek(addressBook.length() + 1);
				addressBook.writeChars(pane.street.getText());

				difference = 32 - (pane.street.getText().length() * 2);

				addressBook.setLength(addressBook.length() + difference);

				addressBook.seek(addressBook.length() + 1);
				addressBook.writeChars(pane.city.getText());

				difference = 20 - (pane.city.getText().length() * 2);

				addressBook.setLength(addressBook.length() + difference);

				addressBook.seek(addressBook.length() + 1);
				addressBook.writeChars(pane.state.getText());

				difference = 4 - (pane.state.getText().length() * 2);

				addressBook.setLength(addressBook.length() + difference);

				addressBook.seek(addressBook.length() + 1);
				addressBook.writeChars(pane.zip.getText());

				difference = 10 - (pane.zip.getText().length() * 2);

				addressBook.setLength(addressBook.length() + difference);
				pointer = (int)addressBook.getFilePointer() + difference;

				firstEntry = false;

				entries++;
			}
		}
			catch (IOException ex) {
				System.out.println(ex);
			}
		pane.clear();
		pane.getTF().requestFocus();
	}

	public void next() {
		try {
			if (addressBook.length() > 0) {
				int value = pointer;

				if (value == addressBook.length()) {
						getFirst();
						return;
				}

				pane.name.setText(getData(value, value + 32, "", false));
				value += 33;

				pane.street.setText(getData(value, value + 32, "", false));
				value += 33;

				pane.city.setText(getData(value, value + 20, "", false));
				value += 21;

				pane.state.setText(getData(value, value + 4, "", false));
				value += 5;

				pane.zip.setText(getData(value, value + 10, "", true));
			}
		}
		catch (IOException ex) {
			System.out.println(ex);
		}
		pointer += 102;
	}

	public void previous() {
		try {
			if (addressBook.length() > 0) {
				int value = pointer;

				if (value == 101) {
					if (entries == 1) {
						getFirst();
						return;
					}
					else {
						getLast();
						return;
					}
				}
				if (value == 203) {
					getFirst();
					return;
				}

				value -= 204;

				pane.name.setText(getData(value, value + 32, "", false));
				value += 33;

				pane.street.setText(getData(value, value + 32, "", false));
				value += 33;

				pane.city.setText(getData(value, value + 20, "", false));
				value += 21;

				pane.state.setText(getData(value, value + 4, "", false));
				value += 5;

				pane.zip.setText(getData(value, value + 10, "", true));
			}
		}
		catch (IOException ex) {
			System.out.println(ex);
		}
		pointer -= 102;
	}

	public void getFirst() {
		try {
			if (addressBook.length() > 0) {
				pane.name.setText(getData(0, 31, "", false));

				pane.street.setText(getData(32, 64, "", false));

				pane.city.setText(getData(65, 85, "", false));

				pane.state.setText(getData(86, 90, "", false));

				pane.zip.setText(getData(91, 101, "", true));
			}
		}
		catch (IOException ex) {
			System.out.println(ex);
		}
		pointer = 101;
	}

	public void getLast() {
		try {
			if (addressBook.length() > 0) {
				int value = (int)addressBook.length() - 102;

				if (entries == 1) {
					getFirst();
					return;
				}

				pane.name.setText(getData(value, value + 32, "", false));
				value += 33;

				pane.street.setText(getData(value, value + 32, "", false));
				value += 33;

				pane.city.setText(getData(value, value + 20, "", false));
				value += 21;

				pane.state.setText(getData(value, value + 4, "", false));
				value += 5;

				pane.zip.setText(getData(value, value + 10, "", true));

				pointer = (int)addressBook.length();
			}
		}
		catch (IOException ex) {
			System.out.println(ex);
		}
	}

	private String getData(int bytes, int byteLength, String data, boolean lastOne) throws IOException {
		addressBook.seek(bytes);
		while (bytes <= byteLength) {
			data += addressBook.readChar();
			bytes += 2;
			if (lastOne && bytes == byteLength) return data;
			addressBook.seek(bytes);
		}
		return data;
	}

	public void update() {
		try {
			if (!isEmpty() && addressBook.length() > 0) {
				if (pointer == 101) {
					pointer = 0;
					firstEntry = true;
				}
				else {
					pointer -= 102;
				}
				int difference = 0;

				addressBook.seek(pointer);

				addressBook.writeChars(pane.name.getText());

				difference = 32 - (pane.name.getText().length() * 2);

				if (firstEntry)
					pointer = (int)addressBook.getFilePointer() + difference;
				else
					pointer = (int)addressBook.getFilePointer() + difference + 1;

				addressBook.seek(pointer);

				addressBook.writeChars(pane.street.getText());

				difference = 32 - (pane.street.getText().length() * 2);

				pointer = (int)addressBook.getFilePointer() + difference + 1;

				addressBook.seek(pointer);

				addressBook.writeChars(pane.city.getText());

				difference = 20 - (pane.city.getText().length() * 2);
				pointer = (int)addressBook.getFilePointer() + difference + 1;

				addressBook.seek(pointer);

				addressBook.writeChars(pane.state.getText());

				difference = 4 - (pane.state.getText().length() * 2);
				pointer = (int)addressBook.getFilePointer() + difference + 1;

				addressBook.seek(pointer);

				addressBook.writeChars(pane.zip.getText());

				difference = 10 - (pane.zip.getText().length() * 2);
				pointer = (int)addressBook.getFilePointer() + difference;

				if (pointer > addressBook.length()) {
					addressBook.setLength(pointer);
					entries++;
				}

				firstEntry = false;
			}
		}
		catch (IOException ex) {
			System.out.println(ex);
		}
	}

	private boolean isEmpty() {
		int count = 0;
		for (Node node: pane.getPane().getChildren()) {
			if (node instanceof TextField) {
				TextField tf = (TextField)node;
				if (tf.getText().isEmpty())
					count++;
			}
		}
		if (count == 5) return true;
		return false;
	}

	public static void main(String[] args) {
		launch(args);
    }
}//end AddressBook

class AddressBookPane extends BorderPane {
	protected TextField name;
	protected TextField street;
	protected TextField city;
	protected TextField state;
	protected TextField zip;

	protected Button add;
	protected Button first;
	protected Button next;
	protected Button previous;
	protected Button last;
	protected Button update;

	private FlowPane userEnter;

	public AddressBookPane() {
		makeInterface();
	}

	public void makeInterface() {
		name = new TextField();
		name.setPrefColumnCount(23);

		street = new TextField();
		street.setPrefColumnCount(23);

		city = new TextField();
		city.setPrefColumnCount(8);

		state = new TextField();
		state.setPrefColumnCount(2);

		zip = new TextField();
		zip.setPrefColumnCount(4);

		userEnter = new FlowPane(5, 5);
		userEnter.setAlignment(Pos.CENTER);

		userEnter.getChildren().addAll(new Label("Name"), name, new Label("Street"), street,
		new Label("City   "), city, new Label("State"), state, new Label("Zip   "), zip);

		HBox buttons = new HBox(10);
		buttons.setAlignment(Pos.CENTER);

		add = new Button("Add");
		first = new Button("First");
		next = new Button("Next");
		previous = new Button("Previous");
		last = new Button("Last");
		update = new Button("Update");

		buttons.getChildren().addAll(add, first, next, previous, last, update);

		setCenter(userEnter);
		setBottom(buttons);
	}//end makeInterface

	public FlowPane getPane() {
		return userEnter;
	}

	public TextField getTF() {
		return name;
	}

	public void clear() {
		for (Node node: userEnter.getChildren()) {
			if (node instanceof TextField) {
				TextField tf = (TextField)node;
				tf.setText("");
			}
		}
	}
}//end classAddressBookPane
