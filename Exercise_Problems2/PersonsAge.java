//This program reads from the console, a person’s first name,
// current age, and a year in the future and then displays the
// person’s name and future age based on the future year.

import java.util.Scanner;

public class PersonsAge {

	public static void main (String[] args) {
		Scanner input = new Scanner (System.in);

		System.out.print("Enter name: ");
		String name = input.next();

		System.out.print("Enter age: ");
		int currentAge = input.nextInt();

		System.out.print("Enter current year: ");
		int currentYear = input.nextInt();

		System.out.print("Enter future year: ");
		int futureYear = input.nextInt();

		displayFutureAge(name, currentAge, currentYear, futureYear);
	}//end main

	public static void displayFutureAge(String name, int currentAge, int currentYear, int futureYear) {
		int futureAge = 0;
		boolean over = false;

		if (futureYear > currentYear)
			futureAge = currentAge + (futureYear - currentYear);
		else {
			futureAge = currentAge - (currentYear - futureYear);
			over = true;
		}

		if (over) {
			System.out.println(name + " was " + futureAge +
			" years old in the year " + futureYear + ".");
		}
		else {
			System.out.println(name + " will be " + futureAge +
			" years old in the year " + futureYear + ".");
		}
	}

}//end class




