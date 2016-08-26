package mediacenter;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repository.UserRepository;
import repository.WorkoutRepository;
import service.UserService;
import service.WorkoutService;
import config.MainApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MainApplication.class)
public class TestBaseClass {
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected UserRepository userRepo;
	
	@Autowired
	protected WorkoutService workoutService;
	
	@Autowired
	protected WorkoutRepository workoutRepo;

}
