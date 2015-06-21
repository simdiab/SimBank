package org.simbank.logic;

import java.util.List;

import org.simbank.entities.Account;

/**
 * The control logic that sits between the UI and the backend functionality.
 * The member methods of this class typically receive raw String input from the UI
 * ({@link Cli}) class, and transform it into {@link Transaction} objects to be sent-to and 
 * processed-by an instance of {@link TransactionManager}.
 * <p>
 * In lieu of a data access layer, there is one instance of this class that contains 
 * the List of all {@link Account} objects, which in turn each contain a list of all the 
 * transaction history. As long as the app is running, all data will be stored in memory 
 * and is accessible in {@link #getAccountList()}. If we eventually add a real data layer to
 * the app, this class will be refactored to use DAOs.
 * 
 * @see 	Cli
 * @see 	Transaction
 * @see		TransactionManager
 * @author 	simon
 *
 */
public interface BankLogic {
	/**
	 * Calls {@link Account#Account()} to create an Account instance and adds it to 
	 * a List of Account objects.
	 * @param name
	 * @param address
	 * @param phoneNumber
	 */
	public void createAccount(String name, String address, String phoneNumber);
	
	/**
	 * Creates a Transaction object, and then calls 
	 * {@link TransactionManager#lodgeIntoAccount(org.simbank.entities.Transaction)}
	 * @param a the Account into which we want to deposit.
	 * @param amount a String representation of the amount, which will be converted to a 
	 * BigDecimal
	 */
	public void lodge(Account a, String amount);
	
	/**
	 * Creates a Transaction object, and then calls 
	 * {@link TransactionManager#withdrawFromAccount(org.simbank.entities.Transaction)}
	 * @param a the Account from which we want to withdraw.
	 * @param amount a String representation of the amount, which will be converted to a 
	 * BigDecimal
	 */
	public void withdraw(Account a, String amount);
	
	/**
	 * Creates a Transaction object, and then calls
	 * {@link TransactionManager#transferBetweenAccounts(org.simbank.entities.Transaction)}
	 * @param accountFrom the Account from which we want to transfer.
	 * @param accountTo the Account to which we want to transfer.
	 * @param amount a String representation of the amount, which will be converted to a 
	 * BigDecimal
	 */
	public void transfer(Account accountFrom, Account accountTo, String amount);
	
	/**
	 * Return the List of Accounts in memory. Each Account in turn contains a List of all 
	 * completed {@link TransactionRecord}s, allowing us to build up an entire statement history.
	 * @return a List of created Accounts and their corresponding transaction histories.
	 */
	public List<Account> getAccountList();
}
