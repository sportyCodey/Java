//This program creates the concrete child class Square from the
//parent GeometricObject.

public class Square extends GeometricObject implements Colorable{
	private double side;

	public Square(){
		side = 1.0;
	}

	public Square(double side){
		this.side = side;
	}

	public double getSide(){
		return side;
	}

	@Override
	public double getArea(){
		return side * side;
	}

	@Override
	public double getPerimeter(){
		return 2 * (side + side);
	}

	@Override
	public void howToColor() {
		System.out.println("Color all four sides.");
	}

	public String toString(){
		return ("This square has an area of " + getArea() + " and "
		+ " a perimeter of " + getPerimeter());
	}

}//end class Square


