package back_end;

import java.io.Serializable;
import java.util.ArrayList;

public class DietaryAccount implements Serializable {

	private int maxCalBalance;
	private ArrayList<String> preferences;
	private ArrayList<Transaction> transactions;

	public DietaryAccount(int calBalance) {
		this.maxCalBalance = calBalance;
		preferences = new ArrayList<String>();
		transactions = new ArrayList<Transaction>();
	}

	public int getCalBalance() {
		int total=0;
		for(int i=0; i<transactions.size(); i++)
			total+=transactions.get(i).getCal();
		return maxCalBalance-total;
	}
	
	public int getMaxCalBalance() {
		return maxCalBalance;
	}

	public void setMaxCalBalance(int calBalance) {
		this.maxCalBalance = calBalance;
		if(this.maxCalBalance<this.getCalBalance())
			this.maxCalBalance = this.getCalBalance();
	}
	
	public ArrayList<String> getPreferences() {
		return preferences;
	}

	public boolean addPreference(String preference) {
		return this.preferences.add(preference);
	}

	public boolean removePreferences() {
		return preferences.removeAll(preferences);
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(Transaction transactions) {
			this.transactions.add(transactions);
	}
	
	public void clearTransactions()
	{
		this.transactions.removeAll(transactions);
	}
	
	@Override
	public String toString() {
		return this.getCalBalance() + ";" + maxCalBalance + ";" + preferences + ";" + transactions;
	}
}
