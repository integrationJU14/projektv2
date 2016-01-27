package se.arole.datalayer.serviceImp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arole.datalayer.entity.User;
import se.arole.datalayer.repository.UserRepository;
import se.arole.datalayer.service.UserService;

/**
 * @author Holstad
 *
 */
@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	public UserServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserServiceImp() {}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user, Integer userId) {
		User tempUser = userRepository.findByUserId(userId);
		userRepository.delete(tempUser.getId());
		tempUser = user;
		return userRepository.save(tempUser);
	}

	@Override
	public void changeStatusUser(boolean isActive, Integer userId) {
		User tempUser = userRepository.findByUserId(userId);
		userRepository.delete(tempUser.getId());
		userRepository.save(new User(tempUser.getUserName(),tempUser.getFirstName(),tempUser.getLastName(), tempUser.getUserId(), isActive));


	}

	@Override
	public User getUser(Integer userId) {
		return userRepository.findByUserId(userId);
	}

	@Override
	public User getUserByUsername(String username) {
		List<User> users = (List<User>) userRepository.findAll();
		return users.stream().filter(user -> user.getUserName().equals(username)).findFirst().orElse(null);
	}
	

	@Override
	public User getUserByFirstname(String firstname) {
		List<User> users = (List<User>) userRepository.findAll();
		return users.stream().filter(user -> user.getFirstName().equals(firstname)).findFirst().orElse(null);
	}

	@Override
	public User getUserByLastname(String lastname) {
		List<User> users = (List<User>) userRepository.findAll();
		return users.stream().filter(user -> user.getLastName().equals(lastname)).findFirst().orElse(null);
	}


	@Override
	public Collection<User> getAll() {
		Iterable<User> findAll = userRepository.findAll();
		Collection<User> users = new ArrayList<>();
		findAll.forEach(u -> users.add(u));
		return users;
	}
	
	@Override
	public Collection<User> getAllByName(User user) {
		List<User> users = (List<User>) userRepository.findAll();
		List<User> match = new ArrayList<User>();
		
		return null;
	}

}
