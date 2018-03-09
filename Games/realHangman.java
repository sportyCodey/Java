/* the console version of hangman. No big error checks. User has unlimited amount of wrong answers. */

import java.util.Scanner;
import java.util.Arrays;

public class realHangman {

	public static int win = 0;

	public static String[] words = {"HELLO", "FOOD"};

	public static String word = "";

	public static int length = 0;

	public static int wordIndex = -1;

	public static int gameCount = 0;

	public static int random = -1;//(int)(Math.random() * 2);

	public static String[] tokens;// = word.split(" ");

	public static String[] stars;// = new String[length];


	public static String insertBlanks(String s) {
		String result = "";

		for (int i = 0; i < s.length(); i++) {
			result += s.charAt(i) + " ";
		}
		return result;
	}

	public static void reset() {
		win = 0;

		random = (int)(Math.random() * 2);

		while (wordIndex == random) {
			random = (int)(Math.random() * 2);
			if (gameCount == words.length) {
				System.out.println("\nDISCLAIMER. You have played through all the words available.");
				break;
			}
		}

		wordIndex = random;

		length = words[random].length();
		word = insertBlanks(words[random]);

		tokens = word.split(" ");

		stars = new String[length];

		for (int i = 0; i < stars.length; i++) {
				stars[i] = "*";
		}
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

	public static boolean isDuplicate(String array[], int index) {
		for (int i = 0; i < index + 1; i++) {
			for (int j = i + 1; j < index + 1; j++) {
				if (array[i].equals(array[j])) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		reset();

		boolean correct = false;
		boolean playAgain = true;

		String[] guesses = new String[10];
		String guess = "";
		String pg = "";

		int index = 0;

		do {
			System.out.print("The word to guess is: ");
			for (int i = 0; i < stars.length; i++) {
				System.out.print(stars[i] + " ");
			}

			System.out.print("\n\nWhat is your guess? ");
			guess = input.next();

			while (guess.length()!= 1) {
				if (guess.length() != 1) {
					System.out.println("\nYou can only guess 1 character at a time. Guess again.");
					guess = input.next();
				}
			}

			guesses[index] = guess.toUpperCase();
			if (isDuplicate(guesses, index)) {
				System.out.println("\nYou have already guessed that. Guess again.\n");
				continue;
			}

			if (index % 10 == 0)
				guesses = extendArray(guesses);

			index++;

			for (int i = 0; i < tokens.length; i++) {
				if (guess.equalsIgnoreCase(tokens[i])) {
					stars[i] = guess.toUpperCase();
					correct = true;
					win++;
				}
			}

			if (correct)
				System.out.println("\nCorrect!\n");

			else
				System.out.println("\nYou did not guess correctly.\n");

			correct = false;

			if (win == stars.length) {
				System.out.println("You win!\n");
				System.out.println("The final word was: \n");
				for (int i = 0; i < stars.length; i++)
					System.out.print(stars[i] + " ");
				System.out.println("\n\nDo you want to play again? Y/N");
				pg = input.next();

				if (pg.equals("Y")) {
					gameCount++;
					reset();
					for (int i = 0; i < index + 1; i++)
						guesses[i] = null;
					index = 0;
					System.out.println();
				}
				else
					playAgain = false;
			}

		} while(playAgain);

	}// end main
}//end class
