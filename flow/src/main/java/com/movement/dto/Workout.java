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
	
	private String address;
	
	private String distance;
	
	private String duration;
	
	public Workout(){}
	
	public Workout(Long id, Date createdDate, String description, ShowType showType){
		this.id = id;
		this.createdDate = createdDate;
		this.description = description;
		this.showType = showType;
	}
	
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
