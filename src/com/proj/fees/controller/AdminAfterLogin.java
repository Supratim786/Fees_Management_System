package com.proj.fees.controller;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;



public class AdminAfterLogin extends JFrame
{
	private JButton baddstudent,bviewall,baddfees,bdelete,bviewfees,fstudent,treport,fstructure;;
	    
public AdminAfterLogin(String title)
{
	super(title) ;
	Container c=getContentPane();
	  c.setLayout(new GridLayout(2,2));
		baddstudent =new JButton("Add Student");
		bviewall=new JButton("View All Student");
		baddfees=new JButton("Add Fees");
		bdelete=new JButton("Delete Student");
		bviewfees=new JButton("View Fees");
		fstudent=new JButton("Find Student");
		treport=new JButton("Report Generation");
		 fstructure=new JButton("Fees Structure");
		
		
		
		
		c.add(baddfees);
		baddfees.addActionListener(
				(e) ->
                {
       	          new StudentFind();
       	        }
        );
		
		
		c.add(fstudent);
		fstudent.addActionListener(e->{
			
			
			new StudentDetails();
			
			
		});
		c.add(treport);
		
		treport.addActionListener(e->{
			
			
			new Status();
			
			
		});
		
		c.add(fstructure);
		fstructure.addActionListener(e->{
			  
			  new Course();
		  });
		
		
		
		
	setSize(500,300);
	 setLocation(200,200);
	 setResizable(false);
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 setVisible(true);	
}  
			    



public static void main(String[] args) {
	new AdminAfterLogin("AdminAfterLogin.....");
	

	}

}
