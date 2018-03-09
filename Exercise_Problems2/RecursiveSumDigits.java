public class RecursiveSumDigits {
	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);

		long n = input.nextInt();

		System.out.println(sumDigits(n));
	}

	public static int sumDigits(long n) {
		long count = 0;

		if (n != 0) {
			count = n % 10;
			n /= 10;
			count += sumDigits(n);
		}

		return (int)count;
	}
}


