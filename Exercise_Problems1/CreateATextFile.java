//creates a textfile 

import java.io.*;

public class CreateATextFile {
	public static void main(String[] args) throws IOException {
		try (PrintWriter output = new PrintWriter(new FileOutputStream("Exercise17_01.txt", true));) {
			insertRandomNumbers(output);
		}

		System.out.println("Done");
	}//end main

	public static void insertRandomNumbers(PrintWriter output) {
		for (int i = 0; i < 100; i++) {
			output.println(i + 1 + ". " + (int)(Math.random() * 100) + " ");
		}
	}
}//end class





