package com.movement.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;


public class Workout {

	private Long id;
	
	private BaseUser owner;
	
	private Date createdDate;
	
	/**
	 * Description/caption of the workout
	 */
	private String description;
	
	/**
	 * Image associated with workout
	 */
	private Media media;
	
	/**
	 * Privacy level of workout
	 */
	private ShowType showType;
	
	public Workout(){}
	
	public Workout(Long id, Date createdDate, String description, ShowType showType){
		this.id = id;
		this.createdDate = createdDate;
		this.description = description;
		this.showType = showType;
	}

	/**
	 * Stores the activites that were completed during the workout
	 */
	private List<Activity> activities;
	
	private String address;
	
	public List<Activity> getActivities(){
		return activities;
	}
	
	public void setActivities(List<Activity> activities){
		this.activities = activities;
	}
	
	public String getLocation(){
		return address;
	}
	
	public void setLocation(String address){
		this.address = address;
	}
	
	public BaseUser getOwner(){
		return owner;
	}
	
	public void setOwner(BaseUser owner){
		this.owner = owner;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public void setCreatedDate(Date createdDate){
		this.createdDate = createdDate;
	}
	
	public Date getCreatedDate(){
		return createdDate;
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
	
	public Media getMedia(){
		return media;
	}
	
	public void setMedia(Media media){
		this.media = media;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
}
