package org.simbank.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The Account data object.
 * <p>
 * This is the core data structure of the whole app. There is a {@link BankLogic} instance that
 * keeps a List of in-memory Accounts (until such time as we feel the need to add a proper 
 * data-layer). Each Account in turns keeps a List of completed {@link TransactionRecord}s.
 * This simple Account/TransactionRecord structure is all we need to record all banking 
 * activity and can be used to reproduce Account histories in the form of statements.
 * @author simon
 *
 */
public class Account {
	private String name;
	private String address;
	private String phoneNumber;
	private BigDecimal balance;
	private List<TransactionRecord> transactionRecordList;
	
	public Account() {
		super();
		transactionRecordList = new ArrayList<TransactionRecord>();
	}

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

	/**
	 * Subtracts a specified amount from the current Account's balance
	 * @param amount
	 * @return the new balance
	 */
	public BigDecimal debit(BigDecimal amount) {
		this.setBalance(this.getBalance().subtract(amount));
		return this.getBalance();
	}

	/**
	 * Adds a specified amount to the current Account's balance
	 * @param amount
	 * @return the new balance
	 */
	public BigDecimal credit(BigDecimal amount) {
		this.setBalance(this.getBalance().add(amount));
		return this.getBalance();
	}
}
