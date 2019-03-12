package front_end;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import back_end.User;
import back_end.UserValidator;

public class LoginView extends Observable implements ActionListener{

	private JLabel uLabel, pLabel;
	private JTextField uField, pField;
	private JPanel uPanel, pPanel, returnerPanel;
	protected JButton loginButton;
	private User currentUser;
	UserValidator userValidator;
	
	
	public LoginView(UserValidator userValidator)
	{
		currentUser=null;
		this.userValidator = userValidator;
		
		
		
		uPanel = new JPanel();
		pPanel = new JPanel();
		returnerPanel = new JPanel();
		
		uPanel.setLayout(new FlowLayout());
		pPanel.setLayout(new FlowLayout());
		returnerPanel.setLayout(new BorderLayout());
		
		uField = new JTextField(20);
		pField = new JTextField(20);
		
		uLabel = new JLabel("Username: ");
		pLabel = new JLabel("Password: ");
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		
		uPanel.add(uLabel);
		uPanel.add(uField);
		
		pPanel.add(pLabel);
		pPanel.add(pField);
		
		returnerPanel.add(uPanel, BorderLayout.NORTH);
		returnerPanel.add(pPanel, BorderLayout.CENTER);
		returnerPanel.add(loginButton, BorderLayout.SOUTH);		
	}
	
	public JPanel getLoginPanel()
	{
		return returnerPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		currentUser = userValidator.login(uField.getText(),pField.getText());
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
	
	public User getCurrentUser()
	{
		return currentUser;
	}

}
