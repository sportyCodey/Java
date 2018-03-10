//defines the class Octagon that is both Comparable and Cloneable

public class Octagon extends GeometricObject implements Comparable<Octagon>, Cloneable {
	private int side;

	public Octagon(String color, boolean filled, int side) {
		super(color, filled);
		this.side = side;
	}

	@Override
	public double getArea() {
		return (2 + (4 * Math.sqrt(2))) * side * side;
	}

	@Override
	public double getPerimeter() {
		return side * 8;
	}

	@Override
	public int compareTo(Octagon o) {
		if (getArea() > o.getArea())
			return 1;
		else if (getArea() < o.getArea())
			return -1;
		else if (getArea() == o.getArea())
			return 2;
		else
			return 0;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}//end class
