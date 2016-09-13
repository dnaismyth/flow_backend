package com.movement.controller.dto;

import com.movement.dto.Operation;
import com.movement.dto.Workout;

/**
 * Workout Response for WorkoutController
 * @author DN
 *
 */
public class WorkoutResponse {
	
	private Workout workout;
	private Operation op;
	private Long id;
	
	public WorkoutResponse (Workout workout, Operation op){
		this.workout = workout;
		this.op = op;
	}
	
	public WorkoutResponse(Operation op, Long id){
		this.op = op;
		this.id = id;
	}
	
	public WorkoutResponse(Workout workout){
		this.workout = workout;
	}
	
	public Workout getWorkout(){
		return workout;
	}
	
	public void setWorkout(Workout workout){
		this.workout = workout;
	}
	
	public Operation getOperation(){
		return op;
	}
	
	public void setOperation(Operation op){
		this.op = op;
	}
}
