//This program creates the class Circle2D

public class Circle2D{
	private double x;
	private double y;
	private double radius;

	public Circle2D(){
		x = 0;
		y = 0;
		radius = 1;
	}

	public Circle2D(double X,double someY,double someRadius){
		this.x = X;
		this.y = someY;
		this.radius = someRadius;
	}

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public double getRadius(){
		return radius;
	}

	public double getArea(){
		double area = (Math.PI)*(radius * radius);
		return area;
	}

	public double getPerimeter(){
		double circumference = (2)*(Math.PI * radius);
		return circumference;
	}

	public boolean contains(double someX,double someY){
		if(distance(x,y,someX,someY) <= radius)
		return true;
		else return false;
	}

	public boolean contains(Circle2D myCircle2D){
		if(distance(x,y,myCircle2D.getX(),myCircle2D.getY()) <= radius)
		return true;
		else return false;
	}

	public boolean overlaps(Circle2D myCircle2D){
		if(distance(x,y,myCircle2D.getX(),myCircle2D.getY()) <= radius)
		return true;
		else return false;
	}

	private static double distance(double x1,double y1,double x2,double y2){
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
}//end class Circle2D