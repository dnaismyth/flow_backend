package com.movement.service.mapper;

import com.movement.domain.REvent;
import com.movement.dto.Event;

public class EventMapper {

	/**
	 * To event DTO
	 * @param r
	 * @return
	 */
	public Event toEvent(REvent r){
		
		Event e = null;
		if(r != null){
			e = new Event();
			e.setAddress(r.getAddress());
			e.setCreatedDate(r.getCreatedDate());
			e.setDescription(r.getDescription());
			e.setEventDate(r.getEventDate());
			e.setId(r.getId());
			e.setOwnerId(r.getOwnerId());
			e.setShowType(r.getShowType());
			e.setTitle(r.getTitle());
		}
		
		return e;
	}
	
	/**
	 * To Entity event
	 * @param e
	 * @return
	 */
	public REvent toREvent(Event e){
		REvent r = null;
		if(e != null){
			r = new REvent();
			r.setAddress(e.getAddress());
			r.setCreatedDate(e.getCreatedDate());
			r.setDescription(e.getDescription());
			r.setEventDate(e.getEventDate());
			r.setId(e.getId());
			r.setOwnerId(e.getOwnerId());
			r.setShowType(e.getShowType());
			r.setTitle(e.getTitle());
		}
		
		return r;
	}
	
}
