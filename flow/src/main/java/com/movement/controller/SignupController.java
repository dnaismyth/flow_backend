package com.movement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movement.dto.User;
import com.movement.exception.ResourceNotFoundException;
import com.movement.repository.UserRepository;
import com.movement.service.UserService;

/**
 * Controller used for the initial signup of a user
 * Does not need to be authenticated.
 * @author DN
 *
 */
@RestController
@RequestMapping("/signup")
public class SignupController {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/hello", method = RequestMethod.GET)
    @ResponseBody
    public User sayHello() throws ResourceNotFoundException {
        User user = userService.findUserByUsername("dayna");
        return user;
    }
    
    /**
     * Create a user login
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public User createLogin(@RequestBody User user){
    	User u = userService.create(user);
    	return u;
    }

}
