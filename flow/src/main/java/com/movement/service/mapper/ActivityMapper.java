package com.movement.service.mapper;

import java.util.ArrayList;
import java.util.List;

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
	
	/**
	 * List<Activity> to List<RActivity>
	 * @param activities
	 * @return
	 */
	public List<RActivity> toEntityActivities(List<Activity> activities){
		List<RActivity> rActivities = new ArrayList<RActivity>();
		for(Activity a : activities){
			rActivities.add(toEntityActivity(a));
		}
		return rActivities;
	}
}
