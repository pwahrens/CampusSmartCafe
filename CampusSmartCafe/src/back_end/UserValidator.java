package back_end;

public class UserValidator {

	private UserManager users;
	
	public UserValidator(UserManager users)
	{
		this.users=users;
	}
	
	public User login(String userName, String password)
	{
		User temp = null;
		
		if(checkPassword(userName, password))
			temp=users.getUser(userName);
		return temp;
	}
	public UserManager getUserManager()
	{
		return users;
	}
	private boolean checkPassword(String userName, String password)
	{
		User subject;
		
		try
		{
			subject= users.getUser(userName);
		}catch(NullPointerException e) {
			return false;
		}
		

		if(subject==null)
			return false;
		
		if(subject.getPassword().equals(password))
			return true;
		return false;	
	}
}
