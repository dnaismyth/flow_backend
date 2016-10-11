package com.movement.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.movement.dto.ShowType;
import com.movement.dto.TextLength;
import com.movement.dto.UserRole;


@Entity
@Table(name = "workout", indexes = {
		@Index(columnList = "owner_id", name = "idx_owner_id_workout")
})
public class RWorkout extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1195501156843452445L;
	
	@ManyToOne
	private RUser owner;
	
	@Column(name="description", length=TextLength.DESCRIPTION)
	private String description;
	
	//@Cascade{{CascadeType.DELETE}}
	@OneToOne(cascade = CascadeType.REMOVE)
	private RMedia media;
	
	@Column(name="distance", nullable = false)
	private String distance;
	
	@Column(name = "duration", nullable = false)
	private String duration;
	
	@Embedded
	private RLocation location;
	
	@Enumerated(EnumType.STRING)
	@Column(name="showtype")
	private ShowType showType;
	
	public String getDistance(){
		return distance;
	}
	
	public void setDistance(String distance){
		this.distance = distance;
	}
	
	public String getDuration(){
		return duration;
	}
	
	public void setDuration(String duration){
		this.duration = duration;
	}
	
	public RUser getOwner(){
		return owner;
	}
	
	public void setOwner(RUser owner){
		this.owner = owner;
	}
	
	public RLocation getLocation(){
		return location;
	}
	
	public void setLocation(RLocation location){
		this.location = location;
	}
	
	public RMedia getMedia(){
		return media;
	}
	
	public void setMedia(RMedia media){
		this.media = media;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public ShowType getShowType(){
		return showType;
	}
	
	public void setShowType(ShowType showType){
		this.showType = showType;
	}
}