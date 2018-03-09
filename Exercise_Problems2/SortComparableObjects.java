import java.math.*;

public class SortComparableObjects {
  public static void main(String[] args) {
    String[] cities = {"Savannah", "Boston", "Atlanta", "Tampa"};
    java.util.Arrays.sort(cities);
    for (String city: cities)
      System.out.print(city + " ");
    System.out.println();

    BigInteger[] hugeNumbers = {new BigInteger("2323231092923992"),
       new BigInteger("432232323239292"),
       new BigInteger("54623239292")};
    java.util.Arrays.sort(hugeNumbers);
    for (BigInteger number: hugeNumbers)
      System.out.print(number + " ");

	System.out.println();

//THis is from slide 26 and is not part of the original code.
      System.out.println("\n" + new Integer(3).compareTo(new Integer(50)));
	   System.out.println("ABC".compareTo("ABZ"));
	   java.util.Date date1 = new java.util.Date(2013, 1, 1);
	   java.util.Date date2 = new java.util.Date(2012, 1, 1);
       System.out.println(date1.compareTo(date2));
  }
}