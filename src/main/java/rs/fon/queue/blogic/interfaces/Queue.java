package rs.fon.queue.blogic.interfaces;



import java.util.List;

import rs.fon.queue.blogic.exception.StudentAlreadyExistException;
import rs.fon.queue.blogic.impl.Student;



/**
 * FIFO queue
 * 
 * @author Mihailo
 *
 */
public interface Queue {

	/**
	 * Insert student to the end of the queue
	 * 
	 * @param student
	 * @throws StudentAlreadyExistException
	 *             if student already exist in queue <br/>
	 *             Two students are equal if their equals method is true
	 */
	public void insert(Student student) throws StudentAlreadyExistException;

	/**
	 * Remove student from the top of the queue.
	 * 
	 * @return Student or null if the queue is empty
	 */
	public Student remove();

	/**
	 * Finds student in the queue. This would not be same student as input
	 * parameter. Expected input Student should have only index value filled (or
	 * some other values that are in equals method). <br/>
	 * Two students are same if their equals method returns true.
	 * 
	 * @param student
	 *            (with same attributes as before but now there is and position
	 *            in the row) or null if there is no such student
	 * @return concrete student or null if student does not exist
	 */
	public Student findStudent(Student student);

	/**
	 * Creates JSON array which is fixed size to N. If the queue is empty or
	 * there is not enough students, empty positions in JSONarray will be filled
	 * with empty JSON objects.
	 * 
	 * @param N
	 * @return
	 */

	public Student[] firstStudents(int N);

	/**
	 * size of the queue
	 * 
	 * @return
	 */
	public int getSize();

	/**
	 * Reset whole queue. Queue becomes empty. <be/> It should be done at some
	 * specific time every day (midnight)
	 */
	public void reset();

	
	public boolean isEmpty();
	
	/**
	 * 
	 * @return next (first) student in the queue or null if queue is empty
	 */
	public Student getNext();
	
	
	/**
	 * Converts to list
	 * @return
	 */
	public List<Student> toList();
}
