package com.movement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.movement.dto.TextLength;

@Entity
@Table(name="comment")
public class RComment extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1046296817737983081L;

	/**
	 * User that has created the comment
	 */
	@Column(name="user_id")
	private Long userId;
	
	/**
	 * Workout that has been commented on
	 */
	@Column(name="workout_id")
	private Long workoutId;
	
	@Column(name="text", length = TextLength.COMMENT_TEXT)
	private String commentText;
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setWorkoutId(Long workoutId){
		this.workoutId = workoutId;
	}
	
	public Long getWorkoutId(){
		return workoutId;
	}
	
	public void setCommentText(String commentText){
		this.commentText = commentText;
	}
	
	public String getCommentText(){
		return commentText;
	}
	
}
