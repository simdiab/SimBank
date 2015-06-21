package org.simbank.logic;

import java.util.List;

import org.simbank.entities.Account;

public class BankUtil {

	public static Account getAccountByName(List<Account> accountList, String accountName) {
		for (Account a : accountList) {
			if (a.getName().equalsIgnoreCase(accountName)) {
				return a;
			}
		}
		return null;
	}
}
