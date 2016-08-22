package mediacenter;

import java.util.Date;

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
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private WorkoutService workoutService;
	
	@Test
	public void testCreate(){
		Activity activity = new Activity();
		activity.setWorkoutType(WorkoutType.RUN);
		user = new RUser();
		user.setName("Dayna");
		user.setUsername("workouttest");
		user.setPassword("test12");
		user.setEmail("www.dayna.com");
		user.setId(5L);
		user = userRepo.save(user);
		testWorkout = new Workout();
		testWorkout.setId(6L);
		testWorkout.setOwner(userMapper.toUser(user));
		testWorkout.getActivities().add(activity);
		workoutService.createWorkout(userMapper.toUser(user), testWorkout);
	}
}
