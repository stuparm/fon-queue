package rs.fon.queue.blogic.impl;



import java.util.ArrayList;
import java.util.List;

import rs.fon.queue.blogic.exception.MethodNotImplemtedException;
import rs.fon.queue.blogic.exception.StudentAlreadyExistException;
import rs.fon.queue.blogic.interfaces.Queue;


public class TempQueue implements Queue {

	private Student[] array;
	private int top;

	
	public TempQueue(int capacity) {
		array = new Student[capacity];
		top = -1;
		
	}

	@Override
	public void insert(Student student) throws StudentAlreadyExistException {
		assert array != null; // TODO assert
		for (Student s : array) {
			if (s!= null && s.equals(student))
				throw new StudentAlreadyExistException();
		}
		array[top = ++top % array.length] = student;
	}

	@Override
	public Student remove() {
		throw new MethodNotImplemtedException();
	}

	@Override
	public Student findStudent(Student student) {
		//TODO proveriti imutabilnost
		for (Student s : array) {
			if (s.equals(student))
//				return new Student(s);
				return s;
		}
		return null;
	}


	
	@Override
	public Student[] firstStudents(int N) {
		Student[] arr = new Student[N];
		int itter = top;
		for (int i = 0; i < N; i++) {
			Student s = array[itter = ++itter%array.length];
			if (s == null)
				continue;
			arr[i] = s;
			
		}
		return arr;
	}

	@Override
	public int getSize() {
		return array.length;
	}

	@Override
	public void reset() {
		int capacity = array.length;
		array = new Student[capacity];
		top = -1;
	}

	@Override
	public boolean isEmpty() {
		// TODO ukoliko se omoguci izbacivanje iz pomocnog reda potrebno je implementirati ovu metodu
		throw new RuntimeException("TempQueue.isEmpty() should never be called");
	}
	
	@Override
	public Student getNext() {
		// TODO ukoliko se omoguci izbacivanje iz pomocnog reda potrebno je implementirati ovu metodu
		throw new RuntimeException("TempQueue.getNext() should never be called");
	}
	
	
	@Override
	public List<Student> toList() {
		List<Student> list = new ArrayList<Student>();
		int itter = top;
		for (int i = 0; i < array.length; i++) {
			Student s = array[itter = ++itter%array.length];
			if (s != null)
				list.add(s);
		}
		return list;
		
		
	}
	

}
