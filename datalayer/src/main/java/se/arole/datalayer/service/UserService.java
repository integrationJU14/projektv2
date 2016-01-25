package se.arole.datalayer.service;

import se.arole.datalayer.entity.User;

public interface UserService {
	
	User createUser (User user);
	User updateUser (User user, Integer userId);
	void changeStatusUser (boolean isActive, Integer userId);
	User getUser (Integer userId);
	User getUserByUsername (String userName);
	

}
