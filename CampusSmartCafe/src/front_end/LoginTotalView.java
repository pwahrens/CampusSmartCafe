package front_end;

import java.awt.BorderLayout;
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
		usernamePanel.add(Box.createVerticalGlue());
		usernamePanel.add(newUsername);
		
		passwordPanel.add(passLabel);
		passwordPanel.add(Box.createVerticalGlue());
		passwordPanel.add(newPassword);
		
		repasswordPanel.add(repassLabel);
		repasswordPanel.add(Box.createVerticalGlue());
		repasswordPanel.add(retypePassword);
		
		newUserPanel.setLayout(new BoxLayout(newUserPanel, 1));
		//newUserPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginViewTotal.setLayout(new BorderLayout());
		
		newUserPanel.add(new JLabel("Or if you are a new user..."));
		newUserPanel.add(usernamePanel);
		newUserPanel.add(Box.createVerticalGlue());
		newUserPanel.add(passwordPanel);
		newUserPanel.add(Box.createVerticalGlue());
		newUserPanel.add(repasswordPanel);
		newUserPanel.add(Box.createVerticalGlue());
		newUserPanel.add(newUserButton);
		
		
		loginViewTotal.add(super.getLoginPanel(), BorderLayout.NORTH);
		loginViewTotal.add(Box.createVerticalGlue());
		loginViewTotal.add(newUserPanel, BorderLayout.CENTER);
		loginViewTotal.add(Box.createRigidArea(new Dimension(10, 400)), BorderLayout.SOUTH);
		
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
