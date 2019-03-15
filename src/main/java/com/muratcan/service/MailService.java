package com.muratcan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.muratcan.notalma.HomeController;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;
	
	public void registerMail(String mail, String key) {
			
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("muratcan.gokyokus@gmail.com");
		email.setTo(mail);
		email.setSubject("Üyeliği Tamamla..");
		email.setText("Üyeliği tamamlamak için aşağıdaki linke tıklayınız.\n\n" +HomeController.url+"/notalma/reg/"+key);
		mailSender.send(email);
	}

}
