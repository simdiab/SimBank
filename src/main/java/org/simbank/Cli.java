package org.simbank;

import java.io.Console;
import java.math.BigDecimal;

import org.simbank.entities.Account;
import org.simbank.entities.TransactionRecord;
import org.simbank.logic.BankLogic;
import org.simbank.logic.impl.BankLogicImpl;
import org.simbank.util.BankUtil;

/**
 * The entry-point into the app. This class creates a UI and receives user input.
 * User input is sent to an instance of BankLogic, which then creates Transaction 
 * objects that the TransactionManager will process.
 * <p>
 * This class creates one instance of BankLogic, which lives for the lifetime of the app
 * and contains all the banking data.
 * @author simon
 *
 */
public class Cli {
	
	public static void start(){
		BankLogic bankLogic = new BankLogicImpl();
		Console console = System.console();
		
		createDummyData(bankLogic);
		
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        console.printf("Welcome to SimBank.%n");
        executeInput(console, bankLogic);
	}
	
	/**
	 * The command loop that interacts with the user. All subsequent methods should call 
	 * this method after execution in order to continue the app. 
	 * @param console
	 * @param bankLogic
	 */
	private static void executeInput(Console console, BankLogic bankLogic) {
		String input = console.readLine("Please enter an option.%n "
				+ "[c]reate account%n "
				+ "[l]odge money%n "
				+ "[w]ithdraw money%n "
				+ "[t]ransfer between accounts%n "
				+ "[v]iew account statement%n "
				+ "[e]xit%n");
        switch (input) {
         case "c" : createAccount(console, bankLogic);
         			break;
         case "l" : lodge(console, bankLogic);
         			break;
         case "w" : withdraw(console, bankLogic);
		   			break;
         case "t" : transfer(console, bankLogic);
					break;
         case "v" : printStatement(console, bankLogic);
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
		
		if (a.getBalance().subtract(new BigDecimal(amount)).signum() == -1 ) {
			warnOnNegativeBalance(console, bankLogic);
		}
		
		bankLogic.withdraw(a, amount);
		console.printf("Account closing balance: " + a.getBalance() + ".%n");
		executeInput(console, bankLogic);
	}
	
	private static void transfer(Console console, BankLogic bankLogic){
		console.printf("You've chosen to transfer money between two accounts%n");
		console.printf("Available accounts:%n");
		for (Account a : bankLogic.getAccountList()) {
			console.printf("Account: " + a.getName() + "%n");
		}
		String accountName = console.readLine("Enter the name of the account to transfer from: %n");
		Account accountFrom = BankUtil.getAccountByName(bankLogic.getAccountList(), accountName);
		if (accountFrom == null) {
			console.printf("Account not found. Please try again.%n");
			lodge(console, bankLogic);
		}
		console.printf("Account found: " + accountFrom.getName() + ".%n Account starting balance: " + accountFrom.getBalance() + ".%n");
		
		accountName = console.readLine("Enter the name of the account to transfer to: %n");
		Account accountTo = BankUtil.getAccountByName(bankLogic.getAccountList(), accountName);
		if (accountTo == null) {
			console.printf("Account not found. Please try again.%n");
			lodge(console, bankLogic);
		}
		console.printf("Account found: " + accountTo.getName() + ".%n Account starting balance: " + accountTo.getBalance() + ".%n");
		
		
		String amount = console.readLine("Enter the amount of money to transfer.%n");
		
		if (accountFrom.getBalance().subtract(new BigDecimal(amount)).signum() == -1 ) {
			warnOnNegativeBalance(console, bankLogic);
		}
		
		bankLogic.transfer(accountFrom, accountTo, amount);
		console.printf("Originating account: " + accountFrom.getName() + ". Closing balance: " + accountFrom.getBalance() + ".%n");
		console.printf("Destination account: " + accountTo.getName() + ". Closing balance: " + accountTo.getBalance() + ".%n");
		executeInput(console, bankLogic);
	}
	
	private static void printStatement(Console console, BankLogic bankLogic) {
		console.printf("You've chosen to view an account statement %n");
		console.printf("Available accounts:%n");
		for (Account a : bankLogic.getAccountList()) {
			console.printf("Account: " + a.getName() + "%n");
		}
		String accountName = console.readLine("Enter the name of the account to view: %n");
		Account a = BankUtil.getAccountByName(bankLogic.getAccountList(), accountName);
		if (a == null) {
			console.printf("Account not found. Please try again.%n");
			lodge(console, bankLogic);
		}
		console.printf("Account found: " + a.getName() + ".%nAccount starting balance: " + a.getBalance() + ".%n");
		
		console.printf("Action\t\tAmount\t\tBalance%n");
		for (TransactionRecord tr : a.getTransactionRecordList()) {
			console.printf(tr.getAction() + "\t" + tr.getAmount() + "\t\t" + tr.getRunningBalance() + "%n");
		}
		console.printf("Final balance:\t" + a.getBalance() + "%n");
		executeInput(console, bankLogic);
	}
	
	private static void exitWithMessage() {
		System.out.println("Thanks for using SimBank. Now exiting");
		System.exit(1);
	}
	
	private static void warnOnNegativeBalance(Console console, BankLogic bankLogic) {
		String option = console.readLine("This Account will go into negative balance (overdraft). Continue? [y/n]%n");
		if ("N".equalsIgnoreCase(option)) {
			console.printf("Transaction cancelled.%n");
			executeInput(console, bankLogic);
		}
	}
	
	private static void createDummyData(BankLogic bankLogic) {
		bankLogic.createAccount("John", "123 John St", "087 123 1234");
		BankUtil.getAccountByName(bankLogic.getAccountList(), "John").credit(new BigDecimal("100.00"));
		bankLogic.createAccount("Mary", "987 Mary St", "086 987 6543");
		BankUtil.getAccountByName(bankLogic.getAccountList(), "Mary").credit(new BigDecimal("200.00"));
	}

}
