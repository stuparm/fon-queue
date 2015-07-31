package rs.fon.queue.service;

import rs.fon.queue.blogic.exception.InsertionDisabledException;
import rs.fon.queue.blogic.exception.StudentAlreadyExistException;
import rs.fon.queue.blogic.impl.Student;
import rs.fon.queue.blogic.interfaces.Queue;
import rs.fon.queue.domain.User;

public interface FacultyService {

	/**
	 * 
	 * @param username
	 * @return concrete {@link User} or null if user with that user name doesn't
	 *         exist
	 */
	public User findByUsername(String username);

	/**
	 * Open the stand if the stand is closed
	 * 
	 * @param standNumber
	 *            number of stand that should be opened
	 * @return true if stand can be opened. Stand can be opened if it was
	 *         previously closed. Otherwise returns false;
	 */
	public boolean openStand(int standNumber);

	/**
	 * Close the stand if the stand was opened
	 * 
	 * @param standNumber
	 * @return true if stand can become closed. Stand can be closed if it was
	 *         opened previously. Otherwise returns false;
	 */
	public boolean closeStand(int standNumber);

	public boolean isStandOpen(int standNumber);

	/**
	 * 
	 * @return next student (student should be in primary queue) or null if
	 *         there is no next student.
	 */
	public Student getNextStudent(int standNumber);

	/**
	 * get the size of primary queue for specific stand
	 * 
	 * @param standNumber
	 * @return
	 */
	public int getQueueSize(int standNumber);

	/**
	 * Removes the first student from the row( primary queue)
	 * 
	 * @param standNumber
	 * @return first student or null if the row is empty
	 */
	public void removeFirstStudent(int standNumber);

	/**
	 * Insert the student with specified index into primary queue.
	 * 
	 * @param studentIndex
	 * @param standNumber
	 * @throws StudentAlreadyExistException
	 *             if student is already in row
	 */
	public void insertStudent(String studentIndex, int standNumber)
			throws StudentAlreadyExistException, InsertionDisabledException;

	/**
	 * Finds concrete student in row at the stand (with standNumber)
	 * 
	 * @param studentIndex
	 * @param standNumber
	 * @return concrete student or null if student does not exist
	 */
	public Student findInStand(String studentIndex, int standNumber);

	public Queue getTempQueue(int standNumber);

	/**
	 * 
	 * @param standNumber
	 * @return true if student can go to the stand, false otherwise. <br/>
	 *         Returns false when worker disable this ability (i.e. at 1 p.m.).
	 *         It should be automatically be enabled at some specific time (for
	 *         example at midnight or 8a.m.)
	 */
	public boolean isInsertable(int standNumber);

	
	/**
	 * Enables insertion of students to the primary queue at the stand.
	 * @param standNumber
	 */
	public void enableInsertion(int standNumber);
	
	
	/**
	 * Disables insertion of students to the primary queue at the stand.
	 * @param standNumber
	 */
	public void disableInsertion(int standNumber);
	
	
	/**
	 * Reset states of every stand.<br/>
	 * Priority and temp queues will be empty and stands will be closed. 
	 */
	public void resetState();
	
	
}
