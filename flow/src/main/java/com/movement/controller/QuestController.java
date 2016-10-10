package com.movement.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movement.controller.dto.RestResponse;
import com.movement.controller.dto.SimpleResponse;
import com.movement.dto.Operation;
import com.movement.dto.Quest;
import com.movement.dto.User;
import com.movement.dto.Workout;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;

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
	
	/**
	 * Find a quest by the provided id
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 * @throws NoPermissionException
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public RestResponse<Quest> getQuest(@PathVariable Long id) throws ResourceNotFoundException, NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		Quest found = questService.getQuest(id);
		return new RestResponse<Quest>(found);
	}
	
	/**
	 * Allow for an admin to update an existing quest
	 * @param quest
	 * @return
	 * @throws NoPermissionException
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public RestResponse<Quest> updateQuest(@RequestBody final Quest quest) throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		Quest updated = questService.updateQuest(quest, user);
		return new RestResponse<Quest>(Operation.UPDATE, updated);
	}
	
	/**
	 * Allow for an admin to remove an existing quest
	 * @param id
	 * @return
	 * @throws NoPermissionException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public SimpleResponse removeQuest(@PathVariable Long id) throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		boolean deleted = questService.deleteQuest(id, user);
		if(deleted){
			return new SimpleResponse(Operation.DELETE);
		} else {
			return new SimpleResponse(Operation.NO_CHANGE);
		}
	}
	
}
