package com.movement.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.movement.domain.RConfirmation;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.repository.ConfirmationRepository;

public class ConfirmationServiceTest extends TestBaseClass{
	
	@Autowired 
	private ConfirmationRepository confirmRepo;
	
	@Autowired
	private ConfirmationService confirmService;

	private User user;
	private String userName = "testConfirmUser@flow.com";
	private List<RConfirmation> confirmList = new ArrayList<RConfirmation>();
	
	@Before
	public void setUp() throws ResourceNotFoundException{
		user = userService.findUserByUsername(userName);
		if(user == null){
			user = new User();
			user.setName(userName);
			user.setUsername(userName);
			user.setEmail(userName);
			user.setPassword("test12");
			user.setUserRole(UserRole.USER);
			user = userService.create(user);
		}
	}
	
	@After
	public void tearDown() throws NoPermissionException{
		for(RConfirmation rc : confirmList){
			confirmRepo.delete(rc);
		}
		userService.delete(user, user.getId());
	}
	
	// Check that a confirmation instance + key is being created
	@Test
	public void testCreateConfirmationInstance(){
		confirmService.createUserConfirmationInstance(user.getId(), false);
		RConfirmation rc = confirmRepo.findByUserId(user.getId());
		confirmList.add(rc);
		Assert.assertNotNull(rc);
	}
	
}
