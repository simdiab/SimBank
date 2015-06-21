package org.simbank.logic;

import java.util.List;

import org.simbank.entities.Account;
import org.simbank.logic.impl.BankLogicImpl;

import junit.framework.TestCase;

public class BankLogicTest extends TestCase {

	BankLogic bankLogic = new BankLogicImpl();
	
	public void setUp() {
		String accountName = "Account1";
		String accountAddress = "123 Bank St";
		String accountPhoneNumber = "0871234567";
		
		bankLogic.createAccount(accountName, accountAddress, accountPhoneNumber);
		
		accountName = "Account2";
		accountAddress = "456 Bank St";
		accountPhoneNumber = "0879876543";
		
		bankLogic.createAccount(accountName, accountAddress, accountPhoneNumber);
	}
	
	public void testCreateAccount() {
		assertEquals(2, bankLogic.getAccountList().size());
		assertEquals("0.00", bankLogic.getAccountList().get(0).getBalance().toString());
	}

	public void testLodge() {
		Account a =  bankLogic.getAccountList().get(0);
		String amount = "20.00";
		bankLogic.lodge(a, amount);
		assertEquals("20.00", a.getBalance().toString());
	}

	public void testWithdraw() {
		Account a =  bankLogic.getAccountList().get(0);
		String amount = "20.00";
		bankLogic.withdraw(a, amount);
		assertEquals("-20.00", a.getBalance().toString());
	}

	public void testTransfer() {
		Account a1 =  bankLogic.getAccountList().get(0);
		Account a2 =  bankLogic.getAccountList().get(1);
		String amount = "20.00";
		bankLogic.transfer(a1, a2, amount);
		assertEquals("-20.00", a1.getBalance().toString());
		assertEquals("20.00", a2.getBalance().toString());
	}

	public void testGetAccountList() {
		List<Account> accountList = bankLogic.getAccountList();
		assertEquals(2, accountList.size());
	}

}
