package rs.fon.queue.json;

import rs.fon.queue.blogic.impl.Student;

public class StudentJson {

	private String ORD_NUM;
	private String INDEX;
	public String getORD_NUM() {
		return ORD_NUM;
	}
	public void setORD_NUM(String oRD_NUM) {
		ORD_NUM = oRD_NUM;
	}
	public String getINDEX() {
		return INDEX;
	}
	public void setINDEX(String iNDEX) {
		INDEX = iNDEX;
	}
	
	public StudentJson (Student student) {
		if (student != null) {
			ORD_NUM = student.getOrdNum()+"";
			INDEX = student.getIndex();
		}
		else {
			ORD_NUM = "#";
			INDEX = "#";
		}
	}
	
	
}
