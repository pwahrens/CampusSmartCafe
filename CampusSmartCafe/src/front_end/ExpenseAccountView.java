package front_end;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
	private JLabel newBalanceLabel;
	private JPanel balancePanel;
	private JPanel transactionPanel;

	public ExpenseAccountView(ExpenseAccount expenseAccount) {
		this.setLayout(new BorderLayout());

		this.expenseAccount = expenseAccount;

		this.balancePanel = new JPanel();
		balancePanel.setLayout(new FlowLayout());

		this.balanceLabel = new JLabel("Balance: $" + this.expenseAccount.getBalance(), JLabel.CENTER);
		this.balancePanel.add(this.balanceLabel);
		this.newBalanceLabel = new JLabel("        Set a New Balance: ");
		this.balancePanel.add(this.newBalanceLabel);

		this.balanceTextField = new JTextField(6);
		this.balanceTextField.addActionListener(this);
		this.balancePanel.add(this.balanceTextField);

		this.add(balancePanel, BorderLayout.NORTH);

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
