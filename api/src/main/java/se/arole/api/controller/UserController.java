package se.arole.api.controller;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import se.arole.api.adapter.UserAdapter;
import se.arole.api.resource.UserVO;
import se.arole.datalayer.entity.User;
import se.arole.datalayer.service.UserService;

@Controller
public final class UserController {

	@Autowired
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	public UserController() {
	}

	public Collection<UserVO> getAll() {
		return Collections.emptyList();
	}

	public UserVO create(UserVO user) {
		User userDb = UserAdapter.toUserDb(user);
		User createdUser = userService.createUser(userDb);

		return UserAdapter.fromUserDb(createdUser);
	}

	public UserVO update(Integer id, UserVO user) {
		User userDb = UserAdapter.toUserDb(user);
		User updatedUser = userService.updateUser(userDb, id);

		return UserAdapter.fromUserDb(updatedUser);
	}

	public UserVO getUser(Integer id) {
		User user = userService.getUser(id);

		return UserAdapter.fromUserDb(user);
	}

	public UserVO getUser(String userName) {
		User userByUsername = userService.getUserByUsername(userName);

		return UserAdapter.fromUserDb(userByUsername);
	}
}
