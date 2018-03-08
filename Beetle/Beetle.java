//Andrew Hudson
//700656832
//ash68320
//a2
//This program creates the class Beetle which is used in the Beetle game

public class Beetle {
  
  private boolean body;
  private int eyes;
  private int feelers;
  private boolean head;
  private int legs;
  private boolean tail;
  private int numOfRolls;
  private String rollHistory;
  private int[] dieCount;  

  public Beetle() {
    body = false;
    eyes = 0;
    feelers = 0;
    head = false;
    legs = 0;
    tail = false;
    numOfRolls = 0;
    rollHistory = "";
    dieCount = new int[7];
    for (int i = 1; i <= 6; i++) {
      dieCount[i] = 0;
    }
  }
  
  public void updateDieCount(int topFace) {
    dieCount[topFace]++; 
  }


  public boolean addBody() {
    if (body) {
      return false;
    }
    else {
    body = true;
    return true;
    }
  }

  public boolean addEye() {
    if (head && (eyes < 2)) {
       eyes++;
       return true;
    }
    else {
    return false;
    }
  }

  public boolean addHead() {
    if (body && !head) {
      head = true;
      return true;
    }
    else {
    return false;
    }
  }

  public boolean addFeeler() {
    if (head && (feelers < 2)) {
      feelers++;
      return true;
    }
    else {
    return false;
    }
  }

  public boolean addLeg() {
    if (body && (legs < 6)) {
      legs++;
      return true;
    }
    else {
    return false;
    }
  }

  public boolean addTail() {
    if (body && !tail) { 
      tail = true;
      return true;
    }
    else {
    return false;
    }
  }

  public void incrementRollNumber() {
   numOfRolls++; 
  }

  public void updateRollHistory(int topFace) {
    rollHistory += topFace;
  }

  public boolean isComplete() {
    return body && (eyes == 2) && (feelers == 2) && head && (legs == 6) && tail;
  }

  public String toString() {
      String result = "";   
      if (body) {
      if (feelers > 0) {
        result += "\\";
        if (feelers  == 2) {
          result += " /";
        }
         result += "\n";
      }

      if (head) {
        if (eyes > 0) {
          result += "o";
        }
        else {
        result += " ";
        }
        result += "0";
        if (eyes == 2) { result += "o"; }
        result += "\n";
      }
      if (legs > 0) {
      result += "-";
      }
      else {
      result += " ";
      }
      result += "#";
      if (legs > 1) {
        result += "-";
      }
      result += "\n";
      if (legs > 2) {
        result += "-";
      }
      else {
      result += " ";
      }
      result += "#";
      if (legs > 3) {
        result += "-";
      }
      result += "\n";
      if (legs > 4) {
        result += "-";
      }
      else {
      result += " ";
      }
      result += "#";
      if (legs > 5) {
        result += "-";
      }
      if (tail) {
        result += "\n v";
      }
      }
      else {
      result += "(no parts yet)";
      }
      result += "\nRoll Number: " + numOfRolls;
      result += "\nRoll History: " + rollHistory;
      result += "\nNo. of 1's: " + dieCount[1];
      for (int i = 2; i <= 6; i++) {
      result += ", "+ i +"'s: " +  dieCount[i];
      }
      return result;
  }
}
  

