package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import dto.WorkoutType;

//TODO: check to see query speed when there is an index on pk
@Entity
@Table(name="workout")
public class RWorkout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7649522972658785277L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="weight_amount")
	private String weight;
	
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
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
}
