package com.movement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.movement.domain.RLocation;
import com.movement.dto.Activity;
import com.movement.dto.User;
import com.movement.dto.Workout;
import com.movement.dto.WorkoutType;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.repository.UserRepository;
import com.movement.repository.WorkoutJDBCRepository;
import com.movement.service.WorkoutService;
import com.movement.service.mapper.UserMapper;


public class WorkoutServiceTest extends TestBaseClass {

	@Autowired
	private WorkoutJDBCRepository workoutJDBCRepo;
	
	private Workout testWorkout;
	
	private User user, user2;
	private String userName1 = "testworkoutService1@mediacenter.com";
	private String userName2 = "testWorkoutService2@mediacenter.com";
	
	private UserMapper userMapper = new UserMapper();
	//private LocationMapper locationMapper = new LocationMapper();
	
	private List<Activity> activities;
	
	private List<Long>userIds;
	
	@Before
	public void setUp() throws ResourceNotFoundException{
		activities = new ArrayList<Activity>();
		userIds = new ArrayList<Long>();
		user = userService.findUserByUsername(userName1);
		if(user == null){
			user = new User();
			user.setName("Test workout service");
			user.setUsername(userName1);
			user.setPassword("test12");
			user.setEmail(userName1);
			user = userService.create(user);
		}
		
		user2 = userService.findUserByUsername(userName2);
		if(user2 == null){
			user2 = new User();
			user2.setName("Test workout service 2");
			user2.setUsername(userName2);
			user2.setPassword("test12");
			user2.setEmail(userName2);
			user2 = userService.create(user2);
		}
		
	}
	
	@After
	public void tearDown(){
		userService.delete(user.getId());
		userService.delete(user2.getId());
	}
	
	// Test that a workout can be created
	@Test
	public void testCreate(){
		Activity activity = new Activity();
		activity.setWorkoutType(WorkoutType.RUN);
		activities.add(activity);
		userIds.add(user.getId());
		testWorkout = new Workout();
		testWorkout.setOwner(user);
		testWorkout.setActivities(activities);
		Workout created = workoutService.createWorkout(user, testWorkout);
		Assert.assertNotNull(created);
		
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void testDeleteWorkout() throws ResourceNotFoundException, NoPermissionException{
		Activity activity = new Activity();
		activity.setWorkoutType(WorkoutType.DEADLIFT);
		activities.add(activity);
		Workout w = createWorkout(activities, user2, null);
		Assert.assertNotNull(w);
		workoutService.deleteWorkout(user2, w.getId());
		workoutService.findWorkoutById(w.getId());
		
		
	}
	
	// Check that a workout can be updated
	@Test
	public void testUpdateWorkout(){
		Activity activity = new Activity();
		activity.setWorkoutType(WorkoutType.DEADLIFT);
		activities.add(activity);
		Workout w = createWorkout(activities, user2, null);
		
		
	}
	
	// Test that a workout location is being mapped as expected
	@Test
	public void testWorkoutLocation(){
		//RLocation location = locationMapper.toRLocation("Vancouver");
		//Assert.assertTrue(location.getAddress().equals("Vancouver"));
	}
	
	@Test
	public void testFindWorkoutsByOwnerId(){
		Activity a = new Activity();
		a.setWorkoutType(WorkoutType.RUN);
		activities.add(a);
		Workout w = createWorkout(activities, user, null);
		Page<Workout> output = workoutService.findAllWorkoutsByUser(user.getId(), new PageRequest(0,5));
		Assert.assertEquals(1, output.getNumberOfElements());
	}
	
	private Workout createWorkout(List<Activity> activities, User owner, String location){
		Workout workout = new Workout();
		workout.setActivities(activities);
		workout.setLocation(location);
		return workoutService.createWorkout(owner, workout);
	}
	
	
}
