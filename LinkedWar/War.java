//Andrew Hudson

import java.util.Scanner;

public class War {

  public static final Scanner INPUT = new Scanner(System.in);
  private Queue<Card> hand1;
  private Queue<Card> hand2;
  private int count;

  public War() {
    hand1 = new LinkedQueue<Card>();
    hand2 = new LinkedQueue<Card>();
    Deck deck = new Deck();
    while (!(deck.isEmpty())) {
      hand2.add(deck.deal());
      hand1.add(deck.deal());
    }
      count = 0;
  }

  public void play() {
    while (!(hand1.isEmpty() || hand2.isEmpty())) {
      System.out.print("\nHit return to play round: ");
      INPUT.nextLine();
      count++;
      System.out.println("Round " + count);
      playRound();
      System.out.println("Player 1 has " + hand1.getSize() + " cards");
      System.out.println("Player 2 has " + hand2.getSize() + " cards");
      if (hand1.isEmpty()) {
        System.out.println("Player 2 wins!");
      }
      if (hand2.isEmpty()) {
        System.out.println("Player 1 wins!");
      }
    }
  }


  public void playRound() {
    Queue<Card> queue1 = new LinkedQueue<Card>();
    Queue<Card> queue2 = new LinkedQueue<Card>();
    queue1.add(hand1.remove());
    queue2.add(hand2.remove());
    do {
      Card card1 = queue1.peek();
      Card card2 = queue2.peek();
      System.out.println(card1 + " " + card2);
      Queue<Card> winner = null;
      if (card1.getRank() > card2.getRank()) {
        winner = hand1;
      }
      if (card1.getRank() < card2.getRank()) {
        winner = hand2;
      }
      if (winner != null) {
        give(queue1, queue2, winner);
        return;
      }
    } while (!settledByWar(queue1, queue2));
  }

  public boolean settledByWar(Queue queue1, Queue queue2) {
    System.out.println("War!");
    for (int i = 0; i < 4; i++) {
      if (hand1.isEmpty()) {
        give(queue1, queue2, hand2);
        return true;
      }
      queue1.add(hand1.remove());
      if (hand2.isEmpty()) {
        give(queue1, queue2, hand1);
        return true;
      }
      queue2.add(hand2.remove());
    }
    return false;
  }

  public void give(Queue<Card> queue1, Queue<Card> queue2, Queue<Card> winner) {
    if (winner == hand1) {
      System.out.println("Player 1 gets the cards");
    } else {
        System.out.println("Player 2 gets the cards");
      }
    while (!(queue1.isEmpty())) {
      winner.add(queue1.remove());
    }
    while (!(queue2.isEmpty())) {
      winner.add(queue2.remove());
    }
  }

  public static void main(String[] args) {
    System.out.println("Welcom to War.");
    War game = new War();
    game.play();
  }

}
