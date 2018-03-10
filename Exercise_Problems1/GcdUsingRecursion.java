//recursion program to find the GCD

import java.util.Scanner;

public class GcdUsingRecursion {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int m = input.nextInt();

		System.out.println(gcd(n, m));
	}

	public static int gcd(int n, int m) {
		if (m == 0)
			return n;
		else
			return gcd(m, n % m);
	}
}
