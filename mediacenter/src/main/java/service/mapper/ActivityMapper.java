package service.mapper;

import dto.Activity;
import entities.RActivity;

public class ActivityMapper {

	private WorkoutMapper workoutMapper = new WorkoutMapper();
	
	/**
	 * Entity to DTO Activity
	 * @param ra
	 * @return
	 */
	public Activity toActivity(RActivity ra){
		
		Activity a = null;
		if(ra != null){
			a = new Activity();
			a.setWeight(ra.getWeight());
			a.setWorkoutType(ra.getWorkoutType());
			a.setWorkout(workoutMapper.toWorkout(ra.getWorkout()));
			a.setCreatedDate(ra.getCreatedDate());
			a.setId(ra.getId());
		}
		
		return a;
		
	}
	
	
	/**
	 * DTO Activity to Entity
	 * @param a
	 * @return
	 */
	public RActivity toEntityActivity(Activity a){
		RActivity ra = null;
		if(a != null){
			ra = new RActivity();
			ra.setWeight(a.getWeight());
			ra.setWorkoutType(a.getWorkoutType());
			ra.setWorkout(workoutMapper.toEntityWorkout(a.getWorkout()));
			ra.setCreatedDate(a.getCreatedDate());
			ra.setId(a.getId());
		}

		return ra;
	}
}
