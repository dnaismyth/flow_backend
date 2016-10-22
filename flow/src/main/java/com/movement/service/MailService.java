package com.movement.service;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movement.domain.RUser;
import com.movement.dto.User;
import com.movement.repository.UserRepository;
import com.movement.util.RestPreconditions;

@Service
public class MailService {
	
	private static final String BASE_URL = "localhost:8080/";
	
	private static final Logger logger = Logger.getLogger(UserService.class); 

	@Autowired
	private UserRepository userRepo;
	
	/**
	 * Attempt to send a password reset e-mail to the provided address
	 * @param emailAddress
	 * @param user
	 */
	public void sendPasswordResetMail(String emailAddress, User user){
		RestPreconditions.checkNotNull(emailAddress);
		RestPreconditions.checkNotNull(user);
		RUser ru = userRepo.findOne(user.getId());
		ru.setResetPasswordKey(UUID.randomUUID().toString());
		userRepo.save(ru);
		try{
			//TODO: build message template and send to email address
			
		} catch (Exception e){
			logger.debug(e);
		}
	}

}
