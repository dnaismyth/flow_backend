package com.movement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.dynamodbv2.model.OperationType;
import com.movement.controller.dto.RestResponse;
import com.movement.controller.dto.SignupRequest;
import com.movement.dto.Operation;
import com.movement.dto.User;
import com.movement.exception.ResourceNotFoundException;
import com.movement.repository.UserRepository;
import com.movement.service.UserService;
import com.movement.util.RestPreconditions;

/**
 * Controller used for the initial signup of a user
 * Does not need to be authenticated.
 * @author DN
 *
 */
@RestController
@RequestMapping("/register")
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
     * Need to provide email, username, name, and password
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public RestResponse<User> createLogin(@RequestBody SignupRequest req){
    	RestPreconditions.checkNotNull(req);
    	User u = buildUser(req);
    	return new RestResponse<User>(Operation.CREATE, u);
    }
    
//    @RequestMapping(value = "/{provider}", method = RequestMethod.GET)
//    public 
    
    
    // Build user from provided signup request
    private User buildUser(SignupRequest req){
    	User u = new User();
    	u.setEmail(req.getEmail());
    	u.setName(req.getName());
    	u.setUsername(req.getUsername());
    	u.setPassword(req.getPassword());
    	return u;
    }

}
