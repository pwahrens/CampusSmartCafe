package front_end;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
	private JLabel calBalanceLabel;
	private JPanel transactionPanel;

	public DietaryAccountView(DietaryAccount dietaryAccount) {
		this.setLayout(new BorderLayout());
		
		this.dietaryAccount = dietaryAccount;

		
		this.calBalanceTextField = new JTextField();
		this.calBalanceTextField.addActionListener(this);
		this.add(this.calBalanceTextField, BorderLayout.SOUTH);
		
		this.calBalanceLabel = new JLabel("Balance: $" + this.dietaryAccount.getCalBalance(), JLabel.CENTER);
		this.add(this.calBalanceLabel, BorderLayout.NORTH);

		this.transactionPanel = new JPanel();
		ArrayList<Transaction> transactions = this.dietaryAccount.getTransactions();

		for (int i = 0; i < transactions.size(); ++i) {
			this.transactionPanel.add(new JLabel(transactions.get(i).toString()));
		}
		
		this.add(transactionPanel, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		int newCalBalance = this.dietaryAccount.getMaxCalBalance();
		
		try {
			newCalBalance = Integer.getInteger(calBalanceTextField.getText());
		} catch (NumberFormatException ex) {
			return;
		}
		
		this.dietaryAccount.setMaxCalBalance(newCalBalance);

		this.calBalanceLabel.setText("Balance: $" + this.dietaryAccount.getCalBalance());
	}

}
