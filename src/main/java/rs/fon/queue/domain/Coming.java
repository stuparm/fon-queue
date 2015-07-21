package rs.fon.queue.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="arrival")
public class Coming {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="coming_id")
	private Long coming_id;
	
	@Column(name="student_index")
	private String student_index;
	
	
	@Column(name="date_time")
	private Date date_time;

	
	@Column(name="stand_number")
	private Integer standNumber;

	

	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}

	

	public Long getComing_id() {
		return coming_id;
	}

	public void setComing_id(Long coming_id) {
		this.coming_id = coming_id;
	}

	public String getStudent_index() {
		return student_index;
	}

	public void setStudent_index(String student_index) {
		this.student_index = student_index;
	}

	public Integer getStandNumber() {
		return standNumber;
	}

	public void setStandNumber(Integer standNumber) {
		this.standNumber = standNumber;
	}
	
	
	
}
