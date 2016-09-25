package com.movement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movement.controller.dto.ResponseList;
import com.movement.controller.dto.RestResponse;
import com.movement.dto.BaseUser;
import com.movement.dto.User;
import com.movement.service.UserService;
import com.movement.service.WorkoutService;
import com.movement.util.RestPreconditions;

/**
 * The purpose of this controller is to be used for users other than
 * the currently logged in user.
 * @author DN
 *
 */

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	
	private static final String PARAM_PAGE = "page";
	private static final String PARAM_SIZE = "size";
	private static final String PARAM_NAME = "name";

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
		
		return response;	
	}

}


