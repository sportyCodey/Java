//recurisve program that counts the number of occurrences of a letter in a String

public class RecursiveCountCharacter {
	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);

		String str = input.nextLine();
		char ch = input.next().charAt(0);

		System.out.println(count(str, ch));
	}

	public static int count(String str, char a) {
		int count = 0;

		if (str.length() > 0) {
			count = count(str.substring(1), a);

			if (str.charAt(0) == a)
				count += 1;
			else
				count += 0;
			//+ ((str.charAt(0) == a) ? 1 : 0);
		}

		return count;
	}
}
