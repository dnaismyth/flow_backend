package com.movement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movement.domain.REvent;
import com.movement.domain.RLocation;
import com.movement.dto.Event;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.repository.EventJDBCRepository;
import com.movement.repository.EventRepository;
import com.movement.service.mapper.EventMapper;
import com.movement.service.mapper.LocationMapper;
import com.movement.service.util.CompareUtil;
import com.movement.util.RestPreconditions;

@Service
public class EventService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private EventJDBCRepository eventJDBCRepo;
	
	private EventMapper eventMapper = new EventMapper();
	private LocationMapper locationMapper = new LocationMapper();
	
	/**
	 * Find an event by it's id
	 * @param eventId
	 * @return
	 */
	public Event getEvent(Long eventId){
		RestPreconditions.checkNotNull(eventId);
		REvent r = eventRepo.findOne(eventId);
		return eventMapper.toEvent(r);
	}
	
	/**
	 * Create an event for other users to join
	 * @param event
	 * @param ownerId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Event createEvent(Event event, Long ownerId) throws ResourceNotFoundException{
		RestPreconditions.checkNotNull(event);
		RestPreconditions.checkNotNull(ownerId);
		
		User owner = userService.getUser(ownerId);
		event.setOwnerId(owner.getId());
		REvent r = eventMapper.toREvent(event);
		REvent saved = eventRepo.save(r);
		return eventMapper.toEvent(saved);	
	}
	
	/**
	 * Update an event if the current user is the owner or has role type of ADMIN
	 * @param event
	 * @param currentUser
	 * @return
	 * @throws NoPermissionException
	 * @throws ResourceNotFoundException
	 */
	public Event updateEvent(Event event, User currentUser) throws NoPermissionException, ResourceNotFoundException{
		RestPreconditions.checkNotNull(event);
		RestPreconditions.checkNotNull(currentUser);
		
		if(!(currentUser.getId() == event.getOwnerId() || currentUser.getUserRole() == UserRole.ADMIN)){
			throw new NoPermissionException("You do not have permission to update this event.");
		}
		
		REvent r = eventRepo.findOne(event.getId());
		if(r == null){
			throw new ResourceNotFoundException("Cannot find an event with the provided event id");
		}
		
		RLocation rl = locationMapper.toRLocation(event.getLocation());
		if(!CompareUtil.compare(r.getLocation(), rl)){
			r.setLocation(rl);
		}
		
		if(!CompareUtil.compare(r.getDescription(), event.getDescription())){
			r.setDescription(event.getDescription());
		}
		
		if(!CompareUtil.compare(r.getTitle(), event.getTitle())){
			r.setTitle(event.getTitle());
		}
		
		if(!CompareUtil.compare(r.getEventDate(), event.getEventDate())){
			r.setEventDate(event.getEventDate());
		}
		
		if(!CompareUtil.compare(r.getShowType(), event.getShowType())){
			r.setShowType(event.getShowType());
		}
		
		REvent updated = eventRepo.save(r);
		return eventMapper.toEvent(updated);
	}
	
	/**
	 * Delete an event if the current user is the owner or has user role of ADMIN
	 * @param eventId
	 * @param userId
	 * @return
	 * @throws NoPermissionException
	 * @throws ResourceNotFoundException
	 */
	public boolean deleteEvent(Long eventId, Long userId) throws NoPermissionException, ResourceNotFoundException{
		RestPreconditions.checkNotNull(eventId);
		RestPreconditions.checkNotNull(userId);
		
		REvent r = eventRepo.findOne(eventId);
		User user = userService.getUser(userId);
		if(!r.getOwnerId().equals(userId) && user.getUserRole() != UserRole.ADMIN){
			throw new NoPermissionException("You do not have permission to delete this event.");
		}
		
		eventJDBCRepo.deleteEventReferencesByEventId(eventId);
		eventRepo.delete(r);
		return true;
	}
	
	
}
