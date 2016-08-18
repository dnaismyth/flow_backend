package mediacenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import service.WorkoutService;
import config.MainApplication;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MainApplication.class)
public class WorkoutServiceTest {

	@Autowired
	private WorkoutService workoutService;
	
	@Test
	public void testCreate(){
		workoutService.getClass();
	}
}
