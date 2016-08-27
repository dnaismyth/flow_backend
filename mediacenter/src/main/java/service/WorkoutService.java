package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repository.WorkoutRepository;
import service.mapper.ActivityMapper;
import service.mapper.UserMapper;
import service.mapper.WorkoutMapper;
import service.util.CompareUtil;
import util.RestPreconditions;
import dto.User;
import dto.Workout;
import entities.RWorkout;
import exception.ResourceNotFoundException;

/**
 * Service class for Workout objects
 * @author DN
 */
@Service
@Transactional
public class WorkoutService {

	@Autowired
	private WorkoutRepository workoutRepo;
	
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
		return workoutMapper.toWorkout(saved);
		
	}
	
	/**
	 * Update a previous workout
	 * @param workoutId
	 * @throws ResourceNotFoundException 
	 */
	public Workout updateWorkout(Long workoutId, Workout updated) throws IllegalArgumentException, ResourceNotFoundException  {
		RestPreconditions.checkNotNull(workoutId);
		RWorkout rw = workoutRepo.findOne(workoutId);
		
		// Cannot find the workout in the db
		if(rw == null){
			String message = String.format("Cannot find workout with id: %s", workoutId);
			throw new ResourceNotFoundException(message);
		}
		
		// Compare updated input workout to the one currently stored in the db,
		// if they are different then we should update and save differences. 
		if(!CompareUtil.compare(rw.getActivities(), updated.getActivities())){
			//TODO: update acitivty mapper for lists
		}
		
		if(!CompareUtil.compare(rw.getLocation(), updated.getLocation())){
			//TODO: create location mapper
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
	 */
	public boolean deleteWorkout(Long workoutId){
		RestPreconditions.checkNotNull(workoutId);
		//TODO: first delete dependencies, check user authority, delete workout references
		// (activity table)
		
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
}
