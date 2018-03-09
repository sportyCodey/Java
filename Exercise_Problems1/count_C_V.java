import java.util.*;
import java.io.*;

public class count_C_V {

	public static void counting(File file) throws Exception {
		String[] Vowels = {"A", "E", "I", "O", "U"};

		String[] tokens;

		int V_count = 0;
		int C_count = 0;

		Set<String> vowels = new HashSet<>(Arrays.asList(Vowels));

		Scanner input = new Scanner(file);

		String letter = "";

		while(input.hasNext()) {
			letter += input.next().toUpperCase();
		}

		letter = insertBlanks(letter);

		tokens = letter.split(" ");

		for (int i = 0; i < tokens.length; i++) {
			if (vowels.contains(tokens[i]))
				V_count++;
			else if (Character.isLetter(tokens[i].charAt(0)))
				C_count++;
		}

		System.out.println("The number of vowels are: " + V_count);
		System.out.println("The number of consonants are: " + C_count);
	}

	public static String insertBlanks(String s) {
		String result = "";

		for (int i = 0; i < s.length(); i++) {
			result += s.charAt(i) + " ";
		}
		return result;
	}

	public static void main(String[] args) throws Exception {

		Scanner input = new Scanner(System.in);

		System.out.print("Enter a file: ");
		String f = input.next();

		File file = new File(f);

		if (file.exists()) {
			counting(file);
		}
		else
			System.out.println("The file, " + f + ", does not exist.");
	}//end main
}//end class