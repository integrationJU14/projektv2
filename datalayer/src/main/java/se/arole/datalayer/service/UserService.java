package se.arole.datalayer.service;

import java.util.Collection;

import se.arole.datalayer.entity.UserJPA;

public interface UserService {
	
	UserJPA createUser (UserJPA user);
	UserJPA updateUser (UserJPA user, Integer userId);
	void changeStatusUser (boolean isActive, Integer userId);
	UserJPA getUser (Integer userId);
	UserJPA getUserByUsername (String username);
	UserJPA getUserByFirstname (String firstname);
	UserJPA getUserByLastname (String lastName);

	Collection<UserJPA> getAll();
	Collection<UserJPA> getAllByName(UserJPA user);

}
