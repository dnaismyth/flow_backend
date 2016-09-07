package com.movement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movement.dto.User;
import com.movement.service.UserService;

/**
 * Controller used for the current logged in user to retrieve
 * their own information.
 * @author DN
 *
 */
@RestController
@RequestMapping("/me")
public class UserSelfController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
    public User getMyProfile(){
    	return userService.getCurrentUser();
    }
}
