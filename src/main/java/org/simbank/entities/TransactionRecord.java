package org.simbank.entities;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionRecord {
	private Date date;
	private Account accountActedOn;
	private String action; //Debit or Credit
	private BigDecimal amount;
	private BigDecimal runningBalance;
	private Transaction transaction;
	
	public TransactionRecord(Date date, Account accountActedOn, String action,
			BigDecimal amount, BigDecimal runningBalance, Transaction transaction) {
		super();
		this.setDate(date);
		this.setAccountActedOn(accountActedOn);
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
	public Account getAccountActedOn() {
		return accountActedOn;
	}
	public void setAccountActedOn(Account accountActedOn) {
		this.accountActedOn = accountActedOn;
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
