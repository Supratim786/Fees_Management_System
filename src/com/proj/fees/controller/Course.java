package com.proj.fees.controller;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import com.proj.fees.dao.RegistrationDAO;

public class Course extends JFrame {
	
	private JComboBox  acourse;
	private JLabel scourse;
	private JButton find;
	private String course="";
	
	public Course() {
		
		Container c=getContentPane();
		  c.setLayout(new GridLayout(2,2));
		  
		scourse=new JLabel("Select Course");
		
		
		String courses[] = getCourses();
		acourse=new JComboBox(courses);
		
		find=new JButton("Find");
		
		
		c.add(scourse);c.add(acourse);
		c.add(find);
		
		
		find.addActionListener((e) ->
        {
           course = getSelectedCourse();
     	   new FeesStructure(course);
     	   
        });
		
        
		setSize(450, 150);
		 setLocation(200,200);
		 setResizable(false);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setVisible(true);
	}
	
	public String getSelectedCourse() {
		String selectedCourse = "";
		
		selectedCourse = (String) acourse.getSelectedItem();
		return selectedCourse;
	}

	public String[] getCourses() {
		
		RegistrationDAO dao = new RegistrationDAO();
		ArrayList<String> courseList = dao.getCourseList();
		System.out.println(courseList);
		String [] courses = new String[courseList.size()];
		courses = courseList.toArray(courses);
		
		
		return courses;
	}
	
	public static void main(String[] args) {
		new Course();
	}
}
