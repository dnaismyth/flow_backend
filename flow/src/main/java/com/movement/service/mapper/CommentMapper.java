package com.movement.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.movement.domain.RComment;
import com.movement.dto.Comment;

public class CommentMapper {

	/**
	 * To Entity comment
	 * 
	 * @param c
	 * @return
	 */
	public RComment toRComment(Comment c) {
		RComment rc = null;
		if (c != null) {
			rc = new RComment();
			rc.setCreatedDate(c.getCreatedDate());
			rc.setUserId(c.getUserId());
			rc.setWorkoutId(c.getWorkoutId());
			rc.setId(c.getCommentId());
			rc.setCommentText(c.getCommentText());
		}

		return rc;
	}

	/**
	 * To DTO Comment
	 * 
	 * @param rc
	 * @return
	 */
	public Comment toComment(RComment rc) {
		Comment c = null;
		if (rc != null) {
			c = new Comment();
			c.setCreatedDate(rc.getCreatedDate());
			c.setUserId(rc.getUserId());
			c.setWorkoutId(rc.getWorkoutId());
			c.setCommentId(rc.getId());
			c.setCommentText(rc.getCommentText());
		}

		return c;
	}

	/**
	 * To comment list
	 * @param rcomment
	 * @return
	 */
	public List<Comment> toCommentList(List<RComment> rcomment) {
		List<Comment> comments = null;
		if (rcomment != null) {
			comments = new ArrayList<Comment>();
			for (RComment rc : rcomment) {
				comments.add(toComment(rc));
			}
		}
		return comments;
	}
}
