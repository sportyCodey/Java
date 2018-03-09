//This program createst the class Triangle which is
//a child class of SimpleGeometricObject

public class Triangle
	extends SimpleGeometricObject{
		double side1 = 1.0;
		double side2 = 1.0;
		double side3 = 1.0;

	public Triangle(){
	}

	public Triangle(double side1,double side2,double side3){
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}

	public Triangle(
		double side1,double side2,double side3,String color,boolean filled){
			this.side1 = side1;
			this.side2 = side2;
			this.side3 = side3;
			setColor(color);
			setFilled(filled);
		}

	public double getArea(){
		double s = (side1 + side2 + side3)/2;
		double area = Math.sqrt(s*(s - side1)*(s - side2)*(s - side3));
		return area;
	}

	public double getPerimeter(){
		double perimeter = side1 + side2 + side3;
		return perimeter;
	}

	public String toString(){
		return "Triangle: side1 = " + side1 + " side2 = " +
		side2 + " side3 = " + side3;
	}

}//end class Triangle


