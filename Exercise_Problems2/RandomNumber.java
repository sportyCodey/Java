//This program generates 10 random numbers between
//1 and 5, inclusive. The program will then display the number of occurrences of each number.

import java.util.*;

public class RandomNumber {
	public static void main(String[] args) {

		Map<Integer, Integer> counter = new HashMap<>();

		int count = 0;
		for (int i = 0; i < 10; i++) {
			int random = (int)(Math.random() * 6);
			//sSystem.out.println(random);

			if (!counter.containsKey(random))
				counter.put(random, 1);
			else {
				int value = counter.get(random);
				counter.put(random, ++value);
			}
		}

		Set<Map.Entry<Integer, Integer>> set = counter.entrySet();

		for (Map.Entry<Integer, Integer> item: set) {
			System.out.println(item);
		}

	/* the following was my code back in my programming 1 days
	*data structures sure can simplify things :) */

/*
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;

		for(int i = 0; i < 10; i++){
			int value = (int)(Math.random()* 5 + 1);  //generate a random number
			System.out.println(value);

			if (value == 1)
			   count1++;
			else if (value == 2)
			   count2++;
			else if (value == 3)
			   count3++;
			else if (value == 4)
			   count4++;
			else
			   count5++;
		}

		//PRINT OUT THE RESULTS
		System.out.println("Number of 1s is " + count1);
		System.out.println("Number of 2s is " + count2);
		System.out.println("Number of 3s is " + count3);
		System.out.println("Number of 4s is " + count4);
		System.out.println("Numbre of 5s is " + count5);
*/
	}//end main
}//end class RandomNumber
