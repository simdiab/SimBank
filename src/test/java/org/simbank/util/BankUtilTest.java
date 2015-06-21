package org.simbank.util;

import org.simbank.logic.BankLogic;
import org.simbank.logic.impl.BankLogicImpl;

import junit.framework.TestCase;

public class BankUtilTest extends TestCase {
	

	public void testGetAccountByName() {
		BankLogic bankLogic = new BankLogicImpl();
		String accountName = "Account1";
		String accountAddress = "123 Bank St";
		String accountPhoneNumber = "0871234567";
		
		bankLogic.createAccount(accountName, accountAddress, accountPhoneNumber);
		
		accountName = "Account2";
		accountAddress = "456 Bank St";
		accountPhoneNumber = "0879876543";
		
		bankLogic.createAccount(accountName, accountAddress, accountPhoneNumber);
		
		assertNotNull(BankUtil.getAccountByName(bankLogic.getAccountList(), "Account1"));
	}

}
