package org.simbank.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Transaction Record object model.
 * <p>
 * An instance of this will be created to record when a transaction has been completed.
 * It somewhat satisfies the general accounting practice of double-entry bookkeeping, in
 * that a TransactionRecord will be created for each account which is affected by a transaction.
 * For example, a transfer between two accounts will result in two TransactionRecords; one for 
 * each object.
 * <p>
 * Once a Transaction is processed, it will be added to a TransactionRecord via 
 * {@link TransactionRecord#setTransaction(Transaction)}. This will facilitate an audit trail 
 * should such a requirement occur in the future.
 * 
 * @see Transaction 
 * @author simon
 *
 */
public class TransactionRecord {
	private Date date;
	private String action; //Debit or Credit
	private BigDecimal amount;
	private BigDecimal runningBalance;
	private Transaction transaction;
	
	public TransactionRecord(Date date, Account accountActedOn, String action,
			BigDecimal amount, BigDecimal runningBalance, Transaction transaction) {
		super();
		this.setDate(date);
		this.setAction(action);
		this.setAmount(amount);
		this.setRunningBalance(runningBalance);
		this.setTransaction(transaction);
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getRunningBalance() {
		return runningBalance;
	}
	public void setRunningBalance(BigDecimal runningBalance) {
		this.runningBalance = runningBalance;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}
