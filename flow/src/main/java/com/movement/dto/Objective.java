package com.movement.dto;

/**
 * Quest objectives
 * @author DN
 *
 */
public class Objective {
	
	/**
	 * Value the user will be expected to reach to complete the quest/objective
	 */
	private int targetValue;
	
	/**
	 * Type of objective (eg: distance, elevation etc..)
	 */
	private ObjectiveType objectiveType;
	
	/**
	 * Unit of measurement used to describe the targetValue (km, miles etc..)
	 */
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

