package se.arole.datalayer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.arole.datalayer.entity.UserJPA;

public interface UserRepository extends CrudRepository<UserJPA, Long> {
	
	UserJPA findByUserId(Integer userId);

	UserJPA findByUserName(String userName);

	List<UserJPA> findAllByFirstName(String firstName);

	List<UserJPA> findAllByLastName(String lastName);
}
