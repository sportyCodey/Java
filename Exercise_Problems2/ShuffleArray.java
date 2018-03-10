//class that defines a personal shuffle method

import java.util.ArrayList;

public class ShuffleArray {
	public static void main(String[] args) {
		ArrayList<Number> list = new ArrayList<>();

		list.add(10);
		list.add(34);
		list.add(100);
		list.add(-5);
		list.add(34.56);
		list.add(0);
		list.add(67);
		list.add(3445);
		list.add(23);
		list.add(4.5);
		list.add(-90);

		System.out.println("LIST BEFORE SHUFFLING: " + list);

		shuffle(list);

		System.out.println("LIST AFTER SHUFFLING: " + list);
	}

	public static void shuffle(ArrayList<Number> list) {
		int[] duplicates = new int[list.size()];
		initialize(duplicates);
		int random = -1;
		for (int i = 0; i < list.size(); i++) {
			random = getRandom(list);
			//while (isDuplicate(random, duplicates)) { //I don't think I needed this.
			//	random = getRandom(list);
			//}
			duplicates[i] = random;
			Number o = list.set(random, list.get(i));
			list.set(i, o);
		}
	}

	public static boolean isDuplicate(int random, int[] duplicates) {
		for (int i = 0; i < duplicates.length; i++) {
			if (duplicates[i] == random)
				return true;
		}
		return false;
	}

	public static void initialize(int[] duplicates) {
		for (int i = 0; i < duplicates.length; i++) {
			duplicates[i] = -1;
		}
	}

	public static int getRandom(ArrayList<Number> list) {
		return (int)(Math.random() * list.size());
	}

}//end class
