//Andrew Hudson
//700656832
//ash68320
//a3
//This program creates the Cram class and runs it in the main

import java.util.InputMismatchException;

public class Cram {

  public static final java.util.Scanner INPUT = new java.util.Scanner(System.in);

  public static final boolean HORIZONTAL = false;

  public static final boolean VERTICAL = true;

  private int nr, nc;

  private boolean[][] squares;

  public Cram(int nr, int nc) {
    this.nr = nr;
    this.nc = nc;
    squares = new boolean[nr][nc];
  }

  public String toString() {  
  //String result = "  0 1 2 3 4 5 6 7";
    String result = "  ";
    for (int column = 0; column < nc; column++) {
      result += column + " ";
    }
    for (int row = 0; row < nr; row++) {
      result += "\n" + row;
        for (int column = 0; column < nc; column++) {
          if (squares[row][column]) {
            result += " #";
          } else {
            result += " .";
          }
        }
    }
    return result;
  }

  public void play() {
    int row, column;
    int player = 1;
    String str;
    boolean badData;
    char c1, c2, c3;
    while (true) {
      System.out.println("\n" + this);
        if (player == 1) {
          System.out.println("Player to play");
        } else {
          System.out.println("Computer to play");
        }
          if (!(hasLegalMoveFor())) {
            System.out.println("No legal moves == you lose!");
            return;
          }

//**********************************************
        if (player == 1) {
          badData = true;
          do {
            try {
              System.out.print("orc: ");
              str = INPUT.nextLine();
              c1 = str.charAt(0);
              if (c1 != 'h' && c1!= 'v') throw new InvalidOrientationException();
              c2 = str.charAt(1);
              row = Character.getNumericValue(c2);
              c3 = str.charAt(2);
              column = Character.getNumericValue(c3);
              if (c1 == 'h' && (squares[row][column] ||  squares[row][column + 1])) throw new DominosOverlappingException();
              if (c1=='h') playAt(row, column, HORIZONTAL);
              if (c1 == 'v' && (squares[row][column] || squares[row + 1][column])) throw new DominosOverlappingException();
              if (c1=='v') playAt(row, column, VERTICAL);
              player = 2;
              badData = false;
            } catch (InvalidOrientationException e) {
                System.out.println("Invalid orientation - Try again!");    
              }
              catch (DominosOverlappingException e) { 
                System.out.println("Dominos are overlapping - Try again! ");
              }
              catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Domino is off the board - Try again! ");
              }
          } while(badData);

//******************************************************
        } else {

            str = calculateComputerMove();
            System.out.println("Computer Move: "+str);
            c1 = str.charAt(0);
            c2 = str.charAt(1);
            row = Character.getNumericValue(c2);
            c3 = str.charAt(2);
            column = Character.getNumericValue(c3);
            if (c1 == 'h') playAt(row, column, HORIZONTAL);
            if (c1 == 'v') playAt(row, column, VERTICAL);
            player = 1;

        }
           
          }
  }

  public String calculateComputerMove() {
  //Check for horizontal move
    for (int row = 0; row < nr; row++) {
      for (int column = 0; column < nc -1; column++) {
        if (!squares[row][column] && !squares[row][column + 1]) 
          return "h" + row + column;     
      }
    }

  //Check for vertical move
   for (int column = 0; column < nc; column++) {
     for (int row = 0; row < nr - 1; row++) {
        if (!squares[row][column] && !squares[row + 1][column])
          return "v" + row + column;
     }
   } 
  
    return "h00";

  }

  public void playAt(int row, int column, boolean player) {
    squares[row][column] = true;
      if (player == HORIZONTAL) {
        squares[row][column + 1] = true;
      } else {
          squares[row + 1][column] = true;
      }
  }

  public boolean hasLegalMoveFor() {
    int rowOffset, columnOffset;
    for (int i = 1; i <= 2; i++) {
      if (i==1) {
        columnOffset = 1; rowOffset = 0;
      } else {
        columnOffset = 0; rowOffset = 1;
      }
      for (int row = 0; row < (nr - rowOffset); row++) {
        for (int column = 0; column < (nc - columnOffset); column++) {
          if (!(squares[row][column] || squares[row + rowOffset][column + columnOffset])) {
            return true;
          }
        }
      }
    }
      return false;
  }

public static void main(String[] args) {
  boolean badData;
  int r, c;
  r = 8; c = 8;
  System.out.println("Welcome to Cram.");
  badData = true;
  do {
    try {
      System.out.print("Enter rows and columns of game boards (1 - 10): ");
       r = INPUT.nextInt();
       c = INPUT.nextInt();
       if (r < 1 ||  r > 10 || c < 1 || c > 10) throw new TooManyOrTooFewRowsColsException();
       INPUT.nextLine();
       badData = false;
    } catch (InputMismatchException e) {
        System.out.println("Inalid row/column - Try again!");
        INPUT.nextLine();
      }
      catch (TooManyOrTooFewRowsColsException e) {
        System.out.println("Too many or too few rows/columns");
        INPUT.nextLine();
      }
  }   while(badData);
       Cram game = new Cram(r,c);
       game.play();
}

}
