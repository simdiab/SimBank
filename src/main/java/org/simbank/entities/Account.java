package org.simbank.entities;

import java.math.BigDecimal;
import java.util.List;

public class Account {
	private String name;
	private String address;
	private String phoneNumber;
	private BigDecimal balance;
	private List<TransactionRecord> transactionRecordList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public List<TransactionRecord> getTransactionRecordList() {
		return transactionRecordList;
	}

	public void setTransactionRecordList(List<TransactionRecord> transactionRecordList) {
		this.transactionRecordList = transactionRecordList;
	}

	public BigDecimal debit(BigDecimal amount) {
		this.setBalance(this.getBalance().subtract(amount));
		return this.getBalance();
	}

	public BigDecimal credit(BigDecimal amount) {
		this.setBalance(this.getBalance().add(amount));
		return this.getBalance();
	}

}
