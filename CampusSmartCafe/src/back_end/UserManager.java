package back_end;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserManager {
	
	private Map<String, User> users;
	
	public UserManager()
	{
		this.users = new LinkedHashMap<String, User>();
	}
	
	protected User getUser(String userName)
	{
		return users.get(userName);
	}
	
	public boolean removeUser(String userName)
	{
		User temp;
		temp=users.remove(userName);
		
		if(temp==null)
			return false;
		return true;
	}
	
	public boolean addUser(String userName, String password)
	{
		boolean successful;
		User temp = new User(password);
		if(successful=(!users.containsKey(userName)))
			users.put(userName, temp);
		return successful;
	}
	
	
}
