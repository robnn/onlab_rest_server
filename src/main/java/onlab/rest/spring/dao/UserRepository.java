package onlab.rest.spring.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import onlab.rest.spring.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	public User findByUserName(String username);
}
