package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
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
	public void createWorkout(User owner, Workout workout) throws IllegalArgumentException {
		RestPreconditions.checkNotNull(owner);
		RWorkout rw = workoutMapper.toEntityWorkout(workout);
		rw.setOwner(userMapper.toEntityUser(owner));
		workoutRepo.save(rw);
		
	}
	
	/**
	 * Update a previous workout
	 * @param workoutId
	 * @throws ResourceNotFoundException 
	 */
	public Workout updateWorkout(Long workoutId, Workout updated) throws IllegalArgumentException, ResourceNotFoundException  {
		RestPreconditions.checkNotNull(workoutId);
		RWorkout rw = workoutRepo.findOne(workoutId);
		if(rw == null){
			String message = String.format("Cannot find workout with id: %s", workoutId);
			throw new ResourceNotFoundException(message);
		}
		
		if(!CompareUtil.compare(rw.getActivities(), updated.getActivities())){
			//TODO: update acitivty mapper for lists
		}
		
		if(!CompareUtil.compare(rw.getLocation(), updated.getLocation()){
			//TODO: create location mapper
			//rw.setLocation(updated.getLocation());
		}
		
		if(!CompareUtil.compare(rw.getCreatedDate(), updated.getCreatedDate())){
			rw.setCreatedDate(updated.getCreatedDate());
		}
		
		RWorkout saved = workoutRepo.save(rw);
		return workoutMapper.toWorkout(saved);	
	}
	
	public boolean deleteWorkout(Long workoutId){
		RestPreconditions.checkNotNull(workoutId);
		//TODO: first delete dependencies
		workoutRepo.delete(workoutId);
		return true;
	}
	
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
