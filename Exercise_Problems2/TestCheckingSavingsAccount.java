//This program calculates the checking account
//and the savings account

public class TestCheckingSavingsAccount{
	public static void main(String[] args){

		CheckingAccount cAccount = new CheckingAccount(12, 200, -100);
		SavingsAccount sAccount = new SavingsAccount(55, 500);

		System.out.println(cAccount.toString());
		cAccount.withdraw(500);
		System.out.println(cAccount.toString());

		System.out.println(sAccount.toString());
		sAccount.withdraw(600);
		System.out.println(sAccount.toString());
	}//end main
}//end class TestCheckingSavingsAccount

