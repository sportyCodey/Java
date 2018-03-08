//Andrew Hudson
//700656832
//ash68320
//a2
//This program creates the class BeetleGame and will run the Beetle game

import java.util.*;

public class BeetleGame {

  public static final java.util.Scanner INPUT = new java.util.Scanner(System.in);

  private Beetle bug1, bug2, bug3, bug4, bug5;

  private Die die;

  private int  numOfPlayers;

  public BeetleGame(int numOfPlayers) {
    bug1 = new Beetle();
    bug2 = new Beetle();
    bug3 = new Beetle();
    bug4 = new Beetle();
    bug5 = new Beetle();
    this.numOfPlayers = numOfPlayers;
    die = new Die();
  }

  public void play() {
    int player = 1;
    Beetle bug = bug1;
    while (!(bug.isComplete())) {
      if (!(takeTurn(player, bug))) {

        if(numOfPlayers == 2) {
          if (player == 1) {
            player = 2;
            bug = bug2;
          }
          else {
            player = 1;
            bug = bug1;
          }
        }
        else if (numOfPlayers == 3) {
          if (player == 1) {
            player = 2;
            bug = bug2;
          }
          else if (player == 2) {
            player = 3;
            bug = bug3;
          }
          else {
            player = 1;
            bug = bug1;
          }
        }
        else if (numOfPlayers == 4) {
          if (player == 1) {
          player = 2;
          bug = bug2;
          }
          else if (player == 2) {
            player = 3;
            bug = bug3;
          }
          else if (player ==3) {
            player = 4;
            bug = bug4;
          }
          else {
            player = 1;
            bug = bug1;
     	  }
        }
       else if (numOfPlayers == 5) {
         if (player == 1) {
           player = 2;
           bug = bug2;
         }
         else if (player == 2) {
           player = 3;
           bug = bug3;
         }
         else if (player == 3) {
           player = 4;
           bug = bug4;
         }
         else if (player == 4) {
           player = 5;
           bug = bug5;
         }
         else {
           player = 1;
           bug = bug1;
         }
       }

      }
    }
    System.out.println("\nPlayer " + player + " wins!");
    System.out.println(bug);
  }

 public boolean takeTurn(int player, Beetle bug) {
  System.out.println("\nPlayer " + player + ", your beetle:");
  System.out.println(bug);
  System.out.println("Hit return to roll: ");
  INPUT.nextLine();
  die.roll();
  bug.incrementRollNumber();
  bug.updateRollHistory(die.getTopFace());
  bug.updateDieCount(die.getTopFace());
  System.out.print("You rolled a " + die.getTopFace());
  switch (die.getTopFace()) {
    case 1:
      System.out.println(" (body)");
      return bug.addBody();
    case 2:
      System.out.println(" (head)");
      return bug.addHead();
    case 3:
      System.out.println(" (leg)");
      return bug.addLeg();
    case 4:
      System.out.println(" (eye)");
      return bug.addEye();
    case 5:
      System.out.println(" (feeler)");
      return bug.addFeeler();
    default:
      System.out.println(" (tail)");
      return bug.addTail();
    }
  }

public static void main(String[] args) {
  boolean badData = true;
  int numOfPlayers = 2;
  String ans;
  do {
    System.out.println("Welcome to Beetle. ");
    badData = true;
    do {
      try {
        System.out.println("How many people will be playing Beetle? ");
         numOfPlayers = INPUT.nextInt();
	 if (numOfPlayers <= 0 || numOfPlayers > 5)  throw new IllegalArgumentException(); //or use the custom exception we made.
         badData = false;
         INPUT.nextLine();
      }
      catch (InputMismatchException e) {
        System.out.println("Input is not an integer - Try again");
        INPUT.nextLine();
      }
      catch (IllegalArgumentException e) {
        System.out.println("Integer is not postive or you can only have 5 people playing");
      }
    }
    while (badData);
    BeetleGame game = new BeetleGame(numOfPlayers);
    game.play();
    System.out.print("Do you want to play again? (Y or N): ");
    ans = INPUT.nextLine();
  }
    while(ans.equals("Y"));
  }
}
