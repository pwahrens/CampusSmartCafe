package front_end;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
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

public class MainView implements Observer {

	public static User currentUser;
	public static ExpenseAccountView expenseAccountView;
	public static DietaryAccountView dietaryAccountView;

	private JFrame frame;
	private JTabbedPane tabbedPane;
	private UserManager userData;
	private CampusMapView mapView;
	
	public MainView() {
		currentUser = null;

		this.frame = new JFrame("CampusSmartCafe");
		this.frame.setLayout(new BorderLayout());

		this.tabbedPane = new JTabbedPane();
		this.userData = new UserManager();
		this.userData.readFromFile();

		UserValidator userValid = new UserValidator(this.userData);
		LoginTotalView loginView = new LoginTotalView(userValid, this);

		this.tabbedPane.addTab("Login", loginView.getLoginPanel());

		this.frame.add(this.tabbedPane, BorderLayout.CENTER);

		this.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				userData.writeToFile();
			}
		});

		this.frame.setSize(600, 800);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		User user = (User) arg;
		MainView.currentUser = user;

		this.tabbedPane.remove(this.mapView);
		this.tabbedPane.remove(expenseAccountView);
		this.tabbedPane.remove(dietaryAccountView);

		expenseAccountView = new ExpenseAccountView(user.getExpenseAccount());
		dietaryAccountView = new DietaryAccountView(user.getDietaryAccount());
		this.mapView = new CampusMapView();

		this.tabbedPane.addTab("Map", mapView);
		this.tabbedPane.addTab("Expenses", expenseAccountView);
		this.tabbedPane.addTab("Diet", dietaryAccountView);

		this.tabbedPane.setSelectedIndex(1);
		
		this.frame.validate();
		this.frame.repaint();
	}

	public static void main(String[] args) {
		new MainView();
	}
}
