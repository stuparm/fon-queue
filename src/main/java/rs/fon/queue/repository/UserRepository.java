package rs.fon.queue.repository;

import rs.fon.queue.domain.User;

public interface UserRepository {

	/**
	 * finds User in database on specific user name
	 * @param username
	 * @return concrete User or null if this user does not exist in database
	 */
	public User findByUsername(String username);
	
	
	
	
	/**
	 * finds user in database using username and password
	 * @param username
	 * @param password
	 * @return concrete user or null if there is no such user in database
	 */
	public User findByUsernameandPassword(String username, String password);
	
}
