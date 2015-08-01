package rs.fon.queue.blogic.impl;

import rs.fon.queue.blogic.exception.InsertionDisabledException;
import rs.fon.queue.blogic.exception.StudentAlreadyExistException;
import rs.fon.queue.blogic.interfaces.Queue;
import rs.fon.queue.blogic.util.Constants;

/**
 * Stand on the faculty. Each stand has two rows (queues): primary and secondary
 * (temporary) Students register themselves for primary row, and when faculty
 * removes them from that row they go to the temporary row.
 * 
 * @author Mihailo
 *
 */
public class FacultyStand {

	private Queue primaryQueue;
	private Queue tempQueue;
	private int ordNum;

	private String name;
	private boolean open;
	private boolean insertable;

	public FacultyStand(String name)  {
		primaryQueue = new PrimaryQueue();
		tempQueue = new TempQueue(Constants.TMP_QUEUE_CAPACITY);
		ordNum = 1;

		this.name = name;
		open = false;
		insertable = false;
		
		//test code
		try {
			primaryQueue.insert(new Student(1, "31"));
			primaryQueue.insert(new Student(2, "32"));
			primaryQueue.insert(new Student(3, "33"));
			primaryQueue.insert(new Student(4, "34"));
			primaryQueue.insert(new Student(5, "35"));
			primaryQueue.insert(new Student(6, "36"));
			primaryQueue.insert(new Student(7, "37"));
			primaryQueue.insert(new Student(8, "38"));
			ordNum=8;
		} catch (StudentAlreadyExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * When new student register himself to the stand it goes to the end of row.
	 * 
	 * @param studentIndex
	 * @throws StudentAlreadyExistException
	 *             if he is already in the row.<br/>
	 *             That means that he wants to register twice
	 * @return Student object with inserted student. This object has all
	 *         parameters filled
	 * @throws InsertionDisabledException 
	 */
	public Student insert(String studentIndex) throws StudentAlreadyExistException, InsertionDisabledException {
		if (!insertable)
			throw new InsertionDisabledException();
		Student student = new Student();
		student.setIndex(studentIndex);
		student.setOrdNum(ordNum);
		try {
			primaryQueue.insert(student);
			ordNum++;
		} catch (StudentAlreadyExistException e) {
			throw new StudentAlreadyExistException();
		}
		return student;
	}

	/**
	 * finds student in primary queue.
	 * 
	 * @param student
	 *            important parameters of the student are that they are in
	 *            equals method. Other will be filled (or changed) if student
	 *            exist.
	 * @return student or null if student does no exist
	 */
	public Student findStudentinPrimaryQueue(Student student) {
		return primaryQueue.findStudent(student);

	}

	/**
	 * When student finishes the job at the stand it goes out from the primary
	 * row.<br/>
	 * Also, if he is not present he loses his position.
	 *
	 */
	public void remove() {
		Student s = primaryQueue.remove();
		try {
			tempQueue.insert(s);
		} catch (StudentAlreadyExistException e) {
		}

	}

	public Queue getPrimaryQueue() {
		return primaryQueue;
	}

	public Queue getTempQueue() {
		return tempQueue;
	}

	/**
	 * Create student that is in the primary row.
	 * 
	 * @param studentIndex
	 * @return Student or null if student doesn't exist in primary queue
	 */
	public Student findStudent(String studentIndex) {
		Student student = new Student();
		student.setIndex(studentIndex);
		return primaryQueue.findStudent(student);
	}

	/**
	 * Open the stand. Now you can insert and remove students.
	 */
	public void open() {
		open = true;

	}

	/**
	 * Close the stand. Insertion and removal students are disabled.
	 */
	public void close() {
		open = false;

	}

	/**
	 * Check if the stand is open
	 * 
	 * @return true if it is, false if the stand is closed
	 */
	public boolean isOpen() {
		return open;
	}

	public String getName() {
		return name;
	}
	
	/**
	 * get the first next student in the row. This student is in primary queue
	 * @return Student or null, if the queue is empty
	 */
	public Student getNextStudent() {
		return primaryQueue.getNext();
	}
	
	/**
	 * 
	 * @return size (length) of primaryQueue
	 */
	public int getSize() {
		return primaryQueue.getSize();
	}
	
	public void enableInsertion() {
		insertable = true;
	}
	
	public void disableInsertion() {
		insertable = false;
	}
	
	public boolean isInsertable() {
		return insertable;
	}

	public void resetState() {
		primaryQueue = new PrimaryQueue();
		tempQueue = new TempQueue(Constants.TMP_QUEUE_CAPACITY);
		ordNum = 1;

		open = false;
		insertable = false;
	}
	
}
