package back_end;

import java.io.Serializable;

public class Meal extends Food implements Serializable {

	public Meal(String name, int CalCount, double price) {
		super(name, CalCount, price);
	}
}
