package onlab.rest.spring.dal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import onlab.rest.spring.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
	public List<Comment> findBySubjectId(Long subjectId);
}
