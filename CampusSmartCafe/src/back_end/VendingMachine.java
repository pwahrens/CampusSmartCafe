package back_end;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observer;

public class VendingMachine extends FoodProvider {

	public VendingMachine(String name, ArrayList<Food> menu, Point location) {
		super(name, menu, location);
	}

}
