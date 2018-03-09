//This program creates the class SavingsAccount
//and it is a child of Account


import java.util.*;

public class SavingsAccount
	extends Account{

	public SavingsAccount(int id, double balance){
		super(id, balance);

	}

	public void withdraw(double amount){
	if ((getBalance() - amount)>= 0 )
	   super.withdraw(amount);
	else
	   System.out.println("Insufficient funds. ");

	}

	public String toString(){
		return ("This savings account is created on " + getDateCreated() +
		" and the balance is " + getBalance());
	}

}//end class CheckingAccount