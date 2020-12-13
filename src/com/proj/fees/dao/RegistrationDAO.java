package com.proj.fees.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.proj.fees.model.FeesStructureModel;
import com.proj.fees.model.Registration;
import com.proj.fees.properties.PropertyReader;

public class RegistrationDAO {
	
	PropertyReader props;
	String driverName = null;
	String url = null;
	String username = null;
	String password = null;
	Connection con = null;

	public RegistrationDAO()
	{
		props = new PropertyReader();
		init();
	}
	
	private void init()
	{	
		driverName = props.getProperty("driverName").trim();
		url = props.getProperty("url").trim();
		username = props.getProperty("username").trim();
		password = props.getProperty("password").trim();
		getConnection();
	}

	public boolean validateDatabaseProperties(){
		
		if(driverName.isEmpty() || url.isEmpty() || username.isEmpty() || password.isEmpty())
			return false;
		else{
		
			System.out.println("database properties are::"+ driverName+", "+url+", "+username+", "+password);
			//logger.info("database properties are::"+ driverName+", "+url+", "+username+", "+password);
			return true;
		}
			
	}
	private void getConnection() {
		
		if(validateDatabaseProperties()){
			try{
				
				Class.forName(driverName);
				con = DriverManager.getConnection(url,username,password);
				//logger.info(toString());
				System.out.println("connection created");
			//	logger.info("connection created");
				
			}
			catch(Exception e){
				//logger.error(e);
				e.printStackTrace();
			}
		}	
		else{
			System.out.println("connection failed); check connection details");
		}
	}
	
	private void closeConnection(){
		try {
			if(!con.isClosed()){
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getCourseList(){
		ArrayList<String> courseList = new ArrayList<String>();
		String queryForCourses = props.getProperty("FEEMANG.COURSELIST");
		
		try {
			if(con.isClosed()){
				getConnection();
			}
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(queryForCourses);
		
		while(rs.next()) {
			courseList.add(rs.getString(1));
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			courseList = new ArrayList<String>();
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
		 return courseList;	
	}
	public Registration searchByIdSem(String sId, String semsterName) {
		
		Registration registration = null;
		String queryByIdSem = props.getProperty("FEEMANG.STUDENTLISTBYIDSEM").trim();
		
		try {
			PreparedStatement stmt = con.prepareStatement(queryByIdSem);
			stmt.setString(1, sId);
			stmt.setString(2, semsterName);
			System.out.println("queryByIdSem:"+queryByIdSem);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				
				registration = new Registration();
				registration.setId(rs.getString(1));	
				registration.setName(rs.getString(2));
				registration.setEmailid(rs.getString(3));
				registration.setCourseName(rs.getString(4));
				registration.setSemName(rs.getString(5));
				registration.setTfee(rs.getString(6));
				registration.setSfee(rs.getString(7));
				registration.setFine(rs.getString(8));
				registration.setFeeSubmissionDate(rs.getString(9));
				registration.setFeeStatus(rs.getString(10));
				
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			registration = new Registration();
			e.printStackTrace();
		}
		
		return registration;
	}
	
public Registration searchByIdSemDetail(String sId, String semsterName) {
		
		Registration registration = null;
		String queryByIdSem = props.getProperty("FEEMANG.STUDENTLISTBYIDSEMDETAIL").trim();
		
		try {
			PreparedStatement stmt = con.prepareStatement(queryByIdSem);
			stmt.setString(1, sId);
			stmt.setString(2, semsterName);
			System.out.println("queryByIdSem:"+queryByIdSem);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				
				registration = new Registration();
				registration.setId(rs.getString(1));	
				registration.setName(rs.getString(2));
				registration.setEmailid(rs.getString(3));
				registration.setMobno(rs.getString(4));
				registration.setGender(rs.getString(5));
				registration.setSec(rs.getString(6));
				registration.setAddress(rs.getString(7));
				
				registration.setCourseName(rs.getString(8));
				registration.setSemName(rs.getString(9));
				registration.setTfee(rs.getString(10));
				registration.setSfee(rs.getString(11));
				registration.setFine(rs.getString(12));
				registration.setFeeSubmissionDate(rs.getString(13));
				registration.setFeeStatus(rs.getString(14));
				
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			registration = new Registration();
			e.printStackTrace();
		}
		
		return registration;
	}
	
	public ArrayList<String> getSemesterList(){
		
		ArrayList<String> semseterList = new ArrayList<String>();
		String querySemesterNames = props.getProperty("FEEMANG.SEMESTERLIST").trim();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(querySemesterNames);
			
			while(rs.next()) {
				semseterList.add(rs.getString(1));
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			semseterList = new ArrayList<String>();
			e.printStackTrace();
		}
		
		return semseterList;
	}
	
	public ArrayList<FeesStructureModel> getFeesStructure(String courseName){
		
		ArrayList<FeesStructureModel> feesStructureList = new ArrayList<FeesStructureModel>();
		String queryFeesStructure = props.getProperty("FEEMANG.FEESSTRUCTURE").trim();
		
		try {
			PreparedStatement stmt = con.prepareStatement(queryFeesStructure);
			stmt.setString(1, courseName);
			
			ResultSet rs = stmt.executeQuery();
			FeesStructureModel model;
			
			while(rs.next()) {
				model = new FeesStructureModel();
				model.setCourseName(rs.getString(1));
				model.setSemesterName(rs.getString(2));
				model.setFeesAmount(rs.getString(3));
				model.setFirstDateFeesSubm(rs.getString(4));
				
				feesStructureList.add(model);
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			feesStructureList = new ArrayList<FeesStructureModel>();
			e.printStackTrace();
		}
		
		return feesStructureList;
	}
	
	
	public ArrayList<Registration> getStudentByFeeStatus(String status){
		ArrayList<Registration> studentList = new ArrayList<Registration>();
		String queryByFeeStatus = props.getProperty("FEEMANG.STUDENTBYFEESTATUS");
		Registration student;
		try {
			PreparedStatement stmt = con.prepareStatement(queryByFeeStatus);
			stmt.setString(1, status);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				student = new Registration();
				student.setId(rs.getString(1));
				student.setName(rs.getString(2));				
				student.setCourseName(rs.getString(3));
				student.setSemName(rs.getString(4));
				student.setTfee(rs.getString(5));
				student.setSfee(rs.getString(6));
				student.setFine(rs.getString(7));
				student.setFeeSubmissionDate(rs.getString(8));	
				student.setFeeStatus(rs.getString(9));
				
				studentList.add(student);
				
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			studentList = new ArrayList<Registration>();
			e.printStackTrace();
		}
		
		return studentList;
	}
	
	public ArrayList<Registration> getAllStudents(){
		
		ArrayList<Registration> studentList = new ArrayList<Registration>();
		String studentListQuery = props.getProperty("FEEMANG.GETSTUDENTLIST").trim();//query fetching for sql
		System.out.println("studentListQuery:"+studentListQuery);
		Registration student;
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs= stmt.executeQuery(studentListQuery);
			
			while(rs.next()){
				student = new Registration();
				student.setId(rs.getString(1));
				student.setName(rs.getString(2));
				student.setEmailid(rs.getString(3));
				student.setMobno(rs.getString(4));
				student.setAddress(rs.getString(5));
				student.setCity(rs.getString(6));
				student.setCountry(rs.getString(7));
				student.setGender(rs.getString(8));
				student.setCourseName(rs.getString(9));
				student.setSemName(rs.getString(10));
				student.setTfee(rs.getString(11));
				student.setSfee(rs.getString(12));
				student.setFine(rs.getString(13));
				student.setSec(rs.getString(14));
				student.setFeeStatus(rs.getString(15));
				
				studentList.add(student);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			studentList = new ArrayList<Registration>();
			e.printStackTrace();
		}
		return studentList;
	}
	public void rData(){
		
		String studentRemoveQuery = props.getProperty("FEEMANG.DELSTUDENTLIST").trim();
		System.out.println("studentRemoveQuery:"+studentRemoveQuery);
		
		int removeCnt = 0;
		
		try{
			Statement stmt = con.createStatement();
			removeCnt= stmt.executeUpdate(studentRemoveQuery);
		}catch(SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		System.out.println("removeCnt:"+removeCnt);
	}
	
	public void setAllStudents(ArrayList<Registration> wlist){
		
		System.out.println("wlist.size():"+wlist.size());
		String studentInsertQuery = props.getProperty("FEEMANG.PUTSTUDENTLIST").trim();
		System.out.println("studentInsertQuery:"+studentInsertQuery);
		Registration r;
		int insertCount=0;
		try{
			
			PreparedStatement stmt;
			
			for(int i=0; i<wlist.size(); i++){
				
				stmt = con.prepareStatement(studentInsertQuery);
				r = wlist.get(i);
				stmt.setString(1, r.getId());
				stmt.setString(2, r.getName());
				stmt.setString(3, r.getEmailid());
				stmt.setString(4, r.getMobno());
				stmt.setString(5, r.getAddress());
				stmt.setString(6, r.getCity());
				stmt.setString(7, r.getCountry());
				stmt.setString(8, r.getGender());
				stmt.setString(9, r.getCourseName());
				stmt.setString(10, r.getSemName());
				stmt.setString(11, r.getTfee());
				stmt.setString(12, r.getSfee());
				stmt.setString(13, r.getFine());
				stmt.setString(14, r.getSec());
				stmt.setString(15, r.getFeeStatus());
				
				insertCount+= stmt.executeUpdate();
			}
			
		}catch(SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		System.out.println("insertCount:"+insertCount);
		
	}

	public int getFeesAmount(String courseName, String semName) {
		// TODO Auto-generated method stub
		int feesAmt = 0;
		String queryForFeesAmt = props.getProperty("FEEMANG.FEESAMOUNT").trim();
		
		try {
			PreparedStatement stmt = con.prepareStatement(queryForFeesAmt);
			stmt.setString(1, courseName);
			stmt.setString(2, semName);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				feesAmt = Integer.parseInt(rs.getString(1));
			}
			
		}catch(SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			return feesAmt;
			
		}
		return feesAmt;
	}

	public String getFirstDateOfFeesSub(String courseName, String semName) {
		// TODO Auto-generated method stub
		
		String firstDate = "";
		String queryForFeeDate = props.getProperty("FEEMANG.FIRSTDATE").trim();
		System.out.println("queryForFeeDate:"+queryForFeeDate);
		System.out.println("courseName:"+courseName+";semName:"+semName);
		try {
			PreparedStatement stmt = con.prepareStatement(queryForFeeDate);
			stmt.setString(1, courseName);
			stmt.setString(2, semName);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				firstDate = rs.getString(1);
			}
			
		}catch(SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			return firstDate;
			
		}
		return firstDate;
	}
	public int getCountForSemName(String semName, String studentId) {
	
		int count=0;
		
		 String queryForSemName=props.getProperty("FEEMANG.STUDENTSEMNAME").trim();
		 
		 try {
			 PreparedStatement stmt=con.prepareStatement(queryForSemName);
			 stmt.setString(1, semName);
			 stmt.setString(2, studentId);
			 
			 ResultSet rs=stmt.executeQuery();
			 while(rs.next()) {
				 count=rs.getInt(1);
				 
			 }
		 }catch(SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
				return count;
		 }
		 System.out.println("count:"+count);
		 return count;
	}

	public int setFeesTransaction(Registration r) {
		// TODO Auto-generated method stub
		String queryForInsert = props.getProperty("FEEMANG.SETFEESTRAN").trim();
		int row = 0;
		try {
			PreparedStatement stmt = con.prepareStatement(queryForInsert);
			stmt.setString(1, r.getTfee());
			stmt.setString(2, r.getFine());
			stmt.setString(3, r.getSfee());
			stmt.setString(4, r.getFeeSubmissionDate());
			stmt.setString(5, r.getFeeStatus());
			stmt.setString(6, r.getId());
			stmt.setString(7, r.getCourseName());
			stmt.setString(8, r.getSemName());
			
			row = stmt.executeUpdate();
		}catch(SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			return row;
			
		}
		return row;
		
	}
}
