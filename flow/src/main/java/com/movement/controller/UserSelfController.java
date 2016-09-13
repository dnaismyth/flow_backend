package com.movement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movement.domain.RWorkout;
import com.movement.dto.User;
import com.movement.dto.Workout;
import com.movement.service.UserService;

/**
 * Controller used for the current logged in user to retrieve
 * their own information.
 * @author DN
 *
 */
@RestController
@RequestMapping("/me")
public class UserSelfController extends BaseUserController {

	/**
	 * Returns the current logged in user information.
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
    public User getMyProfile(){
    	return getLoggedInUser();
    }
	
	/**
	 * Returns all of the users workouts.
	 * @return
	 */
	@RequestMapping(value="/workouts", method = RequestMethod.GET)
	@ResponseBody
	public Page<Workout> getMyWorkouts(@RequestParam int page, int size){
		User current = getLoggedInUser();
		return workoutService.findAllWorkoutsByUser(current.getId(), new PageRequest(page, size));
	}
	
	/**
	 * Test Function to say hello to the current user
	 * @return
	 */
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	@ResponseBody
	public String sayHello(){
		//User current = getLoggedInUser();
		return "HELLO WORLD";
	}
	
	
}
