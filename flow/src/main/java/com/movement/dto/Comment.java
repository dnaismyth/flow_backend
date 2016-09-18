package com.movement.dto;

import java.util.Date;

public class Comment {

	private Long commentId;
	
	private Date createdDate;
	
	private Long userId;
	
	private Long workoutId;
	
	public void setCommentId(Long commentId){
		this.commentId = commentId;
	}
	
	public Long getCommentId(){
		return commentId;
	}
	
	public void setCreatedDate(Date createdDate){
		this.createdDate = createdDate;
	}
	
	public Date getCreatedDate(){
		return createdDate;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getWorkoutId(){
		return workoutId;
	}
	
	public void setWorkoutId(Long workoutId){
		this.workoutId = workoutId;
	}
}
