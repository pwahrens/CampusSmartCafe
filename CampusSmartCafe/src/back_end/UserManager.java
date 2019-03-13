package back_end;

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

	protected User getUser(String userName) {
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
		Scanner in = null;

		try {
			in = new Scanner(new File("usernames.txt"));
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

		while (in.hasNextLine()) {
			String username = in.nextLine();

			FileInputStream fileStream = null;

			try {
				fileStream = new FileInputStream("userdata.txt");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			ObjectInputStream objectStream = null;

			try {
				objectStream = new ObjectInputStream(fileStream);
			} catch (IOException e) {
				e.printStackTrace();
			}

			Object userObject = null;

			try {
				userObject = objectStream.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (userObject instanceof User) {
				User loadedUser = (User) userObject;

				this.addUser(username, loadedUser.getPassword());

				User user = this.getUser(username);
				user.setDietaryAccount(loadedUser.getDietaryAccount());
				user.setExpenseAccount(loadedUser.getExpenseAccount());
			}
		}
	}

	public void writeToFile() {
		FileWriter writer = null;

		try {
			writer = new FileWriter("usernames.txt", false);
			for (Map.Entry<String, User> userEntry : users.entrySet()) {
				writer.write(userEntry.getKey() + "\n");
			}

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

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

		for (Map.Entry<String, User> userEntry : users.entrySet()) {
			User value = userEntry.getValue();
			try {
				objectStream.writeObject(value);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
