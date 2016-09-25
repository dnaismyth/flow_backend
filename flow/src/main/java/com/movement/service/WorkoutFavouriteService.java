package com.movement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movement.domain.RWorkout;
import com.movement.domain.RWorkoutFavourite;
import com.movement.domain.RWorkoutFavouritePK;
import com.movement.dto.User;
import com.movement.exception.BadRequestException;
import com.movement.repository.WorkoutFavouriteRepository;
import com.movement.repository.WorkoutRepository;
import com.movement.util.RestPreconditions;

/**
 * Service class created for the purpose of allowing a user to
 * add or remove a workout from their favourites.
 * @author DN
 *
 */
@Service
public class WorkoutFavouriteService {

	@Autowired
	private WorkoutFavouriteRepository workoutFavRepo;
	
	@Autowired
	private WorkoutRepository workoutRepo;
	
	@Autowired
	private NotificationService notifyService;
	
	/**
	 * Allow a user to like/add workout to favourites
	 * @param workoutId
	 * @param user
	 * @return
	 * @throws BadRequestException
	 */
	public boolean addFavourite(Long workoutId, User user) throws BadRequestException{
		RestPreconditions.checkNotNull(workoutId);
		RestPreconditions.checkNotNull(user);
		
		// Check that the workout is not already favourited
		RWorkoutFavourite wf = workoutFavRepo.findByUserIdAndWorkoutId(workoutId, user.getId());
		if(wf != null){
			throw new BadRequestException("You have already favourited this workout.");
		}
		
		RWorkoutFavouritePK pk = new RWorkoutFavouritePK();
		pk.setUserId(user.getId());
		pk.setWorkoutId(workoutId);
		wf = new RWorkoutFavourite();
		wf.setWorkoutFavouritePK(pk);
		workoutFavRepo.save(wf);
		
		// Send notification that someone has liked their workout
		RWorkout rw = workoutRepo.findOne(workoutId);
		notifyService.createWorkoutFavouriteNotification(rw.getId());
		return true;		
	}
	
	/**
	 * Allow a user to unlike/remove workout from their favourites
	 * @param workoutId
	 * @param user
	 * @return
	 */
	public boolean deleteFavorite(Long workoutId, User user){
		RestPreconditions.checkNotNull(workoutId);
		RestPreconditions.checkNotNull(user);
		
		RWorkoutFavourite wf = workoutFavRepo.findByUserIdAndWorkoutId(workoutId, user.getId());
		if(wf != null){
			workoutFavRepo.delete(wf);
			return true;
		}
		else
			return false; // 
	}
}
