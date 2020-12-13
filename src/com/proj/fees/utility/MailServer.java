package com.proj.fees.utility;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.proj.fees.properties.PropertyReader;

public class MailServer {
    PropertyReader prop = new PropertyReader();
	public MailServer(){
			
	}
	
	public void sendMail(String recepient, String subjectLine, String messageContent) throws MessagingException{
		
		System.out.println("recepient:"+recepient+";subjectLine:"+subjectLine+";messageContent:"+messageContent);
		System.out.println("preparing to send mail");
		Properties props = new Properties();
		String myAccEmail = prop.getProperty("ADMINEMAILID");
		String myAccPassword = prop.getProperty("ADMINEMAILPASSWORD");
		
		System.out.println("myAccEmail:"+myAccEmail+";myAccPassword:"+myAccPassword);
		
		
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(myAccEmail,myAccPassword);  
         }    
        });    
		
        System.out.println("session:"+session);
		Message message = prepareMessage(session,myAccEmail,recepient,subjectLine,messageContent);
		System.out.println("message:"+message);
		Transport.send(message);
		System.out.println("message send successfully");
	}

	private Message prepareMessage(Session session, String myAccEmail,String recepient, String subjectLine, String messageContent) {
		// TODO Auto-generated method stub
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myAccEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject(subjectLine);
			message.setText(messageContent);
			return message;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
