package se.arole.api.adapter;

import java.util.ArrayList;
import java.util.List;

import se.arole.api.resource.User;
import se.arole.datalayer.entity.UserJPA;

public class UserAdapter {

	public static UserJPA toUserDb(User user) {
		Integer id = user.getUserId();
		String userName = user.getUserName();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		boolean isActive = user.isActive();

		return new UserJPA(userName, firstName, lastName, id, isActive);
	}

	public static User fromUserDb(UserJPA userDb) {
		Integer id = userDb.getUserId();
		String userName = userDb.getUserName();
		String firstName = userDb.getFirstName();
		String lastName = userDb.getLastName();
		boolean isActive = userDb.isActive();

		return new User(id, isActive, userName, firstName, lastName);
	}

	public static List<UserJPA> toDbUserList(List<User> userList) {
		List<UserJPA> userListToDb = new ArrayList<UserJPA>();
		userList.forEach(u -> {
			userListToDb.add(toUserDb(u));
		});
		return userListToDb;
	}

	public static List<User> fromDbUserList(List<UserJPA> userList) {
		List<User> userListFromDb = new ArrayList<User>();
		userList.forEach(u -> {
			userListFromDb.add(fromUserDb(u));
		});
		return userListFromDb;
	}
}
