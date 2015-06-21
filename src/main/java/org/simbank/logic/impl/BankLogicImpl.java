package org.simbank.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.simbank.entities.Account;
import org.simbank.logic.BankLogic;
import org.simbank.logic.TransactionManager;

public class BankLogicImpl implements BankLogic {
	
	List<Account> accountList = new ArrayList<Account>();
	TransactionManager transactionManager = new TransactionManagerImpl();
	
	@Override
	public void createAccount(String name, String address, String phoneNumber) {
		Account account = new Account();
		account.setName(name);
		account.setAddress(address);
		account.setPhoneNumber(phoneNumber);
		accountList.add(account);
	}
 
	public void lodge() {
		
	}
	
	public void withdraw() {
		
	}
	
	public void transfer() {
		
	}
	
	public void getTransactions() {
		
	}

	public List<Account> getAccountList() {
		return accountList;
	}





}
