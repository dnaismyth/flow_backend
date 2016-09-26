package com.movement.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.movement.dto.ObjectiveType;

/**
 * Table to store quests that will involve
 * running objectives. (Ex: Objective to run 10k)
 * @author DN
 *
 */
@Embeddable
public class RObjective {

	@Column(name="target_value")
	private int targetValue;
	
	@Enumerated(EnumType.STRING)
	@Column(name="objective_type")
	private ObjectiveType objective;
	
	public int getTargetValue(){
		return targetValue;
	}
	
	public void setTargetValue(int targetValue){
		
	}
}
