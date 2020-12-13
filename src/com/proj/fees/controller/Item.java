package com.proj.fees.controller;

import javax.swing.*;

import com.proj.fees.model.Registration;

import java.awt.*;
import java.util.*;

public class Item extends JFrame {
	
	private JTextField tid,tname,temailid,tmobno,tgender,tfee,subfee,fine,subDate,tfeestatus,tadd,tdept,tsem,tsec;
	private JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14;
	 
	 private ArrayList<Registration> userlist;
	 private Registration r;
	 
	 public Item(Registration r) {
		 
		 
		 super("Student Details");
		 
		 System.out.println("r:"+r);
		 Container c=getContentPane();
		 c.setLayout(new GridLayout(14,2));
		 
		 tid=new JTextField(20);
		 tid.setText(r.getId());
		 tid.setEditable(false);
		 
		 tname=new JTextField(20);
		 tname.setText(r.getName());
		 tname.setEditable(false);
		 
		 temailid=new JTextField(20);
		 temailid.setText(r.getEmailid());
		 temailid.setEditable(false);
		 
		 tmobno=new JTextField(20);
		 tmobno.setText(r.getMobno());
		 tmobno.setEditable(false);
		 
		 tgender=new JTextField(20);
		 tgender.setText(r.getGender());
		 tgender.setEditable(false);
		 
		 tdept=new JTextField(20);
		 tdept.setText(r.getCourseName());
		 tdept.setEditable(false);
		 
		 tsem=new JTextField(20);
		 tsem.setText(r.getSemName());
		 tsem.setEditable(false);
		  
		 tsec=new JTextField(20);
		 tsec.setText(r.getSec());
		 tsec.setEditable(false);
		 
		 tfee=new JTextField(20);
		 tfee.setText(r.getTfee());
		 tfee.setEditable(false);
		  
		 fine = new JTextField(20);
		 fine.setText(r.getFine());
		 fine.setEditable(false);
		 
		 subfee=new JTextField(20);
		 subfee.setText(r.getSfee());
		 subfee.setEditable(false);
		  
		 subDate=new JTextField(20);
		 subDate.setText(r.getFeeSubmissionDate());
		 subDate.setEditable(false);
		  
		 tfeestatus=new JTextField(20);
		 tfeestatus.setText(r.getFeeStatus());
		 tfeestatus.setEditable(false);
		    
		 tadd=new JTextField(20);
		 tadd.setText(r.getAddress());
		 tadd.setEditable(false);
		  
		  
		  l1=new JLabel("Student Id");
		  l2=new JLabel("Name");
		  l3=new JLabel("Emailid");
		  l4=new JLabel("Mobile No");
		  
		  l5=new JLabel("Gender");
		  l6=new JLabel("Department");
		  l7=new JLabel("Semester");
		  
		  l8=new JLabel("Section");
		  l9=new JLabel("Admission Fee");
		  l10=new JLabel("Fees Submit");
		  l11=new JLabel("Submission Date");
		  l12=new JLabel("Fees status");
		  l13=new JLabel("Home address");
		  l14=new JLabel("Fine");
		  
		  c.add(l1);c.add(tid);
		  c.add(l2);c.add(tname);
		  c.add(l3);c.add(temailid);
		  c.add(l4);c.add(tmobno);
		  c.add(l5);c.add(tgender);
		  c.add(l6);c.add(tdept);
		  c.add(l7);c.add(tsem);
		  c.add(l8);c.add(tsec);
		  c.add(l9);c.add(tfee);
		  c.add(l14);c.add(fine);
		  c.add(l10);c.add(subfee);
		  c.add(l11);c.add(subDate);
		  c.add(l12);c.add(tfeestatus);
		  c.add(l13);c.add(tadd);
		  
		/*
		 * c.add(l14);c.add(country); c.add(l13);c.add(city); c.add(l12);c.add(tadd);
		 */
		  
		  	 setSize(450, 600);
			 setLocation(200,200);
			 setResizable(false);
			 //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 setVisible(true);
	
	 	}
	 public static void main(String args[]) {
		 
		 //new Item();
	 }
}
