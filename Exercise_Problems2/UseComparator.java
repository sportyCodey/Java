//implements a Generic selectionSort method using a Comparator 

import java.util.Comparator;

public class UseComparator {
	public static void main(String[] args) {
		//doThis(new Object()); //this works. You need the <E> before void or compiler cannot find symbol "E". E is a generic type.
		//doThis(34); //autoboxing. Also works.

		GeometricObject[] list = {new Circle(5), new Rectangle(4, 5), new Circle(5.5), new Rectangle(2.4, 5), new Circle(0.5),
								  new Rectangle(4, 65), new Circle(4.5), new Rectangle(4.4, 1), new Circle(6.5), new Rectangle(4, 5)};
		selectionSort(list, new GeometricObjectComparator()); //sorting based on area defiend in GeometricObjectComparator.java
		for (int i = 0; i < list.length; i++)
			System.out.println(list[i].toString());

	}//end main

	public static <E> void doThis(E o) {
		System.out.println("Called");
	}

	public static <E> void selectionSort(E[] list, Comparator<? super E> comparator) {
		for (int i = 0; i < list.length - 1; i++) {
		    // Find the minimum in the list[i..list.length-1]
			E currentMin = list[i];
		    int currentMinIndex = i;

		    for (int j = i + 1; j < list.length; j++) {
		    	if (comparator.compare(list[j], currentMin) > 0) { //order all based on < > ==
		      		currentMin = list[j];
		          	currentMinIndex = j;
		        }
		  	}

		    // Swap list[i] with list[currentMinIndex] if necessary;
		    if (currentMinIndex != i) {
		  	  list[currentMinIndex] = list[i];
		      list[i] = currentMin;
		    }
		}
  }
}//end class
