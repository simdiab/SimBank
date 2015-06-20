package org.simbank;

import java.math.BigDecimal;
import java.util.Date;

import org.simbank.entities.Account;
import org.simbank.entities.Transaction;

import junit.framework.TestCase;

public class TransactionManagerTest extends TestCase {
	
	private TransactionManager tm = new TransactionManager();
	
	public void testLodgeIntoAccount() {
		Account a = new Account();
		a.setBalance(new BigDecimal("0.00"));
		tm.lodgeIntoAccount(a, new BigDecimal("20.99"));
		assertEquals("20.99", a.getBalance().toString());
	}
	
	public void testWithdrawFromAccount() {
		Account a = new Account();
		a.setBalance(new BigDecimal("20.99"));
		tm.withdrawFromAccount(a, new BigDecimal("20.99"));
		assertEquals("0.00", a.getBalance().toString());
	}
	
	public void testProcessAndRecordTransaction() {
		Account accountTo = new Account();
		accountTo.setBalance(new BigDecimal("100"));
		Account accountFrom = new Account();
		accountFrom.setBalance(new BigDecimal("100"));
		
		Transaction t = new Transaction(new Date(), "Transfer", new BigDecimal("50.00"), accountTo, accountFrom);
		
		tm.processAndRecordTransaction(t);
		assertEquals("150.00", accountTo.getBalance().toString());
		assertEquals("50.00", accountFrom.getBalance().toString());
	}

}
