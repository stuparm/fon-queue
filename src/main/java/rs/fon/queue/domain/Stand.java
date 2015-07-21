package rs.fon.queue.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stand")
public class Stand {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="stand_id")
	private Long stand_id;
	
	@Column(name="stand_number")
	private Integer stand_number;

	
	
	
	public Long getStand_id() {
		return stand_id;
	}

	public void setStand_id(Long stand_id) {
		this.stand_id = stand_id;
	}

	public Integer getStand_number() {
		return stand_number;
	}

	public void setStand_number(Integer stand_number) {
		this.stand_number = stand_number;
	}
	
	
}
