package com.movement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.movement.dto.Event;
import com.movement.dto.User;
import com.movement.exception.BadRequestException;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;

public class EventInterestServiceTest extends TestBaseClass {
	
	@Autowired
	private EventInterestService eiService;
	
	private User user1;
	private String userName1 = "eventInterestTester@flow.com";
	private Event event1;
	private List<Long> eventIds;
	private Date date;
	
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
		
		date = new Date();
		eventIds = new ArrayList<Long>();
		
	}
	
	@After
	public void tearDown() throws NoPermissionException, ResourceNotFoundException{
		
		for(Long l : eventIds){
			eventService.deleteEvent(l, user1.getId());
		}
		
		userService.delete(user1.getId());
	}
	
	//Check that an event can be added to a user's interests
	@Test
	public void testAddEventAsInterest() throws ResourceNotFoundException, BadRequestException{
		Event e = createEvent(user1, "Interest test", date, "Vancouver");
		boolean added = eiService.addEventToInterests(e.getId(), user1);
		Assert.assertTrue(added);
		
	}
	
	// Check that a user can remove an event from their interests
	@Test
	public void testRemoveEventAsInterest() throws ResourceNotFoundException, BadRequestException{
		Event e = createEvent(user1, "Interest test to remove", date, "Vancouver");
		boolean added = eiService.addEventToInterests(e.getId(), user1);
		Assert.assertTrue(added);
		
		boolean removed = eiService.removeEventFromInterests(e.getId(), user1);
		Assert.assertTrue(removed);
	}
}
