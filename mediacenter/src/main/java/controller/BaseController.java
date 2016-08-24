package controller;

import org.springframework.beans.factory.annotation.Autowired;

import repository.WorkoutRepository;
import service.WorkoutService;

public class BaseController {

	@Autowired
	protected WorkoutService workoutService;
	
	@Autowired
	protected WorkoutRepository workoutRepo;
}
