//Program to tell if any number is equal in the matrix. 

import java.util.Scanner;

public class AnyEqual {

	public static int anyEqual(int n, int A[][]) {

		for (int i = 0; i < n*n; i++)
			for (int j = i + 1; j < n*n; j++)
				if (A[i/n][i%n] == A[j/n][j%n])
					return 1;
		return 0;
	}

	public static void main(String[] args) {

		System.out.println("Enter length for matrix: ");

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();

		int[][] matrix = new int[n][n];

		System.out.println("Enter " + n * n + " numbers: " );
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
			matrix[i][j] = input.nextInt();
			}
		}

		System.out.println("Any equal? : " + anyEqual(n, matrix));


	}//end main
}//end class
