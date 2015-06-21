package org.simbank;

import java.math.BigDecimal;
import java.util.Date;

import org.simbank.entities.Account;
import org.simbank.entities.Transaction;
import org.simbank.entities.TransactionRecord;
import org.simbank.logic.impl.TransactionManagerImpl;

import junit.framework.TestCase;

public class TransactionManagerTest extends TestCase {
	
	private TransactionManagerImpl tm = new TransactionManagerImpl();
	
	public void testLodgeIntoAccount() {
		Account a = new Account();
		a.setBalance(new BigDecimal("0.00"));
		
		Transaction t = new Transaction(new Date(), "Lodgement", new BigDecimal("20.99"), null, a);
		
		tm.lodgeIntoAccount(t);
		assertEquals("20.99", a.getBalance().toString());
	}
	
	public void testLodgeTransactionRecord() {
		Account a = new Account();
		a.setBalance(new BigDecimal("0.00"));
		
		Transaction t = new Transaction(new Date(), "Lodgement", new BigDecimal("20.99"), null, a);
		tm.lodgeIntoAccount(t);
		
		assertEquals(1, a.getTransactionRecordList().size());
		
		TransactionRecord tr = a.getTransactionRecordList().get(0);
		assertEquals("Lodgement", tr.getAction());
		assertEquals("20.99", tr.getRunningBalance().toString());
		assertEquals(t, tr.getTransaction());
	}
	
	public void testWithdrawFromAccount() {
		Account a = new Account();
		a.setBalance(new BigDecimal("20.99"));
		
		Transaction t = new Transaction(new Date(), "Withdrawal", new BigDecimal("20.99"), a, null);
		
		tm.withdrawFromAccount(t);
		assertEquals("0.00", a.getBalance().toString());
	}
	
	public void testWithdrawTransactionRecord() {
		Account a = new Account();
		a.setBalance(new BigDecimal("20.99"));
		
		Transaction t = new Transaction(new Date(), "Withdrawal", new BigDecimal("20.99"), a, null);
		
		tm.withdrawFromAccount(t);
		
		assertEquals(1, a.getTransactionRecordList().size());
		
		TransactionRecord tr = a.getTransactionRecordList().get(0);
		assertEquals("Withdrawal", tr.getAction());
		assertEquals("0.00", tr.getRunningBalance().toString());
		assertEquals(t, tr.getTransaction());
	}
	
	public void testTransfer() {
		Account accountFrom = new Account();
		accountFrom.setBalance(new BigDecimal("100"));
		Account accountTo = new Account();
		accountTo.setBalance(new BigDecimal("100"));
		
		Transaction t = new Transaction(new Date(), "Transfer", new BigDecimal("50.00"), accountFrom, accountTo);
		
		tm.transferBetweenAccounts(t);
		assertEquals("50.00", accountFrom.getBalance().toString());
		assertEquals("150.00", accountTo.getBalance().toString());
	}
	
	public void testTransferTransactionRecord() {
		Account accountFrom = new Account();
		accountFrom.setBalance(new BigDecimal("100"));
		Account accountTo = new Account();
		accountTo.setBalance(new BigDecimal("100"));
		
		Transaction t = new Transaction(new Date(), "Transfer", new BigDecimal("50.00"), accountFrom, accountTo);
		tm.transferBetweenAccounts(t);
		
		assertEquals(1, accountFrom.getTransactionRecordList().size());
		assertEquals(1, accountTo.getTransactionRecordList().size());
		
		TransactionRecord tr1 = accountFrom.getTransactionRecordList().get(0);
		assertEquals("Transfer", tr1.getAction());
		assertEquals("50.00", tr1.getRunningBalance().toString());
		assertEquals(t, tr1.getTransaction());
		
		TransactionRecord tr2 = accountFrom.getTransactionRecordList().get(0);
		assertEquals("Transfer", tr2.getAction());
		assertEquals("50.00", tr2.getRunningBalance().toString());
		assertEquals(t, tr2.getTransaction());
	}
}
