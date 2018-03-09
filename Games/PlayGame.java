import java.util.Scanner;

public class PlayGame {

	public static void main(String[] args) {

	Scanner input = new Scanner(System.in);

	int numPlayers = 0;
	int randomPlayer = 0;
	int furnitureChoice = 0;


	System.out.println("<><><><><><><><><><><>");
	System.out.println("THE FLOOR IS LAVA!!!!!!");
	System.out.println("<><><><><><><><><><><>");
	System.out.println("Quick! Jump to a piece of Lava Proof Furniture!");

	System.out.println("How many people have accepted this challenge?");
	numPlayers = input.nextInt();

	FloorIsLava gamePlay = new FloorIsLava(numPlayers);

	System.out.println("Current Scores");
	System.out.println("------------");

	gamePlay.printPlayers();

	System.out.println();

	while(true) {

		System.out.println("<><><><><>");
		System.out.println("EARTHQUAKE!");
		System.out.println("<><><><><>");

		randomPlayer = gamePlay.randomPlayerSelection();

		System.out.println("Player " + randomPlayer + ": You must move NOW! " +
		"Where did you land? (-1 if you fell in The Lave)");

		gamePlay.printLavaProofFurniture();

		furnitureChoice = input.nextInt();

		if (furnitureChoice == -1) {
			gamePlay.playerLandsOnFurniture(randomPlayer, furnitureChoice);
			break;
		}

		gamePlay.playerLandsOnFurniture(randomPlayer, furnitureChoice);

		if (gamePlay.getEndGame() >= 10) {
			gamePlay.getWinner();
			break;
		}

		System.out.println("Current Scores");
		System.out.println("------------");

		gamePlay.printPlayers();

		System.out.println();

	}//end while


	}//end main
}//end class PlayGame