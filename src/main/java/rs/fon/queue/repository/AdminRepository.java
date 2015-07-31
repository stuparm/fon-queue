package rs.fon.queue.repository;



import java.util.List;

import rs.fon.queue.domain.Admin;
import rs.fon.queue.domain.User;


public interface AdminRepository {

	
	/**
	 * finds admin in database on specific user name
	 * @param username
	 * @return concrete Admin or null if this admin does not exist in database
	 */
	public Admin findByUsername(String username);
	
	/**
	 * finds admin in database on specific user name and password
	 * @param username
	 * @param password
	 * @return concrete Admin or null if this admin does not exist in database
	 */
	public Admin findByUsernameAndPassword(String username, String password);
	
	
	/**
	 * Insert the user into database
	 * @param user
	 */
	public void insertUser(User user);
	
	public void insertAdmin(Admin admin);
	
	public List<Admin> getAdmins();
	
	public List<User> getUsers();
	
	public boolean delete(User user);
	
	public boolean delete(Admin admin);
	
	public boolean update(User user);
	
	public boolean update(Admin admin);
	
	
	
	
}
