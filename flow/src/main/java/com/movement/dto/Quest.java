package com.movement.dto;

import java.util.Date;

/**
 * DTO For quest objects, used to store
 * basic quest information.
 * @author DN
 *
 */
public class Quest {

	private Long id;
	private Date createdDate;
	private Date startTime;
	private Date endTime;
	private String title;
	private String description;
	private int experience;
	private Objective objective;
	private Status questStatus;
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Date getCreatedDate(){
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate){
		this.createdDate = createdDate;
	}
	
	public Date getStartTime(){
		return startTime;
	}
	
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	public Date getEndTime(){
		return endTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
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
	
	public int getExperience(){
		return experience;
	}
	
	public void setExperience(int experience){
		this.experience = experience;
	}
	
	public Objective getObjective(){
		return objective;
	}
	
	public void setObjective(Objective objective){
		this.objective = objective;
	}
	
	public Status getStatus(){
		return questStatus;
	}
	
	public void setStatus(Status questStatus){
		this.questStatus = questStatus;
	}
}
