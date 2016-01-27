package se.arole.datalayer.service;

import java.util.Collection;

import se.arole.datalayer.entity.User;

public interface UserService {
	
	User createUser (User user);
	User updateUser (User user, Integer userId);
	void changeStatusUser (boolean isActive, Integer userId);
	User getUser (Integer userId);
	User getUserByUsername (String username);
	User getUserByFirstname (String firstname);
	User getUserByLastname (String lastName);

	Collection<User> getAll();
	Collection<User> getAllByName(User user);

}
