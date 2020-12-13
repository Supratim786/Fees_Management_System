package com.proj.fees.controller;

import java.awt.Container;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.proj.fees.dao.RegistrationDAO;
import com.proj.fees.model.Registration;
import com.proj.fees.properties.PropertyReader;

public class StudentFind extends JFrame{

	private JTextField sid;
	private JLabel stId,sSem;
	private JButton find;
	private JComboBox semesters;
	 Container c;  
	
	
	public StudentFind(){
		super("Fee Submission");
		
		
		  c =getContentPane();
		  c.setLayout(new GridLayout(3,2));
		
		sid=new JTextField(20);
		stId=new JLabel("Student ID");
		find=new JButton("Enter");
		sSem=new JLabel("Semester");
		
		String sem[] = getSemesters();
		semesters=new JComboBox(sem);
		
		c.add(stId);c.add(sid);
		c.add(sSem);c.add(semesters);
		c.add(find);
		
		find.addActionListener(e->{
			
			Registration r,r1;
			String semester="";
			String firstDateOfFeesSub="";
			int fineAmt;
			RegistrationDAO dao = new RegistrationDAO();
			String selectedSem = (String) semesters.getSelectedItem();
			Calendar submissionDate = Calendar.getInstance();
			Calendar firstDateFeesSub = Calendar.getInstance();
			String semSplit[] = selectedSem.split("-");
			
			r = dao.searchByIdSem(sid.getText().trim(), (String) semesters.getSelectedItem());
			if(r==null) {
				JOptionPane.showMessageDialog(this, "Invalid Id / Semester");
			}
			else if(r.getFeeStatus().equalsIgnoreCase("completed")) {
				JOptionPane.showMessageDialog(this, "Fees already submitted");
			}	
			
			else {
				
				int semNo = Integer.parseInt(semSplit[1])-1;
				int cnt=-1;
				if(semNo>0) {
					cnt = dao.getCountForSemName(semSplit[0]+"-"+String.valueOf(semNo), sid.getText().trim());
				}
							
				if(cnt==0) {
					JOptionPane.showMessageDialog(this, "please submit fees for previous semester first");
				}
				
				else {
				int fees = dao.getFeesAmount(r.getCourseName(),r.getSemName());
				semester = (String) semesters.getSelectedItem();
				r.setTfee(String.valueOf(fees));
				r.setSemName(semester);
				firstDateOfFeesSub = dao.getFirstDateOfFeesSub(r.getCourseName(),r.getSemName());
				firstDateOfFeesSub = (firstDateOfFeesSub + "-" +String.valueOf(submissionDate.get(Calendar.YEAR))).replace("-", "/");
				System.out.println("firstDateOfFeesSub:"+firstDateOfFeesSub);
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
			
				try {
					firstDateFeesSub.setTime(formatter.parse(firstDateOfFeesSub));
				} catch (ParseException e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				System.out.println("firstDateFeesSub:"+firstDateFeesSub.getTime());
				System.out.println("submissionDate:"+submissionDate.getTime());
			
				r1 = getRegisteredObjectWithAmt(firstDateFeesSub, submissionDate, r);
				System.out.println("registration:"+r);
			
				if(r1 == null || r1.getId()==null || r1.getId()=="")
				{
					JOptionPane.showMessageDialog(this, "Invalid Id / Semester");
					sid.setText("");	
				}
				else
				{
					new SubmitFees(r1);
				}
			}
		}
			
		});
		
		setSize(350, 150);
		   setLocation(200,200);
		   setResizable(false);
		   //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   setVisible(true);	
		
		
	}
	
	public Registration getRegisteredObjectWithAmt(Calendar firstDate, Calendar submissionDate, Registration r) {
		
		Calendar dueDate = Calendar.getInstance();
		PropertyReader prop = new PropertyReader();
		String durationWithoutFine = prop.getProperty("DURATION_OF_DAYS_WITHOUT_FINE");
		dueDate.setTime(firstDate.getTime());
		System.out.println("dueDate before:"+dueDate.getTime());
		System.out.println("durationWithoutFine:"+durationWithoutFine);
		dueDate.add(Calendar.DATE, Integer.parseInt(durationWithoutFine));
		String subDate="", dDate="";
		
		int fineAmt = 0;
		int totalAmt = Integer.parseInt(r.getTfee());
		int submittedAmt = 0;
		int daysDifference = 0;
		int fineAmtPct = Integer.parseInt(prop.getProperty("FINE_AMT_PCT"));
		System.out.println("submissionDate:"+submissionDate.getTime());
		System.out.println("totalAmt:"+totalAmt);
		System.out.println("dueDate:"+dueDate.getTime());
		
		subDate = String.valueOf(submissionDate.get(Calendar.DATE)+"/"+(new SimpleDateFormat("MMM").format(submissionDate.getTime()))+
				"/"+submissionDate.get(Calendar.YEAR));
		dDate = String.valueOf(dueDate.get(Calendar.DATE)+"/"+(new SimpleDateFormat("MMM").format(dueDate.getTime()))+
				"/"+dueDate.get(Calendar.YEAR));
		System.out.println("subDate:"+subDate);
		
		if(submissionDate.compareTo(dueDate)>0) {
			System.out.println("fine will be added");
			daysDifference = Math.abs(submissionDate.get(Calendar.DAY_OF_YEAR)-dueDate.get(Calendar.DAY_OF_YEAR));
			System.out.println("daysDifference:"+daysDifference);
			fineAmt = (fineAmtPct * daysDifference);
			 
		}
		else {
			fineAmt = 0;
			System.out.println("fine will not be added");
		
		}
		submittedAmt = totalAmt + fineAmt;
		System.out.println("submittedAmt:"+submittedAmt);
		
		r.setSfee(String.valueOf(submittedAmt));
		r.setFine(String.valueOf(fineAmt));
		r.setFeeSubmissionDate(subDate);
		r.setDueDate(dDate);
		r.setFeeStatus("Completed");
		
		return r;
	}
	public String[] getSemesters() {
		
		RegistrationDAO dao = new RegistrationDAO();
		ArrayList<String> semesterList = dao.getSemesterList();
		System.out.println(semesterList);
		String [] semesters = new String[semesterList.size()];
		semesters = semesterList.toArray(semesters);
		
		
		return semesters;
	}
	
	public static void main(String args[]) {
		new StudentFind();
	}
}
