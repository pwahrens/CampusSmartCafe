package back_end;

import java.util.ArrayList;

public class DietaryAccount {

	private double calBalance;
	private ArrayList<String> preferences;
	private ArrayList<Transaction> transactions;

	public DietaryAccount(double calBalance) {
		this.calBalance = calBalance;
		preferences = new ArrayList<String>();
		transactions = new ArrayList<Transaction>();
	}

	public double getCalBalance() {
		return calBalance;
	}

	public void setCalBalance(double calBalance) {
		this.calBalance = calBalance;
	}

	public ArrayList<String> getPreferences() {
		return preferences;
	}

	public boolean addPreference(String preference) {
		return this.preferences.add(preference);
	}

	public boolean removePreference(String preference) {
		return preferences.remove(preference);
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(Transaction transactions) {
		this.transactions.add(transactions);
	}
}
