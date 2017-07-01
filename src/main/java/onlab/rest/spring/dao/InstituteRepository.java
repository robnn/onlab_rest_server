package onlab.rest.spring.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import onlab.rest.spring.entities.Institute;

@Repository
public interface InstituteRepository extends CrudRepository<Institute, Long> {
	public Institute findByName(String name);
}
