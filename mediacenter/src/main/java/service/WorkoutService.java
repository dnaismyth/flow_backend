package service;

import org.springframework.stereotype.Service;

import dto.Workout;

@Service
public class WorkoutService {

	
	/**
	 * Create a workout object
	 * @param workout
	 */
	public void createWorkout(Workout workout){
		//TODO: create a workout and save into db
	}
	
	/**
	 * Update a previous workout
	 * @param workoutId
	 */
	public void updateWorkout(Long workoutId){
		//TODO: find workout by id
	}
}
