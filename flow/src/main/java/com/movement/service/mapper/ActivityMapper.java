package com.movement.service.mapper;

import com.movement.domain.RActivity;
import com.movement.dto.Activity;
import com.movement.dto.WorkoutType;

public class ActivityMapper {
	
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
			a.setWorkoutType(WorkoutType.valueOf(ra.getWorkoutType().toString()));
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
			ra.setWorkoutType(WorkoutType.valueOf(a.getWorkoutType().toString()));
		}

		return ra;
	}
}
