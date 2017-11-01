package onlab.rest.spring.dal;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import onlab.rest.spring.model.Institute;

@Repository
public interface InstituteRepository extends CrudRepository<Institute, Long> {
	public Institute findByName(String name);
}
