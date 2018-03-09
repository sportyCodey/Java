import java.io.*;

public class StoreLoanObjects {
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Loan[] loans = {new Loan(), new Loan(), new Loan(), new Loan(), new Loan()};

		try (ObjectOutputStream output = new ObjectOutputStream(
			new BufferedOutputStream(new FileOutputStream("Exercise17_06.dat")));) {

				output.writeObject(loans);
		}

		try (ObjectOutputStream output2 = new ObjectOutputStream(
			new BufferedOutputStream(new FileOutputStream("Exercise17_07.dat")));) {

			output2.writeObject(new Loan());
			output2.writeObject(new Loan(3,4,5));
			output2.writeObject(new Loan(100,100,100));
			output2.writeObject(new Loan());
		}

		try (ObjectInputStream input = new ObjectInputStream(
			new BufferedInputStream(new FileInputStream("Exercise17_06.dat")));) {

			Loan[] arr = (Loan[])input.readObject();
			for (int i = 0; i < arr.length; i++) {
				System.out.println(loans[i]);
			}
		}

	}//end main
}//end class

class Loan implements Serializable {
  private double annualInterestRate;
  private int numberOfYears;
  private double loanAmount;
  private java.util.Date loanDate;

  /** Default constructor */
  public Loan() {
    this(2.5, 1, 1000);
  }

  /** Construct a loan with specified annual interest rate,
      number of years, and loan amount
    */
  public Loan(double annualInterestRate, int numberOfYears,
      double loanAmount) {
    this.annualInterestRate = annualInterestRate;
    this.numberOfYears = numberOfYears;
    this.loanAmount = loanAmount;
    loanDate = new java.util.Date();
  }

  /** Return annualInterestRate */
  public double getAnnualInterestRate() {
    return annualInterestRate;
  }

  /** Set a new annualInterestRate */
  public void setAnnualInterestRate(double annualInterestRate) {
    this.annualInterestRate = annualInterestRate;
  }

  /** Return numberOfYears */
  public int getNumberOfYears() {
    return numberOfYears;
  }

  /** Set a new numberOfYears */
  public void setNumberOfYears(int numberOfYears) {
    this.numberOfYears = numberOfYears;
  }

  /** Return loanAmount */
  public double getLoanAmount() {
    return loanAmount;
  }

  /** Set a newloanAmount */
  public void setLoanAmount(double loanAmount) {
    this.loanAmount = loanAmount;
  }

  /** Find monthly payment */
  public double getMonthlyPayment() {
    double monthlyInterestRate = annualInterestRate / 1200;
    double monthlyPayment = loanAmount * monthlyInterestRate / (1 -
      (1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));
    return monthlyPayment;
  }

  /** Find total payment */
  public double getTotalPayment() {
    double totalPayment = getMonthlyPayment() * numberOfYears * 12;
    return totalPayment;
  }

  /** Return loan date */
  public java.util.Date getLoanDate() {
    return loanDate;
  }

  @Override
  public String toString() {
	  return "This loan object was created on: " + getLoanDate();
  }
}//end class Loan

