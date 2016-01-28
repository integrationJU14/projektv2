package se.arole.datalayer.service;

import java.util.Collection;
import java.util.List;

import se.arole.datalayer.entity.UserJPA;

public interface UserService {
	
	UserJPA createUser (UserJPA user);
	UserJPA updateUser (UserJPA user, Integer userId);
	void changeStatusUser (boolean isActive, Integer userId);
	UserJPA getUser (Integer userId);
	UserJPA getUserByUserName (String userName);
	List<UserJPA> getUserByFirstName (String firstName);
	List<UserJPA> getUserByLastName (String lastName);

	Collection<UserJPA> getAll();
	Collection<UserJPA> getAllByName(UserJPA user);

}
