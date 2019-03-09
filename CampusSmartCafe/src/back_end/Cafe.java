package back_end;

import java.util.ArrayList;

public class Cafe extends FoodProvider{

	// TODO add a private name variable and a method to print it 
	public Cafe(String name, ArrayList<Food> menu, int [] location) {
		super(name, menu, location);
	}
	
	public String getPickupLocation()
	{
		return "Pickup your order at "+ this.getName();
	}
	
}
