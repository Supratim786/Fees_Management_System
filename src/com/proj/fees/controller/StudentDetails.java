package com.proj.fees.controller;

import javax.swing.*;

import com.proj.fees.dao.RegistrationDAO;
import com.proj.fees.model.Registration;

import java.awt.*;
import java.util.ArrayList;

public class StudentDetails extends JFrame {
	
	private JTextField sid;
	private JLabel stId,sSem;
	private JButton b1,e1;
	private JComboBox semesters;
	 Container c;  
	
	
	public StudentDetails(){
		super("find student");
		
		
		  c =getContentPane();
		  c.setLayout(new GridLayout(3,2));
		
		sid=new JTextField(20);
		stId=new JLabel("Student ID");
		b1=new JButton("Enter");
		sSem=new JLabel("Semester");
		
		String sem[] = getSemesters();
		semesters=new JComboBox(sem);
		
		c.add(stId);c.add(sid);
		c.add(sSem);c.add(semesters);
		c.add(b1);
		
		b1.addActionListener(e->{
			
			Registration r;
			RegistrationDAO dao = new RegistrationDAO();
			r = dao.searchByIdSemDetail(sid.getText().trim(), (String) semesters.getSelectedItem());
			
			if(r != null)
    	    {
				
				new Item(r);
    	    }
			else
    	    {
    	       JOptionPane.showMessageDialog(this, "Invalid Id / Semester");
    	       sid.setText("");
    	    }
			
		});
		
		setSize(350, 150);
		   setLocation(200,200);
		   setResizable(false);
		   //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   setVisible(true);	
		
		
	}
	
	public String[] getSemesters() {
		
		RegistrationDAO dao = new RegistrationDAO();
		ArrayList<String> semesterList = dao.getSemesterList();
		System.out.println(semesterList);
		String [] semesters = new String[semesterList.size()];
		semesters = semesterList.toArray(semesters);
		
		
		return semesters;
	}
	public static void main(String[] args) {
		new StudentDetails();
		
	}
	

}
