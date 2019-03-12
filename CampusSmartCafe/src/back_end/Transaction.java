package back_end;

import java.util.Arrays;
import java.util.Date;

public class Transaction {
	private User user;
	private double totalCost;
	private int cal, sodaCal, snackCal, mealCal;
	private Food[] purchases;
	private Date date;

	public Transaction(User user, Food[] purchases) {
		this.user = user;
		this.purchases = purchases;
		this.cal=this.mealCal=this.snackCal=this.sodaCal= 0;
		this.totalCost = 0;
		this.date = new Date();
		

		for (int i = 0; i < this.purchases.length; ++i) {
			this.totalCost += purchases[i].getPrice();
			this.cal += purchases[i].getCalCount();
			if(purchases[i] instanceof Meal)
				mealCal += purchases[i].getCalCount();
			if(purchases[i] instanceof Snack)
				snackCal += purchases[i].getCalCount();
			if(purchases[i] instanceof Soda)
				sodaCal += purchases[i].getCalCount();
		}
	}
	
	public Date getDate()
	{
		return date;
	}
	public int getSodaCal() {
		return sodaCal;
	}

	public int getSnackCal() {
		return snackCal;
	}

	public int getMealCal() {
		return mealCal;
	}

	public User getUser() {
		return user;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public int getCal() {
		return cal;
	}
	public Food[] getPurchases() {
		return purchases;
	}

	@Override
	public String toString() {
		return "Transaction [user=" + user + ", totalCost=" + totalCost + ", purchases=" + Arrays.toString(purchases)
				+ "]";
	}
}
