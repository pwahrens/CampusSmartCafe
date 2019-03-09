package back_end;

public class UserValidator {

	private UserManager users;
	
	public UserValidator(UserManager users)
	{
		this.users=users;
	}
	
	public User login(String userName, String password)
	{
		User subject;
		
		subject= users.getUser(userName);
		
		if(subject==null)
			return subject;
		
		if(subject.getPassword().equals(password))
			return subject;
		return null;
	}
}
