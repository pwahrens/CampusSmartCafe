package back_end;

import java.io.Serializable;

public class Soda extends Food implements Serializable {

	public Soda(String name, int CalCount, double price) {
		super(name, CalCount, price);
	}
}
