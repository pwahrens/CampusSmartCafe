package back_end;

import java.util.Arrays;

public class Transaction {
	private User user;
	private double totalCost;
	private int calCost;
	private Food[] purchases;

	public Transaction(User user, Food[] purchases) {
		this.user = user;
		this.purchases = purchases;
		this.totalCost = 0;

		for (int i = 0; i < this.purchases.length; ++i) {
			this.totalCost += purchases[i].getPrice();
			this.calCost += purchases[i].getCalCount();
		}
	}

	public User getUser() {
		return user;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public int getCalCost() {
		return calCost;
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
