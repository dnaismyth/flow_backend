package com.movement.dto;

import java.io.Serializable;
import java.util.Date;

import org.dom4j.tree.AbstractEntity;

/**
 * Base class for workout, parent class of workout types
 * @author DN
 *
 */
public class Activity {
	
	private static final long serialVersionUID = 4757259894011463240L;
	
	/**
	 * Amount of weight used for workout
	 */
	public String weight;
	
	
	/**
	 * Type of workout exercise
	 */
	public WorkoutType workoutType;
	

	public Activity(){}
	
	
	public String getWeight(){
		return weight;
	}
	
	public void setWeight(String weight){
		this.weight = weight;
	}
	
	public void setWorkoutType(WorkoutType workoutType){
		this.workoutType = workoutType;
	}
	
	public WorkoutType getWorkoutType(){
		return workoutType;
	}
	

}
