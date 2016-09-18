package com.movement.service.mapper;

import com.movement.domain.RComment;
import com.movement.dto.Comment;

public class CommentMapper {

	/**
	 * To Entity comment
	 * @param c
	 * @return
	 */
	public RComment toRComment(Comment c){
		RComment rc = null;
		if(c != null){
			rc = new RComment();
			rc.setCreatedDate(c.getCreatedDate());
			rc.setUserId(c.getUserId());
			rc.setWorkoutId(c.getWorkoutId());
			rc.setId(c.getCommentId());
		}
		
		return rc;
	}
	
	/**
	 * To DTO Comment
	 * @param rc
	 * @return
	 */
	public Comment toComment(RComment rc){
		Comment c = null;
		if(rc != null){
			c = new Comment();
			c.setCreatedDate(rc.getCreatedDate());
			c.setUserId(rc.getUserId());
			c.setWorkoutId(rc.getWorkoutId());
			c.setCommentId(rc.getId());
		}
		
		return c;
	}
}
