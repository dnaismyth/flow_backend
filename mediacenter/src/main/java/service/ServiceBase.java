package service;

import org.springframework.beans.factory.annotation.Autowired;

import repository.UserRepository;
import repository.WorkoutRepository;

public class ServiceBase {

	@Autowired
	protected UserService userService;
	
	@Autowired
	protected UserRepository userRepo;
	
	@Autowired
	protected WorkoutRepository workoutRepo;
	
	@Autowired
	protected WorkoutService workoutService;
}
