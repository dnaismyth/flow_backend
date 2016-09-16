package com.movement.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.domain.RActivity;
import com.movement.domain.RWorkout;
import com.movement.dto.Activity;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.dto.Workout;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.repository.WorkoutJDBCRepository;
import com.movement.repository.WorkoutRepository;
import com.movement.service.mapper.ActivityMapper;
import com.movement.service.mapper.UserMapper;
import com.movement.service.mapper.WorkoutMapper;
import com.movement.service.util.CompareUtil;
import com.movement.util.RestPreconditions;


/**
 * Service class for Workout objects
 * @author DN
 */
@Service
@Transactional
public class WorkoutService {

	@Autowired
	private WorkoutRepository workoutRepo;
	
	@Autowired
	private WorkoutJDBCRepository workoutJDBCRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FeedService feedService;
	
	private WorkoutMapper workoutMapper = new WorkoutMapper();
	private UserMapper userMapper = new UserMapper();
	private ActivityMapper activityMapper = new ActivityMapper();
	
	/**
	 * Create a workout object
	 * @param workout
	 * @throws ResourceNotFoundException 
	 */
	public Workout createWorkout(User owner, Workout workout) throws IllegalArgumentException {
		RestPreconditions.checkNotNull(owner);
		RWorkout rw = workoutMapper.toEntityWorkout(workout);
		rw.setOwner(userMapper.toEntityUser(owner));
		RWorkout saved = workoutRepo.save(rw);
		sendWorkoutToFollowerFeeds(owner.getId(), saved);
		return workoutMapper.toWorkout(saved);
	}
	
	/**
	 * Update a previous workout
	 * @param workoutId
	 * @throws ResourceNotFoundException 
	 */
	@CacheEvict
	public Workout updateWorkout(Long workoutId, Workout updated) throws IllegalArgumentException, ResourceNotFoundException  {
		RestPreconditions.checkNotNull(workoutId);
		RWorkout rw = workoutRepo.findOne(workoutId);
		
		// Cannot find the workout in the db
		if(rw == null){
			String message = String.format("Cannot find workout with id: %s", workoutId);
			throw new ResourceNotFoundException(message);
		}

		if(!CompareUtil.compare(rw.getActivities(), updated.getActivities())){
			rw.setActivities(activityMapper.toEntityActivities(updated.getActivities()));
		}
		
		//TODO: fix mapper
		if(!CompareUtil.compare(rw.getLocation(), updated.getLocation())){
			//rw.setLocation(updated.getLocation());
		}
		
		if(!CompareUtil.compare(rw.getCreatedDate(), updated.getCreatedDate())){
			rw.setCreatedDate(updated.getCreatedDate());
		}
		
		RWorkout saved = workoutRepo.save(rw);
		return workoutMapper.toWorkout(saved);	
	}
	
	/**
	 * Delete a workout by provided id
	 * @param workoutId
	 * @return
	 * @throws ResourceNotFoundException 
	 * @throws NoPermissionException 
	 */
	public boolean deleteWorkout(User user, Long workoutId) throws ResourceNotFoundException, NoPermissionException{
		RestPreconditions.checkNotNull(workoutId);
		RestPreconditions.checkNotNull(user);
		RWorkout found = workoutRepo.findOne(workoutId);
		if(found == null){
			String message = String.format("Cannot find workout with provided id: " + workoutId);
			throw new ResourceNotFoundException(message);
		}
		
//		if(!found.getOwner().getId().equals(user.getId()) && user.getUserRole()!= UserRole.ADMIN){
//			throw new NoPermissionException("User does not have access to modify this content.");
//		}
		
		workoutJDBCRepo.deleteWorkoutQueryReferences(workoutId);
		workoutRepo.delete(workoutId);
		return true;
	}
	
	/**
	 * Find a workout by provided id
	 * @param workoutId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Workout findWorkoutById(Long workoutId) throws ResourceNotFoundException{
		RestPreconditions.checkNotNull(workoutId);
		RWorkout rw = workoutRepo.findOne(workoutId);
		if(rw == null){
			String message = String.format("Cannot find workout with provided id: %s", workoutId);
			throw new ResourceNotFoundException(message);
		}
		return workoutMapper.toWorkout(rw);
	}
	
	//TODO: map Page<RWorkout> to Page<Workout>, testing for now
	/**
	 * Return all of the user workouts (this needs to be updated so entity not being exposed)
	 * @param userId
	 * @param pageable
	 * @return
	 */
	@Cacheable
	public Page<Workout> findAllWorkoutsByUser(Long userId, Pageable pageable){
		RestPreconditions.checkNotNull(userId);
		Page<RWorkout> rw = workoutRepo.getAllUserWorkouts(userId, pageable);
		// Map to Page<Workout>
		return workoutMapper.toWorkoutDTOPage(rw);
	}
	
	/**
	 * Send the workout to feeds of those who are following the
	 * owner of the newly created workout
	 * @param workoutOwnerId
	 */
	private void sendWorkoutToFollowerFeeds(Long workoutOwnerId, RWorkout rw){
		List<Long> followerIds = userService.findFollowersByUserId(workoutOwnerId);
		feedService.addWorkoutToFeed(followerIds, rw);
	}
	
//	public Workout attachImageToWorkout(User user, Media media){
//		
//	}
}
