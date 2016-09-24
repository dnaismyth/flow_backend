package com.movement.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.movement.dto.ShowType;
import com.movement.dto.TextLength;

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
	
	@Column(name="title", length = TextLength.TITLE, nullable = false)
	private String title;
	
	/**
	 * Address of the event
	 */
	@Embedded
	private RLocation location;
	
	/**
	 * Date in which the event is planned for
	 */
	@Column(name="start_date", nullable=false)
	private Date eventDate;
	
	@Column(name="description", length = TextLength.DESCRIPTION)
	private String description;
	
	/**
	 * Visibility of event to other users
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="showtype")
	private ShowType showType;
	
	public Long getOwnerId(){
		return ownerId;
	}
	
	public void setOwnerId(Long ownerId){
		this.ownerId = ownerId;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public RLocation getLocation(){
		return location;
	}
	
	public void setLocation(RLocation location){
		this.location = location;
	}
	
	public ShowType getShowType(){
		return showType;
	}
	
	public void setShowType(ShowType showType){
		this.showType = showType;
	}
	
	public Date getEventDate(){
		return eventDate;
	}
	
	public void setEventDate(Date eventDate){
		this.eventDate = eventDate;
	}
	
	
	
	
	
}
