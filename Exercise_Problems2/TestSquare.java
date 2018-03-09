public class TestSquare {
	public static void main(String[] args) {
		Square square = new Square();

		square.howToColor();

		Square[] list = new Square[5];

		for (int i = 0; i < list.length; i++) {
			list[i] = new Square();
			list[i].howToColor();
		}

	}//end main
}//end class