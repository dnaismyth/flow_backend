package com.movement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movement.controller.dto.ResponseList;
import com.movement.controller.dto.RestResponse;
import com.movement.controller.dto.SimpleResponse;
import com.movement.dto.BaseUser;
import com.movement.dto.Operation;
import com.movement.dto.User;
import com.movement.exception.BadRequestException;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.util.RestPreconditions;

/**
 * The purpose of this controller is to be used for users other than
 * the currently logged in user.
 * @author DN
 *
 */

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
	
	/**
	 * Find one user by id
	 * @param id
	 * @return
	 * @throws NoPermissionException
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public RestResponse<User> getUser(@PathVariable Long id) throws NoPermissionException, ResourceNotFoundException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		User found = userService.getUser(id);
		return new RestResponse<User>(found);
	}
	
	/**
	 * Allow for a user to follow others 
	 * @param id
	 * @return
	 * @throws NoPermissionException
	 * @throws ResourceNotFoundException
	 * @throws BadRequestException
	 */
	@RequestMapping(value="/{id}/follows", method = RequestMethod.POST)
	public SimpleResponse followUser(@PathVariable Long id) throws NoPermissionException, ResourceNotFoundException, BadRequestException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		boolean followed = followService.followUser(user.getId(), id);
		if(followed){
			return new SimpleResponse(Operation.ADD);
		} else {
			return new SimpleResponse(Operation.NO_CHANGE);
		}
	}
	
	/**
	 * Find a list of trending users (based on amount of workout likes for now)
	 * Output limit = 5
	 * @return
	 * @throws NoPermissionException
	 */
	@RequestMapping(value="/trending", method = RequestMethod.GET)
	public ResponseList<BaseUser> getTrendingUsers() throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		List<BaseUser> trending = userService.findTrendingUsersByWorkoutLikes();
		return new ResponseList<BaseUser>(trending);
	}

	/**
	 * Search controller to find a user by their username
	 * @param size
	 * @param page
	 * @param criteria
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody()
	public ResponseList<BaseUser> searchUser(@Param(PARAM_SIZE) int size, 
			@Param(PARAM_PAGE) int page,
			@RequestParam(required=false) Map<String, String> criteria){
		
		RestPreconditions.checkNotNull(size);
		RestPreconditions.checkNotNull(page);
	    ResponseList<BaseUser> response = new ResponseList<BaseUser>();
		if(criteria.containsKey(PARAM_NAME)){
			// if they have provided the name parameter,
			// find the value associated with this key and perform a search.
			String searchName = criteria.get(PARAM_NAME);
			Page<BaseUser> results = userService.searchUserByName(searchName, new PageRequest(page, size));
			response.setPage(results);
		}
		
		//TODO: if there is no name param, return users in which they are following
		
		return response;	
	}
	
	/**
	 * Returns a page of users that are participating in a quest
	 * @param id (corresponds to the quest id)
	 * @param size
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/quests/{id}")
	public ResponseList<BaseUser> findUsersInQuest(@PathVariable("id") Long id, @Param(PARAM_SIZE) int size, 
			@Param(PARAM_PAGE) int page){
		RestPreconditions.checkNotNull(size);
		RestPreconditions.checkNotNull(page);
		Page<BaseUser> users = userService.getUsersEnrolledInQuest(id, new PageRequest(page, size));
		return new ResponseList<BaseUser>(users);		
	}
	
	

}


