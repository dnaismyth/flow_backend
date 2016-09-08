package com.movement.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity to keep track of which users have favourited
 * other workouts posted.
 * @author DN
 *
 */
@Entity
@Table(name="workout_favourite")
public class RWorkoutFavourite extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6289177700944813577L;

	@Embedded
	private RWorkoutFavouritePK workoutFavouritePK;
	
	public RWorkoutFavouritePK getWorkoutFavouritePK(){
		return workoutFavouritePK;
	}
	
	public void setWorkoutFavouritePK(RWorkoutFavouritePK workoutFavouritePK){
		this.workoutFavouritePK = workoutFavouritePK;
	}
	
}
