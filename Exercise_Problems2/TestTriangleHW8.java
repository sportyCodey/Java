//this program tests Triangle

import java.util.Scanner;

public class TestTriangleHW8{
	public static void main(String[] args){

		System.out.println("Enter three sides for a triangle: ");
		Scanner input = new Scanner(System.in);
		double side1 = input.nextDouble();
		double side2 = input.nextDouble();
		double side3 = input.nextDouble();

		System.out.println("Enter the color of the triangle: ");
		String color = input.next();

		System.out.println("Enter true or false if the triangle is filled or not: ");
		boolean filled = input.nextBoolean();

		Triangle t1 = new Triangle(side1,side2,side3,color,filled);


		System.out.println("The area is " + t1.getArea());
		System.out.println("The perimeter is " + t1.getPerimeter());
		System.out.println("The color is " + t1.getColor());
		System.out.println("The triangle is filled " + t1.isFilled());

	}
}





