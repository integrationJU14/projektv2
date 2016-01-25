package se.arole.api.adapter;

import se.arole.api.resource.UserVO;
import se.arole.datalayer.entity.User;

public class UserAdapter {

	public static User toUserDb(UserVO user) {
		String userName = user.getUserName();
		boolean isActive = user.isActive();

		// TODO: Check Integer/Long as UserId
		Integer userId = (int) user.getUserId();

		return new User(userName, userId, isActive);
	}

	public static UserVO fromUserDb(User userDb) {
		Integer id = userDb.getUserId();
		String userName = userDb.getName();

		return new UserVO(id, true, userName, "", "");
	}
}
