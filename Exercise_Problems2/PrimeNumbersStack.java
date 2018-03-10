//prints prime numbers using a Stack

public class PrimeNumbersStack {
	public static void main(String[] args) {
		GenericStack<Integer> stack = new GenericStack<>();

		int numberGenerator = 1;
		int count = 0;
		int divisibleCount = 0;

		while (count < 50) {
			divisibleCount = 0;
			for (int i = 1; i <= numberGenerator; i++) {
				if (divisibleCount > 2)
					break;
				if (numberGenerator % i == 0) {
					divisibleCount++;
				}
			}
			if (divisibleCount == 2) {
				stack.push(numberGenerator);
				count++;
			}
			numberGenerator++;
		}


		System.out.println("The first 50 prime numbers are:\n");
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
			if (stack.getSize() % 10 == 0)
				System.out.println();
		}
	}//end main
}//end class
