//This program creates three RegularPolygon objects.
//For each object, it displays its perimeter and area.

import java.util.Scanner;

public class TestRegularPolygon{
	public static void main(String[] args){
		final double PI = 3.14159;
		RegularPolygon RP1 = new RegularPolygon();
		RegularPolygon RP2 = new RegularPolygon(6,4,0,0);
		RegularPolygon RP3 = new RegularPolygon(10,4, 5.6, 7.8);

		//displaying results for RP1
		System.out.println("The perimeter of Polygon 1 is " +
		RP1.getPerimeter());
		System.out.println("The area of Polygon 1 is " +
		RP1.getArea());

		//displaying results for RP2
		System.out.println("The perimeter of Polygon 2 is " +
		RP2.getPerimeter());
		System.out.println("The area of Polygon 2 is " +
		RP2.getArea());

		//displaying results for RP3
		System.out.println("The perimeter of Polygon 3 is " +
		RP3.getPerimeter());
		System.out.println("The area of Polygon 3 is " +
		RP3.getArea());
	}//end main
}//end class TestRegularPolygon


