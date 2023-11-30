package com.sendEmail.sendEmailContext.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sendEmail.sendEmailContext.Entity.EmailDetails;
import com.sendEmail.sendEmailContext.Service.EmailService;


@RestController
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/sendMail")
	public String sendMail(@RequestBody EmailDetails details) {
		String status = emailService.sendEmail(details);
		return status;
		
	}

}
