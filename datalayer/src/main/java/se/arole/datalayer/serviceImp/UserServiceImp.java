package se.arole.datalayer.serviceImp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

	public UserServiceImp() {
	}

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
		userRepository.save(new UserJPA(tempUser.getUserName(), tempUser.getFirstName(), tempUser.getLastName(),
				tempUser.getUserId(), isActive));

	}

	@Override
	public UserJPA getUser(Integer userId) {
		return userRepository.findByUserId(userId);
	}

	@Override
	public UserJPA getUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public List<UserJPA> getUserByFirstName(String firstName) {
		return userRepository.findAllByFirstName(firstName);
	}

	@Override
	public List<UserJPA> getUserByLastName(String lastName) {
		return userRepository.findAllByLastName(lastName);
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

		return Collections.emptyList();
	}

}
