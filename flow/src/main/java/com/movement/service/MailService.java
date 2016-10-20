package com.movement.service;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movement.domain.RUser;
import com.movement.dto.User;
import com.movement.repository.UserRepository;
import com.movement.util.RestPreconditions;

@Service
public class MailService {
	
	@Autowired
	private UserRepository userRepo;
	
	public void sendPasswordResetMail(String emailAddress, User user){
		RestPreconditions.checkNotNull(emailAddress);
		RestPreconditions.checkNotNull(user);
		//TODO:
	}
	
	private static String uuidToBase64(String str) {
	    Base64 base64 = new Base64();
	    UUID uuid = UUID.fromString(str);
	    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
	    bb.putLong(uuid.getMostSignificantBits());
	    bb.putLong(uuid.getLeastSignificantBits());
	    return base64.encodeBase64URLSafeString(bb.array());
	}
	private static String uuidFromBase64(String str) {
	    Base64 base64 = new Base64(); 
	    byte[] bytes = base64.decodeBase64(str);
	    ByteBuffer bb = ByteBuffer.wrap(bytes);
	    UUID uuid = new UUID(bb.getLong(), bb.getLong());
	    return uuid.toString();
	}

}
