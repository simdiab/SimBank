package org.simbank.logic;

import java.math.BigDecimal;
import java.util.List;

import org.simbank.entities.Account;
import org.simbank.entities.Transaction;
import org.simbank.entities.TransactionRecord;

public interface TransactionManager {
	public void processAndRecordTransaction(Transaction t);
	
	public void transferBetweenAccounts(Transaction t);
		
	public void lodgeIntoAccount(Transaction t);
	
	public void withdrawFromAccount(Transaction t);

	public List<TransactionRecord> getStatementForAccount(Account a);

}
