//recurisve program that reverses a digit

import java.util.*;

public class RecursiveReverseDigit {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.println("Enter an integer: ");

		boolean badData = true;
		do {
			try {
				int value = input.nextInt();
				reverseDisplay(value);
				System.out.println();
				badData = false;
			}
			catch (InputMismatchException ex) {
				System.out.println("Enter an integer: " + ex);
				input.nextLine();
			}
		} while (badData);
	}

	public static void reverseDisplay(int value) {
		if (value != 0) {
			int temp = value % 10;
			System.out.print(temp);
			value /= 10;
			reverseDisplay(value);
		}
	}
}
