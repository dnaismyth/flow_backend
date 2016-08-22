package dto;

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
	
	/**
	 * Activity within the workout
	 */
	public Workout workout;
	
	public Long id;
	
	public Date createdDate;

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
	
	public Workout getWorkout(){
		return workout;
	}
	
	public void setWorkout(Workout workout){
		this.workout = workout;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Date getCreatedDate(){
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate){
		this.createdDate = createdDate;
	}

}
