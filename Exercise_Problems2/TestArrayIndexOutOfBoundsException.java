//This program prompts the user to enter the index of the array,
//then displays the corresponding element value.
//If the specified index is out of bounds, it displays the message “Out of Bounds”

import java.util.*;
import java.lang.ArrayIndexOutOfBoundsException;

public class TestArrayIndexOutOfBoundsException{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);

		int[] myArrayIndex = new int[100];
		for(int i = 0; i < 100; i++){
			myArrayIndex[i] = (int)(Math.random()* 100 + 1);
		}
		System.out.println("Enter an index of the array between "
		+ " 0 and 99");
	    int index = input.nextInt();


			try {

				System.out.println("The number entered is " + myArrayIndex[index]);
			}

			catch(ArrayIndexOutOfBoundsException ex){
				System.out.println("Out of bounds ");
				input.nextLine();
			}
	}//end main
}//end class ArrayIndexOutOufBoundsException


