package com.movement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Stores information on a workout event for others to see
 * and join.
 * @author DN
 *
 */
@Entity
@Table(name="event")
public class REvent extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3250128481277164398L;
	@Column(name="owner_id", nullable=false)
	private Long ownerId;
	
	public Long getOwnerId(){
		return ownerId;
	}
	
	public void setOwner(Long ownerId){
		this.ownerId = ownerId;
	}
	
}
