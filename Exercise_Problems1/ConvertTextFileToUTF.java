import java.io.*;
import java.util.Scanner;

public class ConvertTextFileToUTF {
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Usage: java ConvertTextFileToUTF textFile UTF_file");
			System.exit(1);
		}

		File file = new File(args[0]);

		if (!file.exists()) {
			System.out.println("The file, " + args[0] + " does not exist.");
			System.exit(2);
		}

		System.out.println("The size of the text file is: " + file.length() + " bytes.");

		try (DataOutputStream output = new DataOutputStream(
			new BufferedOutputStream(new FileOutputStream(args[1])));) {

			Scanner input = new Scanner(file);

			while (input.hasNext()) {
				output.writeUTF(input.next());
			}
		}

		System.out.println("The size of the binary file is: " + args[1].length() + " bytes.");
		System.out.println("Done");

		System.out.println("To make sure the conversion happened correctly, " + args[1] + " has the following text: ");

		try {
			try (DataInputStream input = new DataInputStream(
				new BufferedInputStream(new FileInputStream(args[1])));) {
					while (true) {
						System.out.println(input.readUTF());
					}
			}
		}
		catch (EOFException ex) {
			System.out.println("Done reading data.");
		}
	}//end main
}//end class