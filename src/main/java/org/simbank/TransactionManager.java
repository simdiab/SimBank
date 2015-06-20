package org.simbank;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.simbank.entities.Account;
import org.simbank.entities.Transaction;
import org.simbank.entities.TransactionRecord;

public class TransactionManager {
	
	private void lodgeIntoAccount(Account a, BigDecimal amount) {
		BigDecimal accountNewBalance = a.credit(amount);
		TransactionRecord transactionRecord = new TransactionRecord(new Date(), a, "Credit", amount, accountNewBalance);
		a.getTransactionRecordList().add(transactionRecord);
	}
	
	private void withdrawFromAccount(Account a, BigDecimal amount) {
		BigDecimal accountNewBalance = a.debit(amount);
		TransactionRecord transactionRecord = new TransactionRecord(new Date(), a, "Debit", amount, accountNewBalance);
		a.getTransactionRecordList().add(transactionRecord);
	}

	private void processAndRecordTransaction(Transaction t) {
		BigDecimal accountFromNewBalance = t.getAccountFrom().debit(t.getAmount());
		BigDecimal accountToNewBalance = t.getAccountTo().credit(t.getAmount());
		
		TransactionRecord accountFromTransactionRecord = new TransactionRecord(t.getDate(), t.getAccountFrom(), "Debit", t.getAmount(), accountFromNewBalance);
		t.getAccountFrom().getTransactionRecordList().add(accountFromTransactionRecord);
		
		TransactionRecord accountToTransactionRecord = new TransactionRecord(t.getDate(), t.getAccountTo(), "Credit", t.getAmount(), accountToNewBalance);
		t.getAccountTo().getTransactionRecordList().add(accountToTransactionRecord);
	}
	
	private List<TransactionRecord> getStatementForAccount(Account a) {
		return a.getTransactionRecordList();
	}
}
