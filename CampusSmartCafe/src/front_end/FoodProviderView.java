package front_end;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import back_end.Cafe;
import back_end.Food;
import back_end.FoodProvider;
import back_end.Transaction;
import back_end.User;

public class FoodProviderView extends JPanel implements ActionListener {
	private FoodProvider foodProvider;
	private JButton orderButton;
	private JLabel nameLabel;
	private JPanel menuPanel;
	private JCheckBox[] menuItems;

	public FoodProviderView(FoodProvider foodProvider) {
		this.setLayout(new BorderLayout());

		this.foodProvider = foodProvider;

		this.orderButton = new JButton("Order");
		this.orderButton.addActionListener(this);
		this.add(this.orderButton, BorderLayout.SOUTH);

		this.nameLabel = new JLabel(this.foodProvider.getName(), JLabel.CENTER);
		this.nameLabel.setFont(new Font("Serif", Font.BOLD, 16));
		this.add(this.nameLabel, BorderLayout.NORTH);

		this.menuPanel = new JPanel();
		this.menuPanel.setLayout(new BoxLayout(this.menuPanel, BoxLayout.Y_AXIS));
		ArrayList<Food> menuOptions = this.foodProvider.getMenu();
		this.menuItems = new JCheckBox[menuOptions.size()];

		for (int i = 0; i < menuOptions.size(); ++i) {
			JCheckBox menuItem = new JCheckBox(menuOptions.get(i).toString());
			this.menuItems[i] = menuItem;
			this.menuPanel.add(menuItem);
		}

		this.add(this.menuPanel, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (MainView.currentUser != null) {
			User user = MainView.currentUser;
			ArrayList<Food> selectedItems = new ArrayList<Food>();

			for (int i = 0; i < this.menuItems.length; i++) {
				if (this.menuItems[i].isSelected()) {
					selectedItems.add(this.foodProvider.getMenu().get(i));
				}
			}

			Food[] selectedItemsArray = new Food[selectedItems.size()];
			selectedItemsArray = selectedItems.toArray(selectedItemsArray);

			Transaction transaction = new Transaction(user, selectedItemsArray);

			Boolean insufficientBalance = user.getExpenseAccount().getBalance() < transaction.getTotalCost();
			Boolean insufficientCalBal = user.getDietaryAccount().getCalBalance() < transaction.getCal();

			if (insufficientBalance) {
				JOptionPane.showMessageDialog(null, "Not enough balance in expense account");
			} else if (insufficientCalBal) {
				JOptionPane.showMessageDialog(null, "Exceeding your calorie balance");
			} else {
				this.foodProvider.updateAccounts(transaction);
				
				if (this.foodProvider instanceof Cafe) {
					JOptionPane.showMessageDialog(null, ((Cafe) this.foodProvider).getPickupLocation());
				} else {
					JOptionPane.showMessageDialog(null, "Vending machine has dispensed your food");
				}
				
				this.setVisible(false);
			}
		}
	}

	public FoodProvider getFoodProvider() {
		return foodProvider;
	}
}
