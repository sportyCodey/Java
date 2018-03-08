//This program sorts numbers

/* ***************************************************************************************************************** */

import java.io.File;
import java.util.Scanner;

public class QuickSort {

  public static void quicksort(int[] data, int count) {

    quicksortHelper(data, 0, count - 1);
  }

  protected static void quicksortHelper(int[] data, int bottom, int top) {

    if (bottom < top) {
      int midpoint = partition(data, bottom, top);
      quicksortHelper(data, bottom, midpoint - 1);
      quicksortHelper(data, midpoint + 1, top);
    }
  }

  protected static int partition(int[] data, int bottom, int top) {

    int pivot = data[top];
    int firstAfterSmall = bottom;
    for (int i = bottom; i < top; i++) {
      if (data[i] <= pivot) {
        swap(data, firstAfterSmall, i);
        firstAfterSmall++;
      } 
    }
  swap(data, firstAfterSmall, top);
  return firstAfterSmall;
  }

  protected static void swap(int[] data, int i, int j) {
    int temp = data[i];
    data[i] = data[j];
    data[j] = temp;
  }

  public static void main(String[] args) throws Exception {
    File file = new File("input");
    Scanner input = new Scanner(file);

    int[] data = new int[100];
    int count = 0;
    int x;

    while (input.hasNext()) {
      x = input.nextInt();
      data[count++] = x;
    }
  
    System.out.println("Array before Sort");
    for (int i = 0; i < count; i++) {
      System.out.println(data[i]);
    }

    quicksort(data, count);

    System.out.println("Array after QuickSort");
    for (int i = 0; i < count; i++)
      System.out.println(data[i]);
  }
}
