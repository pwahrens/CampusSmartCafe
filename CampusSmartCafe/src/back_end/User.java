package back_end;

import java.io.Serializable;

public class User implements Serializable {
	private String password;
	private ExpenseAccount expenseAccount;
	private DietaryAccount dietaryAccount;

	public User(String password) {
		this.password = password;
		expenseAccount = new ExpenseAccount(0);
		dietaryAccount = new DietaryAccount(2000);
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ExpenseAccount getExpenseAccount() {
		return expenseAccount;
	}

	public DietaryAccount getDietaryAccount() {
		return dietaryAccount;
	}

	public void setExpenseAccount(ExpenseAccount expenseAccount) {
		this.expenseAccount = expenseAccount;
	}

	public void setDietaryAccount(DietaryAccount dietaryAccount) {
		this.dietaryAccount = dietaryAccount;
	}
}
