package com.movement.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amazonaws.util.DateUtils;
import com.movement.dto.Event;
import com.movement.dto.Location;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;

public class EventServiceTest extends TestBaseClass {

	private User user1;
	private String userName1 = "eventTest@flow.com";
	private Date date;
	private List<Long> eventIds;
	
	@Before
	public void setUp() throws ResourceNotFoundException {
		user1 = userService.findUserByUsername(userName1);
		if (user1 == null) {
			user1 = new User();
			user1.setEmail(userName1);
			user1.setUsername(userName1);
			user1.setPassword("test12");
			user1.setName("User 1");
			user1.setUserRole(UserRole.USER);
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
		userService.delete(user1, user1.getId());
	}
	
	@Test
	public void testCreateEvent() throws ResourceNotFoundException{
		Event e = createEvent(user1, "Testing create event", date, "address");
		eventIds.add(e.getId());
		Assert.assertNotNull(e);
	}
	
	@Test
	public void testDeleteEvent() throws NoPermissionException, ResourceNotFoundException{
		Event e = createEvent(user1, "Testing delete event", date, "3134 address");
		Assert.assertNotNull(e);
		boolean deleted = eventService.deleteEvent(e.getId(), user1.getId());
		Assert.assertTrue(deleted);
	}
	
	@Test
	public void testFindEventById() throws ResourceNotFoundException{
		Event e = createEvent(user1, "Testing find event", date, "32525 address");
		Assert.assertNotNull(e);
		Event found = eventService.getEvent(e.getId());
		eventIds.add(e.getId());
		Assert.assertEquals(e.getId(), found.getId());
	}
	
	@Test
	public void testUpdateEvent() throws NoPermissionException, ResourceNotFoundException{
		Event e = createEvent(user1, "Testing update event", date, "my address");
		Assert.assertNotNull(e);
		String title = "my updated title";
		String description = "my updated description";
		e.setTitle(title);
		e.setDescription(description);
		Event updated = eventService.updateEvent(e, user1);
		eventIds.add(updated.getId());
		Assert.assertEquals(title, updated.getTitle());
		Assert.assertEquals(description, updated.getDescription());
	}
	
}
