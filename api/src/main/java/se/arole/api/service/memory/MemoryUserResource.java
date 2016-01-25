package se.arole.api.service.memory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import se.arole.api.resource.UserVO;

public class MemoryUserResource {
	
	private final Map<String, UserVO> users = new HashMap<>();
	
	public UserVO createUser(UserVO user) {
		long userId = Long.parseLong(UUID.randomUUID().toString());
		user = user.setUserId(userId);
		users.put(""+userId, user);
		
		return user;
	}
	
	public UserVO updateUser(UserVO tempUser, long userId) {
		
		UserVO user = users.get(userId);
		
		user.setUserName(tempUser.getUserName());
		user.setFirstName(tempUser.getFirstName());
		user.setLastName(tempUser.getLastName());
		
		users.put(""+userId, user);
		
		return user;
	}
	
	public UserVO get(long userId){
		return users.get(userId);
	}
	
//	public User getUserByUsername(){
//		
//	}

}
