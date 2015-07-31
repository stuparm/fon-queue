package rs.fon.queue.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries( {
	@NamedQuery(name=User.FIND_BY_NAME, query="select u from User u where u.username = :username"),
	@NamedQuery(name=User.FINF_BY_NAME_AND_PASSWORD, query="select u from User u where u.username = :username and u.password=:password")
})


@Entity
@Table(name="user")
public class User {

	public static final String FIND_BY_NAME = "User.findByName";
	public static final String FINF_BY_NAME_AND_PASSWORD = "User,findByNAmeAndPassword";
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;

	@ManyToOne
	@JoinColumn(name="stand_id")
	private Stand stand;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Stand getStand() {
		return stand;
	}
	
	public void setStand(Stand stand) {
		this.stand = stand;
	}
	
}
