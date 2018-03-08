//This program creates the class IdiotsDelight which runs the game Idiot's Delight

import java.util.Scanner;
import java.util.InputMismatchException;

public class IdiotsDelight {

  public static final Scanner INPUT = new Scanner(System.in);

  private Stack<Card>[] stacks;

  private Deck deck;

  private int cardsLeft[];

  private int score;

  public IdiotsDelight() {
    deck = new Deck();
    deck.shuffle();
    stacks = new Stack[4];
    cardsLeft = new int[4];
    score = 0;
    for (int i = 0; i < 4; i++) {
      stacks[i] = new ArrayStack<Card>();
      cardsLeft[i] = 13;
    }
    try {
      deal();
    } catch (IllegalMoveException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public void deal() throws IllegalMoveException {
    if(deck.isEmpty()) {
      throw new IllegalMoveException();
    }
    for (Stack<Card> s : stacks) {
      s.push(deck.deal());
    }
  }

  public void play() {
    while(true) {
      try {
        System.out.println("\n" + this);
        boolean done = true;
        for (Stack<Card> s : stacks) {
          if (!(s.isEmpty())) {
            done = false;
            break;
          }
        }
        if (!(deck.isEmpty())) {
          done = false;
        }
        if (done) {
          System.out.println("You win!");
          return;
        }
        System.out.print("Your command (pair, suit, move, deal, or quit)? "); 
        String command = INPUT.nextLine();
        if (command.equals("pair")) {
          removePair();
        } else if (command.equals("suit")) {
          removeLowCard();
        } else if (command.equals("move")) {
          moveCard();
        } else if (command.equals("deal")) {
          deal();
        } else if (command.equals("quit")) {
          return;
        } else {
          System.out.println("I'm sorry, that's not a legal move.");
        }
      }
        catch (IllegalMoveException e) {
        System.out.println("I'm sorry, that's not a legal move.");
      } catch (InputMismatchException e) {
        System.out.println("I'm sorry, you have entered the wrong data type.");
        INPUT.nextLine();
      } catch (EmptyStructureException e) {
        System.out.println("I'm sorry, there are no cards left in that stack.");
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("I'm sorry, that is not a proper move.");
      }
    }
  }

  public void removeLowCard() throws IllegalMoveException, InputMismatchException, EmptyStructureException, ArrayIndexOutOfBoundsException { 
    System.out.print("Location (1-4) of low card? ");
    int i = INPUT.nextInt();
    System.out.print("Location (1-4) of high card? ");
    int j = INPUT.nextInt();
    INPUT.nextLine();
    if (i == j) throw new IllegalMoveException();
    Card lowCard = stacks[i-1].peek();
    Card highCard = stacks[j-1].peek();
    if ((lowCard.getSuit() != highCard.getSuit()) || (lowCard.getRank() > highCard.getRank())) {
      throw new IllegalMoveException();
    }
    stacks[i-1].pop();
    cardsLeft[lowCard.getSuit()]--;
    score++;
  }

  public void removePair() throws IllegalMoveException, InputMismatchException, EmptyStructureException, ArrayIndexOutOfBoundsException {
    System.out.print("Location (1-4) of first card? ");
    int i = INPUT.nextInt();
    System.out.print("Location (1-4) of second card? ");
    int j = INPUT.nextInt();
    INPUT.nextLine();
    if (i == j) throw new IllegalMoveException();
    Card card1 = stacks[i-1].peek();
    Card card2 = stacks[j-1].peek();
    if (!(card1.equals(card2))) {
      throw new IllegalMoveException();
    }
    stacks[i-1].pop(); 
    stacks[j-1].pop();
    cardsLeft[card1.getSuit()]--;
    cardsLeft[card2.getSuit()]--;
    score += 2; 
  }

  public void moveCard() throws IllegalMoveException, InputMismatchException, EmptyStructureException, ArrayIndexOutOfBoundsException {
    System.out.print("Location (1-4) of card to move? ");
    int i = INPUT.nextInt();
    System.out.print("Location (1-4) of empty stack? ");
    int j = INPUT.nextInt();
    INPUT.nextLine();
    if (i == j) throw new IllegalMoveException();
    Card mCard = stacks[i-1].peek();
    if (!stacks[j-1].isEmpty()) throw new IllegalMoveException();
    stacks[i-1].pop();
    stacks[j-1].push(mCard);
  }

  public String toString() {
    String result = "Score: " + score;
    result += "\nStack Size:   ";
    for (int i = 0; i < 4; i++) {
      result +=  stacks[i].getSize() + "  ";
    }
    result += "\nTop of Stack: ";
    for (int i = 0; i < 4; i++) {
      if (stacks[i].isEmpty()) {
        result += "-- ";
      } else {
        result += stacks[i].peek() + " ";
      }
    }
    result += "\nStack Number: 1  2  3  4";
    result += "\nCards Left in Deck and Stacks - Spades: " + cardsLeft[Card.SPADES] + ", Hearts: " + cardsLeft[Card.HEARTS] + ", Diamonds: " + cardsLeft[Card.DIAMONDS] + ", Clubs: " + cardsLeft[Card.CLUBS];
    return result + "\n" + deck.size() + " cards left in the deck";
  }

  public static void main(String[] args) {
    System.out.println("Welcome to Idiot's Delight.");
    IdiotsDelight game = new IdiotsDelight();
    game.play();
  }

}
