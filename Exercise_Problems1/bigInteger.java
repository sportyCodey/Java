//This program finds the first ten numbers
//greater than Long.MAX_VALUE that are divisible
//by 5 or 6.

import java.math.*;

public class bigInteger{
	public static void main(String[] args){
		BigInteger bigNum = new BigInteger(Long.MAX_VALUE + "");

		bigNum = bigNum.add(BigInteger.ONE);

		int count = 0;

		while(count < 10){

			if((bigNum.remainder(new BigInteger("5")).equals(BigInteger.ZERO))||
			(bigNum.remainder(new BigInteger("6")).equals(BigInteger.ZERO))){
				System.out.println(bigNum);
				count++;
			}

			bigNum = bigNum.add(BigInteger.ONE);
		}//end while loop


	}//end main
}//end class bigInteger