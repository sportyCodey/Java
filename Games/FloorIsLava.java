import java.util.ArrayList;
import java.util.Random;

public class FloorIsLava {

	private ArrayList<LavaProofFurniture> itemsInRoom;
	private ArrayList<Integer> playerScores;
	private Random rand;
	private int endGame;

	FloorIsLava(int numberOfPlayers) {
		itemsInRoom = new ArrayList<LavaProofFurniture>();
		itemsInRoom.add(new LavaProofFurniture("Chair", 2));
		itemsInRoom.add(new LavaProofFurniture("Table", 1));
		itemsInRoom.add(new LavaProofFurniture("Side Table", 2));
		itemsInRoom.add(new LavaProofFurniture("Couch", 1));
		itemsInRoom.add(new LavaProofFurniture("Cat Bed Island", 3));

		playerScores = new ArrayList<Integer>();
		for (int i = 0; i < numberOfPlayers; i++) {
			playerScores.add(0);
		}
		rand = new Random();
	}

	public int randomPlayerSelection() {
		return rand.nextInt(playerScores.size());
	}

	public void printLavaProofFurniture() {
		for (int i = 0; i < itemsInRoom.size(); i++) {
			System.out.println(itemsInRoom.get(i).toListString(i));
		}
	}

	public void printPlayers() {
		System.out.println(playerScores.toString());
	}

	public void playerLandsOnLava(int playerNumber) {
		playerScores.set(playerNumber, 0);
	}

	public void playerLandsOnFurnitureScore(int playerNumber, int score) {
		playerScores.set(playerNumber, score + playerScores.get(playerNumber));
	}

	public int getEndGame() {
		return endGame;
	}

	public void playerLandsOnFurniture(int playerNumber, int furnitureID) {
		endGame = playerScores.get(playerNumber);

		if (furnitureID == -1) {
			playerLandsOnLava(playerNumber);
			getWinner();
		}
		else if (furnitureID == 0) {
			playerLandsOnFurnitureScore(playerNumber, 2);
			endGame = playerScores.get(playerNumber);
		}
		else if (furnitureID == 1) {
			playerLandsOnFurnitureScore(playerNumber, 1);
			endGame = playerScores.get(playerNumber);
		}
		else if (furnitureID == 2) {
			playerLandsOnFurnitureScore(playerNumber, 2);
			endGame = playerScores.get(playerNumber);
		}
		else if (furnitureID == 3) {
			playerLandsOnFurnitureScore(playerNumber, 1);
			endGame = playerScores.get(playerNumber);
		}
		else {
			playerLandsOnFurnitureScore(playerNumber, 3);
			endGame = playerScores.get(playerNumber);
		}
	}

	public void getWinner() {
		System.out.println("<><><><><>");
		System.out.println("GAME OVER!!!");
		int max = 0;
		int player = 0;
			for (int i = 0; i < playerScores.size(); i++) {
				if (playerScores.get(i) > max) {
					max = playerScores.get(i);
					player = i;
				}
			}
		System.out.println("The winner is: Player " + player);

		System.out.println();

		System.out.println("Final Scores:");
		System.out.println("------------");
		printPlayers();
	}

}//end class
