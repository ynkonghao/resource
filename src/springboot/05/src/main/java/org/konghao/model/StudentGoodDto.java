package org.konghao.model;

public class StudentGoodDto {
	private int sid;
	private String sname;
	private int claId;
	private String claName;
	private String grade;
	
	public StudentGoodDto() {}
	
	public StudentGoodDto(int sid,String sname, int claId, String claName, String grade) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.claId = claId;
		this.claName = claName;
		this.grade = grade;
	}
	
	
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getClaId() {
		return claId;
	}
	public void setClaId(int claId) {
		this.claId = claId;
	}
	public String getClaName() {
		return claName;
	}
	public void setClaName(String claName) {
		this.claName = claName;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
