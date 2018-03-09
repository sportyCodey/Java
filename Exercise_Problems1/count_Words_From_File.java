import java.util.*;
import java.io.*;

public class count_Words_From_File {

	public static void counting(File file) throws Exception {
		Map<String, Integer> map = new TreeMap<>();

		Scanner input = new Scanner(file);

		String words = "";

		while(input.hasNext()) {
			words += input.next() + " ";
		}

		String[] tokens = words.split("[ \n\t\r.,;:!?(){}]");

		for (int i = 0; i < tokens.length; i++) {
			String key = tokens[i].toLowerCase();

			if (key.length() > 0 && Character.isLetter(key.charAt(0))) {
				if (!map.containsKey(key)) {
					map.put(key, 1);
				}
				else {
					int value = map.get(key);
					value++;
					map.put(key, value);
				}
			}
		}

		Set<Map.Entry<String, Integer>> entrySet = map.entrySet();

		for (Map.Entry<String, Integer> entry: entrySet)
			System.out.println(entry.getKey() + "\t" + entry.getValue());

	}//end counting

	public static void main(String[] args) throws Exception {

	Scanner input = new Scanner(System.in);

	System.out.print("Enter a file: ");
	String f = input.next();

	File file = new File(f);

	if (file.exists()) {
		counting(file);
	}
	else
		System.out.println("The file, " + f + ", does not exist.");

	}//end main
}//end class