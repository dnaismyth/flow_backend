package com.movement.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.exception.NoPermissionException;
import com.movement.service.EventInterestService;
import com.movement.service.EventService;
import com.movement.service.FeedService;
import com.movement.service.FollowService;
import com.movement.service.MediaService;
import com.movement.service.UserService;
import com.movement.service.WorkoutFavouriteService;
import com.movement.service.WorkoutService;

/**
 * Base controller used for services other than users
 * @author DN
 *
 */
public class BaseController {
	
	protected static final String PARAM_PAGE = "page";
	protected static final String PARAM_SIZE = "size";
	protected static final String PARAM_NAME = "name";
	
	@Autowired
	protected WorkoutFavouriteService workoutFavService;
	
	@Autowired 
	protected WorkoutService workoutService;
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected EventService eventService;
	
	@Autowired
	protected FeedService feedService;
	
	@Autowired
	protected MediaService mediaService;
	
	@Autowired
	protected FollowService followService;
	
	@Autowired
	protected EventInterestService eventInterestService;
	
	protected User getLoggedInUser(){
		return userService.getCurrentUser();
	}
	
	/**
	 * Check that the user is not a guest
	 * @param user
	 * @throws NoPermissionException 
	 */
	public void checkUserPermission(User user) throws NoPermissionException{
		if(user.getUserRole() == UserRole.GUEST){
			throw new NoPermissionException("You must be logged in to view this page.");
		}
	}
}
