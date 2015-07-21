package rs.fon.queue.blogic.impl;

public class Student {

	private int ordNum;
	private String index;
	
	
	public Student() {
	}
	
	public Student(int ordNum, String index) {
		this.ordNum = ordNum;
		this.index = index;
	}
//	
//	public Student(Student student) {
//		index = student.getIndex();
//		ordNum = student.getOrdNum();
//	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Student))
			return false;
		Student s = (Student)obj;
		if (index.equals(s.getIndex()))
			return true;
		return false;
	}
	
	
	
	public int getOrdNum() {
		return ordNum;
	}
	public void setOrdNum(int ordNum) {
		this.ordNum = ordNum;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	
	
	
	
}
