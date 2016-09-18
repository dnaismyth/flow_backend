package com.movement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movement.domain.RComment;
import com.movement.dto.Comment;
import com.movement.dto.ShowType;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.dto.Workout;
import com.movement.exception.BadRequestException;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.repository.CommentRepository;
import com.movement.service.mapper.CommentMapper;
import com.movement.service.util.CompareUtil;
import com.movement.util.RestPreconditions;

@Service
public class CommentService {

	@Autowired
	private WorkoutService workoutService;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private NotificationService notifyService;
	
	@Autowired
	private UserService userService;
	
	private CommentMapper commentMapper = new CommentMapper();
	
	
	/**
	 * Find a comment by id
	 * @param commentId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Comment getCommentById(Long commentId) throws ResourceNotFoundException{
		RestPreconditions.checkNotNull(commentId);
		RComment rc = commentRepo.findOne(commentId);
		if(rc == null){
			String message = String.format("Cannot find comment with provided id: %s ", commentId);
			throw new ResourceNotFoundException(message);
		}
		
		return commentMapper.toComment(rc);
	}
	
	/**
	 * Create a workout comment
	 * @param workoutId
	 * @param commenterId
	 * @return
	 * @throws ResourceNotFoundException 
	 * @throws BadRequestException 
	 */
	public Comment createWorkoutComment(Comment comment, Long commenterId) throws ResourceNotFoundException, BadRequestException{
		RestPreconditions.checkNotNull(comment.getWorkoutId());
		RestPreconditions.checkNotNull(comment.getUserId());
		
		Workout w = workoutService.findWorkoutById(comment.getWorkoutId());
		if(w.getShowType() == ShowType.PRIVATE){
			throw new BadRequestException("You cannot create a comment on a private workout.");
		}
		
		RComment rc = commentMapper.toRComment(comment);
		rc.setUserId(commenterId);
		RComment saved = commentRepo.save(rc);
		
		// Send a notification to the owner of the workout
		notifyService.createCommentNotification(w.getOwner().getId());
		
		return commentMapper.toComment(saved);
	}
	
	/**
	 * For user to update text of their previously created comment
	 * @param comment
	 * @param commenter
	 * @return
	 * @throws ResourceNotFoundException 
	 * @throws NoPermissionException 
	 */
	public Comment updateComment(Comment comment, Long commenterId) throws ResourceNotFoundException, NoPermissionException{
		RestPreconditions.checkNotNull(comment);
		RestPreconditions.checkNotNull(commenterId);
		
		User u = userService.getUser(commenterId);
		if(!(comment.getUserId() == u.getId() && u.getUserRole() == UserRole.ADMIN)){
			throw new NoPermissionException("You do not have permission to delete this comment");
		}
		
		RComment rc = commentRepo.findOne(comment.getCommentId());
		
		if(!(CompareUtil.compare(rc.getCommentText(), comment.getCommentText()))){
			rc.setCommentText(comment.getCommentText());
		}
		
		RComment saved = commentRepo.save(rc);
		return commentMapper.toComment(saved);
	}
	
	/**
	 * Delete a comment by id if the user is:
	 * 1) Owner of the workout
	 * 2) Owner of the comment
	 * 3) Current user has role type of ADMIN
	 * 
	 * @param commentId
	 * @return
	 * @throws ResourceNotFoundException 
	 * @throws NoPermissionException 
	 */
	public boolean deleteWorkoutComment(Long commentId, Long userId) throws ResourceNotFoundException, NoPermissionException{
		RestPreconditions.checkNotNull(commentId);
		RComment rc = commentRepo.findOne(commentId);
		
		Workout w = workoutService.findWorkoutById(rc.getWorkoutId());
		User u = userService.getUser(userId);
		Long ownerId = w.getOwner().getId();
		
		if(!(rc.getUserId() == u.getId() && ownerId == u.getId() && u.getUserRole() == UserRole.ADMIN)){
			throw new NoPermissionException("You do not have permission to delete this comment");
		}
		
		commentRepo.delete(commentId);
		return true;
	}
	
	
}
