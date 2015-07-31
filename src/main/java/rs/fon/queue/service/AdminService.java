package rs.fon.queue.service;

import java.util.List;

import rs.fon.queue.blogic.exception.AdminAlreadyExistException;
import rs.fon.queue.blogic.exception.EntityDoesNotExistException;
import rs.fon.queue.blogic.exception.UserAlreadyExistException;
import rs.fon.queue.domain.User;
import rs.fon.queue.model.AdminModel;
import rs.fon.queue.model.CRUDModel;
import rs.fon.queue.model.UserModel;

public interface AdminService {

	public List<UserModel> getUsers();

	public List<AdminModel> getAdmins();

	/**
	 * 
	 * @param username
	 * @return Concrete admin model or null if there is no such admin
	 */
	public AdminModel findByUsername(String username);

	/**
	 * delete concrete user from database
	 * @param user
	 * @return true if deletion is successful, false otherwise
	 */
	public boolean deleteUser(User user);

	/**
	 * Insert new user to the database 
	 * @param crudUser there are a lot of fields, but important fields for this operation are:<br/>
	 * -username,<br/>-password,<br/>-stand<br/>
	 * @throws UserAlreadyExistException
	 */
	public void insertUser(CRUDModel crudUser);
	
	/**
	 * Insert new admin to the database 
	 * @param crudAdmin there are a lot of fields, but important fields for this operation are:<br/>
	 * -username,<br/>-password,<br/>-firstName,<br/>-lastName,<br/>-telephone,<br/>-email
	 * @throws AdminAlreadyExistException
	 */
	public void insertAdmin(CRUDModel crudAdmin);
	
	
	/**
	 * 
	 * @param crudAdmin
	 * @throws EntityDoesNotExistException 
	 */
	public boolean updateAdmin(CRUDModel crudAdmin);
	
	/**
	 * 
	 * @param crudAdmin
	 * @throws EntityDoesNotExistException 
	 */
	public boolean deleteAdmin(CRUDModel crudAdmin);
	
	/**
	 * 
	 * @param crudUser
	 * @throws EntityDoesNotExistException 
	 */
	public boolean updateUser(CRUDModel crudUser);
	
	/**
	 * 
	 * @param crudUser
	 * @throws EntityDoesNotExistException 
	 */
	public boolean deleteUser(CRUDModel crudUser);
	
	/**
	 * Updates user or admin
	 * @param crudModel
	 * @throws IllegalArgumentException, {@link EntityDoesNotExistException}
	 */
	public void update(CRUDModel crudModel);
	
	/**
	 * Delete user or admin
	 * @param crudModel
	 * @throws EntityDoesNotExistException, {@link IllegalArgumentException}
	 */
	public void delete(CRUDModel crudModel);
	
}
