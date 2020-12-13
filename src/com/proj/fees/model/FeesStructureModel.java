package com.proj.fees.model;

public class FeesStructureModel {
	
	private String courseName;
	private String semesterName;
	private String feesAmount;
	private String firstDateFeesSubm;
	public FeesStructureModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FeesStructureModel(String courseName, String semesterName, String feesAmount, String firstDateFeesSubm) {
		super();
		this.courseName = courseName;
		this.semesterName = semesterName;
		this.feesAmount = feesAmount;
		this.firstDateFeesSubm = firstDateFeesSubm;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getSemesterName() {
		return semesterName;
	}
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}
	public String getFeesAmount() {
		return feesAmount;
	}
	public void setFeesAmount(String feesAmount) {
		this.feesAmount = feesAmount;
	}
	public String getFirstDateFeesSubm() {
		return firstDateFeesSubm;
	}
	public void setFirstDateFeesSubm(String firstDateFeesSubm) {
		this.firstDateFeesSubm = firstDateFeesSubm;
	}
	@Override
	public String toString() {
		return "FeesStructureModel [courseName=" + courseName + ", semesterName=" + semesterName + ", feesAmount="
				+ feesAmount + ", firstDateFeesSubm=" + firstDateFeesSubm + "]";
	}
	
	

}
