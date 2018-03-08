import java.io.*;
import java.util.Scanner;

public class Deck {

  private Card[] cards;

  private int size;

  public Deck() {
    cards = new Card[52];
    File infile;
    size = 0;
    Scanner input;

  try {
    infile = new File("cards");
    input = new Scanner(infile);
  } catch (FileNotFoundException e) {
      System.out.println("cards File not found!");
      return;
    }

    String s;
    char rankchar, suitchar;
    int rank, suit;
    int i;
    String ranks, suits;

    ranks = "--23456789TJQKA";
    suits = "shdc";

    for (i = 0; i < 52; i++) {
      size++;
      s = input.nextLine();
      rankchar = s.charAt(0);
      suitchar = s.charAt(1);
      rank = ranks.indexOf(rankchar);
      suit = suits.indexOf(suitchar);
      cards[i] = new Card(rank,suit);

    }

  }

  public Card deal() {
    size--;
    return cards[size];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

}
