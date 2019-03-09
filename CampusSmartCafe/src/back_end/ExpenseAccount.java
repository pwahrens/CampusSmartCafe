package back_end;

import java.util.ArrayList;

public class ExpenseAccount {
	private double balance;
	private ArrayList<Transaction> transactions;

	public ExpenseAccount(double balance) {
		this.balance = balance;
		this.transactions = new ArrayList<Transaction>();
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void decrementBalance(double amount) {
		this.setBalance(this.balance - amount);
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(Transaction transactions) {
		this.transactions.add(transactions);
	}
}
