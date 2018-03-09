//This program creates the class Account and the methods associated with it to
//calculate an account.

import java.util.*;

public class Account{
	//private instance variables
	private int id;
	private double balance;
	private Date dateCreated;

	//private static variable
	private static double annualInterestRate;

	public Account(){
		id = 0;
		balance = 0;
		dateCreated = new Date();
		annualInterestRate = 0;
	}

	public Account(int id, double balance){
		this.id = id;
		this.balance = balance;
		dateCreated = new Date();
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public double getBalance(){
		return balance;
	}

	public void setBalance(double balance){
		this.balance = balance;
	}

	public double getAnnualInterestRate(){
		return annualInterestRate;
	}

	public void setAnnualInterestRate(double newRate){
		annualInterestRate = newRate;
	}

	public Date getDateCreated(){
		return dateCreated;
	}

	public double getMonthlyInterestRate(){
		double newannualInterestRate = annualInterestRate/100;
		double monthlyInterestRate = newannualInterestRate/12;
		return monthlyInterestRate;
	}

	public double getMonthlyInterest(){
		double amount = balance * getMonthlyInterestRate();
		return amount;
	}

	public void withdraw(double amt){
		balance = balance - amt;
	}

	public void deposit(double amt){
		balance = balance + amt;
	}
}//end class Account

