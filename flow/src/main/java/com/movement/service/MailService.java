package com.movement.service;

import java.util.Locale;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.movement.domain.RUser;
import com.movement.dto.User;
import com.movement.repository.UserRepository;
import com.movement.util.RestPreconditions;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	private static final String BASE_URL = "http://localhost:8080/api/";
		
	private static final Logger logger = Logger.getLogger(UserService.class); 

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TemplateEngine emailTemplateEngine;
	
	/**
	 * Attempt to send a password reset e-mail to the provided address
	 * @param emailAddress
	 * @param user
	 * @throws MessagingException 
	 */
	@Async
	public void sendPasswordResetMail(String emailAddress, User user) throws MessagingException{
		RestPreconditions.checkNotNull(emailAddress);
		RestPreconditions.checkNotNull(user);
		RUser ru = userRepo.findOne(user.getId());
		ru.setResetPasswordKey(UUID.randomUUID().toString());
		userRepo.save(ru);
		
		Context ctx = new Context(Locale.ENGLISH);
		ctx.setVariable("name", ru.getName() != null ? ru.getName() : ru.getUsername());
		ctx.setVariable("url", BASE_URL + "resources/resetpassword/" + ru.getResetPasswordKey());
		
		String context = emailTemplateEngine.process("passwordreset", ctx);
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
		
		try {
			message.setFrom("");
			message.setTo("");
			message.setSubject("STRIVE. Password Reset");
			message.setText(context, true);
			
			mailSender.send(mimeMessage);
		} catch (MessagingException e1) {
			logger.debug(e1);
		}

	}
	
	@Async
	public void setConfirmationEmail(String emailAddress, User user){
		RestPreconditions.checkNotNull(emailAddress);
		RestPreconditions.checkNotNull(user);
		RUser ru = userRepo.findOne(user.getId());
	}
	
}
