//This program creates the class Card

public class Card {

  public static final int SPADES = 0;
  public static final int HEARTS = 1;
  public static final int DIAMONDS = 2;
  public static final int CLUBS = 3;
  public static final int ACE = 14;
  public static final int JACK = 11;
  public static final int QUEEN = 12;
  public static final int KING = 13;
  public static final int TWO = 2;
  private int rank;
  private int suit;

  public Card(int rank, int suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (that == null) {
      return false;
    }
    if (getClass() != that.getClass()) {
      return false;
    }
    Card thatCard = (Card)that;
    return rank == thatCard.rank;
  }

  public int getRank() {
    return rank;
  }

  public int getSuit() {
    return suit;
  }

  public String toString() {
    return "" + "--23456789TJQKA".charAt(rank) + "shdc".charAt(suit);
  }

  public static void main(String[] args) {
  
    Card c1,c2,c3;
    c1 = new Card(10,2);
    System.out.println(c1);
    c2 = new Card(14,0);
    System.out.println(c2);
    c3 = new Card(QUEEN,CLUBS);
    System.out.println(c3);
    }

}


