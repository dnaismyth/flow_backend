package com.movement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.dto.Notification;
import com.movement.dto.NotificationType;
import com.movement.dto.User;
import com.movement.exception.BadRequestException;
import com.movement.repository.NotificationRepository;
import com.movement.service.mapper.NotificationMapper;
import com.movement.util.RestPreconditions;


@Service
@Transactional
public class NotificationService {
	
	@Autowired
	private NotificationRepository notifyRepo;
	
	private NotificationMapper notifyMapper = new NotificationMapper();
	
	/**
	 * Creates a notification if a user is being followed
	 * @param follower
	 * @param target
	 * @throws BadRequestException 
	 */
	@Async
	@Transactional
	public void createFollowNotification(User follower, User target) throws BadRequestException{
		RestPreconditions.checkNotNull(follower);
		RestPreconditions.checkNotNull(target);
		if(follower.getId().equals(target.getId())){
			throw new BadRequestException("Do not need to send yourself notifications.");
		}
		
		Notification notify = new Notification(target.getId(), NotificationType.FOLLOW);
		notifyRepo.save(notifyMapper.toRNotification(notify));
	}
	
	/**
	 * Send the owner of the workout being commented on a notification
	 * @param ownerId
	 */
	@Async
	@Transactional
	public void createCommentNotification(Long ownerId){
		RestPreconditions.checkNotNull(ownerId);
		Notification notify = new Notification(ownerId, NotificationType.COMMENT);
		notifyRepo.save(notifyMapper.toRNotification(notify));
	}
	
	//TODO: create like and comment notifications when services are added
}
