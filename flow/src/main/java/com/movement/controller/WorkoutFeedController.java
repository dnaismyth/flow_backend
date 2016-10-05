package com.movement.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movement.controller.dto.ResponseList;
import com.movement.dto.Event;
import com.movement.dto.User;
import com.movement.dto.Workout;
import com.movement.exception.NoPermissionException;

@RestController
@RequestMapping("/feed")
public class WorkoutFeedController extends BaseController {


	/**
	 * Find workouts for user feed
	 * @param id
	 * @return
	 * @throws NoPermissionException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseList<Workout> getEvent(@Param(PARAM_SIZE) int size, 
			@Param(PARAM_PAGE) int page) throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		Page<Workout> feed = feedService.findWorkoutsInUserFeed(user.getId(), new PageRequest(page, size));
		return new ResponseList<Workout>(feed);
	}
}
