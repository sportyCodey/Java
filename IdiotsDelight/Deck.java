//This program creates the class Deck

public class Deck {

  private Card[] cards;

  private int size;

  public Deck() {
    cards = new Card[52];
    size = 0;
    for (int suit = Card.SPADES; suit <= Card.CLUBS; suit++) {
      for (int rank = Card.TWO; rank <= Card.ACE; rank++) {
        cards[size] = new Card(rank, suit);
        size += 1;
      }
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

  public void shuffle() {
    for (int i = size - 1; i > 0; i--) {
      swap(i, (int)(Math.random() * (i + 1)));
    }
  }

  protected void swap(int i, int j) {
    Card temp = cards[i];
    cards[i] = cards[j];
    cards[j] = temp;
  }

}
 
