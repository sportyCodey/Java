public class RecursiveReverseString {
	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);

		String str = input.nextLine();
		reverseDisplay(str);

		System.out.println();
	}

	public static void reverseDisplay(String value) {
		if (!value.isEmpty()) {
			char ch = value.charAt(value.length() - 1);
			System.out.print(ch);

			String temp = value.substring(0, value.length() - 1);
			reverseDisplay(temp);
		}
	}
}