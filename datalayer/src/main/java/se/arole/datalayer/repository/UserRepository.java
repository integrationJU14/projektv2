package se.arole.datalayer.repository;

import org.springframework.data.repository.CrudRepository;

import se.arole.datalayer.entity.User;

public interface UserRepository extends CrudRepository <User, Long> {
	User findByUserId(Integer userId);
}
