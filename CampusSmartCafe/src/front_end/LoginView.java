package front_end;

import java.awt.BorderLayout;
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

public class LoginView extends JFrame implements ActionListener{

	private JLabel uLabel, pLabel;
	private JTextField uField, pField;
	private JPanel uPanel, pPanel;
	private JButton loginButton;
	private User currentUser;
	UserValidator userValidator;
	
	
	public LoginView(UserValidator userValidator)
	{
		super("Login Window");
		
		currentUser=null;
		this.userValidator = userValidator;
		
		this.setLayout(new BorderLayout());
		
		uPanel = new JPanel();
		pPanel = new JPanel();
		
		uPanel.setLayout(new FlowLayout());
		pPanel.setLayout(new FlowLayout());
		
		uField = new JTextField(20);
		pField = new JTextField(20);
		
		uLabel = new JLabel("Username");
		pLabel = new JLabel("Password");
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		
		pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		currentUser = userValidator.login(uField.getText(),pField.getText());
	}

}
