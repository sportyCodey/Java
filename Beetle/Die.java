//This program creates the class Die for the Beetle game


public class Die {

  private int topFace;

  public Die() {
    this.topFace = 1;
  }

  public int getTopFace() {
    return this.topFace;
  }

  public void setTopFace(int topFace) {
    this.topFace = topFace;
  }

  public void roll() {
    this.topFace = ((int)(Math.random() * 6)) + 1;
  }

  public String toString() {
     return "" + topFace;
  }

  public static void main(String[] args) {
    Die d = new Die();
    System.out.println(d.getTopFace());
    d.setTopFace(6);
    System.out.println(d.getTopFace());
    d.roll();
    System.out.println(d.getTopFace());
  }
}


  
