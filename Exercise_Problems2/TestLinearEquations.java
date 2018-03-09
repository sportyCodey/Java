//This program asks the user to type in values for
//a,b,c,d,e,and f. It calculates the values in the
//X and Y formula.


import java.util.Scanner;

public class TestLinearEquations{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);

		System.out.print("Please enter a,b,c,d,e,and f");
		double a = input.nextDouble();
		double b = input.nextDouble();
		double c = input.nextDouble();
		double d = input.nextDouble();
		double e = input.nextDouble();
		double f = input.nextDouble();

		LinearEquation Equation =
			new LinearEquation(a,b,c,d,e,f);

		if (Equation.isSolvable() == true){
			System.out.println("The solution to the X equation is " +
			Equation.getX());
			System.out.println("The solution to the Y equation is " +
			Equation.getY());
		}
		else
			System.out.println("The equation has no solution ");

	}//end main
}//end class TestLinearEquation

