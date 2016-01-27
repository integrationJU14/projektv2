package se.arole.datalayer.serviceImp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arole.datalayer.entity.UserJPA;
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
	public UserJPA createUser(UserJPA user) {
		return userRepository.save(user);
	}

	@Override
	public UserJPA updateUser(UserJPA user, Integer userId) {
		UserJPA tempUser = userRepository.findByUserId(userId);
		userRepository.delete(tempUser.getId());
		tempUser = user;
		return userRepository.save(tempUser);
	}

	@Override
	public void changeStatusUser(boolean isActive, Integer userId) {
		UserJPA tempUser = userRepository.findByUserId(userId);
		userRepository.delete(tempUser.getId());
		userRepository.save(new UserJPA(tempUser.getUserName(),tempUser.getFirstName(),tempUser.getLastName(), tempUser.getUserId(), isActive));


	}

	@Override
	public UserJPA getUser(Integer userId) {
		return userRepository.findByUserId(userId);
	}

	@Override
	public UserJPA getUserByUsername(String username) {
		List<UserJPA> users = (List<UserJPA>) userRepository.findAll();
		return users.stream().filter(user -> user.getUserName().equals(username)).findFirst().orElse(null);
	}
	

	@Override
	public UserJPA getUserByFirstname(String firstname) {
		List<UserJPA> users = (List<UserJPA>) userRepository.findAll();
		return users.stream().filter(user -> user.getFirstName().equals(firstname)).findFirst().orElse(null);
	}

	@Override
	public UserJPA getUserByLastname(String lastname) {
		List<UserJPA> users = (List<UserJPA>) userRepository.findAll();
		return users.stream().filter(user -> user.getLastName().equals(lastname)).findFirst().orElse(null);
	}


	@Override
	public Collection<UserJPA> getAll() {
		Iterable<UserJPA> findAll = userRepository.findAll();
		Collection<UserJPA> users = new ArrayList<>();
		findAll.forEach(u -> users.add(u));
		return users;
	}
	
	@Override
	public Collection<UserJPA> getAllByName(UserJPA user) {
		List<UserJPA> users = (List<UserJPA>) userRepository.findAll();
		List<UserJPA> match = new ArrayList<UserJPA>();
		
		return null;
	}

}
