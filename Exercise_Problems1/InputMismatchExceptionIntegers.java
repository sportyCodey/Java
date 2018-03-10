//Exception handling in action

import java.util.*;

public class InputMismatchExceptionIntegers{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    boolean continueInput = true;

    do {
      try {
        System.out.print("Enter two integers: ");
        int number1 = input.nextInt();
        int number2 = input.nextInt();

        int sum = number1 + number2;

        // Display the result
        System.out.println("The sum of the two integers are: " + sum);

        continueInput = false;
      }
      catch (InputMismatchException ex){
        System.out.println("Enter an acceptable integer ");
        input.nextLine(); // discard input
      }
    } while (continueInput);
  }//end main
}//end class InputMismatchExceptionIntegers
