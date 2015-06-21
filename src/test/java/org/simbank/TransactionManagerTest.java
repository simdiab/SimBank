package org.simbank;

import java.math.BigDecimal;
import java.util.Date;

import org.simbank.entities.Account;
import org.simbank.entities.Transaction;
import org.simbank.logic.impl.TransactionManagerImpl;

import junit.framework.TestCase;

public class TransactionManagerTest extends TestCase {
	
	private TransactionManagerImpl tm = new TransactionManagerImpl();
	
	public void testLodgeIntoAccount() {
		Account a = new Account();
		a.setBalance(new BigDecimal("0.00"));
		
		Transaction t = new Transaction(new Date(), "Lodgement", new BigDecimal("20.99"), null, a);
		
		tm.processAndRecordTransaction(t);
		assertEquals("20.99", a.getBalance().toString());
	}
	
	public void testWithdrawFromAccount() {
		Account a = new Account();
		a.setBalance(new BigDecimal("20.99"));
		
		Transaction t = new Transaction(new Date(), "Withdrawal", new BigDecimal("20.99"), a, null);
		
		tm.processAndRecordTransaction(t);
		assertEquals("0.00", a.getBalance().toString());
	}
	
	public void testProcessAndRecordTransaction() {
		Account accountFrom = new Account();
		accountFrom.setBalance(new BigDecimal("100"));
		Account accountTo = new Account();
		accountTo.setBalance(new BigDecimal("100"));
		
		Transaction t = new Transaction(new Date(), "Transfer", new BigDecimal("50.00"), accountFrom, accountTo);
		
		tm.processAndRecordTransaction(t);
		assertEquals("50.00", accountFrom.getBalance().toString());
		assertEquals("150.00", accountTo.getBalance().toString());
	}

}
