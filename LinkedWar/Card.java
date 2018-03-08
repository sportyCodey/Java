//Andrew Hudson

public class Card {

  public static final int SPADES = 0;
  public static final int HEARTS = 1;	
  public static final int DIAMONDS = 2;
  public static final int CLUBS = 3;
  public static final int ACE = 14;
  public static final int TWO = 2;
  public static final int JACK = 11;
  public static final int QUEEN = 12;
  public static final int KING = 13;
  
  private int rank;
  private int suit;

  public Card(int rank, int suit) {
    this.rank =  rank;
    this.suit = suit;
  }

  public int getRank() {
    return rank;
  }
 
  public int getSuit() {
    return suit;
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

  public String toString() {
    return "" + "--23456789TJQKA".charAt(rank) + "shdc".charAt(suit);
  }
}
