//GUI app that simulates OS page replacement algorithms
//small personal project

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.*;
import javafx.geometry.Pos;
import java.util.Arrays;

public class PageReplacementGUI extends Application {
	public static int frames = 0;
	public static int place = 0;
	public static int leave = 0;
	public static int pageFault = 0;
	public static int index = 0;
	public static int f_Counter = 0;
	public static int beginIndex = 1;
	public static int stashIndex = 0;
	public static int blankIndex = 0;
	public static int allBlankIndex = 0;
	public static int lookupIndex = 0;
	public static int locationX = 40;
	public static int locationY = 50;
	public static int endLoop = 0;

	public static int[] pages;
	public static int[] lookup;
	public static int[] frameCompare;
	public static int[] frame_Array;
	public static int[] initialFrames;
	public static int[] compare;
	public static int[] stash = new int[10];

	public static String[] endBlanks = new String[10];
	public static String[] beginBlanks = new String[10];
	public static String[] beginBlanksStash = new String[10];
	public static String[] allBlanks = new String[10];

	public static Button fifo;
	public static Button optimal;
	public static Button lru;
	public static Button reset;
	public static Button result;
	public static Button quick;

	public static boolean entered = false;
	public static boolean f_Entered = false;
	public static boolean beginning = false;
	public static boolean ending = false;
	public static boolean skip = false;
	public static boolean restart = false;
	public static boolean noBlanks = false;
	public static boolean blanksCalc = false;
	public static boolean stop = false;
	public static boolean isInitialize = false;
	public static boolean incremented = false;

	public static TextField string;
	public static TextField frames_Input;

	public static Label alert;
	public static Label text;
	public static Label faults;

	public static void calculateBeginning() {
		int count = 0;
		int frameIndex = 0;
		int spot = 0;
		boolean distinct = true;
		compare = new int[pages.length];
		for (int i = 0; i < compare.length; i++)
				compare[i] = -1;
		if(!isDistinct()) {
			distinct = false;
			for (int i = 0; i < compare.length; i++) {
				if(count == beginBlanks.length) {
					int oldSize = beginBlanks.length;
					beginBlanks = extendArray(beginBlanks);
					for (int j = oldSize; j < beginBlanks.length; j++)
						beginBlanks[j] = "B";
					beginBlanksStash = extendArray(beginBlanksStash);
				}
				if(compare[i] != -1) {
					frame_Array[frameIndex] = compare[i];
					initialFrames[frameIndex] = compare[i];
					frameIndex++;
				}
				else
					beginBlanks[count] = "Empty";
				count++;
			}
		}
		else {
			for (int i = 0; i < pages.length; i++) {
				if(count == beginBlanks.length) {
					int oldSize = beginBlanks.length;
					beginBlanks = extendArray(beginBlanks);
					for (int j = oldSize; j < beginBlanks.length; j++)
						beginBlanks[j] = "B";
					beginBlanksStash = extendArray(beginBlanksStash);
				}
				if (!anyEqual(pages[i], frame_Array)) {
					frame_Array[f_Counter] = pages[i];
					initialFrames[f_Counter] = pages[i];
					index++;
					f_Counter++;
				}
				else
					beginBlanks[count] = "Empty";
				if (f_Counter == frame_Array.length) {
					spot = count;
					break;
				}
				count++;
			}
			int compareCount = 0;
			for (int i = 0; i < compare.length; i++) {
				if (compare[i] != -1)
					compareCount++;
			}
			if (f_Counter != compareCount) {
				count++;
				while(noChange(pages[count])) {
					if(count == beginBlanks.length) {
						int oldSize = beginBlanks.length;
						beginBlanks = extendArray(beginBlanks);
						for (int j = oldSize; j < beginBlanks.length; j++)
							beginBlanks[j] = "B";
						beginBlanksStash = extendArray(beginBlanksStash);
					}
					beginBlanks[count] = "Empty";
					count++;
					spot = count - 1;
				}
			}
		}
		count = 0;
		for (int i = 0; i < beginBlanks.length; i++) {
			if (!beginBlanks[i].equals("B"))
				count++;
		}
		if (count == 0) {
			noBlanks = true;
		}
		else
			blanksCalc = true;
		for (int i = 0; i < beginBlanks.length; i++) {
			beginBlanksStash[i] = beginBlanks[i];
			if (i == allBlanks.length) {
				int oldSize = allBlanks.length;
				allBlanks = extendArray(allBlanks);
				for (int j = oldSize; j < allBlanks.length; j++)
					allBlanks[j] = "A";
			}
			if (frames > pages.length || !distinct)
				allBlanks[i] = beginBlanks[i];
			else if (frames <= pages.length) {
				if (i <= spot)
					allBlanks[i] = beginBlanks[i];
			}
		}
		insertIntoArray(frame_Array.length, frame_Array);
		for (int i = spot + 1; i < pages.length; i++)
			endLoop++;
		index = spot + 1;
	}//end calculateBeginning

	public static void calculateFIFO() {
		calculateBeginning();
		int count = 0;
		int spot = index;
		while(count < endLoop) {
			if (count == endBlanks.length) {
				int oldSize = endBlanks.length;
				endBlanks = extendArray(endBlanks);
				for (int j = oldSize; j < endBlanks.length; j++)
					endBlanks[j] = "E";
			}
			if (!anyEqual(pages[index], frame_Array)) {
				if (leave >= frames)
					leave = 0;
				frame_Array[leave] = pages[index];
				leave++;
				index++;
				insertIntoArray(frame_Array.length, frame_Array);
			}
			else {
				endBlanks[count] = "Empty";
				index++;
			}
			count++;
		}
		count = 0;
		for (int i = 0; i < stash.length; i++) {
			if(stash[i] != -1)
				count++;
		}
		lookup = new int[count];
		for (int i = 0; i < lookup.length; i++)
			lookup[i] = stash[i];
		for (int i = 0; i < allBlanks.length; i++) {
			if (allBlanks[i].equals("B") || allBlanks[i].equals("Empty"))
				spot = i;
		}
		spot += 1;
		for (int i = 0; i < endBlanks.length; i++) {
			if (spot == allBlanks.length) {
				int oldSize = allBlanks.length;
				allBlanks = extendArray(allBlanks);
				for (int j = oldSize; j < allBlanks.length; j++)
						allBlanks[j] = "A";
			}
			allBlanks[spot] = endBlanks[i];
			spot++;
		}
	}//end calculateFIFO

	public static void calculateOPTIMAL () {
		calculateBeginning();
		int spot = index;
		int count = 0;
		int newIndex = 0;
		boolean notFound = false;
		while(count < endLoop) {
			if (count == endBlanks.length) {
				int oldSize = endBlanks.length;
				endBlanks = extendArray(endBlanks);
				for (int j = oldSize; j < endBlanks.length; j++)
					endBlanks[j] = "E";
			}
			if (!anyEqual(pages[index], frame_Array)) {
				int[] newArray = new int[pages.length - index];
				newIndex = index;
				for (int i = 0; i < pages.length - index; i++) {
					newArray[i] = pages[newIndex];
					newIndex++;
				}
				int temp = 0;
				int max = 0;
				for(int i = 0; i < frame_Array.length; i++) {
					temp = indexOf(frame_Array[i], newArray);
					if (temp != -1) {
						if (temp > max)
							max = temp;
					}
					else {
						max = frame_Array[i];
						leave = indexOf(frame_Array[i], frame_Array);
						notFound = true;
						break;
					}
				}
				temp = indexOf(-1, frame_Array);
				if (temp != -1)
					frame_Array[temp] = pages[index];
				else if (notFound)
					frame_Array[leave] = pages[index];
				else {
					leave = indexOf(newArray[max], frame_Array);
					frame_Array[leave] = pages[index];
				}
				notFound = false;
				pageFault++;
				index++;
				insertIntoArray(frame_Array.length, frame_Array);
			}
			else {
				endBlanks[count] = "Empty";
				index++;
			}
			leave = 0;
			count++;
		}
		count = 0;
		for (int i = 0; i < stash.length; i++) {
			if(stash[i] != -1)
				count++;
		}
		lookup = new int[count];
		for (int i = 0; i < lookup.length; i++)
			lookup[i] = stash[i];
		for (int i = 0; i < allBlanks.length; i++) {
			if (allBlanks[i].equals("B") || allBlanks[i].equals("Empty"))
				spot = i;
		}
		spot += 1;
		for (int i = 0; i < endBlanks.length; i++) {
			if (spot == allBlanks.length) {
				int oldSize = allBlanks.length;
				allBlanks = extendArray(allBlanks);
				for (int j = oldSize; j < allBlanks.length; j++)
						allBlanks[j] = "A";
			}
			allBlanks[spot] = endBlanks[i];
			spot++;
		}
	}//end calculateOPTIMAL

	public static void calculateLRU () {
		calculateBeginning();
		int spot = index;
		int count = 0;
		int newIndex = 0;
		boolean notFound = false;
		while(count < endLoop) {
			if (count == endBlanks.length) {
				int oldSize = endBlanks.length;
				endBlanks = extendArray(endBlanks);
				for (int j = oldSize; j < endBlanks.length; j++)
					endBlanks[j] = "E";
			}
			if (!anyEqual(pages[index], frame_Array)) {
				int[] newArray = new int[index + 1];
				newIndex = index;
				for (int i = 0; i < index; i++) {
					newArray[i] = pages[i];
				}
				int temp = 0;
				int max = 100;
				for(int i = 0; i < frame_Array.length; i++) {
					temp = lastIndexOf(frame_Array[i], newArray);
					if (temp != -1) {
						 if (temp < max)
							max = temp;
					}
					else {
						max = frame_Array[i];
						leave = indexOf(frame_Array[i], frame_Array);
						notFound = true;
						break;
					}
				}
				temp = indexOf(-1, frame_Array);
				if (temp != -1)
					frame_Array[temp] = pages[index];
				else if (notFound)
					frame_Array[leave] = pages[index];
				else {
					leave = indexOf(newArray[max], frame_Array);
					frame_Array[leave] = pages[index];
				}
				notFound = false;
				pageFault++;
				index++;
				insertIntoArray(frame_Array.length, frame_Array);
			}
			else {
				endBlanks[count] = "Empty";
				index++;
			}
			leave = 0;
			count++;
		}
		count = 0;
		for (int i = 0; i < stash.length; i++) {
			if(stash[i] != -1)
				count++;
		}
		count = 0;
		for (int i = 0; i < stash.length; i++) {
			if(stash[i] != -1)
				count++;
		}
		lookup = new int[count];
		for (int i = 0; i < lookup.length; i++)
			lookup[i] = stash[i];
		for (int i = 0; i < allBlanks.length; i++) {
			if (allBlanks[i].equals("B") || allBlanks[i].equals("Empty"))
				spot = i;
		}
		spot += 1;
		for (int i = 0; i < endBlanks.length; i++) {
			if (spot == allBlanks.length) {
				int oldSize = allBlanks.length;
				allBlanks = extendArray(allBlanks);
				for (int j = oldSize; j < allBlanks.length; j++)
						allBlanks[j] = "A";
			}
			allBlanks[spot] = endBlanks[i];
			spot++;
		}
	}//end calculateLRU

	public static boolean isDistinct() {
		int count = 0;
		for (int i = 0; i < pages.length; i++) {
			if(!anyEqual(pages[i], compare))
				compare[i] = pages[i];
		}
		for (int i = 0; i < compare.length; i++) {
			if(compare[i] != -1)
				count++;
		}
		if(count >= frames)
			return true;
		return false;
	}

	public static void getNoDuplicates() {
		for (int i = 0; i < pages.length; i++) {
			if(!anyEqual(pages[i], compare))
				compare[i] = pages[i];
		}
	}

	public static boolean allThereIs() {
		for (int i = 0 ; i < compare.length; i++) {

		}
		return true;
	}

	public static boolean noChange(int value) {
		if(anyEqual(value, frame_Array))
			return true;
		return false;
	}

	public static void insertIntoArray(int n, int A[]) {
		for (int i = 0; i < n; i++) {
			if (stashIndex == stash.length) {
				int oldSize = stash.length;
				stash = extendArray(stash);
				for (int j = oldSize; j < stash.length; j++)
					stash[j] = -1;
			}
			stash[stashIndex] = A[i];
			stashIndex++;
		}
	}

	public static boolean anyEqual(int key, int A[]) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] == key) {
				return true;
			}
		}
		return false;
	}

	public static int lastIndexOf(int key, int A[]) {
		int max = -1;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == key) {
				if (i > max)
					max = i;
			}
		}
		if (max != -1)
			return max;
		else
			return -1;
	}

	public static int indexOf(int key, int A[]) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] == key)
				return i;
		}
		return -1;
	}

	public static int[] extendArray(int array[]) {
		int oldSize = array.length;
		int newArray[] = new int[oldSize + 10];

		for (int i = 0; i < oldSize; i++) {
		 	newArray[i] = array[i];
		}
		for (int i = 0; i < newArray.length; i++) {
			array = Arrays.copyOf(newArray, i + 2);
		}
		return array;
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

	public static boolean beginningDone(int[] A) {
		int count = 0;
		for (int i = 0; i < A.length; i++) {
			if(anyEqual(A[i], frameCompare))
				count++;
		}
		if(count == frame_Array.length || count == pages.length) {
			stop = true;
			return true;
		}
		return false;
	}

	public static Pane getShapes() {
		Pane shapes = new Pane();

		shapes.getChildren().clear();

		if (!restart) {
			allBlankIndex = 0;
			for (int i = 0; i < place; i++) {
				for (int j = 0; j < frames; j++) {
					Rectangle frame = new Rectangle(30, 30, 30, 30);
					frame.setFill(Color.TRANSPARENT);
					if (allBlanks[allBlankIndex].equals("Empty"))
						frame.setStroke(Color.LIGHTGRAY);
					else
						frame.setStroke(Color.BLACK);
					frame.setStrokeWidth(3);
					frame.setTranslateX(locationX);
					frame.setTranslateY(locationY + 250);
					locationY += 30;
					shapes.getChildren().add(frame);
				}
				allBlankIndex++;
				locationX += 50;
				locationY = 0;
			}
			locationX = 0;
			locationY = 0;

			if (place > frames && beginningDone(initialFrames) && frames <= pages.length) {
				if (noBlanks && blanksCalc)
						ending = true;
				else if (noBlanks && !blanksCalc)
					ending = true;
			}
			else
				beginning = true;
			blankIndex = 0;
			incremented = false;
			if (beginning || ending) {
				int counter = 1;
				for (int i = 0; i < beginIndex; i++) {
					for (int j = 0; j < counter; j++) {
						if (beginBlanks[blankIndex].equals("Empty")) {
							counter -= 1;
							beginBlanksStash[blankIndex] = "B";
							break;
						}
						if (j == 0 && !incremented)
							pageFault++;
						text = new Label();
						text.setText(Integer.toString(lookup[j]));
						if(!stop) {
							if(!anyEqual(lookup[j], frameCompare))
								frameCompare[j] = lookup[j];
						}
						text.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
						text.setTranslateX(locationX + 40);
						text.setTranslateY(locationY + 283);
						locationY += 30;
						shapes.getChildren().add(text);
					}
					locationX += 50;
					locationY = 0;
					counter++;
					blankIndex++;
				}
				int count = 0;
				for (int i = 0; i < beginBlanksStash.length; i++) {
					if (!beginBlanksStash[i].equals("B"))
						count++;
				}
				if (count == 0 && blanksCalc && place > frames && beginningDone(initialFrames)) {
					noBlanks = true;
					skip = true;
				}
				else if (count == 0 && !blanksCalc && beginningDone(initialFrames)) //place > frames - 1)
					skip = true;
				if (!skip)
					beginIndex++;
			}
			if (ending && beginning) {
				lookupIndex = 0;
				blankIndex = 0;
				for (int i = beginIndex; i < place; i++) {
					for (int j = 0; j < frames; j++) {
						if (endBlanks[blankIndex].equals("Empty"))
							break;
						if (j == frames - 1)
							pageFault++;
						text = new Label();
						text.setText(Integer.toString(lookup[frames + lookupIndex]));
						text.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
						text.setTranslateX(locationX + 40);
						text.setTranslateY(locationY + 283);
						locationY += 30;
						shapes.getChildren().add(text);
						lookupIndex++;
					}
					locationX += 50;
					locationY = 0;
					blankIndex++;
				}
			}

			if (place == pages.length) {
				Label done = new Label("The final solution.");
				done.setFont(Font.font("Time New Roman", FontWeight.BOLD, 30));
				done.setTranslateX(500);
				done.setTranslateY(75);
				shapes.getChildren().add(done);
				result.setDisable(true);
				quick.setDisable(true);

			}
		}
		return shapes;
	}//end getShapes

	public HBox getInterface() {
		HBox ui = new HBox(20);

		Label text = new Label("Enter a reference string and press Enter: ");
		text.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
		Label f_Text = new Label("Enter the number of frames and press Enter: ");
		f_Text.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));

		string = new TextField();
		frames_Input = new TextField();
		frames_Input.setPrefColumnCount(2);

		fifo = new Button("FIFO");
		optimal = new Button("OPTIMAL");
		lru = new Button("LRU");
		reset = new Button("Reset");
		result = new Button("Result");
		quick = new Button("Quick");

		ui.setAlignment(Pos.CENTER);
		ui.getChildren().addAll(text, string, f_Text, frames_Input, fifo, optimal, lru, reset, result, quick);
		return ui;
	}

	public Pane getLabel() {
		Pane pane = new Pane();

		pane.getChildren().clear();

		alert = new Label();
		alert.setFont(Font.font("Time New Roman", FontWeight.BOLD, 25));
		alert.setTranslateX(490);
		alert.setTranslateY(150);

		Label info = new Label("Press Reset anytime to start over.");
		info.setFont(Font.font("Time New Roman", FontWeight.BOLD, 25));
		info.setTranslateX(450);
		info.setTranslateY(50);

		faults = new Label();
		faults.setFont(Font.font("Time New Roman", FontWeight.BOLD,25));
		faults.setTranslateX(550);
		faults.setTranslateY(200);

		pane.getChildren().addAll(alert, info, faults);

		if(isInitialize) {
			int x = 35;
			for (int i = 0; i < pages.length; i++) {
				Label individual = new Label();
				individual.setText(Integer.toString(pages[i]));
				individual.setFont(Font.font("Time New Roman", FontWeight.BOLD, 30));
				individual.setTranslateX(x);
				individual.setTranslateY(265);
				x += 50;
				pane.getChildren().add(individual);
			}
		}
		return pane;
	}

	public Pane getInfo() {
		Pane pane = new Pane();

		Label step = new Label("  Step by\n     step");
		step.setFont(Font.font("Time New Roman", FontWeight.BOLD, 12));
		Label all = new Label(" Immediate\n    Answer");
		all.setFont(Font.font("Time New Roman", FontWeight.BOLD, 12));
		step.setTranslateX(-65);
		step.setTranslateY(550);
		all.setTranslateX(-5);
		all.setTranslateY(550);

		pane.getChildren().addAll(step, all);
		return pane;
	}

	public static void initializeOthers() {
		frames = Integer.parseInt(frames_Input.getText());
		frameCompare = new int[frames];
		initialFrames = new int[frames];
		frame_Array = new int[frames];
		for (int i = 0; i < frame_Array.length; i++) {
			frame_Array[i] = -1;
			frameCompare[i] = -1;
		}
		for (int i = 0; i < stash.length; i++)
			stash[i] = -1;
		for (int i = 0; i < beginBlanks.length; i++) {
			beginBlanks[i] = "B";
			endBlanks[i] = "E";
			allBlanks[i] = "A";
		}
	}

	public void initializePages() {
		String[] tokens = null;
		int count = 0;
		int[] newArray = new int[10];
		if (string.getText().indexOf(",") != -1) {
			tokens = string.getText().split(",");
			for (int i = 0; i < tokens.length; i++) {
				if (count ==  newArray.length)
					newArray = extendArray(newArray);
				newArray[i] = Integer.parseInt(tokens[i]);
				count++;
			}
		}
		else {
			tokens = string.getText().split(" ");
				for (int i = 0; i < tokens.length; i++) {
					if (count == newArray.length)
						newArray = extendArray(newArray);
					newArray[i] = Integer.parseInt(tokens[i]);
					count++;
				}
		}
		pages = new int[count];
		for (int i = 0; i < pages.length; i++)
			pages[i] = newArray[i];
		isInitialize = true;
	}

	public static void resetArrays() {
		int[] pages;
		int[] lookup;
		int[] compare;
		int[] frame_Array;
		int[] frameCompare;
		int[] initialFrames;
		int[] stash = new int[10];
		String[] endBlanks = new String[10];
		String[] beginBlanks = new String[10];
		String[] beginBlanksStash = new String[10];
		String[] allBlanks = new String[10];
	}

	public static void resetValues() {
		frames = 0;
		place = 0;
		leave = 0;
		pageFault = 0;
		index = 0;
		f_Counter = 0;
		beginIndex = 1;
		stashIndex = 0;
		blankIndex = 0;
		allBlankIndex = 0;
		lookupIndex = 0;
		locationX = 40;
		locationY = 50;
		endLoop = 0;
	}

	public static void resetBoolean() {
		entered = false;
		f_Entered = false;
		beginning = false;
		ending = false;
		skip = false;
		restart = true;
		noBlanks = false;
		blanksCalc = false;
		stop = false;
		isInitialize = false;
		incremented = false;
	}

	@Override
	public void start(Stage primaryStage) {
		BorderPane pane = new BorderPane();

		pane.setBottom(getInterface());
		pane.setTop(getLabel());
		pane.setRight(getInfo());

		result.setOnAction( e -> {
			restart = false;
			if (!entered) {
				if (!string.getText().equals("")) {
					if (Character.isLetterOrDigit(string.getText().charAt(0)))
						alert.setText("Press Enter to submit.");
				}
				else
					alert.setText("Enter the required input.");
			}
			if (!f_Entered) {
				if (!frames_Input.getText().equals("")) {
					if (Character.isLetterOrDigit(frames_Input.getText().charAt(0)))
						alert.setText("Press Enter to submit.");
				}
				else
						alert.setText("Enter the required input.");
			}
			else {
				locationX = 0;
				locationY = 0;
				place++;
				pane.setCenter(getShapes());
				pane.setTop(getLabel());
				faults.setText("Page Faults: " + Integer.toString(pageFault));
				pageFault = 0;
			}
		});

		quick.setOnAction( e -> {
			restart = false;
			if (!entered) {
				if (!string.getText().equals("")) {
					if (Character.isLetterOrDigit(string.getText().charAt(0)))
						alert.setText("Press Enter to submit.");
				}
				else
					alert.setText("Enter the required input.");
			}
			if (!f_Entered) {
				if (!frames_Input.getText().equals("")) {
					if (Character.isLetterOrDigit(frames_Input.getText().charAt(0)))
						alert.setText("Press Enter to submit.");
				}
				else
						alert.setText("Enter the required input.");
			}
			else {
				place = 0;
				pageFault = 0;
				for (int i = 0; i < pages.length; i++) {
					locationX = 0;
					locationY = 0;
					place++;
					pane.setCenter(getShapes());
					pane.setTop(getLabel());
					faults.setText("Page Faults: " + Integer.toString(pageFault));
					pageFault = 0;
				}
			}
		});

		fifo.setOnAction( e -> {
			restart = false;
			if (!entered) {
				if (!string.getText().equals("")) {
					if (Character.isLetterOrDigit(string.getText().charAt(0)))
						alert.setText("Press Enter to submit.");
				}
				else
					alert.setText("Enter the required input.");
			}
			if (!f_Entered) {
				if (!frames_Input.getText().equals("")) {
					if (Character.isLetterOrDigit(frames_Input.getText().charAt(0)))
						alert.setText("Press Enter to submit.");
				}
				else
						alert.setText("Enter the required input.");
			}
			else {
				calculateFIFO();
				fifo.setDisable(true);
				optimal.setDisable(true);
				lru.setDisable(true);
				result.setDisable(false);
				quick.setDisable(false);
				reset.setDisable(false);
			}

		});

		optimal.setOnAction( e -> {
			restart = false;
			if (!entered) {
				if (!string.getText().equals("")) {
					if (Character.isLetterOrDigit(string.getText().charAt(0)))
						alert.setText("Press Enter to submit.");
				}
				else
					alert.setText("Enter the required input.");
			}
			if (!f_Entered) {
				if (!frames_Input.getText().equals("")) {
					if (Character.isLetterOrDigit(frames_Input.getText().charAt(0)))
						alert.setText("Press Enter to submit.");
				}
				else
						alert.setText("Enter the required input.");
			}
			else {
				calculateOPTIMAL();
				fifo.setDisable(true);
				optimal.setDisable(true);
				lru.setDisable(true);
				result.setDisable(false);
				quick.setDisable(false);
				reset.setDisable(false);
			}
		});

		lru.setOnAction( e -> {
			restart = false;
			if (!entered) {
				if (!string.getText().equals("")) {
					if (Character.isLetterOrDigit(string.getText().charAt(0)))
						alert.setText("Press Enter to submit.");
				}
				else
					alert.setText("Enter the required input.");
			}
			if (!f_Entered) {
				if (!frames_Input.getText().equals("")) {
					if (Character.isLetterOrDigit(frames_Input.getText().charAt(0)))
						alert.setText("Press Enter to submit.");
				}
				else
						alert.setText("Enter the required input.");
			}
			else {
				calculateLRU();
				fifo.setDisable(true);
				optimal.setDisable(true);
				lru.setDisable(true);
				result.setDisable(false);
				quick.setDisable(false);
				reset.setDisable(false);
			}
		});

		reset.setOnAction( e -> {
			faults.setText("");
			resetValues();
			resetArrays();
			resetBoolean();
			string.setText("");
			frames_Input.setText("");
			pane.setCenter(getShapes());
			pane.setTop(getLabel());
			string.setEditable(true);
			frames_Input.setEditable(true);
			fifo.setDisable(false);
			optimal.setDisable(false);
			lru.setDisable(false);
			result.setDisable(false);
			quick.setDisable(false);
		});

		string.setOnKeyPressed( e -> {
			switch(e.getCode()) {
				case ENTER: if (!string.getText().equals("")) {
								initializePages();
								pane.setTop(getLabel());
								entered = true;
							}
			}
		});

		frames_Input.setOnKeyPressed( e -> {
			switch(e.getCode()) {
				case ENTER: if (Integer.parseInt(frames_Input.getText()) > 10)
								alert.setText("       Too many frames.");
							else {
								if (!frames_Input.getText().equals("")) {
									if (!entered) {
										alert.setText("Enter reference string first.");
										frames_Input.setText("");
									}
									else {
										f_Entered = true;
										result.setDisable(true);
										quick.setDisable(true);
										pane.setTop(getLabel());
										frames_Input.setEditable(false);
										string.setEditable(false);
										initializeOthers();
										faults.setText("Page Faults: " + Integer.toString(pageFault));
									}

								}
							}
			}
		});

		Scene scene = new Scene(pane, 500, 500);
		primaryStage.setTitle("PageReplacement");
		primaryStage.setScene(scene);
		primaryStage.show();
	}//end start

	public static void main(String[] args) {
	      launch(args);
    }
}//end class
