package com.movement.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class REventInterestPK {
	
	@Column(nullable = false)
	private Long user_id;
	
	@Column(nullable = false)
	private Long event_id;
	
	
	public void setUserId(Long user_id){
		this.user_id = user_id;
	}
	
	public Long getUserId(){
		return user_id;
	}
	
	public void setEventId(Long event_id){
		this.event_id = event_id;
	}
	
	public Long getWorkoutId(){
		return event_id;
	}
}
