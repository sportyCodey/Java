/* You can change whether it sorts increasing or decreasing by changing 1 to -1 and so on */
//defines class Point

public class Point implements Comparable<Point> {
	private int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int compareTo(Point point) {
		if (point.getX() < x)
			return 1;
		else if (point.getX() > x)
			return -1;
		else {
			if (point.getY() < y)
				return 1;
			else if (point.getY() > y)
				return -1;
			else
				return 0;
		}
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}//end class Point
