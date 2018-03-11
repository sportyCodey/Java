//this program sorts Points

import java.util.*;

public class TestCompare {
	public static void main(String[] args) {
		Point[] points = new Point[100];

		for (int i = 0; i < points.length; i++) {
			points[i] = new Point((int)(Math.random() * 100), (int)(Math.random() * 100));
		}

		List<Point> list = new ArrayList<>();

		for (int i = 0; i < 100; i++) {
			list.add(new Point((int)(Math.random() * 100), (int)(Math.random() * 100)));
		}

		Arrays.sort(points);

		//Collections.sort(list);
		//System.out.println(list);

		System.out.println("ALL THE POINTS:");
		for (int i = 0; i < points.length; i++) {
			System.out.print(points[i].toString());
		}

		System.out.println("\n\n\nSORTED IN INCREASING ORDER OF IT'S X-COORDINATES:");
		for (int i = 0; i < points.length; i++) {
			System.out.print(points[i].getX());
			if (i != 99)
				System.out.print(",");
		}

		Comparator f = new CompareY();
		//Arrays.sort(points, f);
		Arrays.sort(points, new CompareY());

		System.out.println("\n\n\nSORTED IN INCREASING ORDER OF IT'S Y-COORDINATES:");
		 for (int i = 0; i < points.length; i++) {
			System.out.print(points[i].getY());
			if (i != 99)
				System.out.print(",");
		}
		System.out.println();
	}//end main
}//end class
