//this program calculates the number(s) with the most occurrences from numbers from the user

import java.util.*;

public class countNumbers {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		Map<Integer, Integer> map = new HashMap<>();

		int max = 0;

		System.out.println("Enter numbers. Press 0 to exit.");
		int num = input.nextInt();

		while(num != 0) {
			if (!map.containsKey(num))
				map.put(num, 1);
			else {
				int value = map.get(num);
				value++;
				map.put(num, value);
			}
			num = input.nextInt();
		}

		Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();

		for (Map.Entry<Integer, Integer> entry: entrySet) {
			if (entry.getValue() >= max)
				max = entry.getValue();
		}

		System.out.println("The number(s) that occurred the most are: ");
		for (Map.Entry<Integer, Integer> entry: entrySet) {
			if (entry.getValue() >= max) {
				System.out.print(entry.getKey() + " ");
			}
		}

	}//end main
}//end class
