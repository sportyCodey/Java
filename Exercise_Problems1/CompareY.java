//implements Comparator interface so Points can be compared

import java.util.Comparator;

public class CompareY implements Comparator<Point> {
	@Override
	public int compare(Point one, Point two) {
		int y1 = one.getY();
		int y2 = two.getY();

		int x1 = one.getX();
		int x2 = one.getY();

		if (y1 < y2)
			return -1;
		else if (y1 > y2)
			return 1;
		else {
			if (x1 < x2)
				return -1;
			else if (x1 > x2)
				return 1;
			else
				return 0;
		}
	}

}//end class
