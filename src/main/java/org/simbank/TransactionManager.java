package org.simbank;

import java.math.BigDecimal;

public class TransactionManager {

	private void processAndRecordTransaction(Transaction t) {
		BigDecimal accountFromNewBalance = t.getAccountFrom().debit(t.getAmount());
		BigDecimal accountToNewBalance = t.getAccountTo().credit(t.getAmount());
		
		TransactionRecord accountFromTransactionRecord = new TransactionRecord(t.getDate(), t.getAccountFrom(), "Debit", t.getAmount(), accountFromNewBalance);
		TransactionRecord accountToTransactionRecord = new TransactionRecord(t.getDate(), t.getAccountTo(), "Credit", t.getAmount(), accountToNewBalance);
	}
}
