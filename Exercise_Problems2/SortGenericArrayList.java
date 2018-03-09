import java.util.ArrayList;

public class SortGenericArrayList {
	public static void main(String[] args) {
		ArrayList<Integer> myList = new ArrayList<>();

		myList.add(1000);
		myList.add(-2);
		myList.add(-900);
		myList.add(79);
		myList.add(40);
		myList.add(0);

		sort(myList);
	}

	public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
		System.out.println("THE UNSORTED ARRAY IS: " + list);
		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).compareTo(list.get(j)) > 0) {
					E first = list.get(i);
					E second = list.get(j);
					list.set(i, second);
					list.set(j, first);
				}
			}
		}
		System.out.println("THE SORTED ARRAY IS: " + list);
	}
}

