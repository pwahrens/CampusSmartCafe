package front_end;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import back_end.DietaryAccount;
import back_end.Transaction;

public class DietaryAccountView extends JPanel implements ActionListener, Observer{
	private BarGraphView barGraph;
	private DietaryAccount dietaryAccount;
	private JTextField calBalanceTextField, prefField;
	private JLabel calBalanceLabel, newCalLabel, unusedCalLabel, mealCalLabel, snackCalLabel, sodaCalLabel, prefLabel, newPref;
	private JPanel maxCalPanel, prefPanel, graphLabelPanel, topPanel;
	private int mCal, snCal, soCal, totalCal;
	public DietaryAccountView(DietaryAccount dietaryAccount) {
		this.setLayout(new BorderLayout());
		
		this.dietaryAccount = dietaryAccount;

		this.maxCalPanel = new JPanel();
		this.prefPanel = new JPanel();
		this.topPanel = new JPanel();
		
		maxCalPanel.setLayout(new FlowLayout());
		prefPanel.setLayout(new FlowLayout());
		topPanel.setLayout(new BoxLayout(topPanel,1));
		
		this.calBalanceLabel = new JLabel("Daily Calories: " + this.dietaryAccount.getMaxCalBalance());
		this.maxCalPanel.add(this.calBalanceLabel);
		this.newCalLabel = new JLabel("        Set a New Daily Calorie Limit: ");
		this.maxCalPanel.add(this.newCalLabel);
		this.prefLabel = new JLabel("Preferences: " + this.dietaryAccount.getPreferences());
		this.prefPanel.add(prefLabel);
		this.newPref = new JLabel("   New Preference: ");
		this.prefPanel.add(newPref);
		
		this.calBalanceTextField = new JTextField(6);
		this.maxCalPanel.add(this.calBalanceTextField);
		this.prefField = new JTextField(12);
		this.prefPanel.add(this.prefField);	

		this.calBalanceTextField.addActionListener(this);
		this.prefField.addActionListener(this);
		
		topPanel.add(maxCalPanel);
		topPanel.add(prefPanel);
		
		this.add(topPanel, BorderLayout.NORTH);
		
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
		if(e.getSource()==calBalanceTextField)
		{
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
		
			int nums[] = {dietaryAccount.getCalBalance(),mCal,snCal,soCal};
			barGraph = new BarGraphView(nums);
		
			unusedCalLabel.setText("Unused: " + dietaryAccount.getCalBalance());
		}
		if(e.getSource()==prefField)
		{
			if(prefField.getText().equals("Delete"))
				this.dietaryAccount.removePreferences();
			
			else
			{
				this.dietaryAccount.addPreference(prefField.getText());
				this.prefLabel.setText("Preferences: " + this.dietaryAccount.getPreferences());
			}
			
		}
		this.revalidate();
		this.repaint();
	}
	
	public void update(Observable o, Object arg)
	{
		this.calBalanceLabel.setText("Daily Calories: " + this.dietaryAccount.getMaxCalBalance());
		
		ArrayList<Transaction> transactions = this.dietaryAccount.getTransactions();
		
		for (int i = 0; i < transactions.size(); ++i) {
			mCal = transactions.get(i).getMealCal();
			snCal = transactions.get(i).getSnackCal();
			soCal = transactions.get(i).getSodaCal();
		}
	
		int nums[] = {dietaryAccount.getCalBalance(),mCal,snCal,soCal};
		barGraph = new BarGraphView(nums);
	
		unusedCalLabel.setText("Unused: " + dietaryAccount.getCalBalance());
		
		this.revalidate();
		this.repaint();

	}
	

}
