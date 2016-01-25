package se.arole.datalayer.repository;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import se.arole.datalayer.entity.User;

public interface UserRepository extends CrudRepository <User, Serializable> {

}
