package com.proj.fees.controller;

import java.awt.Container;
import java.awt.GridLayout;

import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.*;

import com.proj.fees.dao.RegistrationDAO;
import com.proj.fees.model.Registration;
import com.proj.fees.utility.MailServer;


public class SubmitFees extends JFrame {
	private JTextField tsid,subDate,dueDate,tFee,fine,sFee,sName;
	private JButton bsubmit;
	//  private JComboBox day,month,year;
	  private JLabel l1,l2,l3,l4,l5,l6,l7;
	  
	
	public SubmitFees(Registration r) {
		super("Fees Submission");
		Container c=getContentPane();
		  c.setLayout(new GridLayout(8,2));
		  tsid=new JTextField(20);
		  tsid.setText(r.getId());
		  tsid.setEditable(false);
		  
		  sName=new JTextField(20);
		  sName.setText(r.getName());
		  sName.setEditable(false);
		  
		  subDate = new JTextField(20);
		  subDate.setText(r.getFeeSubmissionDate());
		  subDate.setEditable(false);
		  
		  dueDate=new JTextField(20);
		  dueDate.setText(r.getDueDate());
		  dueDate.setEditable(false);
		  
		  tFee=new JTextField(20);
		  tFee.setText(r.getTfee());
		  tFee.setEditable(false);
		  
		  fine=new JTextField(20);
		  fine.setText(r.getFine());
		  fine.setEditable(false);
		  
		  sFee=new JTextField(20);
		  sFee.setText(r.getSfee());
		  sFee.setEditable(false);
		  
		  l1=new JLabel("Student ID");
		  l7=new JLabel("Student Name");
		  l2=new JLabel("Submission Date ");
		  l3=new JLabel("Due Date");
		  l4=new JLabel("Semestar Fee");
		  l5=new JLabel("Fine");
		  l6=new JLabel("Total Amount");
		  bsubmit=new JButton("Submit");
		  
		  
		  c.add(l1);c.add(tsid);
		  c.add(l7);c.add(sName);
		  c.add(l2);c.add(subDate);
		  c.add(l3);c.add(dueDate);
		  c.add(l4);c.add(tFee);
		  c.add(l5);c.add(fine);
		  c.add(l6);c.add(sFee);
		  c.add(bsubmit);
		  
		  
		     setSize(350, 200);
			 setLocation(100,100);
			 setResizable(false);
			 
			 setVisible(true);
			 
			 bsubmit.addActionListener(e->{
				 int record = new RegistrationDAO().setFeesTransaction(r);
				 System.out.println(record+ " record has been updated");
				 
				 if(record>0) {
					 String message = "Hi "+ r.getName() +",\n" + "Fees " + r.getSfee() +
							  " has been submitted for " + r.getSemName();
					 JOptionPane.showMessageDialog(this, "Fees submitted successfully");
					 dispose();
					 try {
						new MailServer().sendMail(r.getEmailid(), "Fees Submission", message);
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
				 else {
					 JOptionPane.showMessageDialog(this, "Fees not submitted");
				 }
			 });
			 
		  
		    
		
		
	}
	
	public static void main(String [] args) {
		//new SubmitFees(" ");
	}

}
