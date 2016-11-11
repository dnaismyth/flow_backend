package com.movement.service;


import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.movement.dto.Comment;
import com.movement.dto.User;
import com.movement.dto.Workout;
import com.movement.exception.BadRequestException;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;

public class CommentServiceTest extends TestBaseClass {

	@Autowired
	private CommentService commentService;
	
	private List<Long> commentIds;

	private String userName1 = "commentservicetest@flow.com";
	private User user;


	@Before
	public void setUp() throws ResourceNotFoundException{
		commentIds = new ArrayList<Long>();
		user = userService.findUserByUsername(userName1);
		if(user == null){
			user = new User();
			user.setName("Test workout service");
			user.setUsername(userName1);
			user.setPassword("test12");
			user.setEmail(userName1);
			user = userService.create(user);
		}
	}

	@After
	public void tearDown() throws NoPermissionException, ResourceNotFoundException{
		for(Long id : commentIds){
			commentService.deleteWorkoutComment(id, user.getId());
		}
		userService.delete(user, user.getId());
	}
	
	// Test to check that a workout comment can be created
	@Test
	public void testCreateWorkoutComment() throws ResourceNotFoundException, BadRequestException{
		String commentText = "Nict Job!";
		Workout w = createWorkout("20km", "2hours", user, null);
		Comment comment = new Comment(commentText);
		comment.setWorkoutId(w.getId());
		Comment workoutComment = commentService.createWorkoutComment(comment, user.getId());
		commentIds.add(workoutComment.getCommentId());
		Assert.assertEquals(commentText, workoutComment.getCommentText());
	}
	
	// Test that all comments are being returned for a workout
	@Test
	public void testGetAllWorkoutComments() throws ResourceNotFoundException, BadRequestException{
		Workout w = createWorkout("10km", "2Hours", user, null);
		Comment comment = new Comment("Testing get all comments for workout");
		comment.setWorkoutId(w.getId());
		commentService.createWorkoutComment(comment,  user.getId());
		Page<Comment> allComments = commentService.getAllCommentsForWorkout(w.getId(), new PageRequest(0,5));
		Assert.assertTrue(allComments.getNumberOfElements() == 1);
	}
	
}
