package rs.fon.queue.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CRUDModel {

	@Size(min=3, max=20, message="3-20 karaktera")
	@Pattern(regexp="^[a-zA-Z0-9]+$", message="Isključivo slova i cifre")
	private String username;
	
	@Size(min=8, max=20, message="8-20 karaktera")
	private String password;
	
	private String standNumber;
	private String firstName;
	private String lastName;
	private String telephone;
	private String email;
	
	
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
	public String getStandNumber() {
		return standNumber;
	}
	public void setStandNumber(String standNumber) {
		this.standNumber = standNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
