public class LavaProofFurniture {

	public String name;
	public int points;

	LavaProofFurniture(String name, int points) {
		this.name = name;
		this.points = points;
	}

	public String toListString(int number) {
		return number + ") " + name + "- " + points + " points";
	}

}
