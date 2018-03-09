import java.util.*;

public class GuessTheCapitals {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		Map<String,String> entry = new LinkedHashMap<>(); //I'm using a LinkedHashMap because I want it to be in order. However,
														//using a hash map or tree set would mix it up which would make it challenging
		setUp(entry);

		int count = 0;

		Set<Map.Entry<String,String>> set = entry.entrySet();
		for (Map.Entry<String,String> value: set) {
			System.out.println("What is the capital of " + value.getKey() + "?");
			String answer = input.nextLine();

			if (!answer.equalsIgnoreCase(value.getValue()))
				System.out.println("The correct answer should be " + value.getValue());
			else  {
				System.out.println("Your answer is corrct");
				count++;
			}
		}

		System.out.println("\nThe correct count is " + count);
	}//end main

	public static void setUp(Map<String,String> entry) {
		entry.put("Alabama", "Montgomery");
		entry.put("Alaska", "Juneau");
		entry.put("Arizona", "Phoenix");
		entry.put("Arkansas", "Little Rock");
	}

}//end class