package com.incapp.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.incapp.dao.AdminDao;

public class sentMail {
	public static String SendMail(String remail,String sub,String body ){
		try {
			AdminDao d=new AdminDao();
			String adminDetais[]=d.getAdminDetails();
			String senderMail=adminDetais[0];
			String senderPassword=adminDetais[1];
			Properties prop = new Properties();  
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.port", "587");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.starttls.enable", "true");
			
			Session ses = Session.getInstance(prop,  
		    new javax.mail.Authenticator() {  
		      protected PasswordAuthentication getPasswordAuthentication() {  
		    return new PasswordAuthentication(senderMail,senderPassword);  
		      }  
		    });
			
			Message message=new MimeMessage(ses);
            message.setFrom(new InternetAddress(senderMail));
            message.setRecipients(Message.RecipientType.TO, 
            		InternetAddress.parse(remail));
            message.setSubject(sub);
            message.setContent(body,"text/html" );
            
            Transport.send(message);
            return "Mail Send Success!";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Mail Send Failed!";
			
		}
	}

}
