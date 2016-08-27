package dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;


public class Workout {

	private Long id;
	
	private User owner;
	
	private Date createdDate;
	
	/**
	 * Description/caption of the workout
	 */
	private String description;
	
	/**
	 * Image associated with the workout
	 */
	private Media media;
	
	
	/**
	 * Stores the activites that were completed during the workout
	 */
	private List<Activity> activities;
	
	private Location location;
	
	public List<Activity> getActivities(){
		return activities;
	}
	
	public void setActivities(List<Activity> activities){
		this.activities = activities;
	}
	
	public Location getLocation(){
		return location;
	}
	
	public void setLocation(Location location){
		this.location = location;
	}
	
	public User getOwner(){
		return owner;
	}
	
	public void setOwner(User owner){
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
	
	public Media getMedia(){
		return media;
	}
	
	public void setMedia(Media media){
		this.media = media;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
}
