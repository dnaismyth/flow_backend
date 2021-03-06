package com.movement.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.movement.dto.Media;
import com.movement.dto.User;
import com.movement.dto.Workout;
import com.movement.dto.WorkoutType;
import com.movement.exception.BadRequestException;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;

public class FeedServiceTest extends TestBaseClass {

	@Autowired
	private FeedService feedService;
	
	private String userName1 = "testFeedService@flow.com";
	private String userName2 = "testFeedService2@flow.com";
	
	private User user1, user2;
	
	private List<Workout> workouts;
	
	@Before
	public void setUp() throws ResourceNotFoundException{
		user1 = userService.findUserByUsername(userName1);
		if(user1 == null){
			user1 = new User();
			user1.setUsername(userName1);
			user1.setEmail(userName1);
			user1.setPassword("test1");
			user1.setName("Feed tester 1");
			user1 = userService.create(user1);
		}
		
		user2 = userService.findUserByUsername(userName2);
		if(user2 == null){
			user2 = new User();
			user2.setEmail(userName2);
			user2.setUsername(userName2);
			user2.setPassword("test2");
			user2.setName("Feed Tester 2");
			user2 = userService.create(user2);
		}
		
		workouts = new ArrayList<Workout>();
	}
	
	@After
	public void tearDown() throws ResourceNotFoundException, NoPermissionException{
		for(Workout w : workouts){
			workoutService.deleteWorkout(w.getOwner().getId(), w.getId());
		}
		userService.delete(user1, user1.getId());
		userService.delete(user2, user2.getId());
	}
	
	
	// Test that workouts are being shown in user feed
	@Test
	public void testFindWorkoutsForUserFeed() throws ResourceNotFoundException, BadRequestException{
		boolean followed = followService.followUser(user1.getId(), user2.getId());
		Assert.assertTrue(followed);
		Media m = createMedia(user2, "filename.jpg",  "testing");
		Workout w = createWorkout("20km", "1 hour", user2, null);
		w.setMedia(m);
		Workout updated = workoutService.updateWorkout(w.getId(), w);
		Page<Workout> output = feedService.findWorkoutsInUserFeed(user1.getId(), new PageRequest(0,5));
		Assert.assertEquals(1, output.getNumberOfElements());
	}
}
