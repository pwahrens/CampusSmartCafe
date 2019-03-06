package back_end;

public class Transaction {
	private User user;
	private Food [] purchases;
	
	public Transaction(User user, Food[] purchases) {
		this.user = user;
		this.purchases = purchases;
	}

	public User getUser() {
		return user;
	}

	public Food[] getPurchases() {
		return purchases;
	}
}
