package mediacenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
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
import service.WorkoutService;
import service.mapper.UserMapper;
import config.MainApplication;
import dto.Activity;
import dto.User;
import dto.Workout;
import dto.WorkoutType;
import entities.RUser;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MainApplication.class)
public class WorkoutServiceTest {

	private Workout testWorkout;
	
	private RUser user;
	
	private UserMapper userMapper = new UserMapper();
	
	private List<Activity> activities;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private WorkoutService workoutService;
	
	private List<Long>userIds;
	
	@Before
	public void setUp(){
		activities = new ArrayList<Activity>();
		userIds = new ArrayList<Long>();
		
	}
	
	@After
	public void tearDown(){
		
		for(Long l : userIds){
			userRepo.delete(l);
		}
	}
	
	@Test
	public void testCreate(){
		Activity activity = new Activity();
		activity.setWorkoutType(WorkoutType.RUN);
		activities.add(activity);
		user = new RUser();
		user.setName("Dayna");
		user.setUsername("workouttest");
		user.setPassword("test12");
		user.setEmail("www.dayna.com");
		user.setId(5L);
		user = userRepo.save(user);
		userIds.add(user.getId());
		testWorkout = new Workout();
		testWorkout.setId(5L);
		testWorkout.setOwner(userMapper.toUser(user));
		testWorkout.setActivities(activities);
		workoutService.createWorkout(userMapper.toUser(user), testWorkout);
	}
}
