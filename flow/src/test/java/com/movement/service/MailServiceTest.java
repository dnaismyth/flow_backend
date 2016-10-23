package com.movement.service;

import javax.mail.MessagingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.movement.dto.User;
import com.movement.exception.ResourceNotFoundException;

@Ignore
public class MailServiceTest extends TestBaseClass {
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private ConfirmationService confirmService;
	
	private User user;
	
	@Before
	public void setUp() throws ResourceNotFoundException{
		
		user = userService.findUserByUsername("admin");
	}
	
	@After
	public void tearDown(){
		
	}
	
	@Test
	public void testSendPasswordResetEmail() throws MessagingException{
		mailService.sendPasswordResetMail(user.getEmail(), user);
	}
	
	@Test
	public void testSendConfirmationEmail() throws ResourceNotFoundException{
		confirmService.createUserConfirmationInstance(user.getId(), false);
		mailService.sendConfirmationEmail(user.getEmail(), user);
	}
}
