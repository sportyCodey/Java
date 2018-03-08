//Andrew Hudson
//ash68320
//700656832
//This program creates the class Traversal and runs the program

import java.io.*;
import java.util.Scanner;

public class Traversal {

  public static void main(String[] args) throws IOException {
  
    File infile = new File("binarytree");
    Scanner input = new Scanner(infile);

    String s = input.next();
    System.out.println("binarytree\n" + s);
    String t;
    BinaryNode []array = new BinaryNode[100];

    for (int i = 1; i <= 99; i++)
      array[i] = new BinaryNode("", null, null);

    for (int i = 1; i <= s.length(); i++)
      array[i].setItem(s.substring(i-1,i));

    for (int i = 1; i <= (s.length() - 1) / 2; i++) {
      t = s.substring(2 * i - 1, 2 * i);
      if (!(t.equals("-")))
        array[i].setLeft(array[2 * i]);
      t = s.substring(2 * i, 2 * i + 1);
      if (!(t.equals("-")))
        array[i].setRight(array[2 * i + 1]);
    }

    System.out.println("output");
    System.out.println("pre: " + array[1].toStringPreorder());
    System.out.println("in: " + array[1].toStringInOrder());
    System.out.println("post: " + array[1].toStringPostorder());
  }// end main
}// end class Traversal
