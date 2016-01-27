package se.arole.api.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import se.arole.api.adapter.UserAdapter;
import se.arole.api.resource.User;
import se.arole.datalayer.entity.UserJPA;
import se.arole.datalayer.service.UserService;

public final class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	public UserController() {
	}

	public Collection<User> getAll() {
		List<UserJPA> all = (List<UserJPA>) userService.getAll();
		List<User> fromDbUserList = UserAdapter.fromDbUserList(all);
		return fromDbUserList;
	}

	public User create(User user) {
		UserJPA userDb = UserAdapter.toUserDb(user);
		UserJPA createdUser = userService.createUser(userDb);

		return UserAdapter.fromUserDb(createdUser);
	}

	public User update(Integer id, User user) {
		UserJPA userDb = UserAdapter.toUserDb(user);
		UserJPA updatedUser = userService.updateUser(userDb, id);

		return UserAdapter.fromUserDb(updatedUser);
	}

	public User getUser(Integer id) {
		UserJPA user = userService.getUser(id);

		return UserAdapter.fromUserDb(user);
	}

	public User getUserByUsername(String userName) {
		UserJPA userByUsername = userService.getUserByUserName(userName);

		return UserAdapter.fromUserDb(userByUsername);
	}
	
	public User getUserByFirstname(String firstName) {
		UserJPA userByFirstname = userService.getUserByFirstName(firstName);

		return UserAdapter.fromUserDb(userByFirstname);
	}
	
	public User getUserByLastname(String lastName) {
		UserJPA userByLastname = userService.getUserByLastName(lastName);

		return UserAdapter.fromUserDb(userByLastname);
	}

}
