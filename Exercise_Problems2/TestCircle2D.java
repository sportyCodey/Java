//This program creates a circle object and
//checks to see if the circle contains values in it
//or overlaps.

public class TestCircle2D{
	public static void main(String[] args){

		Circle2D c1 = new Circle2D(2.0,2.0,5.5);

		System.out.println("The area of Circle 1 is " +
		c1.getArea());
		System.out.println("The perimeter of Circle 1 is " +
		c1.getPerimeter());

		if(c1.contains(3,3) == true)
			System.out.println("C1 contains 3,3 ");
		else
			System.out.println("C1 does not contain 3,3 ");

		if(c1.contains(new Circle2D(4,5,10.5)) == true)
			System.out.println("C1 contains 4,5,10.5 ");
		else
			System.out.println("C1 does not contain 4,5,10.5 ");

		if(c1.overlaps(new Circle2D(3,5,2.3)) == true)
			System.out.println("C1 contains 3,5,2.3 ");
		else
			System.out.println("C1 does not contain 3,5,2.3 ");

	}//end main
}//end class TestCircle2D

