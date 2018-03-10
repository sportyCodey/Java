//class that models a Snack and its various attributes

public class Snack {

	private String name;
	private String strg;
	private String type;
	private double price;
	private int servings;
	private double calories;

	public Snack(String strg) {
		this.strg = strg;
		String[] output = strg.split(",");
		name = output[0];
		type = output[1];
		price = Double.valueOf(output[2]);
		servings = Integer.valueOf(output[3]);
		calories = Double.valueOf(output[4]);
	}

	public double caloriesPerServing() {
		return calories/servings;
	}

	public double getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public String toString() {
		return "Name: " + name + "\nType: " + type +
		"\nPrice: " + price + "\nServings: " + servings +
		"\nTotal Calories: " + calories;

	}
}//end class Snack
