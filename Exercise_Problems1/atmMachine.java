//This program generates an ATM machine
//that the user can choose from four different
//options.


import java.util.Scanner;

public class atmMachine{
	public static void main(String[] args){
		Account[] accountList = new Account[10];
		for(int i = 0; i < 10; i++){
			accountList[i] = new Account(i,100);
		}

		Scanner input = new Scanner (System.in);
		//loop to make user pick I.D. 0-9
		while(true){

			int id = 10;
			while((id >= 10) || (id < 0)){
			System.out.println("Please enter an I.D. (0-9): ");
				id = input.nextInt();
		 }

		 int choice = 0;
		 while(choice != 4){
			System.out.println("Main menu ");
			System.out.println("1: Check the balance ");
			System.out.println("2: Withdraw ");
			System.out.println("3: Desposit ");
			System.out.println("4: Exit ");

			System.out.println("Enter a choice: ");

		choice = input.nextInt();

			if(choice == 1)
				System.out.println("The balance is " + accountList[id].getBalance());
			else if(choice == 2){
				System.out.println("Enter an amount to withdraw ");
				double amount = input.nextDouble();
				accountList[id].withdraw(amount);
			}
			else if(choice == 3){
				System.out.println("Enter an amount to deposit ");
				double amount = input.nextDouble();
				accountList[id].deposit(amount);
			}
			else if(choice == 4)
			System.out.println("Exit ");
		}//end while choice != 4 loop
		}//end while true loop
	}//end main
}//end class atmMachine






