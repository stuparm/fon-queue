package rs.fon.queue.blogic.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import rs.fon.queue.blogic.exception.StudentAlreadyExistException;
import rs.fon.queue.blogic.interfaces.Queue;
;

public class PrimaryQueue implements Queue {

	private LinkedList<Student> list;
	private Map<String, Student> map; // key <- broj indeksa, value <- student 
	
	
	public PrimaryQueue() {
		list = new LinkedList<Student>();
		map = new HashMap<String, Student>();
	}

	@Override
	public void insert(Student student) throws StudentAlreadyExistException {
//		if (list.contains(student))
//			throw new StudentAlreadyExistException();
//		list.addLast(student);
		if (map.containsKey(student.getIndex()))
			throw new StudentAlreadyExistException();
		list.add(student);
		map.put(student.getIndex(), student);

	}

	@Override
	public Student remove() {
//		if (list.isEmpty())
//			return null;
//		return list.removeFirst();
		if (list.isEmpty())
			return null;
		Student s = list.removeFirst();
		map.remove(s.getIndex());
		return s;
	}

	@Override
	public Student findStudent(Student student) {
//		if (list.isEmpty())
//			return null;
//		for (Student s : list) {
//			if (s.equals(student))
////				return new Student(s);
//				return s;
//		}
//		return null;
		return map.get(student.getIndex());
	}


	@Override
	public Student[] firstStudents(int N) {
		Student[] arr = new Student[N];
		for (int i = 0; i < N; i++) {
			try {
				Student s = list.get(i);
				arr[i] = s;
			} catch (IndexOutOfBoundsException e) {
				return arr;
			}
		}
		return arr;
	}

	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public void reset() {
		list = new LinkedList<Student>();
		//
		map = new HashMap<String, Student>();
	}
	
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	@Override
	public Student getNext() {
		if (list.isEmpty())
			return null;
		return list.getFirst();
	}

	@Override
	public List<Student> toList() {
		return list;
	}
	
}
