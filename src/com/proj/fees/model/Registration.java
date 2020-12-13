package com.proj.fees.model;

import java.io.Serializable;

public class Registration implements Serializable 
{
	
	
	private String id;
	private String name;
	private String emailid;
	private String mobno;
    private String address;
	private String city;
	private String country;
	private String gender;
	private String courseName;
	private String semName;
	private String tfee;
	private String sfee;
	private String fine;
	private String feeSubmissionDate;
	private String dueDate;
	private String sec;
	private String feeStatus;
	
	
	public Registration(){
		
	}

	

	public Registration(String id, String name, String emailid, String mobno, String address, String city,
			String country, String gender, String courseName, String semName, String tfee, String sfee, String fine,
			String feeSubmissionDate, String dueDate, String sec, String feeStatus) {
		super();
		this.id = id;
		this.name = name;
		this.emailid = emailid;
		this.mobno = mobno;
		this.address = address;
		this.city = city;
		this.country = country;
		this.gender = gender;
		this.courseName = courseName;
		this.semName = semName;
		this.tfee = tfee;
		this.sfee = sfee;
		this.fine = fine;
		this.feeSubmissionDate = feeSubmissionDate;
		this.dueDate = dueDate;
		this.sec = sec;
		this.feeStatus = feeStatus;
	}



	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {
		this.mobno = mobno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSemName() {
		return semName;
	}

	public void setSemName(String semName) {
		this.semName = semName;
	}

	public String getTfee() {
		return tfee;
	}

	public void setTfee(String tfee) {
		this.tfee = tfee;
	}

	public String getSfee() {
		return sfee;
	}

	public void setSfee(String sfee) {
		this.sfee = sfee;
	}

	public String getFine() {
		return fine;
	}

	public void setFine(String fine) {
		this.fine = fine;
	}

	public String getSec() {
		return sec;
	}

	public void setSec(String sec) {
		this.sec = sec;
	}

	public String getFeeSubmissionDate() {
		return feeSubmissionDate;
	}

	public void setFeeSubmissionDate(String feeSubmissionDate) {
		this.feeSubmissionDate = feeSubmissionDate;
	}

	public String getFeeStatus() {
		return feeStatus;
	}

	public void setFeeStatus(String feeStatus) {
		this.feeStatus = feeStatus;
	}

	@Override
	public String toString() {
		return "Registration [id=" + id + ", name=" + name + ", emailid=" + emailid + ", mobno=" + mobno + ", address="
				+ address + ", city=" + city + ", country=" + country + ", gender=" + gender + ", courseName="
				+ courseName + ", semName=" + semName + ", tfee=" + tfee + ", sfee=" + sfee + ", fine=" + fine
				+ ", feeSubmissionDate=" + feeSubmissionDate + ", dueDate=" + dueDate + ", sec=" + sec + ", feeStatus="
				+ feeStatus + "]";
	}
	
	
	
		
}
