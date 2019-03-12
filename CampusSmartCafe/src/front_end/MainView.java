package front_end;

import java.awt.BorderLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import back_end.Cafe;
import back_end.DietaryAccount;
import back_end.ExpenseAccount;
import back_end.Food;
import back_end.Meal;

public class MainView {
	public static void main(String[] args) {
		JFrame frame = new JFrame("CampusSmartCafe");
		frame.setLayout(new BorderLayout());
		JTabbedPane tabbedPane = new JTabbedPane();

		CampusMapView mapView = new CampusMapView();
		
		ExpenseAccountView expenseAccountView = new ExpenseAccountView(new ExpenseAccount(0));
		DietaryAccountView dietaryAccountView = new DietaryAccountView(new DietaryAccount(2000));
		
		// TODO test code probably best located somewhere else
		ArrayList<Food> menu = new ArrayList<Food>();
		menu.add(new Meal("Pizza", 200, 5));
		menu.add(new Meal("Burger", 400, 8));
		menu.add(new Meal("Salad", 175, 5));
		FoodProviderView foodProviderView = new FoodProviderView(new Cafe("Pete's", menu, new Point(100, 100)));

		tabbedPane.addTab("Map", mapView);
		tabbedPane.addTab("Pete's", foodProviderView);
		tabbedPane.addTab("Expenses", expenseAccountView);
		tabbedPane.addTab("Diet", dietaryAccountView);
		frame.add(tabbedPane, BorderLayout.CENTER);

		frame.setSize(600, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
