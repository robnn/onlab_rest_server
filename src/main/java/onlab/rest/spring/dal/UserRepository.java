package onlab.rest.spring.dal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import onlab.rest.spring.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	public User findByUserName(String username);
}
