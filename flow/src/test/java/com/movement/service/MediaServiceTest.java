package com.movement.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.movement.domain.RUser;
import com.movement.dto.Media;
import com.movement.dto.User;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;

public class MediaServiceTest extends TestBaseClass {

	private String userName1 = "testmedia@mediacenter.com";
	private User user1;
	
	@Before
	public void setUp() throws ResourceNotFoundException{
		user1 = userService.findUserByUsername(userName1);
		if(user1 == null){
			user1 = new User();
			user1.setEmail(userName1);
			user1.setUsername(userName1);
			user1.setPassword("test12");
			user1.setName("User 1");
			user1 = userService.create(user1);
		}
	}
	
	@After
	public void tearDown() throws NoPermissionException{
		if(user1.getId() != null)
			userService.delete(user1, user1.getId());
	}
	
	// Test that media is being created
	@Test
	public void testCreateMedia(){
		Media m = new Media();
		m.setCaption("Media caption");
		m.setFileName("filename");
		Media created = mediaService.createMedia(m,  user1);
		Assert.assertNotNull(created);
	}
}
