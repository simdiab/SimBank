package org.simbank.logic;

import java.util.List;

import org.simbank.entities.Account;
import org.simbank.entities.Transaction;
import org.simbank.entities.TransactionRecord;

/**
 * The core logic that processes {@link org.simbank.Transaction}s.
 * <p>
 * An instance of this class will be created by {@link BankLogic}.
 * Each method receives a Transaction as input, and then 
 * processes the transaction, before finally creating a {@link TransactionRecord}.
 * <p>
 * It is designed as non-static in the eventuality that we want to introduce Spring into the project.
 * This will allows us to refactor this into a SpringBean that can be injected into the app context 
 * at runtime in different parts of the app.
 * 
 * @see BankLogic
 * @author simon
 *
 */
public interface TransactionManager {
	
	/**
	 * Receives a Transaction object that must specify both an origination (accountFrom)
	 * and destination (accountTo), as well as the date and a transaction type.
	 *
	 * @param  t a Transaction object
	 * @see    Transaction
	 */
	public void transferBetweenAccounts(Transaction t);
	
	/**
	 * Receives a Transaction object that must specify a destination (accountTo) 
	 * account, as well as the date and a transaction type.
	 *
	 * @param  t a Transaction object
	 * @see    Transaction
	 */
	public void lodgeIntoAccount(Transaction t);
	
	/**
	 * Receives a Transaction object that must specify an origination (accountFrom)
	 * account, as well as the date and a transaction type.
	 *
	 * @param  t a Transaction object
	 * @see    Transaction
	 */
	public void withdrawFromAccount(Transaction t);

}
