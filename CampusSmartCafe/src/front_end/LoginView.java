package front_end;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import back_end.User;
import back_end.UserValidator;

public class LoginView extends JPanel implements ActionListener{

	private JLabel uLabel, pLabel;
	private JTextField uField, pField;
	private JPanel uPanel, pPanel;
	private JButton loginButton;
	private User currentUser;
	UserValidator userValidator;
	
	
	public LoginView(UserValidator userValidator)
	{
		currentUser=null;
		this.userValidator = userValidator;
		
		this.setLayout(new BorderLayout());
		
		uPanel = new JPanel();
		pPanel = new JPanel();
		
		uPanel.setLayout(new FlowLayout());
		pPanel.setLayout(new FlowLayout());
		
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
		
		this.add(uPanel, BorderLayout.NORTH);
		this.add(pPanel, BorderLayout.CENTER);
		this.add(loginButton, BorderLayout.SOUTH);
		
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
