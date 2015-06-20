package org.simbank.entities;

import java.math.BigDecimal;

import org.simbank.entities.Account;

import junit.framework.TestCase;

public class AccountTest extends TestCase {

	public void testDebit() {
		BigDecimal startingBalance = new BigDecimal("100.00");
		Account fromAccount = new Account();
		fromAccount.setBalance(startingBalance);
		fromAccount.debit(new BigDecimal("20.00"));
		assertEquals(80.0, fromAccount.getBalance().doubleValue());
	}
	

	public void testCredit() {
		BigDecimal startingBalance = new BigDecimal("100.00");
		Account toAccount = new Account();
		toAccount.setBalance(startingBalance);
		toAccount.credit(new BigDecimal("20.00"));
		assertEquals(120.0, toAccount.getBalance().doubleValue());
	}

}
