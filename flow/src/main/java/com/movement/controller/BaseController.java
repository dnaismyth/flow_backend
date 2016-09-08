package com.movement.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.movement.dto.User;
import com.movement.service.UserService;
import com.movement.service.WorkoutService;

/**
 * Base controller used for services other than users
 * @author DN
 *
 */
public class BaseController {
	
	@Autowired 
	protected WorkoutService workoutService;
	
	@Autowired
	protected UserService userService;
	
	protected User getLoggedInUser(){
		return userService.getCurrentUser();
	}
}
