package com.proj.fees.controller;

import java.awt.Container;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.proj.fees.dao.RegistrationDAO;
import com.proj.fees.model.Registration;
import com.proj.fees.properties.PropertyReader;

public class Status extends JFrame {
	
	
	private JRadioButton l1,l2;
	private JButton e1,e2;
	private JLabel d1,d2;
	
	
	public Status() {
		super(" Report Generation");
		
		Container c=getContentPane();
		  c.setLayout(new GridLayout(4,4));
		  
		  l1=new JRadioButton("Submitted");
		  l2=new JRadioButton("Not submitted");
		  e1 =new JButton("Generate");
		  e2 =new JButton("Download");
		  
		  ButtonGroup rgroup=new ButtonGroup();
		   rgroup.add(l1);
		   rgroup.add(l2);
		   
		   
		   
		   JPanel gpanel=new JPanel();
		   gpanel.add(l1);
		   gpanel.add(l2);
		   
		   d1=new JLabel("Fees status");
		   
		   c.add(d1);c.add(gpanel);
		   c.add(e1);
		   c.add(e2);
		   
		   
		   
		   
		   
		   e1.addActionListener(e->{
			   
			   generation();
			  // ;
			   
		   });
		   
		
		  e2.addActionListener(e->{
		  
		  try {
			FileUtils.copyURLToFile(new URL("D:/project"), new File("report_not-submitted.xlsx"));
		} catch (MalformedURLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} 
		  });	
		 
		   
		   setSize(350, 200);
			 setLocation(200,200);
			 setResizable(false);
			 //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 setVisible(true);
		  
	}
	public void generation() {
		boolean fSubmitted=false;
		if(l1.isSelected()) {
			   fSubmitted = true; 
		   }
		   else if(l2.isSelected()) {
			   fSubmitted = false;
		   }
		
		System.out.println("fSubmitted:::"+fSubmitted);
		boolean generateSuccess = getGeneratedReport(fSubmitted);
		
		if(generateSuccess) {
			JOptionPane.showMessageDialog(this, "Report generated successfully");
		}
		else {
			JOptionPane.showMessageDialog(this, "Report generated failed");
		}
		
		System.out.println("generateSuccess:"+generateSuccess);
	}
	
	public boolean getGeneratedReport(boolean fSubmitted) {
	
		boolean generateSuccess = false;
		String status = fSubmitted ? "Completed" : "Not Completed";
		PropertyReader props = new PropertyReader();
		String generateReportPath = props.getProperty("REPORTGENPATH");
		String fileName = generateReportPath+ File.separator +"report_"+ (fSubmitted ? "submitted" : "not-submitted" )+ ".xlsx";
		System.out.println("fileName:"+fileName);
		RegistrationDAO dao = new RegistrationDAO();
		
		ArrayList<Registration> studentList = dao.getStudentByFeeStatus(status);
		System.out.println("studentList:"+studentList);
		
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Report");
        int rowCount = 0;
        Row row;
        Cell cell;
        
        row = sheet.createRow(rowCount);
        cell = row.createCell(0);
        cell.setCellValue("Student Id");
        cell = row.createCell(1);
        cell.setCellValue("Student Name");
        cell = row.createCell(2);
        cell.setCellValue("Course");
        cell = row.createCell(3);
        cell.setCellValue("Semester");
        cell = row.createCell(4);
        cell.setCellValue("Submitted Fees");
        cell = row.createCell(5);
        cell.setCellValue("Submission Date");
        cell = row.createCell(6);
        cell.setCellValue("Fees Status");
        
        
        for (Registration r : studentList) {
            row = sheet.createRow(++rowCount);
            
                cell = row.createCell(0);
                cell.setCellValue(r.getId());
                cell = row.createCell(1);
                cell.setCellValue(r.getName());
                cell = row.createCell(2);
                cell.setCellValue(r.getCourseName());
                cell = row.createCell(3);
                cell.setCellValue(r.getSemName());
                cell = row.createCell(4);
                cell.setCellValue(r.getSfee());
                cell = row.createCell(5);
                cell.setCellValue(r.getFeeSubmissionDate());
                cell = row.createCell(6);
                cell.setCellValue(r.getFeeStatus());
             
        }
         
        try {
        	
        FileOutputStream outputStream = new FileOutputStream(fileName); 
            workbook.write(outputStream);
            
            workbook.close();
            generateSuccess = true;
            
        }catch(Exception e) {
        	e.printStackTrace();
        	return false;
        }
		
		return generateSuccess;
	}
	
	public static void main(String [] args) {
		new Status();
	}

}
