package front_end;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import back_end.ExpenseAccount;
import back_end.Transaction;

public class ExpenseAccountView extends JPanel implements ActionListener, Observer {
	private ExpenseAccount expenseAccount;
	private JTextField balanceTextField;
	private JLabel balanceLabel;
	private JLabel newBalanceLabel;
	private JPanel balancePanel;
	private JPanel transactionPanel;
	private Border border;

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
		this.transactionPanel.setLayout(new BoxLayout(this.transactionPanel, BoxLayout.Y_AXIS));
		ArrayList<Transaction> transactions = this.expenseAccount.getTransactions();

		this.border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4);

		for (int i = 0; i < transactions.size(); ++i) {
			JLabel label = new JLabel("<html>Total Cost: $" + transactions.get(i).getTotalCost() + "<br/>"
					+ Arrays.toString(transactions.get(i).getPurchases()) + "</html>");
			label.setBorder(this.border);

			this.transactionPanel.add(label);
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

	@Override
	public void update(Observable o, Object arg) {
		Transaction transaction = (Transaction) arg;
		
		JLabel label = new JLabel("<html>Total Cost: $" + transaction.getTotalCost() + "<br/>"
				+ Arrays.toString(transaction.getPurchases()) + "</html>");
		label.setBorder(this.border);

		this.transactionPanel.add(label);
		
		this.balanceLabel.setText("Balance: $" + this.expenseAccount.getBalance());
		
		this.validate();
		this.repaint();
	}

}
