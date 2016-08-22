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
}
