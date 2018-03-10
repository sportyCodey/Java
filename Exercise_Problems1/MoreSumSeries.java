//recursion problem for this calculation

public class MoreSumSeries {
	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			System.out.println(m(i));
		}
	}

	public static double m(int i) {
		if (i == 1)
			return 1.0 / 3;
		else
			return m(i - 1) + i / ((2 * i) + 1.0);
	}
}
