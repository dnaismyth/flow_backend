package com.movement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class RComment extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1046296817737983081L;

	/**
	 * User that has created the comment
	 */
	@Column(name="user_id")
	private Long userId;
	
	/**
	 * Workout that has been commented on
	 */
	@Column(name="workout_id")
	private Long workoutId;
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setWorkoutId(Long workoutId){
		this.workoutId = workoutId;
	}
	
	public Long getWorkoutId(){
		return workoutId;
	}
	
}
