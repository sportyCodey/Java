//creates the classes Transportation, SolarCar, and Bicyle
//it then tests them

public class TransportationTestStudent {
  public static void main(String[] args) {
	  SolarCar x = new SolarCar();
	  Bicycle y = new Bicycle();

	  System.out.println("Solar car is " + x.fueledBy()+ " carries " + x.getNumberPassengers() + " passengers.");
	  System.out.println("Bicycle is " + y.fueledBy()+ " carries " + y.getNumberPassengers() + " passenger.");

  }//end main
}//end TransportationTest1

abstract class Transportation {
	private int numberPassengers;

	public Transportation(int numberPassengers) {
		this.numberPassengers = numberPassengers;
	}

	public int getNumberPassengers(){
		return numberPassengers;
	}

	public abstract String fueledBy();

}//end class Transportation

class SolarCar extends Transportation {

	public SolarCar() {
		super(2);
	}

	@Override
	public String fueledBy() {
		return "Solar Powered";
	}

}//end class SolarCar

 class Bicycle extends Transportation {

	public Bicycle() {
		super(1);
	}

	@Override
	public String fueledBy() {
		return "human powered" ;
	}

}//end class Bicycle


