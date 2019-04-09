package com.homedepot.hs.monitoring.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.util.Properties;



@Component("email")
public class EmailUtils {
	
	
	@Autowired
    private Environment env;

	@Autowired
	JavaMailSender mailSender;

	@Value("${monitoremail.sendTo:guruprasad_padmanabhan@homedepot.com}")
	String emailSenderList;

	@Value("${monitoremail.from:guruprasad_padmanabhan@homedepot.com}")
	String emailFrom;

	@Value("${monitoremail.subject:Attention! Home Depot Page}")
	String emailSubject;

	public void sendMail(String emailMessage) {
		String[] recipients = { "alert@homedepot.com","AHS_L3_Support@HomeDepot.onmicrosoft.com","Saleforce_Lead_Mgmt_IT@homedepot.com" };
		String from ="guruprasad_padmanabhan@homedepot.com";
		
		boolean debug = false;
		Properties props = new Properties();
		props.put("mail.smtp.host", "mail2.homedepot.com");
//		props.put("mail.smtp.auth", "true");
	
//		Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(debug);
		Message msg = new MimeMessage(session);
		try {
			InternetAddress addressFrom = new InternetAddress(from);
			msg.setFrom(addressFrom);
			InternetAddress[] addressTo = new InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++) {
				addressTo[i] = new InternetAddress(recipients[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, addressTo);
			msg.setSubject("Lead Management Application - Monitoring Alert !!!");
			msg.setContent(emailMessage, "text/html");
			Transport.send(msg);
		} catch (MessagingException me) {
			me.printStackTrace();
			
		}
	}
	/*
	public void sendMail(String emailMessage){
		mailSender.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, "UTF-8");
				message.setFrom(emailFrom);
				message.setTo(emailSenderList.split(","));
				message.setSubject(emailSubject);
				message.setPriority(1);
				message.setText(emailMessage, false);
			}
		});

	}*/



}
