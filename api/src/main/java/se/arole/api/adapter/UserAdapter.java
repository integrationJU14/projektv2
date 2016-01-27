package se.arole.api.adapter;

import java.util.ArrayList;
import java.util.List;

import se.arole.api.resource.UserVO;
import se.arole.datalayer.entity.User;

public class UserAdapter {

	public static User toUserDb(UserVO user) {
		String userName = user.getUserName();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		boolean isActive = user.isActive();

		// TODO: Check Integer/Long as UserId
		Integer userId = (int) user.getUserId();

		return new User(userName, firstName, lastName, userId, isActive);
	}

	public static UserVO fromUserDb(User userDb) {
		Integer id = userDb.getUserId();
		String userName = userDb.getUserName();
		String firstName = userDb.getFirstName();
		String lastName = userDb.getLastName();

		return new UserVO(id, true, userName, firstName, lastName);
	}
	
	public static List<User> toDbUserList(List<UserVO> userList){
		List<User> userListToDb = new ArrayList<User>();
		userList.forEach(u -> {
			userListToDb.add(toUserDb(u));
		});
		return userListToDb;
	}
	
	public static List<UserVO> fromDbUserList(List<User> userList){
		List<UserVO> userListFromDb = new ArrayList<UserVO>();
		userList.forEach(u -> {
			userListFromDb.add(fromUserDb(u));
		});
		return userListFromDb;
	}
}
