package com.movement.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.movement.dto.WorkoutType;


//TODO: check to see query speed when there is an index on pk
@Embeddable
public class RActivity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2667842344285485383L;

	@Column(name="weight_amount")
	private String weight;
	
    @Enumerated(EnumType.STRING)
    @Column(name="workout_type")
    private WorkoutType workoutTypes;
	
	
	public void setWeight(String weight){
		this.weight = weight;
	}
	
	public String getWeight(){
		return weight;
	}
	
	public WorkoutType getWorkoutType(){
		return workoutTypes;
	}
	
	public void setWorkoutType(WorkoutType workoutTypes){
		this.workoutTypes = workoutTypes;
	}
	
	
	
}
