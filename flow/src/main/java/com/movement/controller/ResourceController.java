package com.movement.controller;

import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.BasicSessionCredentials;
import com.movement.controller.dto.EmailRequest;
import com.movement.controller.dto.FlowResponseCode;
import com.movement.controller.dto.PasswordResetRequest;
import com.movement.controller.dto.TokenResponse;
import com.movement.dto.User;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.service.AwsS3Service;
import com.movement.service.ConfirmationService;

@RestController
@RequestMapping("/resources")
public class ResourceController extends BaseController {
	
	@Autowired
	private ConfirmationService confirmService;
	
	private static final String KEY_PARAM = "key";
	private static final String EMAIL = "email";
	private static final String USERNAME = "username";
	
	/**
	 * Allow for a user to request for a password reset
	 * @param req
	 * @throws ResourceNotFoundException 
	 * @throws MessagingException 
	 */
	@RequestMapping(value="/resetpassword", method = RequestMethod.POST)
	public void generatePasswordReset(@RequestBody EmailRequest email) throws ResourceNotFoundException, MessagingException{
		userService.userPasswordResetRequest(email.getEmail());	
	}
		
	/**
	 * Mapping to begin password reset. 
	 * @param req
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value="/resetpassword", method = RequestMethod.GET)
	public String startPasswordReset(@RequestParam(required=false) Map<String, String> criteria) throws ResourceNotFoundException{
		if(criteria.containsKey(KEY_PARAM))
			return "Start password reset"; // TODO: change temp return values
		else
			return "no key provided";
	}
	
	@RequestMapping(value="/finish-reset-password", method = RequestMethod.POST)
	public void finishPasswordReset(@RequestBody final PasswordResetRequest resetRequest, HttpServletRequest req){
		
	}
	
	/**
	 * Update the user's confirmation and set status as confirmed.
	 * @param criteria
	 * @return
	 */
	@RequestMapping(value="/confirmation", method = RequestMethod.GET)
	public String verifyUserConfirmation(@RequestParam(required=false) Map<String, String> criteria){
		if(criteria.containsKey(KEY_PARAM)){
			confirmService.setUserConfirmationStatus(criteria.get(KEY_PARAM), true);	// set the status as confirmed
			return "Thank you for confirming your e-mail address.";
		}
		else
			return "e-mail not confirmed";
	}
	
	/**
	 * Check for unique username on initial user sign up
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/unique", method = RequestMethod.GET)
	@ResponseBody
	public FlowResponseCode checkUniqueUsername(@RequestParam(required=false) Map<String, String> criteria){
		boolean unique = false;
		if(criteria.containsKey(USERNAME)){
			unique = userService.isUniqueUsername(criteria.get(USERNAME));
		}
		if(unique){
			return FlowResponseCode.OK;
		} else {
			return FlowResponseCode.USERNAME_TAKEN;
		}
	}
	
}
