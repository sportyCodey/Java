import java.io.*;

public class CreateABinaryDataFile {
  public static void main(String[] args) throws IOException {
	  try (DataOutputStream output = new DataOutputStream(
	    new BufferedOutputStream(new FileOutputStream("Exercise17_02.dat", true)));) {
			for (int i = 0; i < 100; i++) {
				output.writeInt((int)(Math.random() * 100));
	  		}
	  }

	  System.out.println("Done");
  }//end main
}//end class

