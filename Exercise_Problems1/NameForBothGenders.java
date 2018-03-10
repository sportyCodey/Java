//reads from a file and displays names that are in both genders

import java.util.*;
import java.io.*;

public class NameForBothGenders {
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a file name for baby name ranking: ");
		String file = input.next();

		Set<String> boy = new HashSet<>();
		Set<String> girl = new HashSet<>();

		if (!new File(file).exists()) {
			System.out.println("There is not file of the name " + file);
		}
		else {
			Scanner scanFile = new Scanner(new File(file));
			while (scanFile.hasNext()) {
				int startIndex = 0;
				int endIndex = 0;
				String line = scanFile.nextLine();
				startIndex = getLetterIndex(line, 0);
				endIndex = getWhitespaceIndex(line, startIndex + 1);

				String boyName = line.substring(startIndex, endIndex);

				startIndex = getLetterIndex(line, endIndex + 1);
				endIndex = getWhitespaceIndex(line, startIndex + 1);

				String girlName = line.substring(startIndex, endIndex);

				boy.add(boyName);
				girl.add(girlName);

				/*
				 while (input.hasNext()) {
				      input.nextInt(); // Skip an integer for ranking
				      boyNames.add(input.next());
				      input.nextInt(); // Skip an integer for number of boys
				      girlNames.add(input.next());
				      input.nextInt(); // Skip an integer for number of girls
    			}
				*/

				//this is what I had originally
				//String[] tokens = line.split("[0123456789,]"); //just FYI | is the regex operator OR //also regex - a delimiting regular expression
				//for (int i = 0; i < tokens.length; i++) {
				//	if (!tokens[i].isEmpty()) {
				//		System.out.println(tokens[i].trim());
				//	}
				//}
			}

			boy.retainAll(girl);
			int count = 0;
			for (String names: boy) {
				count++;
			}

			System.out.println(count + " names used for both genders\nThey are");
			for (String names: boy) {
				System.out.println(names + " ");
			}
		}
	}//end main

	public static int getLetterIndex(String line, int starter) {
		int index = 0;
		for (int i = starter; i < line.length(); i++) {
			if (Character.isLetter(line.charAt(i))) {
				index = i;
				break;
			}
		}
		return index;
	}

	public static int getWhitespaceIndex(String line, int starter) {
		int index = 0;
		for (int i = starter; i < line.length(); i++) {
			if (Character.isWhitespace(line.charAt(i))) {
				index = i;
				break;
			}
		}
		return index;
	}
}//end class
