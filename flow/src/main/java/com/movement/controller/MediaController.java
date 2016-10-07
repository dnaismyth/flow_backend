package com.movement.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movement.controller.dto.RestResponse;
import com.movement.controller.dto.SimpleResponse;
import com.movement.dto.Media;
import com.movement.dto.Operation;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.dto.Workout;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/media")
public class MediaController extends BaseController{

	/**
	 * Find Media by id
	 * @param id
	 * @return
	 * @throws NoPermissionException
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public RestResponse<Media> getUser(@PathVariable Long id) throws NoPermissionException, ResourceNotFoundException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		Media media = mediaService.getMedia(id);
		return new RestResponse<Media>(media);
	}
	
	/**
	 * Delete media
	 * @param id
	 * @return
	 * @throws NoPermissionException
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public SimpleResponse deleteMedia(@PathVariable Long id) throws NoPermissionException, ResourceNotFoundException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		boolean deleted = mediaService.deleteMedia(user, id);
		if(deleted){
			return new SimpleResponse(Operation.DELETE);
		} else {
			return new SimpleResponse(Operation.NO_CHANGE);
		}
	}
	
	/**
	 * Create media to be added to a workout
	 * @param media
	 * @return
	 * @throws NoPermissionException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public RestResponse<Media> createMedia(@RequestBody Media media) throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		Media created = mediaService.createMedia(media, user);
		return new RestResponse<Media>(Operation.CREATE, created);
	}
	
	/**
	 * Update existing media
	 * @param updated
	 * @return
	 * @throws IllegalArgumentException
	 * @throws ResourceNotFoundException
	 * @throws NoPermissionException
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public RestResponse<Media> updateMedia(@RequestBody Media updated) throws IllegalArgumentException, ResourceNotFoundException, NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		Media m = mediaService.updateMedia(updated, user);
		return new RestResponse<Media>(Operation.UPDATE, m);
	}
}
