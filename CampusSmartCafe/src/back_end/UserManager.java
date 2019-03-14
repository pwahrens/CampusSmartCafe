package back_end;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class UserManager {

	private Map<String, User> users;

	public UserManager() {
		this.users = new LinkedHashMap<String, User>();
	}

	protected User getUser(String userName) throws NullPointerException{
		return users.get(userName);
	}

	public boolean removeUser(String userName) {
		User temp;
		temp = users.remove(userName);

		if (temp == null)
			return false;
		return true;
	}

	public boolean addUser(String userName, String password) {
		boolean successful;
		User temp = new User(password);
		if (successful = (!users.containsKey(userName)))
			users.put(userName, temp);
		return successful;
	}

	public void readFromFile() {
		FileInputStream fileStream = null;

		try {
			fileStream = new FileInputStream("userdata.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ObjectInputStream objectStream = null;

		try {
			objectStream = new ObjectInputStream(fileStream);
		} catch (EOFException e) {
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}

		Object usersObject = null;

		try {
			usersObject = objectStream.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.users = (Map<String, User>) usersObject;
	}

	public void writeToFile() {
		FileOutputStream fileStream = null;

		try {
			fileStream = new FileOutputStream("userdata.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ObjectOutputStream objectStream = null;

		try {
			objectStream = new ObjectOutputStream(fileStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			objectStream.writeObject(this.users);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
