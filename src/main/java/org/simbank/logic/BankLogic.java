package org.simbank.logic;

import java.util.List;

import org.simbank.entities.Account;

public interface BankLogic {
	public void createAccount(String name, String address, String phoneNumber);
	public void lodge();
	public void withdraw();
	public void transfer();
	public void getTransactions();
	public List<Account> getAccountList();
}
