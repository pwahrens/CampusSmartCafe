package back_end;

import java.util.ArrayList;

public abstract class FoodProvider {
	private String name;
	private ArrayList<Food> menu;
	private int[] location;

	public FoodProvider(String name, ArrayList<Food> menu, int[] location) {
		this.name = name;
		this.menu = menu;
		this.location = location;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Food> getMenu() {
		return menu;
	}

	public void setMenu(ArrayList<Food> menu) {
		this.menu = menu;
	}

	public int[] getLocation() {
		return location;
	}

	public boolean addFoodToMenu(Food food) {
		return this.menu.add(food);
	}

	public boolean removeFoodFromMenu(Food food) {
		return this.menu.remove(food);
	}

	public void updateAccounts(Transaction transaction) {
		DietaryAccount dietaryAccount = transaction.getUser().getDietaryAccount();
		ExpenseAccount expenseAccount = transaction.getUser().getExpenseAccount();

		for (int i = 0; i < transaction.getPurchases().length; ++i) {
			Food item = transaction.getPurchases()[i];

			dietaryAccount.decrementCalBalance(item.getCalCount());
			expenseAccount.decrementBalance(item.getPrice());
		}
	}
}
