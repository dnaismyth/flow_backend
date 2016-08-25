package controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dto.Workout;
import entities.RWorkout;

@RestController
@RequestMapping(value="/api/workout")
public class WorkoutController extends BaseController {

	@RequestMapping(value="/?id=", method=RequestMethod.GET)
	
	public RWorkout get(@PathVariable Long id) {

		return workoutRepo.findOne(id);
	}
	
}
