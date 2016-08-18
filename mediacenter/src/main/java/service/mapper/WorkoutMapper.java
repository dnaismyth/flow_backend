package service.mapper;

import dto.Workout;
import entities.RWorkout;

public class WorkoutMapper {

	/**
	 * RWorkout to Workout
	 * @param rw
	 * @return
	 */
	public Workout toWorkout(RWorkout rw){
		
		Workout w = null;
		if(rw != null){
			w = new Workout();
			w.setId(rw.getId());
			w.setWeight(rw.getWeight());
			w.setWorkoutType(rw.getWorkoutType());
		}
		return w;
	}
	
	/**
	 * Workout to RWorkout
	 * @param w
	 * @return
	 */
	public RWorkout toEntityWorkout(Workout w){
		RWorkout rw = null;
		if(w != null){
			//rw.setCreatedDate(w.getWorkoutDate());
			rw.setId(w.getId());
			rw.setWeight(w.getWeight());
			rw.setWorkoutType(w.getWorkoutType());
		}
		
		return rw;
	}
	
	
}
