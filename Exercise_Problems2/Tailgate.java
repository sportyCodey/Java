import java.util.ArrayList;
import java.io.*;

public class Tailgate {

	private ArrayList<Snack> list = new ArrayList<Snack>();

	public Tailgate(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String strg = br.readLine();
		strg = br.readLine();

		while(strg != null) {
			list.add(new Snack(strg));
			strg = br.readLine();
		}
		br.close();
	}

	public double computeTotalPrice() {
		double sum = 0.0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).getPrice();
		}
		return sum;
	}

	public int numberOfDesserts() {
		int num_desserts = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getType().equals("Dessert"))
				num_desserts++;
		}
		return num_desserts;
	}

	public boolean snacksInPriceOrder() {
		boolean inOrder = true;
		double lastPrice = -1;
		double price = 0;
		for (int i = 0; i < list.size(); i++) {
			price = list.get(i).getPrice();
		    	if (price < lastPrice) {
			    	inOrder = false;
				}
	        	lastPrice = price;
		}
		return inOrder;
	}

	public Snack lowestCaloriesPerServing() {
		double lowestCaloriesPerServing = Double.POSITIVE_INFINITY;
	    Snack lowestCalSnack = null;

	     for (int i = 0; i < list.size(); i++) {
			if (list.get(i).caloriesPerServing() < lowestCaloriesPerServing) {
				lowestCaloriesPerServing = list.get(i).caloriesPerServing();
				lowestCalSnack = list.get(i);
			}

	     }
	     return lowestCalSnack;
	}

	@Override
	public String toString() {
		String result = "";

		result += "Total Cost Of Tailgate:\n\n";
		result += computeTotalPrice();

		result += "\n\nNumber Of Desserts:\n\n";
		result += numberOfDesserts();

		result += "\n\nSnacks In Price Order: \n\n";
		result += snacksInPriceOrder();

		result += "\n\nSnack with Lowest Calories Per Serving:\n\n";
		result += lowestCaloriesPerServing().toString();

		return result;
	}

}//end class Tailgate