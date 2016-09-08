package com.movement.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.movement.domain.RUser;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.dto.Workout;
import com.movement.exception.ResourceNotFoundException;


public class UserServiceTest extends TestBaseClass {

	private String userName1 = "testuser@mediacenter.com";
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
	public void tearDown(){
		//userService.delete(user1.getId());
	}
	
	// Test that a user is being stored in the db
	@Test
	public void testCreateUser(){
		RUser ru = userRepo.findOne(user1.getId());
		Assert.assertEquals(ru.getId(), user1.getId());
	}
	
	// Check that a user can be found with their username
	@Test
	public void testFindByUsername() throws ResourceNotFoundException{
		User found = userService.findUserByUsername(userName1);
		Assert.assertEquals(found.getId(), user1.getId());
	}

}
