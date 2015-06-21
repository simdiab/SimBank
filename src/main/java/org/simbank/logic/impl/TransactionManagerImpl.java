package org.simbank.logic.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.simbank.entities.Account;
import org.simbank.entities.Transaction;
import org.simbank.entities.TransactionRecord;
import org.simbank.logic.TransactionManager;

public class TransactionManagerImpl implements TransactionManager {
	
//	public void processAndRecordTransaction(Transaction t) {
//		if (("Transfer".equals(t.getTransactionType())) && (t.getAccountFrom() != null) && (t.getAccountTo() != null)){
//			transferBetweenAccounts(t);
//		}
//		else if (("Withdrawal".equals(t.getTransactionType())) && (t.getAccountFrom() != null)) {
//			withdrawFromAccount(t);
//		}
//		else if (("Lodgement".equals(t.getTransactionType())) && (t.getAccountTo() != null)) {
//			lodgeIntoAccount(t);
//		}
//	}
	
	public void transferBetweenAccounts(Transaction t) {
		BigDecimal accountFromNewBalance = t.getAccountFrom().debit(t.getAmount());
		BigDecimal accountToNewBalance = t.getAccountTo().credit(t.getAmount());
		
		TransactionRecord accountFromTransactionRecord = new TransactionRecord(t.getDate(), t.getAccountFrom(), t.getTransactionType(), t.getAmount(), accountFromNewBalance);
		t.getAccountFrom().getTransactionRecordList().add(accountFromTransactionRecord);

		TransactionRecord accountToTransactionRecord = new TransactionRecord(t.getDate(), t.getAccountTo(), t.getTransactionType(), t.getAmount(), accountToNewBalance);
		t.getAccountTo().getTransactionRecordList().add(accountToTransactionRecord);
	}
		
	public void lodgeIntoAccount(Transaction t) {
		BigDecimal accountToNewBalance = t.getAccountTo().credit(t.getAmount());
		TransactionRecord accountToTransactionRecord = new TransactionRecord(t.getDate(), t.getAccountTo(), t.getTransactionType(), t.getAmount(), accountToNewBalance);
		t.getAccountTo().getTransactionRecordList().add(accountToTransactionRecord);
	}
	
	public void withdrawFromAccount(Transaction t) {
		BigDecimal accountFromNewBalance = t.getAccountFrom().debit(t.getAmount());
		TransactionRecord accountFromTransactionRecord = new TransactionRecord(t.getDate(), t.getAccountFrom(), t.getTransactionType(), t.getAmount(), accountFromNewBalance);
		t.getAccountFrom().getTransactionRecordList().add(accountFromTransactionRecord);
	}

	public List<TransactionRecord> getStatementForAccount(Account a) {
		return a.getTransactionRecordList();
	}
}
