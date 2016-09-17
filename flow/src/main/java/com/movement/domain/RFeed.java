package com.movement.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "feed")
public class RFeed {

	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * Feed belonging to this user
	 */
	@Column
	private Long userId;
	
	/**
	 * Workouts that will appear in the feed (posted by this user, and users who they follow)
	 */
	@Column
	@OneToMany
	private Collection<RWorkout> workout = new ArrayList<RWorkout>();
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Collection<RWorkout> getWorkouts(){
		return workout;
	}
	
	public void setWorkouts(Collection<RWorkout> workout){
		this.workout = workout;
	}
	
	
	
	
}
