//converts an infix expression to a postfix expression

import java.util.*;

public class ConvertInfixToPostfix {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		String expression = input.nextLine();

		System.out.println(infixToPostfix(expression));
	}//end main

	public static String insertBlanks(String s) {
		String result = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '+' || s.charAt(i) == '-' ||
				s.charAt(i) == '*' || s.charAt(i) == '/' ||
				s.charAt(i) == '(' || s.charAt(i) == ')')
				result += " " + s.charAt(i) + " ";
			else
				result += s.charAt(i);
		}
		return result;
	}

	public static String infixToPostfix(String expression) {
		String expandedExpression = insertBlanks(expression);
		String[]tokens = expandedExpression.split(" ");

		Stack<String> stack = new Stack<>();
		Stack<String> postfix = new Stack<>();

		for (String token: tokens) {
			if (token.length() == 0)
				continue;
			else if (token.equals("+") || token.equals("-") ||
					 token.equals("*") || token.equals("/") ||
					 token.equals("(") || token.equals(")"))
					processElements(stack, postfix, token);
			else
				postfix.push(token);
		}
		while (!stack.isEmpty()) {
			postfix.push(stack.pop());
		}

	//	StringBuilder builder = new StringBuilder();
	//	for (String string: postfix) {
	//	    builder.append(string + " ");
	//	}
	//	String solution = builder.toString();

		String solution = "";
		for (String element: postfix)
			solution += element + " ";

		return solution;
	}

	public static void processElements(Stack<String> stack, Stack<String> postfix, String token) {
		if (stack.size() > 0) {
			String peek = "";
			if (token.equals("+")) {
				peek = stack.peek();
				if (peek.equals("*") || peek.equals("/")) { //checking if in front of priority
					postfix.push(stack.pop());
				}
				if (stack.size() > 0) {
					peek = stack.peek();
					if (peek.equals("-") || peek.equals("+")) //checking if element with same priority already in stack
						postfix.push(stack.pop());
				}
				stack.push(token);
			}
			else if (token.equals("-")) {
				peek = stack.peek();
				if (peek.equals("*") || peek.equals("/"))
					postfix.push(stack.pop());
				if (stack.size() > 0) {
					peek = stack.peek();
					if (peek.equals("+") || peek.equals("-"))
						postfix. push(stack.pop());
				}
				stack.push(token);
			}
			else if (token.equals("*")) {
				peek = stack.peek();
				if (peek.equals("/") || peek.equals("*"))
					postfix.push(stack.pop());
				stack.push(token);
			}
			else if (token.equals("/")) {
				peek = stack.peek();
				if (peek.equals("*") || peek.equals("/"))
					postfix.push(stack.pop());
				stack.push(token);
			}
			else if (token.equals(")")) {
				postfix.push(stack.pop());
				stack.pop();
			}
			else if (token.equals("("))
				stack.push(token);
		}
		else
			stack.push(token);
	}
}//end class
