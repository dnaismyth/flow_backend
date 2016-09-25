package com.movement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movement.domain.REvent;
import com.movement.domain.REventInterest;
import com.movement.domain.REventInterestPK;
import com.movement.domain.RWorkoutFavourite;
import com.movement.domain.RWorkoutFavouritePK;
import com.movement.dto.User;
import com.movement.exception.BadRequestException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.repository.EventInterestRepository;
import com.movement.repository.EventRepository;
import com.movement.util.RestPreconditions;

/**
 * Service class to add or remove an event from
 * user's interest
 * @author DN
 *
 */
@Service
public class EventInterestService {

	@Autowired
	private EventInterestRepository eventInterestRepo;
	
	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private NotificationService notifyService;
	
	/**
	 * Add an event to the provided user.id's interests
	 * This event will be something that they may consider attending.
	 * @param eventId
	 * @param user
	 * @return
	 * @throws BadRequestException 
	 */
	public boolean addEventToInterests(Long eventId, User user) throws BadRequestException{
		RestPreconditions.checkNotNull(eventId);
		RestPreconditions.checkNotNull(user);
		
		REventInterest ei = eventInterestRepo.findByUserIdAndEventId(user.getId(), eventId);
		if(ei != null){
			throw new BadRequestException("You have already added this event to your interests.");
		}
		
		REventInterestPK pk = new REventInterestPK();
		pk.setUserId(user.getId());
		pk.setEventId(eventId);
		ei = new REventInterest();
		ei.setEventInterestPK(pk);
		eventInterestRepo.save(ei);
		
		// Send notification to the owner of the event
		REvent event = eventRepo.findOne(eventId);
		notifyService.createEventInterestNotification(event.getOwnerId());
		
		return true;		
	}
	
	/**
	 * Remove an event from user's interest
	 * @param eventId
	 * @param user
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public boolean removeEventFromInterests(Long eventId, User user) throws ResourceNotFoundException{
		RestPreconditions.checkNotNull(eventId);
		RestPreconditions.checkNotNull(user);
		
		REventInterest ei = eventInterestRepo.findByUserIdAndEventId(user.getId(), eventId);
		if(ei == null){
			throw new ResourceNotFoundException("Cannot find event interest with provided user and event.id");
		}
		
		eventInterestRepo.delete(ei);
		return true;
	}
	
}
