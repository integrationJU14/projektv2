package se.arole.datalayer.repository;

import org.springframework.data.repository.CrudRepository;

import se.arole.datalayer.entity.UserJPA;

public interface UserRepository extends CrudRepository <UserJPA, Long> {
	UserJPA findByUserId(Integer userId);
}
