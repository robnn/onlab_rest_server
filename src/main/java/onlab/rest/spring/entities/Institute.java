package onlab.rest.spring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="INSTITUTE")
public class Institute {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="INSTITUTE_SEQ")
	@SequenceGenerator(name="INSTITUTE_SEQ", sequenceName="INSTITUTE_SEQ", allocationSize=1)
	@Column(name = "INSTITUTE_ID")
	private Long Id;
	
	@Column(name = "NAME")
    private String name;

    public Institute() { }

    public Institute( String name) {
        this.name = name;
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
}
