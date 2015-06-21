package org.simbank;

import java.io.Console;

import org.simbank.entities.Account;
import org.simbank.logic.BankLogic;
import org.simbank.logic.BankUtil;
import org.simbank.logic.impl.BankLogicImpl;

public class Cli {
	
	
	public static void start(){
		BankLogic bankLogic = new BankLogicImpl();
		Console console = System.console();
		
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        console.printf("Welcome to SimBank.%n");
        executeInput(console, bankLogic);
	}
	
	private static void executeInput(Console console, BankLogic bankLogic) {
		String input = console.readLine("Please enter an option.%n [c]reate account%n [l]odge money%n [w]ithdraw money%n [e]xit%n");
        switch (input) {
         case "c" : createAccount(console, bankLogic);
         			break;
         case "l" : lodge(console, bankLogic);
         			break;
         case "w" : withdraw(console, bankLogic);
		   			break;
         case "e" : exitWithMessage();
         			break;
         default : executeInput(console, bankLogic);
        }
	}
	
	private static void createAccount(Console console, BankLogic bankLogic) {
		console.printf("You've chosen to create an Account%n");
		String accountName = console.readLine("Enter an account name: %n");
		String accountAddress = console.readLine("Enter an address: %n");
		String accountPhoneNumber= console.readLine("Enter a phone number: %n");
		
		bankLogic.createAccount(accountName, accountAddress, accountPhoneNumber);
		console.printf("Account created with name: " + bankLogic.getAccountList().get(0).getName() + "%n");
		executeInput(console, bankLogic);
	}
	
	private static void lodge(Console console, BankLogic bankLogic) {
		console.printf("You've chosen to lodge money in an account%n");
		console.printf("Available accounts:%n");
		for (Account a : bankLogic.getAccountList()) {
			console.printf("Account: " + a.getName() + "%n");
		}
		String accountName = console.readLine("Enter the name of the account to lodge into: %n");
		Account a = BankUtil.getAccountByName(bankLogic.getAccountList(), accountName);
		if (a == null) {
			console.printf("Account not found. Please try again.%n");
			lodge(console, bankLogic);
		}
		console.printf("Account found: " + a.getName() + ".%n Account starting balance: " + a.getBalance() + ".%n");
		String amount = console.readLine("Enter the amount of money to lodge.%n");
		
		bankLogic.lodge(a, amount);
		console.printf("Account closing balance: " + a.getBalance() + ".%n");
		executeInput(console, bankLogic);
	}
	
	private static void withdraw(Console console, BankLogic bankLogic) {
		console.printf("You've chosen to withdraw money from an account%n");
		console.printf("Available accounts:%n");
		for (Account a : bankLogic.getAccountList()) {
			console.printf("Account: " + a.getName() + "%n");
		}
		String accountName = console.readLine("Enter the name of the account to withdraw from: %n");
		Account a = BankUtil.getAccountByName(bankLogic.getAccountList(), accountName);
		if (a == null) {
			console.printf("Account not found. Please try again.%n");
			lodge(console, bankLogic);
		}
		console.printf("Account found: " + a.getName() + ".%n Account starting balance: " + a.getBalance() + ".%n");
		String amount = console.readLine("Enter the amount of money to withdraw.%n");
		
		bankLogic.withdraw(a, amount);
		console.printf("Account closing balance: " + a.getBalance() + ".%n");
		executeInput(console, bankLogic);
	}
	

	
	private static void exitWithMessage() {
		System.out.println("Thanks for using SimBank. Now exiting");
		System.exit(1);
	}

}
