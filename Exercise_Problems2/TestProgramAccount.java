//This program tests the program account by adding values
//to the account. It calculates a withdraw and a deposit.
//It gives the date of when this occurs.

public class TestProgramAccount{
	public static void main(String[] args){
		Account a1 = new Account(1122,20000.0);
		a1.setAnnualInterestRate(4.5);

		a1.withdraw(2500);
		System.out.println("After withdrawing 2500, the "
		+ " amount is " + a1.getBalance());

		a1.deposit(3000);
		System.out.println("After depositing 3000, the "
		+ " amount is " + a1.getBalance());


		System.out.println("The balance is " + a1.getBalance());
		System.out.println("The monthly interest is " + a1.getMonthlyInterest());
		System.out.println("The date is " + a1.getDateCreated());
	}//end main

}//end class TestProgramAccount
