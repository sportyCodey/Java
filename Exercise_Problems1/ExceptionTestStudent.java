//classes that use and test custom Exception types 

public class ExceptionTestStudent {
  public static void main(String[] args) {

      try {
      	SquareWithException sq1 = new SquareWithException(4);
      	System.out.println("Perimeter for sq1: " + sq1.getPerimeter());
      	System.out.println("Area for sq1: " + sq1.getArea());

      	SquareWithException sq2 = new SquareWithException(-3);
      	System.out.println("Perimeter for sq2: " + sq2.getPerimeter());
      	System.out.println("Area for sq2: " + sq2.getArea());

      }

      catch(IllegalSquareException ex) {
	  System.out.println("Error: " + ex.getSides() + " is negative ");
      }
  }
}


class IllegalSquareException extends Exception {
  private int sides;

  public IllegalSquareException(int side1,String s) {
    super(s);
    this.sides = side1;
  }

  public int getSides() {
    return sides;
  }

}

class SquareWithException extends Object {
  int sides;

  /** Constructor */
  public SquareWithException(int side1) throws IllegalSquareException {
    this.sides = side1;
    if(side1 <= 0)
    	throw new IllegalSquareException(sides, "Error");
  }


  public int getArea() {
      return sides * sides;
  }


  public double getPerimeter() {
    return sides * 4;
  }

  @Override
  public String toString() {
    // return the side length.
    return "Square: sides = " + sides;
  }
}


