package onlab.rest.spring.dal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import onlab.rest.spring.model.Subject;


@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
	public Subject findByName(String name);
	public List<Subject> findByInstituteId(Long instituteId);
}
