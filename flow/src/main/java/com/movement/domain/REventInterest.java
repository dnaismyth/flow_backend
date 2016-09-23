package com.movement.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Table used to store the primay keys of an event and user id.
 * This relationship will represent the event in which a user is interested
 * in attending.
 * @author DN
 *
 */
@Entity
@Table(name="event_interest")
public class REventInterest extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -690065762209919202L;
	@Embedded
	private REventInterestPK eventInterestPK;
	
	public REventInterestPK getEventInterestPK(){
		return eventInterestPK;
	}
	
	public void setEventInterestPK(REventInterestPK eventInterestPK){
		this.eventInterestPK = eventInterestPK;
	}
}
