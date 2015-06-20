package org.simbank;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.simbank.entities.Account;
import org.simbank.entities.Transaction;
import org.simbank.entities.TransactionRecord;

public class TransactionManager {
	
	public void processAndRecordTransaction(Transaction t) {
		if (("Transfer".equals(t.getTransactionType())) && (t.getAccountFrom() != null) && (t.getAccountTo() != null)){
			boolean success = transferBetweenAccounts(t);
		}
		else if (("Withdrawal".equals(t.getTransactionType())) && (t.getAccountFrom() != null)) {
			boolean success = withdrawFromAccount(t);
		}
		else if (("Lodgement".equals(t.getTransactionType())) && (t.getAccountTo() != null)) {
			boolean success = lodgeIntoAccount(t);
		}
	}
	
	private boolean transferBetweenAccounts(Transaction t) {
		BigDecimal accountFromNewBalance = t.getAccountFrom().debit(t.getAmount());
		BigDecimal accountToNewBalance = t.getAccountTo().credit(t.getAmount());
		
		TransactionRecord accountFromTransactionRecord = new TransactionRecord(t.getDate(), t.getAccountFrom(), "Debit", t.getAmount(), accountFromNewBalance);
		t.getAccountFrom().getTransactionRecordList().add(accountFromTransactionRecord);

		TransactionRecord accountToTransactionRecord = new TransactionRecord(t.getDate(), t.getAccountTo(), "Credit", t.getAmount(), accountToNewBalance);
		t.getAccountTo().getTransactionRecordList().add(accountToTransactionRecord);
		return true;
	}
		
	private boolean lodgeIntoAccount(Transaction t) {
		BigDecimal accountToNewBalance = t.getAccountTo().credit(t.getAmount());
		TransactionRecord accountToTransactionRecord = new TransactionRecord(t.getDate(), t.getAccountTo(), "Credit", t.getAmount(), accountToNewBalance);
		t.getAccountTo().getTransactionRecordList().add(accountToTransactionRecord);
		return true;
	}
	
	private boolean withdrawFromAccount(Transaction t) {
		BigDecimal accountFromNewBalance = t.getAccountFrom().debit(t.getAmount());
		TransactionRecord accountFromTransactionRecord = new TransactionRecord(t.getDate(), t.getAccountFrom(), "Debit", t.getAmount(), accountFromNewBalance);
		t.getAccountFrom().getTransactionRecordList().add(accountFromTransactionRecord);
		return true;
	}

	public List<TransactionRecord> getStatementForAccount(Account a) {
		return a.getTransactionRecordList();
	}
}
