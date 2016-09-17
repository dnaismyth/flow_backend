package com.movement.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "feed")
public class RFeed implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7919770747320936711L;
	
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Feed belonging to this user
	 */
	@OneToOne
	private RUser user;
	
	/**
	 * Workouts that will appear in the feed (posted by this user, and users who they follow)
	 */
	@OneToMany(fetch = FetchType.LAZY)
	private List<RWorkout> workout = new ArrayList<RWorkout>();
	
	public RFeed(){}
	
	public RFeed(RUser user){
		this.user = user;
	}
	
	public void addWorkoutToCollection(RWorkout rw){
		this.workout.add(rw);
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public RUser getUserId(){
		return user;
	}
	
	public void setUser(RUser user){
		this.user = user;
	}
	
	public List<RWorkout> getWorkouts(){
		return workout;
	}
	
	public void setWorkouts(List<RWorkout> workout){
		this.workout = workout;
	}
	
	
	
	
}
