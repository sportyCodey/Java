public class FloodFillExample {

	public static final int LENGTH = 10;

	public static String[][] board;

	public static void main(String[] args) {
		makeBoard();

		printBoard(board);

		floodFill(0, 0);

		System.out.println();

		printBoard(board);
	}//end main

	public static void makeBoard() {
		 board = createBoard();

		int counter = 0;
		while (counter < 10) {
			int rand1 = (int)(Math.random() * 10);
			int rand2 = (int)(Math.random() * 10);

			if (board[rand1][rand2].equals(" * "))
				continue;

			board[rand1][rand2] = " * ";
			counter++;
		}
	}

	public static String[][] createBoard() {
		String[][] board = new String[LENGTH][LENGTH];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = " X ";
			}
		}
		return board;
	}

	public static void floodFill(int x, int y) {
		if (x < 0 || x > 9 || y < 0 || y > 9)
			return;

		if (!board[x][y].equals(" * ") && !board[x][y].equals("   ")) {
			board[x][y] = "   ";
			floodFill(x + 1, y);
			floodFill(x - 1, y);
			floodFill(x, y + 1);
			floodFill(x, y - 1);
		}
	}

	public static void printBoard(String[][] board) {
		System.out.println("    1  2  3  4  5  6  7  8  9  10");
		System.out.println("---------------------------------");

		for (int i = 0; i < board.length; i++) {
			if (i == board.length - 1)
				System.out.print((i + 1) + " ");
			else
				System.out.print((i + 1) + "  ");
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
}//end class