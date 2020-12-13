package com.proj.fees.controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.proj.fees.dao.RegistrationDAO;
import com.proj.fees.model.FeesStructureModel;

public class FeesStructure extends JFrame{

	public FeesStructure(String courseName) {
		
		super("Fees Structure");
		String heading[]={"SEMESTER NAME","FEES AMOUNT","FIRST DATE FEES SUBMISSION"};
		String data[][];
		
		System.out.println("courseName:"+courseName);
		RegistrationDAO dao = new RegistrationDAO();
		ArrayList<FeesStructureModel> feesStructureList;
		feesStructureList = dao.getFeesStructure(courseName);
		
		data = new String[feesStructureList.size()][3];
		
		int r=0;
		for(FeesStructureModel model : feesStructureList)
		{
			
			data[r][0]=model.getSemesterName();
			data[r][1]=model.getFeesAmount();
			data[r][2]=model.getFirstDateFeesSubm();
			r++;
		}
		
		Container con=getContentPane();
		con.setLayout(new BorderLayout());
		
		JTable datatable=new JTable(data, heading);
		JScrollPane jsp=new JScrollPane(datatable);
		
		con.add(new JLabel("Fees Structure Details"),BorderLayout.NORTH);
		con.add(jsp,BorderLayout.CENTER);
		
		setSize(850, 300);
		
		setLocation(200, 200);
		setVisible(true);
	}

	
	
	/*
	 * public ArrayList<FeesStructureModel> getFeesStructureModel(String
	 * courseName){ RegistrationDAO dao = new RegistrationDAO(); ArrayList
	 * 
	 * }
	 */
}
