//sums integers from a binary file

import java.io.*;

public class SumIntegersInBinaryFile {
	private static int sum = 0;

	public static void main(String[] args) throws IOException {
		File file = new File("Exercise17_03.dat");

		try {
			try (DataOutputStream output = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(file)));) {
					for (int i = 0; i < 100; i++) {
						output.writeInt((int)(Math.random() * 100));
					}
			}

			try (DataInputStream input = new DataInputStream(
				new BufferedInputStream(new FileInputStream(file)));) {

				while (true) {
					sum += input.readInt();
				}
			}
		}
		catch (EOFException ex) {
			System.out.println("Sum is: " + sum);
		}
  	}//end main
}//end class



