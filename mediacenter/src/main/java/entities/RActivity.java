package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import dto.WorkoutType;

//TODO: check to see query speed when there is an index on pk
@Entity
@Table(name="activity")
public class RActivity extends BaseEntity{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2667842344285485383L;

	@Column(name="weight_amount")
	private String weight;
	
	@Column(name="workout", nullable=false)
	private RWorkout workout;
	
    @Enumerated(EnumType.STRING)
    @Column(name="workout_type")
    private WorkoutType workoutType;
	
	
	public void setWeight(String weight){
		this.weight = weight;
	}
	
	public String getWeight(){
		return weight;
	}
	
	public WorkoutType getWorkoutType(){
		return workoutType;
	}
	
	public void setWorkoutType(WorkoutType workoutType){
		this.workoutType = workoutType;
	}
	
	public RWorkout getWorkout(){
		return workout;
	}
	
	public void setWorkout(RWorkout workout){
		this.workout = workout;
	}
	
	
}
