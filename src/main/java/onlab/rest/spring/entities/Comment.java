package onlab.rest.spring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SUBJECT_COMMENT")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SUBJECT_COMMENT_SEQ")
	@SequenceGenerator(name="SUBJECT_COMMENT_SEQ", sequenceName="SUBJECT_COMMENT_SEQ", allocationSize=1)
	@Column(name = "COMMENT_ID")
	private Long Id;
	
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name = "SUBJECT_ID")
	private Long subjectId;
	
	@Column(name = "COMMENT_TEXT")
    private String commentText;

	public Comment() {
		super();
	}

	public Comment(Long userId, Long subjectId, String commentText) {
		super();
		this.userId = userId;
		this.subjectId = subjectId;
		this.commentText = commentText;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	
}
