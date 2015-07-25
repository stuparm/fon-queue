package rs.fon.queue.json;

import java.util.List;

/**
 * Class that represents json for one stand which will be represent on the
 * monitor
 * 
 * @author Mihailo
 *
 */
public class StandJson {

	
	private boolean OPEN;
	private StudentJson NEXT_STUDENT;
	private String PRIMARY_Q_LENGTH;
	private List<StudentJson> TEMP_QUEUE;
	private boolean INSERTABLE;
	
	public boolean isOPEN() {
		return OPEN;
	}
	public void setOPEN(boolean oPEN) {
		OPEN = oPEN;
	}
	
	
	
	public StudentJson getNEXT_STUDENT() {
		return NEXT_STUDENT;
	}
	public void setNEXT_STUDENT(StudentJson nEXT_STUDENT) {
		NEXT_STUDENT = nEXT_STUDENT;
	}
	public String getPRIMARY_Q_LENGTH() {
		return PRIMARY_Q_LENGTH;
	}
	public void setPRIMARY_Q_LENGTH(String pRIMARY_Q_LENGTH) {
		PRIMARY_Q_LENGTH = pRIMARY_Q_LENGTH;
	}
	public List<StudentJson> getTEMP_QUEUE() {
		return TEMP_QUEUE;
	}
	public void setTEMP_QUEUE(List<StudentJson> tEMP_QUEUE) {
		TEMP_QUEUE = tEMP_QUEUE;
	}
	public boolean isInsertable() {
		return INSERTABLE;
	}
	public void setInsertable(boolean iNSERTABLE) {
		this.INSERTABLE = iNSERTABLE;
	}
	

}