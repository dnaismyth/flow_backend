package com.movement.controller;

import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.BasicSessionCredentials;
import com.movement.controller.dto.EmailRequest;
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
	private AwsS3Service awsService;
	
	@Autowired
	private ConfirmationService confirmService;
	
	private static final String KEY_PARAM = "key";
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
	
}
