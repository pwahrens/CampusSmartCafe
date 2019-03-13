package front_end;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

	public static User currentUser;
	
	public static void main(String[] args) {
		currentUser = null;

		JFrame frame = new JFrame("CampusSmartCafe");
		frame.setLayout(new BorderLayout());
		JTabbedPane tabbedPane = new JTabbedPane();

		UserManager userData = new UserManager();
		userData.readFromFile();
		UserValidator userValid = new UserValidator(userData);

		LoginTotalView loginView = new LoginTotalView(userValid);
		CampusMapView mapView = new CampusMapView();

		ExpenseAccountView expenseAccountView = new ExpenseAccountView(new ExpenseAccount(0));
		DietaryAccountView dietaryAccountView = new DietaryAccountView(new DietaryAccount(2000));

		tabbedPane.addTab("Login", loginView.getLoginPanel());
		tabbedPane.addTab("Map", mapView);
		tabbedPane.addTab("Expenses", expenseAccountView);
		tabbedPane.addTab("Diet", dietaryAccountView);
		frame.add(tabbedPane, BorderLayout.CENTER);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				userData.writeToFile();
			}
		});

		frame.setSize(600, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
