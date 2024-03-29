package back_end;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Food implements Serializable {
	private String name;
	private int calCount;
	private double price;
	private ArrayList<String> attributes;

	public Food(String name, int CalCount, double price) {
		this.name = name;
		this.calCount = CalCount;
		this.price = price;
		attributes = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCalCount() {
		return calCount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<String> getAttributes() {
		return attributes;
	}

	@Override
	public String toString() {
		return name + ", Calories: " + calCount + ", Price: $" + price;
	}
}
