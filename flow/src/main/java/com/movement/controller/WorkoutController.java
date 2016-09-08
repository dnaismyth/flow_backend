package com.movement.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movement.dto.User;
import com.movement.dto.Workout;

@RestController
@RequestMapping("/workout")
public class WorkoutController extends BaseController {
	
	@RequestMapping(method = RequestMethod.POST)
	public Workout createWorkout(@RequestBody Workout workout){
		
		User user = getLoggedInUser();
		return workoutService.createWorkout(user, workout);
	}
}
