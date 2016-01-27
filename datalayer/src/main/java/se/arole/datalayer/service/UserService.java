package se.arole.datalayer.service;

import java.util.Collection;

import se.arole.datalayer.entity.UserJPA;

public interface UserService {
	
	UserJPA createUser (UserJPA user);
	UserJPA updateUser (UserJPA user, Integer userId);
	void changeStatusUser (boolean isActive, Integer userId);
	UserJPA getUser (Integer userId);
	UserJPA getUserByUserName (String userName);
	UserJPA getUserByFirstName (String firstName);
	UserJPA getUserByLastName (String lastName);

	Collection<UserJPA> getAll();
	Collection<UserJPA> getAllByName(UserJPA user);

}
