package onlab.rest.spring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SUBJECT")
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SUBJECT_SEQ")
	@SequenceGenerator(name="SUBJECT_SEQ", sequenceName="SUBJECT_SEQ", allocationSize=1)
	@Column(name = "SUBJECT_ID")
	private Long Id;
	
	@Column(name = "NAME")
    private String name;
	
	@Column(name = "SEMESTER")
    private int semester;
	
	@Column(name = "INSTITUTE_ID")
	private Long instituteId;

	public Subject() {
		super();
	}

	public Subject(String name, int semester, Long instituteId) {
		super();
		this.name = name;
		this.semester = semester;
		this.instituteId = instituteId;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public Long getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(Long instituteId) {
		this.instituteId = instituteId;
	}
	
}
