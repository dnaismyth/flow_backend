package com.movement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movement.controller.dto.ResponseList;
import com.movement.controller.dto.RestResponse;
import com.movement.controller.dto.SimpleResponse;
import com.movement.dto.Comment;
import com.movement.dto.Event;
import com.movement.dto.Operation;
import com.movement.dto.User;
import com.movement.dto.Workout;
import com.movement.exception.BadRequestException;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController extends BaseController {

	@Autowired
	private CommentService commentService;
	
	/**
	 * Create a new workout comment
	 * @param comment
	 * @return
	 * @throws NoPermissionException
	 * @throws ResourceNotFoundException
	 * @throws BadRequestException 
	 */
	@RequestMapping(value="/workout/{workoutId}", method = RequestMethod.POST)
	public RestResponse<Comment> createWorkoutComment(@RequestBody Comment comment) throws NoPermissionException, ResourceNotFoundException, BadRequestException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		Comment created = commentService.createWorkoutComment(comment, user.getId());
		return new RestResponse<Comment>(Operation.CREATE, created);
	}
	
	/**
	 * Update an existing comment
	 * @param id
	 * @param comment
	 * @return
	 * @throws NoPermissionException
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public RestResponse<Comment> updateWorkoutComment(@PathVariable("id") Long id, @RequestBody Comment comment) throws NoPermissionException, ResourceNotFoundException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		comment.setCommentId(id);
		Comment updated = commentService.updateComment(comment, user.getId());
		return new RestResponse<Comment>(Operation.UPDATE, updated);
	}
	
	/**
	 * Delete a workout comment
	 * @param id
	 * @return
	 * @throws NoPermissionException
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public SimpleResponse deleteComment(@PathVariable("id") Long id) throws NoPermissionException, ResourceNotFoundException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		boolean deleted = commentService.deleteWorkoutComment(id, user.getId());
		if(deleted){
			return new SimpleResponse(Operation.DELETE);
		}else{
			return new SimpleResponse(Operation.NO_CHANGE);
		}
	}
	
	/**
	 * Get all comments for a particular workout
	 * @param id
	 * @param size
	 * @param page
	 * @return
	 * @throws NoPermissionException
	 */
	@RequestMapping(value="/workout/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseList<Comment> getAllCommentsByWorkoutId(@PathVariable Long id, @Param(PARAM_SIZE) int size, 
			@Param(PARAM_PAGE) int page) throws NoPermissionException{
		User current = getLoggedInUser();
		checkUserPermission(current);
		Page<Comment> workoutComments = commentService.getAllCommentsForWorkout(id, new PageRequest(page, size));
		return new ResponseList<Comment>(workoutComments);
	}
	
	
}
