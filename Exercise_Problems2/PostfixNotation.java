/* programt that prints a postfix expression */

import java.util.*;

public class PostfixNotation {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		String postfix = input.nextLine();
		String expression = insertBlanks(postfix);

		String[] tokens = expression.split(" ");

		calculatePostfix(tokens);
	}//end main

	public static String insertBlanks(String s) {
		String result = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '+' || s.charAt(i) == '-' ||
				s.charAt(i) == '*' || s.charAt(i) == '/')
				result += " " + s.charAt(i) + " ";
			else
				result += s.charAt(i);
		}
		return result;
	}

	public static void calculatePostfix(String[] tokens) {
		Stack<Integer> stack = new Stack<>();
		for (String token: tokens) {
			if (token.length() == 0)
				continue;
			else if (token.equals("+") || token.equals("-") ||
				token.equals("*") || token.equals("/"))
					processData(stack, token);
			else
				stack.push(new Integer(token)); //A constructor Integer(String s). Interesting!
		}
		System.out.println("The final result is: " + stack);
	}

	public static void processData(Stack<Integer> stack, String token) {
		int d1 = stack.pop();
		int d2 = stack.pop();
		if (token.equals("+"))
			stack.push(d1 + d2);
		else if (token.equals("-")) {
		//	if (d1 > d2)
				stack.push(d2 - d1); //I don't think this is necessary. In a mathematical equation you won't put (2 - 3).
			//else
				//stack.push(d2 - d1);
		}
		else if (token.equals("*"))
			stack.push(d1 * d2);
		else
			stack.push(d2 / d1);
	}
}//end class
