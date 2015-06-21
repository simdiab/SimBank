package org.simbank.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.simbank.entities.Account;
import org.simbank.entities.Transaction;
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
		account.setBalance(new BigDecimal("0.00"));
		accountList.add(account);
	}
 
	public void lodge(Account a, String amount) {
		Transaction t = new Transaction(new Date(), "Lodgement", new BigDecimal(amount), null, a);
		transactionManager.lodgeIntoAccount(t);	
	}
	
	public void withdraw(Account a, String amount) {
		Transaction t = new Transaction(new Date(), "WithDrawal", new BigDecimal(amount), a, null);
		transactionManager.withdrawFromAccount(t);	
	}
	
	public void transfer(Account accountFrom, Account accountTo, String amount) {
		Transaction t = new Transaction(new Date(), "WithDrawal", new BigDecimal(amount), accountFrom, accountTo);
		transactionManager.transferBetweenAccounts(t);
	}

	public List<Account> getAccountList() {
		return accountList;
	}
}
