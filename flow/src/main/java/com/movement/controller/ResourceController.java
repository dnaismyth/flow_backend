package com.movement.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.BasicSessionCredentials;
import com.movement.controller.dto.EmailRequest;
import com.movement.controller.dto.PasswordResetRequest;
import com.movement.controller.dto.ResponseList;
import com.movement.controller.dto.TokenResponse;
import com.movement.dto.BaseUser;
import com.movement.dto.User;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.service.AwsS3Service;

@RestController
@RequestMapping("/resources")
public class ResourceController extends BaseController {

	@Autowired
	private AwsS3Service awsService;
	/**
	 * Allow user access to S3 Bucket
	 * @return
	 * @throws NoPermissionException 
	 */
	@RequestMapping(value = "/s3token", method = RequestMethod.GET)
	public TokenResponse generateS3Access() throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		BasicSessionCredentials credentials = awsService.getS3UserCredentials();
		return new TokenResponse(credentials);
	}
	
	/**
	 * Allow for a user to request for a password reset
	 * @param req
	 * @throws ResourceNotFoundException 
	 */
	@RequestMapping(value="/resetpassword", method = RequestMethod.POST)
	public void generatePasswordReset(@RequestBody EmailRequest email) throws ResourceNotFoundException{
		userService.userPasswordResetRequest(email.getEmail());	
	}
		
	/**
	 * Mapping to begin password reset. 
	 * @param req
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value="/resetpassword/{key}", method = RequestMethod.GET)
	public String startPasswordReset(@PathVariable("key") int key) throws ResourceNotFoundException{
		return "Start password reset";
	}
	
	@RequestMapping(value="/finish-reset-password", method = RequestMethod.POST)
	public void finishPasswordReset(@RequestBody final PasswordResetRequest resetRequest, HttpServletRequest req){
		
	}
	
}
