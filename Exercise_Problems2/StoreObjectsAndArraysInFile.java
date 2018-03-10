//writes Objects and Arrays to binary file

import java.io.*;

public class StoreObjectsAndArraysInFile {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		int[] numbers = {1,2,3,4,5};

		try (ObjectOutputStream output = new ObjectOutputStream(
			new BufferedOutputStream(new FileOutputStream("Exercise17_05.dat")));) {

			output.writeObject(numbers);
			output.writeObject(new java.util.Date());
			output.writeDouble(5.5);
		}

		try (ObjectInputStream input = new ObjectInputStream(
			new BufferedInputStream(new FileInputStream("Exercise17_05.dat")));) {

			int[] arr = (int[])input.readObject();
			java.util.Date date = (java.util.Date)input.readObject();

			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println("\n" + date + "\n" + input.readDouble());
		}

		System.out.println("Done");
	}//end main
}//end class
