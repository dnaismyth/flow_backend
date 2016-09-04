package com.movement.service.mapper;

import com.movement.domain.RActivity;
import com.movement.dto.Activity;

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
			a.setWorkoutType(ra.getWorkoutType());
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
		}

		return ra;
	}
}
