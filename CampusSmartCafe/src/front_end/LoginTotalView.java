package front_end;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import back_end.UserValidator;

public class LoginTotalView extends LoginView implements ActionListener{

	private JPanel newUserPanel, usernamePanel,passwordPanel,repasswordPanel, loginViewTotal;
	private JTextField newUsername, newPassword, retypePassword;
	private JLabel userLabel, passLabel, repassLabel;
	private JButton newUserButton;
	public LoginTotalView(UserValidator userValidator)
	{
		super(userValidator);
		
		newUserPanel = new JPanel();
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
		newUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		newUserButton.addActionListener(this);
		
		
		usernamePanel.add(userLabel);
		usernamePanel.add(newUsername);
		
		passwordPanel.add(passLabel);
		passwordPanel.add(newPassword);
		
		repasswordPanel.add(repassLabel);
		repasswordPanel.add(retypePassword);
		
		newUserPanel.setLayout(new BoxLayout(newUserPanel, BoxLayout.PAGE_AXIS));
		//newUserPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginViewTotal.setLayout(new BorderLayout());
		
		newUserPanel.add(new JLabel("Or if you are a new user..."));
		newUserPanel.add(usernamePanel);
		newUserPanel.add(passwordPanel);
		newUserPanel.add(repasswordPanel);
		newUserPanel.add(newUserButton);
		newUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		loginViewTotal.add(super.getLoginPanel(), BorderLayout.NORTH);
		loginViewTotal.add(newUserPanel);
		loginViewTotal.add(Box.createRigidArea(new Dimension(10, 375)), BorderLayout.SOUTH);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==loginButton)
			super.actionPerformed(e);
		else
		{
			newUsername.setForeground(Color.BLACK);
			newPassword.setForeground(Color.BLACK);
			retypePassword.setForeground(Color.BLACK);
			
			if(!newPassword.getText().equals(retypePassword.getText()))
			{
				newPassword.setForeground(Color.RED);
				retypePassword.setForeground(Color.RED);
			}
			else
			{
				if(!userValidator.getUserManager().addUser(newUsername.getText(), newPassword.getText()))
					newUsername.setForeground(Color.RED);
				else
				{
					currentUser=userValidator.login(newUsername.getText(), newPassword.getText());
					newUsername.setForeground(Color.GREEN);
					newPassword.setForeground(Color.GREEN);
					retypePassword.setForeground(Color.GREEN);
				}
				
			}
		}
	}
	
	public JPanel getLoginPanel()
	{
		return loginViewTotal;
	}
}
