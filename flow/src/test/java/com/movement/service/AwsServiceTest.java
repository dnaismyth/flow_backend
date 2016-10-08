package com.movement.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.dto.Workout;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;

@Ignore
public class AwsServiceTest extends TestBaseClass{

	@Autowired
	private AwsS3Service service;

	private User user;
	private String userName1 = "awsServiceTest@flow.com";

	@Before
	public void setUp() throws ResourceNotFoundException {
		
		user = userService.findUserByUsername(userName1);
		if (user == null) {
			user = new User();
			user.setName("Test workout service");
			user.setUsername(userName1);
			user.setPassword("test12");
			user.setEmail(userName1);
			user.setUserRole(UserRole.USER);
			user = userService.create(user);
		}
	}
	
	@After
	public void tearDown() throws ResourceNotFoundException, NoPermissionException{
		//userService.delete(user, user.getId());
	}

	@Test
	public void testS3Upload() {
		String uploadFile = "C:/Users/dayna/Pictures/IMG_4424.jpg";
		// String fileName = "IMG_4424.jpg";
		service.uploadFile(user, uploadFile);
	}

	
}
