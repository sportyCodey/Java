import java.util.*;

public class hashSets {
	public static void main(String[] args) {

	HashSet<String> set1 = new HashSet<>(Arrays.asList("George", "Jim", "John", "Blake", "Kevin", "Michael"));

	HashSet<String> c1 = (HashSet<String>)(set1.clone());

	Set<String> set2 = new HashSet<>(Arrays.asList("George", "Katie", "Kevin", "Michelle", "Ryan"));

	c1.addAll(set2);

	System.out.println("The union of set1 and set2 are " + c1);

	c1 = (HashSet<String>)(set1.clone());

	c1.removeAll(set2);

	System.out.println("The difference of set1 and set2 are " + c1);

	c1 = (HashSet<String>)(set1.clone());

	c1.retainAll(set2);

	System.out.println("The intersection of set1 and set2 are " + c1);

	System.out.println("\n\nThe original sets are: \t Set 1: " + set1 + "\n" + "\t\t\t Set 2: " + set2);

	}//end main
}//end class