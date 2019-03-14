package back_end;

import java.io.Serializable;

public class Snack extends Food implements Serializable {

	public Snack(String name, int CalCount, double price) {
		super(name, CalCount, price);
	}
}
