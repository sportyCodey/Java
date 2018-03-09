//HW10 - This assignment uses interface Comparable

public class HW10 {
  // Main method
  public static void main(String[] args) {
    // Create two comparable circles
    Circle1 circle1 = new Circle1(5);
    Circle1 circle2 = new Circle1(4);

    // Display the max circle
    Circle1 circle = (Circle1) GeometricObject1.max(circle1, circle2);
    System.out.println("The max circle's radius is " + circle.getRadius());
    System.out.println(circle);
  }
}

abstract class GeometricObject1 implements Comparable<GeometricObject1> {
	//fill in the **** to implement the interface Comparable
	//see page 511 for a hint.


  protected String color;
  protected double weight;

  // Default construct
  protected GeometricObject1() {
    color = "white";
    weight = 1.0;
  }

  // Construct a geometric object
  protected GeometricObject1(String color, double weight) {
    this.color = color;
    this.weight = weight;
  }

  // Getter method for color
  public String getColor() {
    return color;
  }

  // Setter method for color
  public void setColor(String color) {
    this.color = color;
  }

  // Getter method for weight
  public double getWeight() {
    return weight;
  }

  // Setter method for weight
  public void setWeight(double weight) {
    this.weight = weight;
  }

  // Abstract method
  public abstract double getArea();

  // Abstract method
  public abstract double getPerimeter();

  public int compareTo(GeometricObject1 o) {
  //**** need to implemenet the compareTo method
  //see pg. 511 for a hint.
		if(this.getArea() > o.getArea())
			return 1;
		else if(this.getArea() < o.getArea())
			return -1;
		else return 0;
		}

  public static GeometricObject1 max(GeometricObject1 o1, GeometricObject1 o2) {
	  // this method returns the max of o1 and o2.

	  //hint: pay attention to the return type.. either o1 or o2 will be returned.
	  //use area as a measure.

      if (o1.compareTo(o2) > 0)
        return o1;
      else
        return o2;
    }


  }


// Circle.java: The circle class that extends GeometricObject
class Circle1 extends GeometricObject1 {
  protected double radius;

  // Default constructor
  public Circle1() {
    this(1.0, "white", 1.0);
  }

  // Construct circle with specified radius
  public Circle1(double radius) {
    super("white", 1.0);
    this.radius = radius;
  }

  // Construct a circle with specified radius, weight, and color
  public Circle1(double radius, String color, double weight) {
    super(color, weight);
    this.radius = radius;
  }

  // Getter method for radius
  public double getRadius() {
    return radius;
  }

  // Setter method for radius
  public void setRadius(double radius) {
    this.radius = radius;
  }

  // Implement the findArea method defined in GeometricObject
  public double getArea() {
    return radius * radius * Math.PI;
  }

  // Implement the findPerimeter method defined in GeometricObject
  public double getPerimeter() {
    return 2 * radius * Math.PI;
  }

  // Override the equals() method defined in the Object class
  public boolean equals(Circle1 circle) {
    return this.radius == circle.getRadius();
  }

  @Override
  public String toString() {
    return "[Circle] radius = " + radius;
  }

  public int compareTo(Circle1 o) {
	  if(this.radius > o.getRadius())
	  	return 1;
	  else if(this.radius < o.getRadius())
	  	return -1;
	  else
	  	return 0;
	  //**** need to implement the compareTo method
	  // to return 1 if radius is bigger than radius of o,
	  // -1 if less than, and 0 if they are equal.
	  // see page 511 for example.

   }
}
