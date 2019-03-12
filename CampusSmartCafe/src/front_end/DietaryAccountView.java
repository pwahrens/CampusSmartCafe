package front_end;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import back_end.DietaryAccount;
import back_end.Transaction;

public class DietaryAccountView extends JPanel implements ActionListener{
	private BarGraphView barGraph;
	private DietaryAccount dietaryAccount;
	private JTextField calBalanceTextField;
	private JLabel calBalanceLabel, newCalLabel, unusedCalLabel, mealCalLabel, snackCalLabel, sodaCalLabel;
	private JPanel maxCalPanel,graphLabelPanel;
	private int mCal, snCal, soCal, totalCal;
	public DietaryAccountView(DietaryAccount dietaryAccount) {
		this.setLayout(new BorderLayout());
		
		this.dietaryAccount = dietaryAccount;

		this.maxCalPanel = new JPanel();
		maxCalPanel.setLayout(new FlowLayout());
		
		this.calBalanceLabel = new JLabel("Daily Calories: " + this.dietaryAccount.getMaxCalBalance());
		this.maxCalPanel.add(this.calBalanceLabel);
		this.newCalLabel = new JLabel("        Set a New Daily Calorie Limit: ");
		this.maxCalPanel.add(this.newCalLabel);
		
		this.calBalanceTextField = new JTextField(6);
		this.maxCalPanel.add(this.calBalanceTextField);

		this.calBalanceTextField.addActionListener(this);
		
		this.add(maxCalPanel, BorderLayout.NORTH);
		
		//this.transactionPanel = new JPanel();
		ArrayList<Transaction> transactions = this.dietaryAccount.getTransactions();

		for (int i = 0; i < transactions.size(); ++i) {
			mCal = transactions.get(i).getMealCal();
			snCal = transactions.get(i).getSnackCal();
			soCal = transactions.get(i).getSodaCal();
		}
		int nums[] = {dietaryAccount.getCalBalance(),mCal,snCal,soCal};
		barGraph = new BarGraphView(nums);
		
		this.add(barGraph, BorderLayout.CENTER);
		
		unusedCalLabel = new JLabel("Unused: " + dietaryAccount.getCalBalance());
		mealCalLabel = new JLabel("Meal: " + mCal);
		snackCalLabel = new JLabel("Snack: " + snCal);
		sodaCalLabel = new JLabel("Soda: " + soCal);
		
		graphLabelPanel = new JPanel();
		
		graphLabelPanel.setLayout(new FlowLayout(1,75,0));
		
		graphLabelPanel.add(unusedCalLabel);
		graphLabelPanel.add(mealCalLabel);
		graphLabelPanel.add(snackCalLabel);
		graphLabelPanel.add(sodaCalLabel);
		
		this.add(graphLabelPanel, BorderLayout.SOUTH);
		
	}

	public void actionPerformed(ActionEvent e) {
		int newCalBalance = this.dietaryAccount.getMaxCalBalance();
		
		try {
			String temp = calBalanceTextField.getText();
			newCalBalance= Integer.parseInt(temp);
		} catch (Exception ex){
			ex.printStackTrace();
			return;
		}
		
		this.dietaryAccount.setMaxCalBalance(newCalBalance);

		this.calBalanceLabel.setText("Daily Calories: " + this.dietaryAccount.getMaxCalBalance());
	}

}
