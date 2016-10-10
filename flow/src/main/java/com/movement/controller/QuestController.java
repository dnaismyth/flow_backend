package com.movement.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movement.controller.dto.RestResponse;
import com.movement.dto.Operation;
import com.movement.dto.Quest;
import com.movement.dto.User;
import com.movement.exception.NoPermissionException;

@RestController
@RequestMapping("/quests")
public class QuestController extends BaseController {

	/**
	 * Create a new workout, set the owner to the current user
	 * @param workout
	 * @return
	 * @throws NoPermissionException 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public RestResponse<Quest> createQuest(@RequestBody Quest quest) throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		Quest created = questService.createQuest(quest, user);
		return new RestResponse<Quest>(Operation.CREATE, created);
	}
	
}
