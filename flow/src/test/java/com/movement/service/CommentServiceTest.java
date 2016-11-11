package com.movement.service;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.movement.dto.Comment;
import com.movement.dto.User;
import com.movement.dto.Workout;
import com.movement.exception.BadRequestException;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;

public class CommentServiceTest extends TestBaseClass {

	@Autowired
	private CommentService commentService;

	private String userName1 = "commentservicetest@flow.com";
	private User user;


	@Before
	public void setUp() throws ResourceNotFoundException{
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
	public void tearDown() throws NoPermissionException{
		userService.delete(user, user.getId());
	}
	
	@Test
	public void testCreateWorkoutComment() throws ResourceNotFoundException, BadRequestException{
		String commentText = "Nict Job!";
		Workout w = createWorkout("20km", "2hours", user, null);
		Comment comment = new Comment(commentText);
		comment.setWorkoutId(w.getId());
		Comment workoutComment = commentService.createWorkoutComment(comment, user.getId());
		Assert.assertEquals(commentText, workoutComment.getCommentText());
	}
	
}
