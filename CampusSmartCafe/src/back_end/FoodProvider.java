package back_end;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import front_end.MainView;

public abstract class FoodProvider extends Observable {
	private String name;
	private ArrayList<Food> menu;
	private Point location;

	public FoodProvider(String name, ArrayList<Food> menu, Point location) {
		this.name = name;
		this.menu = menu;
		this.location = location;
		this.addObserver(MainView.expenseAccountView);
		//this.addObserver(MainView.dietaryAccountView);
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

	public Point getLocation() {
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

			dietaryAccount.addTransaction(transaction);
			expenseAccount.addTransaction(transaction);
		}
		
		setChanged();
		notifyObservers(transaction);
	}
}
