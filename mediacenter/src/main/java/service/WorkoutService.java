package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.WorkoutRepository;
import service.mapper.UserMapper;
import service.mapper.WorkoutMapper;
import dto.User;
import dto.Workout;
import entities.RWorkout;

@Service
public class WorkoutService {

	@Autowired
	private WorkoutRepository workoutRepo;
	
	private WorkoutMapper workoutMapper = new WorkoutMapper();
	private UserMapper userMapper = new UserMapper();
	/**
	 * Create a workout object
	 * @param workout
	 */
	public void createWorkout(User owner, Workout workout){
		
		RWorkout rw = workoutMapper.toEntityWorkout(workout);
		rw.setOwner(userMapper.toEntityUser(owner));
		workoutRepo.save(rw);
		
	}
	
	/**
	 * Update a previous workout
	 * @param workoutId
	 */
	public void updateWorkout(Long workoutId){
		//TODO: find workout by id
	}
}
