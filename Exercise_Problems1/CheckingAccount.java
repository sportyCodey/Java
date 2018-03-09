//This program creates the class CheckingAccount
//and it is a child of Account


import java.util.*;

public class CheckingAccount
	extends Account{

	private double overDraft;

	public CheckingAccount(int id,double balance,double overDraft){
		super(id,balance);
		this.overDraft = overDraft;
	}

	public void withdraw(double amount){
		if ((getBalance() - amount) >= overDraft){
		 super.withdraw(amount);
	    }
		else
		  System.out.println("Insufficient funds. ");

	}

	public String toString(){
		return ("This checking account is created on " + getDateCreated() +
		" and the balance is " + getBalance());
	}

}//end class CheckingAccount