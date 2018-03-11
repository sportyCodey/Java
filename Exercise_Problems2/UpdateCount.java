//keeps count of how many times the user has opened up this program

import java.io.*;

public class UpdateCount {
	public static void main(String[] args) throws IOException {
		try (RandomAccessFile output = new RandomAccessFile("Exercise18_08.dat", "rw");) {

			int count = 0;

			if (output.length() > 0) {
				count = output.readInt();
			}

			output.seek(0);
			output.writeInt(++count);

			System.out.println("You have accessed this file " + count + " times.");
		}
	}//end main
}//end class
