package org.simbank.util;

import java.util.List;

import org.simbank.entities.Account;

/**
 * Contains common util methods that don't logically belong to other classes.
 * @author simon
 *
 */
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
