package org.konghao.model;

import java.util.List;


/**
 * 获取班级和具体的学生列表
 * @author konghao
 *
 */
public class ClassroomDto {
	private int cid;
	private String name;
	private String grade;
	
	private List<Student> stus;

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public List<Student> getStus() {
		return stus;
	}

	public void setStus(List<Student> stus) {
		this.stus = stus;
	}
}
