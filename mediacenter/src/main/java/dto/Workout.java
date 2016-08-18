package dto;

import java.io.Serializable;
import java.util.Date;

import org.dom4j.tree.AbstractEntity;

/**
 * Base class for workout, parent class of workout types
 * @author DN
 *
 */
public class Workout implements Serializable {
	
	private static final long serialVersionUID = 4757259894011463240L;

	/**
	 * Unique workout id
	 */
	public Long id;
	
	/**
	 * Owner of the workout
	 */
	public User owner;
	
	/**
	 * Amount of weight used for workout
	 */
	public String weight;
	
	/**
	 * Date of workout
	 */
	public Date workoutDate;
	
	/**
	 * Type of workout exercise
	 */
	public WorkoutType workoutType;

	public Workout(){}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
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
	
	public Date getWorkoutDate(){
		return workoutDate;
	}
	
	public void setWorkoutDate(Date workoutDate){
		this.workoutDate = workoutDate;
	}
	
	public User getOwner(){
		return owner;
	}
	
	public void setOwner(User owner){
		this.owner = owner;
	}
}
