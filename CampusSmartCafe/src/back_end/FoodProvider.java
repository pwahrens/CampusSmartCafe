package back_end;

import java.util.ArrayList;

public abstract class FoodProvider {
	private ArrayList<Food> menu;

	public FoodProvider(ArrayList<Food> menu) {
		this.menu = menu;
	}

	public ArrayList<Food> getMenu() {
		return menu;
	}

	public void setMenu(ArrayList<Food> menu) {
		this.menu = menu;
	}

	public void addFoodToMenu(Food food) {
		this.menu.add(food);
	}

	public void removeFoodFromMenu(Food food) {
		this.menu.remove(food);
	}

	public void updateDietaryAccount(Transaction transaction) {
		// TODO
	}

	public void updateExpenseAccount(Transaction transaction) {
		
	}
}
