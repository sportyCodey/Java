public class TestOctagon {
	public static void main(String[] args) {
		Octagon octagon1 = new Octagon("Blue", true, 5);

		System.out.println("The area is: " + octagon1.getArea());
		System.out.println("The perimeter is: " + octagon1.getPerimeter());

		try {
			Octagon octagon2 = (Octagon)octagon1.clone();
			System.out.println("Comparing these shapes we get... " + octagon1.compareTo(octagon2));
		}
		catch (CloneNotSupportedException ex) {
			System.out.println("Cannot be cloned.");
		}

	}//end main
}//end class