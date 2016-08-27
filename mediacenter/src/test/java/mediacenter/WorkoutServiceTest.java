package mediacenter;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repository.UserRepository;
import repository.WorkoutJDBCRepository;
import service.WorkoutService;
import service.mapper.UserMapper;
import config.MainApplication;
import dto.Activity;
import dto.User;
import dto.Workout;
import dto.WorkoutType;
import entities.RUser;
import exception.ResourceNotFoundException;

public class WorkoutServiceTest extends TestBaseClass {

	@Autowired
	private WorkoutJDBCRepository workoutJDBCRepo;
	
	private Workout testWorkout;
	
	private User user;
	private String userName1 = "testWorkoutService@mediacenter.com";
	
	private UserMapper userMapper = new UserMapper();
	
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
		
	}
	
	@After
	public void tearDown(){
		userService.delete(user.getId());
	}
	
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
	
	@Test
	public void testQuery(){
		workoutJDBCRepo.deleteWorkoutQueryReferences(1l);
	}
}
