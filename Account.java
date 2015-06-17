public class Account {
	private String name;
	private String address;
	private String phoneNumber;
	private BigDecimal balance;

	public BigDecimal debit(BigDecimal amount) {
		this.setBalance(this.getBalance() - amount);
		return this.getBalance;
	}

	public BigDecimal credit(BigDecimal amount) {
		this.setBalance(this.getBalance() + amount);
		return this.getBalance;
	}
}

public class Transaction {
	private	Date date;
	private String transactionType;
	private String amount;
	private Account accountTo;
	private Account accountFrom;
}

public class TransactionRecord {
	private Date date;
	private Account accountActedOn;
	private String action; //Debit or Credit
	private BigDecimal amount;
}

public class TransactionManager {
	private void processTransaction(Transaction t) {
		BigDecimal accountFromNewBalance = t.accountFrom.debit(t.amount);
		BigDecimal accountToNewBalance = t.accountTo.credit(t.amount);
		
		TransactionRecord accountFromTransactionRecord = new TransactionRecord(t.getDate(), t.accountFrom(), "Debit", t.getAmount(), accountFromNewBalance);
		TransactionRecord accountToTransactionRecord = new TransactionRecord(t.getDate(), t.accountTo(), "Credit", t.getAmount(), accountToNewBalance);
	}
}