package com.movement.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movement.controller.dto.RestResponse;
import com.movement.controller.dto.SimpleResponse;
import com.movement.dto.Operation;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.dto.Workout;
import com.movement.exception.BadRequestException;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/workouts")
public class WorkoutController extends BaseController {
	
	/**
	 * Create a new workout, set the owner to the current user
	 * @param workout
	 * @return
	 * @throws NoPermissionException 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public RestResponse<Workout> createWorkout(@RequestBody Workout workout) throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		Workout created = workoutService.createWorkout(user, workout);
		return new RestResponse<Workout>(Operation.CREATE, created);
	}
	
	/**
	 * Find a workout by the provided id
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 * @throws NoPermissionException
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public RestResponse<Workout> getWorkout(@PathVariable Long id) throws ResourceNotFoundException, NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		Workout workout = workoutService.findWorkoutById(id);
		return new RestResponse<Workout>(workout);
	}
	
	/**
	 * Update a workout if the currently logged in user is the owner
	 * @param id
	 * @param updated
	 * @return
	 * @throws IllegalArgumentException
	 * @throws ResourceNotFoundException
	 * @throws NoPermissionException
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public RestResponse<Workout> updateWorkout(@RequestBody Workout updated) throws IllegalArgumentException, ResourceNotFoundException, NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		// Check that only the current logged in user or an admin can update a workout
		if(!(updated.getOwner().getId().equals(user.getId()) && user.getUserRole() != UserRole.ADMIN)){
			throw new NoPermissionException("Only the owner can update this workout.");
		}
		Workout w = workoutService.updateWorkout(updated.getId(), updated);
		return new RestResponse<Workout>(Operation.UPDATE, w);
	}
	
	/**
	 * Delete a workout if workout.owner = current logged in user
	 * @return
	 * @throws NoPermissionException
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public RestResponse<Workout> deleteWorkout(@PathVariable Long id) throws NoPermissionException, ResourceNotFoundException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		workoutService.deleteWorkout(user.getId(), id);
		return new RestResponse<Workout>(Operation.DELETE, id);
	}
	
	/**
	 * Favourite a workout
	 * @param id (workout id)
	 * @return
	 * @throws NoPermissionException
	 * @throws BadRequestException
	 */
	@RequestMapping(value="/{id}/likes", method = RequestMethod.POST)
	public SimpleResponse likeWorkout(@PathVariable Long id) throws NoPermissionException, BadRequestException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		boolean added = workoutFavService.addFavourite(id, user);
		if(added){
			return new SimpleResponse(Operation.ADD);
		}
		else
			return new SimpleResponse(Operation.NO_CHANGE);
		
	}
	
	/**
	 * Remove a workout from favourites
	 * @param id (workout id)
	 * @return
	 * @throws NoPermissionException
	 */
	@RequestMapping(value="/{id}/likes", method = RequestMethod.DELETE)
	public SimpleResponse unlikeWorkout(@PathVariable Long id) throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		boolean removed = workoutFavService.deleteFavorite(id, user);
		if(removed){
			return new SimpleResponse(Operation.DELETE);
		}
		else
			return new SimpleResponse(Operation.NO_CHANGE);
	}
}

