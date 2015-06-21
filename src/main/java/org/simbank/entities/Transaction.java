package org.simbank.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
	private	Date date;
	private String transactionType;
	private BigDecimal amount;
	private Account accountFrom;
	private Account accountTo;
	
	public Transaction(Date date, String transactionType, BigDecimal amount, Account accountFrom, Account accountTo) {
		super();
		this.date = date;
		this.transactionType = transactionType;
		this.amount = amount;
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Account getAccountFrom() {
		return accountFrom;
	}
	public void setAccountFrom(Account accountFrom) {
		this.accountFrom = accountFrom;
	}
	public Account getAccountTo() {
		return accountTo;
	}
	public void setAccountTo(Account accountTo) {
		this.accountTo = accountTo;
	}
}
