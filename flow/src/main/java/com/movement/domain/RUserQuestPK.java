package com.movement.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Store primary keys of user and quests
 * in which the user is currently participating in
 * @author DN
 *
 */
@Embeddable
public class RUserQuestPK {
	
	@Column(name = "user_id", nullable = false)
	private Long userId;
	
	@Column(name = "quest_id", nullable = false)
	private Long questId;
	
	public Long getUserId(){
		return userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getQuestId(){
		return questId;
	}
	
	public void setQuestId(Long questId){
		this.questId = questId;
	}
}
