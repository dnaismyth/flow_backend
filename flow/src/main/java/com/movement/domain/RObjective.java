package com.movement.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.movement.dto.MeasurementUnit;
import com.movement.dto.ObjectiveType;

/**
 * Table to store quests that will involve
 * running objectives. (Ex: Objective to run 10k)
 * @author DN
 *
 */
@Embeddable
public class RObjective {

	/**
	 * Target value will represent the value the user is expected to
	 * reach in order to complete the objective/quest
	 */
	@Column(name="target_value")
	private int targetValue;
	
	/**
	 * The type of objective: distance, duration, elevation etc.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="objective_type")
	private ObjectiveType objectiveType;
	
	/**
	 * The type of unit used to describe the target value
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="unit_of_measurement")
	private MeasurementUnit measureUnit;
	
	public int getTargetValue(){
		return targetValue;
	}
	
	public void setTargetValue(int targetValue){
		this.targetValue = targetValue;
	}
	
	public ObjectiveType getObjectiveType(){
		return objectiveType;
	}
	
	public void setObjectiveType(ObjectiveType objectiveType){
		this.objectiveType = objectiveType;
	}
	
	public MeasurementUnit getMeasureUnit(){
		return measureUnit;
	}
	
	public void setMeasureUnit(MeasurementUnit measureUnit){
		this.measureUnit = measureUnit;
	}
}
