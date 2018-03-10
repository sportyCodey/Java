//program to determine if a Java file uses the symbols in the right places 

import java.util.Scanner;
import java.io.*;
import java.util.Stack;

public class MatchGroupingSymbols {
	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println("No command given.");
			System.exit(1);
		}

		File file = new File(args[0]);

		try {
			scanFile(file);

		}
		catch (FileNotFoundException ex) {
			System.out.println("That file does not exist.");
		}

	}//end main

	public static void scanFile(File file) throws FileNotFoundException {
		Stack<Character> symbols = new Stack<>();
		Scanner scanFile = new Scanner(file);
		while (scanFile.hasNext()) {
			String nextLine = scanFile.nextLine();
			for (int i = 0; i < nextLine.length(); i++) {
				char ch = nextLine.charAt(i);
				if (ch == '(' ||
				    ch == '{' ||
				    ch == '[') {
					symbols.push(ch);
				}
				else if (ch == ')' ||
				   		 ch == '}' ||
				   		 ch == ']') {
					if(!isMatching(symbols, ch)) {
						System.out.println("Not matching!");
						System.exit(1);
					}

				}

			 }
			/* The reason why we have to work with individual characters is because a paranthesese, for example,
			* most likely is abosrbed in the word. For example, new Object(), the paraenthesese would not be counted in the code below.
			String word = scanFile.next();
			if (word.equals("(") || word.equals(")") || word.equals("{") || word.equals("}") || word.equals("[") || word.equals("]"))
				System.out.println(word);
			*/
		}
		scanFile.close();

		if (!symbols.isEmpty())
			System.out.println("Not matching!"); //We have to have this for a particular reason. For example,
													/* public void doThis() {

													} {
													The stack is not empty and the while loop has ended */
		else
			System.out.println("Matching!");
	}

	private static boolean isMatching(Stack<Character> symbols, char ch) {
		if (symbols.size() > 0) {
			if (symbols.peek() == '(' && ch == ')' ||
				symbols.peek() == '{' && ch == '}' ||
				symbols.peek() == '[' && ch == ']') {
					symbols.pop();
					return true;
			}
			else if (symbols.peek() != '(' && ch == ')' ||
					 symbols.peek() != '[' && ch == ']' ||
					 symbols.peek() != '{' && ch == '}')
						return false;
		}
		return false;
	}
}//end class
