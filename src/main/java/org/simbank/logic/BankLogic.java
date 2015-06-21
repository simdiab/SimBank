package org.simbank.logic;

import java.util.List;

import org.simbank.entities.Account;

public interface BankLogic {
	public void createAccount(String name, String address, String phoneNumber);
	public void lodge(Account a, String amount);
	public void withdraw(Account a, String amount);
	public void transfer(Account accountFrom, Account accountTo, String amount);
	public void getTransactions();
	public List<Account> getAccountList();
}
