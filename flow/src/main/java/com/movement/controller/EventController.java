package com.movement.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movement.controller.dto.RestResponse;
import com.movement.controller.dto.SimpleResponse;
import com.movement.dto.Event;
import com.movement.dto.Operation;
import com.movement.dto.User;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/event")
public class EventController extends BaseController {

	/**
	 * Find an event by id
	 * @param id
	 * @return
	 * @throws NoPermissionException
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Event getEvent(@PathVariable("id") Long id) throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		Event found = eventService.getEvent(id);
		return found;
	}
	
	/**
	 * Create a new event
	 * @param event
	 * @return
	 * @throws NoPermissionException
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public RestResponse<Event> createEvent(@RequestBody Event event) throws NoPermissionException, ResourceNotFoundException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		Event created = eventService.createEvent(event, user.getId());
		return new RestResponse<Event>(Operation.CREATE, created);
	}
	
	/**
	 * Update an existing event
	 * @param id
	 * @param event
	 * @return
	 * @throws NoPermissionException
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public RestResponse<Event> updateEvent(@PathVariable("id") Long id, @RequestBody Event event) throws NoPermissionException, ResourceNotFoundException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		event.setId(id);
		Event updated = eventService.updateEvent(event, user);
		return new RestResponse<Event>(Operation.UPDATE, updated);
	}
	
	/**
	 * Delete an existing event
	 * @param id
	 * @return
	 * @throws NoPermissionException
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public SimpleResponse deletEvent(@PathVariable("id") Long id) throws NoPermissionException, ResourceNotFoundException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		boolean deleted = eventService.deleteEvent(id, user.getId());
		if(deleted){
			return new SimpleResponse(Operation.DELETE);
		}else{
			return new SimpleResponse(Operation.NO_CHANGE);
		}
	}
}
