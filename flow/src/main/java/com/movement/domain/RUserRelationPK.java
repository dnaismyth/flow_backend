package com.movement.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * Embedded into RFollow to hold the relationship between two users
 * @author DN
 */
@Embeddable
public class RUserRelationPK {

	@Column(nullable = false)
	private Long user_id;
	
	@Column(nullable = false)
	private Long target_id;
	
	public void setUserId(Long user_id){
		this.user_id = user_id;
	}
	
	public Long getUserId(){
		return user_id;
	}
	
	public void setTargetId(Long target_id){
		this.target_id = target_id;
	}
	
	public Long getFollowingId(){
		return target_id;
	}
	
}
