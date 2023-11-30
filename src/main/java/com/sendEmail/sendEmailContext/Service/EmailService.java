package com.sendEmail.sendEmailContext.Service;

import com.sendEmail.sendEmailContext.Entity.EmailDetails;

public interface EmailService {
	String sendEmail(EmailDetails details);	
}
