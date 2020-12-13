package com.proj.fees.controller;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.proj.fees.properties.PropertyReader;


public class AdminLogin extends JFrame
	{
		  private JLabel l1,l2;
		  private JTextField tid;
		  private JPasswordField tpass;
		  private JButton blogin;
		  Container c; 
    
 public AdminLogin(String title)
		  {
			  super(title);
		  
			  c =getContentPane();
			  c.setLayout(new GridLayout(3,2));
			  
			  
			  l1=new JLabel("Enter User Id");
			  l2=new JLabel("Enter Password");
			  
			//bresetpassword=new JButton("Forget password");		
			  tid=new JTextField(20);
			  tpass=new JPasswordField(20);
			  blogin =new JButton("LOGIN");
			  blogin.addActionListener(
				(e) ->
	            {
	            	
	           	    loginCheck();
	            }
	            );
			 
			  c.add(l1);c.add(tid);
			  c.add(l2);c.add(tpass);
			  c.add(blogin);//c.add(bresetpassword);	

			   setSize(550, 150);
			   setLocation(200,200);
			   setResizable(false);
			   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			   setVisible(true);	
		   }
		  

	
		private void loginCheck() 
		{
			PropertyReader prop = new PropertyReader();
			String appAdminUser = prop.getProperty("APP_ADMIN_USER_NAME");
			String appAdminPassword = prop.getProperty("APP_ADMIN_PASSWORD");
			String id=tid.getText().trim();
			String pass=tpass.getText().trim();
			
			
			
			if(id.equals(appAdminUser) && pass.equals(appAdminPassword))
			{
				
				super.setVisible(false);//??????
				 new AdminAfterLogin("ADMIN AFTER LOGIN...");
				 
			}
			else
			{
				 JOptionPane.showMessageDialog( this, "LOGIN UNSUCCESSFUL...");
				 tid.setText("");//???????
				 tpass.setText("");//?????
				    
			}
		 }
			
		public static void main(String[] args) 
		{
			new AdminLogin("Administrator Login");
			 
	    }

}
	
