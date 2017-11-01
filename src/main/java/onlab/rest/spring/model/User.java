package onlab.rest.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="USER_TABLE")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USER_TABLE_SEQ")
	@SequenceGenerator(name="USER_TABLE_SEQ", sequenceName="USER_TABLE_SEQ", allocationSize=1)
	@Column(name = "USER_ID")
	private Long Id;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "REAL_NAME")
	private String realName;
	
	@Column(name = "INSTITUTE_ID")
	private Long instituteId;
	
	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ROLE")
	private String role;
	
	public User() {
		
	}

	public User(String userName, String realName, Long instituteId, String password) {
		super();
		this.userName = userName;
		this.realName = realName;
		this.instituteId = instituteId;
		this.password = password;
	}

	public User(String userName, String realName, String password) {
		super();
		this.userName = userName;
		this.realName = realName;
		this.password = password;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Long getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(Long instituteId) {
		this.instituteId = instituteId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
