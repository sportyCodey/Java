//The program uses the IllegalTriangleException
//if one of the sides is invalid.

import java.util.Scanner;

public class TestTriangleHW9{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    System.out.print("Enter three sides: ");
    double side1 = input.nextDouble();
    double side2 = input.nextDouble();
    double side3 = input.nextDouble();

    try {

    Triangle2 triangle = new Triangle2(side1, side2, side3);
     System.out.println("The area is " + triangle.getArea());
	 System.out.println("The perimeter is "
	 + triangle.getPerimeter());
	 System.out.println(triangle);

	}

	catch(IllegalTriangleException ex){
		System.out.println(ex);
	}


  }//end main
}//end class TestTriangleHW9

class Triangle2{
  private double side1 = 1.0, side2 = 1.0, side3 = 1.0;

  /** Constructor */
  public Triangle2(){
  }

  /** Constructor */
  public Triangle2(double side1,double side2,double side3)
  		throws IllegalTriangleException{
	if((side1 + side2 > side3) && (side2 + side3 > side1)
	&& (side1 + side3 > side2)){
	this.side1 = side1;
	this.side2 = side2;
	this.side3 = side3;
	}
	else
	throw new IllegalTriangleException(side1,side2,side3);
  }

  public double getSide1(){
    return side1;
  }

  public double getSide2(){
    return side2;
  }

  public double getSide3(){
    return side3;
  }


  public double getArea(){
    double s = (side1 + side2 + side3) / 2;
    return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
  }


  public double getPerimeter(){
    return side1 + side2 + side3;
  }


  public String toString(){
    return "Triangle: side1 = " + side1 + " side2 = " + side2 +
      " side3 = " + side3;
  }
}
