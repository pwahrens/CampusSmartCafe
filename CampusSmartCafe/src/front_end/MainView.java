package front_end;

import java.awt.BorderLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import back_end.Cafe;
import back_end.DietaryAccount;
import back_end.ExpenseAccount;
import back_end.Food;
import back_end.Meal;
import back_end.User;
import back_end.UserManager;
import back_end.UserValidator;

public class MainView {

	public static void main(String[] args) {
		User currentUser=null;
		
		JFrame frame = new JFrame("CampusSmartCafe");
		frame.setLayout(new BorderLayout());
		JTabbedPane tabbedPane = new JTabbedPane();

		
		UserManager userData = new UserManager();
		userData.addUser("billyBob", "bob123");
		UserValidator userValid = new UserValidator(userData);
		
		LoginView loginView = new LoginTotalView(userValid);
		CampusMapView mapView = new CampusMapView();
		
		ExpenseAccountView expenseAccountView = new ExpenseAccountView(new ExpenseAccount(0));
		DietaryAccountView dietaryAccountView = new DietaryAccountView(new DietaryAccount(2000));
		
		// TODO test code probably best located somewhere else
		ArrayList<Food> menu = new ArrayList<Food>();
		menu.add(new Meal("Pizza", 200, 5));
		menu.add(new Meal("Burger", 400, 8));
		menu.add(new Meal("Salad", 175, 5));
		FoodProviderView foodProviderView = new FoodProviderView(new Cafe("Pete's", menu, new Point(100, 100)));

		tabbedPane.addTab("Login", loginView.getLoginPanel());
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
