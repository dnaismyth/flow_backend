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
import com.movement.domain.RWorkoutFavourite;
import com.movement.dto.BaseUser;
import com.movement.dto.User;
import com.movement.dto.Workout;
import com.movement.dto.WorkoutInfo;
import com.movement.dto.WorkoutType;
import com.movement.exception.BadRequestException;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.repository.UserRepository;
import com.movement.repository.WorkoutFavouriteRepository;
import com.movement.repository.WorkoutJDBCRepository;
import com.movement.service.WorkoutService;
import com.movement.service.mapper.UserMapper;


public class WorkoutServiceTest extends TestBaseClass {

	@Autowired
	private WorkoutJDBCRepository workoutJDBCRepo;
	
	@Autowired
	private WorkoutFavouriteRepository workoutFavRepo;
	
	private Workout testWorkout;
	
	private User user, user2;
	private String userName1 = "testworkoutService1@mediacenter.com";
	private String userName2 = "testWorkoutService2@mediacenter.com";
	
	private UserMapper userMapper = new UserMapper();
	//private LocationMapper locationMapper = new LocationMapper();
	
	
	private List<Long>userIds;
	
	private List<Workout> workouts;
	
	@Before
	public void setUp() throws ResourceNotFoundException{
		userIds = new ArrayList<Long>();
		workouts = new ArrayList<Workout>();
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
	public void tearDown() throws ResourceNotFoundException, NoPermissionException{
		for(Workout w : workouts){
			workoutService.deleteWorkout(w.getOwner().getId(), w.getId());
		}
		userService.delete(user, user.getId());
		userService.delete(user2, user2.getId());
	}
	
	// Test that a workout can be created
	@Test
	public void testCreate(){
		userIds.add(user.getId());
		testWorkout = new Workout();
		testWorkout.setOwner(user);
		testWorkout.setDistance("10km");
		testWorkout.setDuration("1 hour");
		Workout created = workoutService.createWorkout(user, testWorkout);
		workouts.add(created);
		Assert.assertNotNull(created);
		
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void testDeleteWorkout() throws ResourceNotFoundException, NoPermissionException{
		Workout w = createWorkout("10km", "1 hour", user2, null);
		Assert.assertNotNull(w);
		workoutService.deleteWorkout(user2.getId(), w.getId());
		workoutService.findWorkoutById(w.getId());	
	}
	
	// Check that a workout can be updated
	@Test
	public void testUpdateWorkout(){
		Workout w = createWorkout("10km", "1hour", user2, null);
		workouts.add(w);	
	}
	
	// Test that a workout location is being mapped as expected
	@Test
	public void testWorkoutLocation(){
		//RLocation location = locationMapper.toRLocation("Vancouver");
		//Assert.assertTrue(location.getAddress().equals("Vancouver"));
	}
	
	@Test
	public void testFindWorkoutsByOwnerId(){
		Workout w = createWorkout("20km", "2hours", user, null);
		workouts.add(w);
		Page<Workout> output = workoutService.findAllWorkoutsByUser(user.getId(), new PageRequest(0,5));
		Assert.assertEquals(1, output.getNumberOfElements());
	}
	
	// Check that a user can add a workout to their favourites
	@Test
	public void testAddWorkoutToFavourites() throws BadRequestException{
		Workout w = createWorkout("15km", "1 hour", user, null);
		workouts.add(w);
		userService.addWorkoutToFavourites(user2, w.getId());
		RWorkoutFavourite fav = workoutFavRepo.findByUserIdAndWorkoutId(w.getId(), user2.getId());
		Assert.assertNotNull(fav);	
	}
	
	// Check that workout stats are being updated
	@Test
	public void testFillWorkoutStats() throws BadRequestException, ResourceNotFoundException{
		Workout w = createWorkout("15km", "1 hr", user, null);
		workouts.add(w);
		userService.addWorkoutToFavourites(user2, w.getId());
		List<WorkoutInfo> workoutList = new ArrayList<WorkoutInfo>();
		WorkoutInfo wi = workoutService.findWorkoutById(w.getId());
		workoutList.add(wi);
		workoutService.exposeWorkoutStats(workoutList, user2);
		Assert.assertTrue(workoutList.get(0).getStats().isLiked());
	}
	
}
