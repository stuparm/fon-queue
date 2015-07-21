package rs.fon.queue.repository;



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
	 * Insert the user into database
	 * @param user
	 */
	public void insertUser(User user);
	
	
}
