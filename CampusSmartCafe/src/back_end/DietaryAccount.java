package back_end;

import java.util.ArrayList;

public class DietaryAccount {

	private int calBalance;
	private ArrayList<String> preferences;
	private ArrayList<Transaction> transactions;

	public DietaryAccount(int calBalance) {
		this.calBalance = calBalance;
		preferences = new ArrayList<String>();
		transactions = new ArrayList<Transaction>();
	}

	public int getCalBalance() {
		return calBalance;
	}

	public void setCalBalance(int calBalance) {
		this.calBalance = calBalance;
	}
	
	public void decrementCalBalance(int amount) {
		this.setCalBalance(this.calBalance - amount);
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
