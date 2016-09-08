package com.movement.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Embeds into WorkoutFavourite to
 * Track users favourite workouts posted by
 * other users.
 * @author DN
 *
 */
@Embeddable
public class RWorkoutFavouritePK {
	@Column(nullable = false)
	private Long user_id;
	
	@Column(nullable = false)
	private Long workout_id;
	
	
	public void setUserId(Long user_id){
		this.user_id = user_id;
	}
	
	public Long getUserId(){
		return user_id;
	}
	
	public void setWorkoutId(Long workout_id){
		this.workout_id = workout_id;
	}
	
	public Long getWorkoutId(){
		return workout_id;
	}
}
