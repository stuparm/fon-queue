package rs.fon.queue.service;

import rs.fon.queue.model.StudentModel;

public interface StandService {

	public void open();
	
	public void close();
	
	public void insertStudent(StudentModel studentModel);
	
	
}
