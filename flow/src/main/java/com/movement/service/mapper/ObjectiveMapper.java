package com.movement.service.mapper;

import com.movement.domain.RObjective;
import com.movement.dto.Objective;

/**
 * Mapper for quest objectives
 * @author DN
 *
 */
public class ObjectiveMapper {

	/**
	 * To DTO Objective
	 * @param ro
	 * @return
	 */
	public Objective toObjective(RObjective ro){
		Objective o = null;
		if(ro != null){
			o = new Objective();
			o.setMeasureUnit(ro.getMeasureUnit());
			o.setObjectiveType(ro.getObjectiveType());
			o.setTargetValue(ro.getTargetValue());
		}
		
		return o;
	}
	
	/**
	 * To Entity Objective
	 * @param o
	 * @return
	 */
	public RObjective toRObjective(Objective o){
		RObjective ro = null;
		if(o != null){
			ro = new RObjective();
			ro.setMeasureUnit(o.getMeasureUnit());
			ro.setObjectiveType(o.getObjectiveType());
			ro.setTargetValue(o.getTargetValue());
		}
		
		return ro;
	}
}
