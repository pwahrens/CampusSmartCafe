package back_end;

import java.io.Serializable;
import java.util.ArrayList;

public class DietaryAccount implements Serializable {
	
	private int calBalance;
	private int maxCalBalance;
	private ArrayList<String> preferences;
	private ArrayList<Transaction> transactions;

	public DietaryAccount(int calBalance) {
		this.maxCalBalance = this.calBalance = calBalance;
		preferences = new ArrayList<String>();
		transactions = new ArrayList<Transaction>();
	}

	public int getCalBalance() {
		return calBalance;
	}
	
	public int getMaxCalBalance() {
		return maxCalBalance;
	}

	public void setMaxCalBalance(int calBalance) {
		this.maxCalBalance = calBalance;
		if(this.maxCalBalance<this.calBalance)
			this.maxCalBalance = this.calBalance;
	}
	
	public void decrementCalBalance(int amount) {
		this.calBalance -= amount;
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
	
	@Override
	public String toString() {
		return calBalance + ";" + maxCalBalance + ";" + preferences + ";" + transactions;
	}
}
