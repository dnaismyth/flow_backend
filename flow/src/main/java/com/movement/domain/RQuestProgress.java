package com.movement.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Table to store the user's current quest progress
 * Eg: 10km / 15km completed
 * @author DN
 *
 */
@Entity
@Table(name = "quest_progress")
public class RQuestProgress extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7200971599806736015L;

	@Embedded
	private RUserQuestPK userQuestPK;
	
	/**
	 * User's current quest progression in percentage,
	 * initial value will be set to 0
	 */
	@Column(name="percent_complete")
	private float percentComplete = 0;
	
	/**
	 * flag to store whether the user has completed the quest
	 * initial value = false
	 */
	@Column(name="completed")
	private boolean completed = false;
	
	public RUserQuestPK getUserQuestPK(){
		return userQuestPK;
	}
	
	public void setUserQuestPK(RUserQuestPK userQuestPK){
		this.userQuestPK = userQuestPK;
	}
	
	public float getPercentCompleted(){
		return percentComplete;
	}
	
	public void setPercentComplete(float percentComplete){
		this.percentComplete = percentComplete;
	}
	
	public boolean isCompleted(){
		return completed;
	}
	
	public void setCompleted(boolean completed){
		this.completed = completed;
	}
	
	
}
