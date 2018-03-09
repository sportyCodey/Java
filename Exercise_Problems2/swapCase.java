//This program returns a new string in which the uppercase
//letters are changed to lowercase and lowercase letters
//are changed to uppercase.

import java.util.Scanner;

public class swapCase{
	public static String swapCase(String str){
		StringBuilder sb = new StringBuilder(str);

		for(int i = 0; i < sb.length(); i++){
			char ch = sb.charAt(i);

			if(Character.isUpperCase(ch))
				sb.setCharAt(i,Character.toLowerCase(ch));
			else
				sb.setCharAt(i,Character.toUpperCase(ch));

		}//end for loop

		return sb.toString();
	}//end swapCase

	public static void main(String[] args){

		Scanner input = new Scanner(System.in);

		System.out.println("Enter a string ");
		String name = input.next();

		System.out.println("The new string is: " + swapCase(name));
	}//end main

}//end class swapCase