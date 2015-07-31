package rs.fon.queue.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import rs.fon.queue.blogic.exception.InsertionDisabledException;
import rs.fon.queue.blogic.exception.StudentAlreadyExistException;
import rs.fon.queue.blogic.impl.FacultyStand;
import rs.fon.queue.blogic.impl.Student;
import rs.fon.queue.blogic.interfaces.Queue;
import rs.fon.queue.domain.Coming;
import rs.fon.queue.domain.User;
import rs.fon.queue.repository.ComingRepository;
import rs.fon.queue.repository.UserRepository;
import rs.fon.queue.service.FacultyService;

@Service
public class FacultyServiceImpl implements FacultyService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ComingRepository commingRepository;
	
	
	@Autowired
	private FacultyStand stand_1;
	@Autowired
	private FacultyStand stand_2;
	@Autowired
	private FacultyStand stand_3;
	@Autowired
	private FacultyStand stand_4;
	@Autowired
	private FacultyStand stand_5;
	

	@Override
	public boolean openStand(int standNumber) {
		FacultyStand stand = getStandByNumber(standNumber);
		assert stand!= null;
		if (stand.isOpen())
			return false;
		stand.open();
		return true;
	}
	

	@Override
	public User findByUsername(String username) {
		User user = getUserRepository().findByUsername(username);
		return user;
	}

	
	@Override
	public boolean closeStand(int standNumber) {
		FacultyStand stand = getStandByNumber(standNumber);
		assert stand!= null;
		if (!stand.isOpen())
			return false;
		stand.close();
		return true;
	}
	
	@Override
	public boolean isStandOpen(int standNumber) {
		return getStandByNumber(standNumber).isOpen();
	}
	
	@Override
	public Student getNextStudent(int standNumber) {
		return getStandByNumber(standNumber).getNextStudent();
	}
	
	@Override
	public int getQueueSize(int standNumber) {
		return getStandByNumber(standNumber).getSize();
	}
	
	@Override
	public void removeFirstStudent(int standNumber) {
		getStandByNumber(standNumber).remove();
	}
	
	@Override
	public void insertStudent(String studentIndex, int standNumber) throws StudentAlreadyExistException, InsertionDisabledException {
		getStandByNumber(standNumber).insert(studentIndex);
		// the exception will be throw above if insertion can't be executed 
		Coming coming = new Coming();
		coming.setDate_time(new Date());
		coming.setStandNumber(standNumber);
		coming.setStudent_index(studentIndex);
		getCommingRepository().insertComing(coming);
	}
	
	public Student findInStand(String studentIndex, int standNumber) {
		return getStandByNumber(standNumber).findStudent(studentIndex);
	}
	
	
	@Override
	public Queue getTempQueue(int standNumber) {
		return getStandByNumber(standNumber).getTempQueue();
	}
	
	@Override
	public boolean isInsertable(int standNumber) {
		return getStandByNumber(standNumber).isInsertable();
	}
	
	@Override
	public void enableInsertion(int standNumber) {
		getStandByNumber(standNumber).enableInsertion();
	}
	
	@Override
	public void disableInsertion(int standNumber) {
		getStandByNumber(standNumber).disableInsertion();
	}
	
	@Override
	@Scheduled(cron="0 4 2 * * *")
	public void resetState() {
		System.out.println("FacultyServiceImpl - 130 =====================================");
		stand_1.resetState();
		stand_2.resetState();
		stand_3.resetState();
		stand_4.resetState();
		stand_5.resetState();
	}
	
	
	/**
	 * 
	 * @param standNumber
	 * @return concrete stand or null if stand with that number does not exist
	 */
	private FacultyStand getStandByNumber(int standNumber) {
		if (standNumber == 1)
			return stand_1;
		if (standNumber == 2)
			return stand_2;
		if (standNumber == 3)
			return stand_3;
		if (standNumber == 4)
			return stand_4;
		if (standNumber == 5)
			return stand_5;
		return null;
		
	}
	
	
	
	public UserRepository getUserRepository() {
		return userRepository;
	}
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public ComingRepository getCommingRepository() {
		return commingRepository;
	}
	public void setCommingRepository(ComingRepository commingRepository) {
		this.commingRepository = commingRepository;
	}








}
