//This program creates the class RegularPolygon.

public class RegularPolygon{
	private int n; //defines the number of sides
	private double side; //stores the length of the side
	private double x; //defines the x-coordinate
	private double y; // defines the y-coordinate

	public RegularPolygon(){
		n = 3;
		side = 1;
	 	x = 0;
		y = 0;
	}

	public RegularPolygon(int someN,double someSide,double someX,double someY){
		this.n = someN;
		this.side = someSide;
		this.x = someX;
		this.y = someY;
	}

	public int getN(){
		return n;
	}

	public void setN(int newN){
		n = newN;
	}

	public double getSide(){
		return side;
	}

	public void setSide(double newSide){
		side = newSide;
	}

	public double getX(){
		return x;
	}

	public void setX(double newX){
		x = newX;
	}

	public double getY(){
		return y;
	}

	public void setY(double newY){
		y = newY;
	}

	public double getArea(){
		return (n * side * side)/(Math.tan(Math.PI/n)*4);
	}

	public double getPerimeter(){
		return 2 * (n + n);
	}
}//end class Regular Polygon





