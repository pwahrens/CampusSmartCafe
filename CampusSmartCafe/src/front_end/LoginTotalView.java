package front_end;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import back_end.UserValidator;

public class LoginTotalView extends LoginView implements ActionListener{

	private JPanel newUserPanel1, newUserPanel2, usernamePanel,passwordPanel,repasswordPanel, loginViewTotal;
	private JTextField newUsername, newPassword, retypePassword;
	private JLabel userLabel, passLabel, repassLabel;
	private JButton newUserButton;
	public LoginTotalView(UserValidator userValidator)
	{
		super(userValidator);
		
		newUserPanel1 = new JPanel();
		newUserPanel2 = new JPanel();
		usernamePanel = new JPanel();
		passwordPanel = new JPanel();
		repasswordPanel = new JPanel();
		loginViewTotal = new JPanel();
		
		newUsername = new JTextField(20);
		newPassword = new JTextField(20);
		retypePassword = new JTextField(20);
		
		userLabel = new JLabel("New Username: ");
		passLabel = new JLabel("New Password: ");
		repassLabel = new JLabel("Retype Password: ");
		
		newUserButton = new JButton("Create User");
		newUserButton.addActionListener(this);
		
		usernamePanel.add(userLabel);
		usernamePanel.add(newUsername);
		
		passwordPanel.add(passLabel);
		passwordPanel.add(newPassword);
		
		repasswordPanel.add(repassLabel);
		repasswordPanel.add(retypePassword);
		
		newUserPanel1.setLayout(new BorderLayout());
		newUserPanel2.setLayout(new BorderLayout());
		loginViewTotal.setLayout(new BorderLayout());
		
		newUserPanel1.add(usernamePanel, BorderLayout.NORTH);
		newUserPanel1.add(passwordPanel, BorderLayout.CENTER);
		newUserPanel1.add(retypePassword, BorderLayout.SOUTH);
		
		newUserPanel2.add(new JLabel("Or if you are a new user..."), BorderLayout.NORTH);
		newUserPanel2.add(newUserPanel1, BorderLayout.CENTER);
		newUserPanel2.add(newUserButton, BorderLayout.SOUTH);
		
		loginViewTotal.add(super.getLoginPanel(), BorderLayout.NORTH);
		loginViewTotal.add(newUserPanel2, BorderLayout.SOUTH);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==loginButton)
			super.actionPerformed(e);
		else
		{
			if(!newPassword.getText().equals(retypePassword.getText()))
				return;
			else
				userValidator.getUserManager().addUser(newUsername.getText(), newPassword.getText());
		}
	}
	
	public JPanel getLoginPanel()
	{
		return loginViewTotal;
	}
}
