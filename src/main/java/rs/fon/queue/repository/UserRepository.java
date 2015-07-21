package rs.fon.queue.repository;

import rs.fon.queue.domain.User;

public interface UserRepository {

	/**
	 * finds User in database on specific user name
	 * @param username
	 * @return concrete User or null if this user does not exist in database
	 */
	public User findByUsername(String username);
	
	
}
