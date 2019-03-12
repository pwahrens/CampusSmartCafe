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

import back_end.ExpenseAccount;
import back_end.Transaction;

public class ExpenseAccountView extends JPanel implements ActionListener {
	private ExpenseAccount expenseAccount;
	private JTextField balanceTextField;
	private JLabel balanceLabel;
	private JPanel transactionPanel;

	public ExpenseAccountView(ExpenseAccount expenseAccount) {
		this.setLayout(new BorderLayout());
		
		this.expenseAccount = expenseAccount;

		this.balanceTextField = new JTextField();
		this.balanceTextField.addActionListener(this);
		this.add(this.balanceTextField, BorderLayout.SOUTH);
		
		this.balanceLabel = new JLabel("Balance: $" + this.expenseAccount.getBalance(), JLabel.CENTER);
		this.add(this.balanceLabel, BorderLayout.NORTH);

		this.transactionPanel = new JPanel();
		ArrayList<Transaction> transactions = this.expenseAccount.getTransactions();

		for (int i = 0; i < transactions.size(); ++i) {
			this.transactionPanel.add(new JLabel(transactions.get(i).toString()));
		}
		
		this.add(transactionPanel, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		double newBalance = this.expenseAccount.getBalance();
		
		try {
			newBalance = Double.parseDouble(balanceTextField.getText());
		} catch (NumberFormatException ex) {
			return;
		}
		
		DecimalFormat moneyFormat = new DecimalFormat("#.##");
		moneyFormat.setRoundingMode(RoundingMode.DOWN);
		
		newBalance = Double.parseDouble(moneyFormat.format(newBalance));
		this.expenseAccount.setBalance(newBalance);

		this.balanceLabel.setText("Balance: $" + this.expenseAccount.getBalance());
	}

}
