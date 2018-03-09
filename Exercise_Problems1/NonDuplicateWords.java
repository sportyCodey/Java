import java.util.*;
import java.io.*;

public class NonDuplicateWords {
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out.println("No input given. Terminating program.");
			System.exit(1);
		}

		File file = new File(args[0]);

		if (!file.exists()) {
			System.out.println("There is no file named, " + args[0]);
			System.exit(1);
		}

		Set<String> scanWords = new TreeSet<>();

		Scanner scanFile = new Scanner(file);
/*
		while(scanFile.hasNext()) {
			String word = scanFile.next();
			char ch = word.charAt(0);

			if (Character.isLetter(ch))
				scanWords.add(word);
		}
*/
		//another way to do it (some from solution manuel)
		String s = "";
		while(scanFile.hasNext()) {
			s += scanFile.next() + " ";
		}

		String[] tokens = s.split("[|;|,|.|?|/|:|(|)|\" ]");

		for (int i = 0; i < tokens.length; i++) {
			scanWords.add(tokens[i]);
		}

		System.out.println("The words from " + args[0] + " in ascending "
		+ "order are: \n\n");

		for (String word: scanWords) {
			System.out.println(word);
		}
	}//end main
}//end class