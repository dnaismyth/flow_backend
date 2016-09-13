package com.movement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movement.controller.dto.UserResponse;
import com.movement.controller.dto.WorkoutResponse;
import com.movement.domain.RWorkout;
import com.movement.dto.Operation;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.dto.Workout;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
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
	 * @throws NoPermissionException 
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
    public User getMyProfile() throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
    	return getLoggedInUser();
    }
	
	/**
	 * Update the current user profile
	 * @param id
	 * @param updated
	 * @return
	 * @throws IllegalArgumentException
	 * @throws ResourceNotFoundException
	 * @throws NoPermissionException
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public UserResponse updateMyProfile(@RequestBody User updated) throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		// Check that only the current user can update their own profile
		if(!(updated.getId().equals(user.getId()) && user.getUserRole() != UserRole.ADMIN)){
			throw new NoPermissionException("Only the owner can update their own profile.");
		}
		User u = userService.updateUser(updated);
		return new UserResponse(u, Operation.UPDATE);
	}
	
	/**
	 * User option to delete their account
	 * @param id
	 * @return
	 * @throws NoPermissionException
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public UserResponse deleteProfile() throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		userService.delete(user.getId());
		return new UserResponse(Operation.DELETE, user.getId());
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
