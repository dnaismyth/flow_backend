package com.movement.dto;

import java.util.Date;

/**
 * Workout containing extra information that should not be
 * exposed to a user upon Creating a workout. 
 * 
 * This class will be used for GET requests.  
 * 
 * @author DN
 *
 */
public class WorkoutInfo extends Workout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5388511201671478405L;

	private WorkoutStats stats;
	
	public WorkoutInfo(Long id, BaseUser owner, Date createdDate, String description, Media media,
			ShowType showType, String address, String distance, String duration){
		
		super(id, owner, createdDate, description, media, showType, address, distance, duration);
	}
	
	public void setStats(WorkoutStats stats){
		this.stats = stats;
	}

	public WorkoutStats getStats(){
		return stats;
	}
}
