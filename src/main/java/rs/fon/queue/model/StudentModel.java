package rs.fon.queue.model;


import javax.validation.constraints.Pattern;



public class StudentModel {

	@Pattern(regexp="^[0-9][0-9][0-9][0-9]/[0-9][0-9][0-9][0-9]$", message="Pravilan format: YYYY/XXXX. Primer: 2012/0012")
	private String indexNumber;
	
	//@NumberFormat(pattern="^[1-3]|$")
	@Pattern(regexp="^|[1-3]$", message="Šalter može biti samo 1, 2 ili 3.")
	private String standNumber;
	
	@Pattern(regexp="^$", message="Ostavi ovo polje prazno.")
	private String antiSpam;
	
	
	public String getIndexNumber() {
		return indexNumber;
	}
	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}
	public String getStandNumber() {
		return standNumber;
	}
	public void setStandNumber(String standNumber) {
		this.standNumber = standNumber;
	}
	public String getAntiSpam() {
		return antiSpam;
	}
	public void setAntiSpam(String antiSpam) {
		this.antiSpam = antiSpam;
	}
	
}
