package com.movement.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Table to store quest information
 * @author DN
 *
 */
@Entity
@Table(name="quest")
public class RQuest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2425460056599031552L;

	/**
	 * Unique Id
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * Date of creation
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date", nullable = false)
	private Date createdDate;
	
	/**
	 * Start date of the quest
	 */
	@Column(name="start_time")
	private Date startTime;
	
	/**
	 * Quest expire time (eg: 10 days after joining quest)
	 */
	@Column(name="end_time")
	private Date endTime;
	
	@Column
	private String description;
	
	@Column
	private String title;
	
	/**
	 * Experience gained from completing the quest
	 */
	@Column
	private int experience;
	
	/**
	 * Quest objectives (eg: run 10k)
	 */
	@Embedded
	private RObjective objective;
	
	
	@PrePersist
    protected void onCreate() {
		createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
    	createdDate = new Date();
    }
    
	
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
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public RObjective getObjective(){
		return objective;
	}
	
	public void setObjective(RObjective objective){
		this.objective = objective;
	}
	
	public int getExperience(){
		return experience;
	}
	
	public void setExperience(int experience){
		this.experience = experience;
	}
    
    
}

