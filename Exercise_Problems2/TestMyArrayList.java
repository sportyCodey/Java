public class TestMyArrayList {
  /** Main method */
  public static void main(String[] args) {
    // Create a list for strings
    MyArrayList<String> list = new MyArrayList<>();

    // Add elements to the list
    list.add("America"); // Add it to the list
    System.out.println("(1) " + list);

    list.add(0, "Canada"); // Add it to the beginning of the list
    System.out.println("(2) " + list);

    list.add("Russia"); // Add it to the end of the list
    System.out.println("(3) " + list);

    list.add("France"); // Add it to the end of the list
    System.out.println("(4) " + list);

    list.add(2, "Germany"); // Add it to the list at index 2
    System.out.println("(5) " + list);

    list.add(5, "Norway"); // Add it to the list at index 5
    System.out.println("(6) " + list);

    list.add(0, "Poland"); // Same as list.addFirst("Poland")
    System.out.println("(7) " + list);

    // Remove elements from the list
    list.remove(0); // Same as list.remove("Australia") in this case
    System.out.println("(8) " + list);

   // list.remove(2); // Remove the element at index 2
    System.out.println("(9) " + list);

    list.remove("Germany");

    list.remove(list.size() - 1); // Remove the last element
    System.out.print("(10) " + list + "\n(11) ");

    list.add("Canada");
    list.add(0, "America");

    list.set(2, "Tulsa");
    list.set(4, "Oklahoma");

    System.out.println(list);
    list.set(list.size() - 1, "New York");
    System.out.println(list);

    System.out.println("The one that got erased was: " + list.set(1, "Tulsa"));

    for (String s: list) {
      System.out.print(s.toUpperCase() + " ");
  	}

    System.out.println("\n\nIs the element America in the LinkedList? " + list.contains("America") + "\n");

   	System.out.println("Returning everything using get() method.");
   	for (int i = 0; i < list.size(); i++)
   		System.out.print(list.get(i) + " ");
   	System.out.println();

   	System.out.println("Deeper test. Let's return this element: " + list.get(3) + "\n");

   	System.out.println("What index is France at? " + list.indexOf("France") + "\n");

   	System.out.println("What is the last index America is at? " + list.lastIndexOf("America") + "\n");

   	System.out.println("Is the list empty? " + list.isEmpty());

    java.util.Iterator<String> loop = list.iterator();
	System.out.println("Using an iterator we get...");
    while(loop.hasNext()) {
	   System.out.println(loop.next());
    }

  //System.out.println("IT IS: " + loop.next()); //throws nullpoint because next() is now null.

    System.out.println("The list size is: " + list.size());

    System.out.println("The list is: " + list);

    list.add("FishSticks");
    System.out.println(list);

	System.out.println("Removing all the elements...");
	java.util.Iterator<String> loop2 = list.iterator();
	while(loop2.hasNext()) {
		loop2.next();
		loop2.remove();
	}

    System.out.println("List is now: \n" + list + "\nand the size is: " + list.size());

    list.add("Hudson");
	list.add("Jordan");
	list.add("Shawn");
	list.add("Franky");

	System.out.println("After adding some elements in, the list is now: " + list);

    list.clear();
    System.out.println("\nAfter clearing the list, the list size is "
      + list.size() + "\nSee. It's gone: " + list);

	list.add("Drew");
	list.add("Nick");
	list.add("Morgan");
	list.add("Samuel");
	list.add("Mom");
	list.add("Dad");
	list.add("Grandma");
	list.add("Grandpa");
	list.add("Hudson");
	list.add("Jordan");
	list.add("Shawn");
	list.add("Franky");
	list.add("Mick");

	MyArrayList<String> list2 = new MyArrayList<>();
	list2.add("Mom");
	list2.add("Dad");
	list2.add("Franky");
	list2.add("Drew");
	list2.add("Nick");
	list2.add("Fred");
	list2.add("Fred");
	list2.add("Mick");

	System.out.println("First list: " + list);
	System.out.println("Second list: " + list2);

	list.removeAll(list2);
	System.out.println("After removeAll...");
	System.out.println("First list: " + list);
	System.out.println("Second list: " + list2);

	System.out.println("After retainAll...");
	list.retainAll(list2);
	System.out.println("First list: " + list);

	System.out.println("\n\nSecond list: " + list2 + "\nFirst list: " + list);
	System.out.println(list2.containsAll(list));

	list.addAll(list2);

	System.out.println("It is: " + list);

	Object[] arr = new Object[1];

	arr = list.toArray();

	for (Object data: arr) {
		System.out.println(data);
	}

	System.out.println();

	String[] arr2 = new String[1];
	String[] arr3 = new String[list.size()];
	arr2 = list.toArray(arr2);

	String[] y = list.toArray(new String[0]);

	for (int i = 0; i < arr2.length; i++) {
		System.out.println(arr2[i]);
	}

/* ************************************************************************************************************************************************/



	System.out.println("\n\n\n\nTESTING WITH THE JAVA IMPLEMENTATION\n\n\n\n");



	java.util.ArrayList<String> list3 = new java.util.ArrayList<>();
	java.util.ArrayList<String> list4 = new java.util.ArrayList<>();

	list3.add("America"); // Add it to the list3
	System.out.println("(1) " + list3);

	list3.add(0, "Canada"); // Add it to the beginning of the list3
	System.out.println("(2) " + list3);

	list3.add("Russia"); // Add it to the end of the list3
	System.out.println("(3) " + list3);

	list3.add("France"); // Add it to the end of the list3
	System.out.println("(4) " + list3);

	list3.add(2, "Germany"); // Add it to the list3 at index 2
	System.out.println("(5) " + list3);

	list3.add(5, "Norway"); // Add it to the list3 at index 5
	System.out.println("(6) " + list3);

	list3.add(0, "Poland"); // Same as list3.addFirst("Poland")
	System.out.println("(7) " + list3);

	// Remove elements from the list3
	list3.remove(0); // Same as list3.remove("Australia") in this case
	System.out.println("(8) " + list3);

	//list3.remove(2); // Remove the element at index 2
	System.out.println("(9) " + list3);

	list3.remove("Germany");

	list3.remove(list3.size() - 1); // Remove the last element
	System.out.print("(10) " + list3 + "\n(11) ");

	list3.add("Canada");
	list3.add(0, "America");

	list3.set(2, "Tulsa");
	list3.set(4, "Oklahoma");

	System.out.println(list3);
	list3.set(list3.size() - 1, "New York");
	System.out.println(list3);

	System.out.println("The one that got erased was: " + list3.set(1, "Tulsa"));

	for (String s: list3) {
	  System.out.print(s.toUpperCase() + " ");
    }

	System.out.println("\n\nIs the element America in the LinkedList? " + list3.contains("America") + "\n");

	System.out.println("Returning everything using get() method.");
	for (int i = 0; i < list3.size(); i++)
	   System.out.print(list3.get(i) + " ");
	System.out.println();

	System.out.println("Deeper test. Let's return this element: " + list3.get(3) + "\n");

	System.out.println("What index is France at? " + list3.indexOf("France") + "\n");

	System.out.println("What is the last index America is at? " + list3.lastIndexOf("America") + "\n");

	System.out.println("Is this list empty? " + list3.isEmpty());

	java.util.Iterator<String> iterator = list3.iterator();
	System.out.println("Using an iterator to traverse we get...");
	while(iterator.hasNext()) {
		System.out.println(iterator.next());
	}

	//System.out.println("IT IS: " + iterator.next()); //throws NullPointerException because next() would be null.

	System.out.println("The list size: " + list3.size());

	System.out.println("The list is: " + list3);

	list3.add("FishSticks");
	System.out.println(list3);

	System.out.println("Removing all the elements...");
	java.util.Iterator<String> iterator2 = list3.iterator();
	while (iterator2.hasNext()) {
		iterator2.next();
		iterator2.remove();
	}

    System.out.println("List is now: \n" + list3 + "\nand the size is: " + list3.size());

    list3.add("Hudson");
	list3.add("Jordan");
	list3.add("Shawn");
	list3.add("Franky");

	System.out.println("After adding some elements in, the list is now: " + list3);

    list3.clear();
    System.out.println("\nAfter clearing the list, the list size is "
      + list3.size() + "\nSee. It's gone: " + list3);

	list3.add("Drew");
	list3.add("Nick");
	list3.add("Morgan");
	list3.add("Samuel");
	list3.add("Mom");
	list3.add("Dad");
	list3.add("Grandma");
	list3.add("Grandpa");
	list3.add("Hudson");
	list3.add("Jordan");
	list3.add("Shawn");
	list3.add("Franky");
	list3.add("Mick");

	list4.add("Mom");
	list4.add("Dad");
	list4.add("Franky");
	list4.add("Drew");
	list4.add("Nick");
	list4.add("Fred");
	list4.add("Fred");
	list4.add("Mick");

	System.out.println("First list: " + list3);
	System.out.println("Second list: " + list4);

	list3.removeAll(list4);
	System.out.println("After removeAll...");
	System.out.println("First list: " + list3);
	System.out.println("Second list: " + list4);

	System.out.println("After retainAll...");
	list3.retainAll(list4);
	System.out.println("First list: " + list3);

	System.out.println("\n\nSecond list: " + list4 + "\nFirst list: " + list3);
	System.out.println(list4.containsAll(list3)); //because list3 is empty, list4 contains everything in list3. It's a matter of logic.
													//there is nothing in list3 that is not in list4

	list3.addAll(list4);
	System.out.println("It is: " + list3);

	Object[] newArr = new Object[1];

	newArr = list.toArray();

	for (Object data: newArr) {
		System.out.println(data);
	}

	System.out.println();

	String[] arr4 = new String[1];
	String[] arr5 = new String[list.size()];
	arr4 = list.toArray(arr4);

	 String[] x = list.toArray(new String[0]);

	for (int i = 0; i < arr4.length; i++) {
		System.out.println(arr4[i]);
	}
  }//end main
}//end class

