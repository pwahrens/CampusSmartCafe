package back_end;

public class User {
	private String userName;
	private String password;
	private ExpenseAccount expenseAccount;
	private DietaryAccount dietaryAccount;
	
	public User(String userName, String password)
	{
		this.userName=userName;
		this.password=password;
		expenseAccount=new ExpenseAccount(0);
		dietaryAccount=new DietaryAccount(2000);	
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public void setExpenseAccount(ExpenseAccount expenseAccount) {
		this.expenseAccount = expenseAccount;
	}
	public DietaryAccount getDietaryAccount() {
		return dietaryAccount;
	}
	public void setDietaryAccount(DietaryAccount dietaryAccount) {
		this.dietaryAccount = dietaryAccount;
	}
	
}
