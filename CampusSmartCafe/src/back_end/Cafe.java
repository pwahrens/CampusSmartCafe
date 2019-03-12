package back_end;

import java.awt.Point;
import java.util.ArrayList;

public class Cafe extends FoodProvider{
	public Cafe(String name, ArrayList<Food> menu, Point location) {
		super(name, menu, location);
	}
	
	public String getPickupLocation()
	{
		return "Pickup your order at "+ this.getName();
	}
	
}
