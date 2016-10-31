package com.movement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.BasicSessionCredentials;
import com.movement.controller.dto.ResponseList;
import com.movement.controller.dto.RestResponse;
import com.movement.controller.dto.TokenResponse;
import com.movement.domain.RWorkout;
import com.movement.dto.Notification;
import com.movement.dto.Operation;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.dto.Workout;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.service.AwsS3Service;
import com.movement.service.NotificationService;
import com.movement.service.UserService;

/**
 * Controller used for the current logged in user to retrieve
 * their own information.
 * @author DN
 *
 */
@RestController
@RequestMapping("/me")
public class UserSelfController extends BaseController {

	@Autowired
	private AwsS3Service awsService;
	
	@Autowired
	private NotificationService notifyService;
	
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
	 * Returns the current logged in user information.
	 * @return
	 * @throws NoPermissionException 
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
    public User getMyProfile() throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
    	return getLoggedInUser();
    }
	
	/**
	 * Update the current user profile
	 * @param id
	 * @param updated
	 * @return
	 * @throws IllegalArgumentException
	 * @throws ResourceNotFoundException
	 * @throws NoPermissionException
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public RestResponse<User> updateMyProfile(@RequestBody User updated) throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		// Check that only the current user can update their own profile
		if(!(updated.getId().equals(user.getId()) && user.getUserRole() != UserRole.ADMIN)){
			throw new NoPermissionException("Only the owner can update their own profile.");
		}
		User u = userService.updateUser(updated);
		return new RestResponse<User>(Operation.UPDATE, u);
	}
	
	/**
	 * User option to delete their account
	 * @param id
	 * @return
	 * @throws NoPermissionException
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public RestResponse<User> deleteProfile() throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		userService.delete(user, user.getId());
		return new RestResponse<User>(Operation.DELETE, user.getId());
	}
	
	/**
	 * Find notifications for the current logged in user to be displayed on their navigation bar
	 * @return
	 * @throws NoPermissionException
	 */
	@RequestMapping(value="/notifications", method = RequestMethod.GET)
	public ResponseList<Notification> getNotificationsForUser() throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		List<Notification> notifications = notifyService.findNotificationsForUser(user.getId());
		return new ResponseList<Notification>(notifications);
	}
	/**
	 * Test Function to say hello to the current user
	 * @return
	 */
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	@ResponseBody
	public String sayHello(){
		//User current = getLoggedInUser();
		return "HELLO WORLD";
	}
	
	
}
