package front_end;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import back_end.User;
import back_end.UserValidator;

public class LoginTotalView extends Observable implements ActionListener{

	private JLabel uLabel, pLabel;
	private JTextField uField, newUsername;
	private JPanel uPanel, pPanel, returnerPanel;
	protected JButton loginButton;
	protected static User currentUser;
	UserValidator userValidator;
	private JPanel newUserPanel, usernamePanel,passwordPanel,repasswordPanel, loginViewTotal;
	private JPasswordField newPassword, retypePassword, pField;
	private JLabel userLabel, passLabel, repassLabel;
	private JButton newUserButton;
	
	public LoginTotalView(UserValidator userValidator, Observer o)
	{
		currentUser=null;
		this.userValidator = userValidator;
		
		this.addObserver(o);
		
		uPanel = new JPanel();
		pPanel = new JPanel();
		returnerPanel = new JPanel();
		
		uPanel.setLayout(new FlowLayout());
		pPanel.setLayout(new FlowLayout());
		returnerPanel.setLayout(new BoxLayout(returnerPanel,BoxLayout.PAGE_AXIS));
		
		uField = new JTextField(20);
		pField = new JPasswordField(20);
		
		uLabel = new JLabel("Username: ");
		pLabel = new JLabel("Password: ");
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		uPanel.add(uLabel);
		uPanel.add(uField);
		
		pPanel.add(pLabel);
		pPanel.add(pField);
		
		returnerPanel.add(uPanel);
		returnerPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		returnerPanel.add(pPanel);
		returnerPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		returnerPanel.add(loginButton);
		
		
		
		newUserPanel = new JPanel();
		usernamePanel = new JPanel();
		passwordPanel = new JPanel();
		repasswordPanel = new JPanel();
		loginViewTotal = new JPanel();
		
		newUsername = new JTextField(20);
		newPassword = new JPasswordField(20);
		retypePassword = new JPasswordField(20);
		
		userLabel = new JLabel("New Username: ");
		passLabel = new JLabel("New Password: ");
		repassLabel = new JLabel("Retype Password: ");
		
		newUserButton = new JButton("Create User");
		newUserButton.setHorizontalAlignment(SwingConstants.CENTER);
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
		
		
		loginViewTotal.add(returnerPanel, BorderLayout.NORTH);
		loginViewTotal.add(newUserPanel);
		loginViewTotal.add(Box.createRigidArea(new Dimension(10, 375)), BorderLayout.SOUTH);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==loginButton)
		{
			uField.setForeground(Color.BLACK);
			pField.setForeground(Color.BLACK);
		
			currentUser = userValidator.login(uField.getText(),String.valueOf(pField.getPassword()));
			if(currentUser==null)
			{
				uField.setForeground(Color.RED);
				pField.setForeground(Color.RED);
			}
			else
			{
				uField.setForeground(Color.GREEN);
				pField.setForeground(Color.GREEN);
			}
		}
		
		else
		{
			newUsername.setForeground(Color.BLACK);
			newPassword.setForeground(Color.BLACK);
			retypePassword.setForeground(Color.BLACK);
			
			if(!newPassword.getPassword().equals(retypePassword.getPassword()))
			{
				newPassword.setForeground(Color.RED);
				retypePassword.setForeground(Color.RED);
			}
			else
			{
				if(!userValidator.getUserManager().addUser(newUsername.getText(), String.valueOf(newPassword.getPassword())))
					newUsername.setForeground(Color.RED);
				else
				{
					currentUser=userValidator.login(newUsername.getText(), String.valueOf(newPassword.getPassword()));
					newUsername.setForeground(Color.GREEN);
					newPassword.setForeground(Color.GREEN);
					retypePassword.setForeground(Color.GREEN);
				}
				
			}
		}
		this.notifyObservers(currentUser);
	}
	
	public JPanel getLoginPanel()
	{
		return loginViewTotal;
	}
}
