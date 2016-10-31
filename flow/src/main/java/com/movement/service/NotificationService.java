package com.movement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.domain.RNotification;
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
	
	/**
	 * Send notification to owner of the event that someone is interested in
	 * attending their event
	 * @param ownerId
	 */
	@Async
	@Transactional
	public void createEventInterestNotification(Long ownerId){
		RestPreconditions.checkNotNull(ownerId);
		Notification notify = new Notification(ownerId, NotificationType.EVENT_INTEREST);
		notifyRepo.save(notifyMapper.toRNotification(notify));
	}
	
	/**
	 * Send notification to workout owner that someone has "liked" their
	 * workout
	 * @param ownerId
	 */
	@Async
	@Transactional
	public void createWorkoutFavouriteNotification(Long ownerId){
		RestPreconditions.checkNotNull(ownerId);
		Notification notify = new Notification(ownerId, NotificationType.LIKE);
		notifyRepo.save(notifyMapper.toRNotification(notify));
	}
	
	/**
	 * Find Notifications for a user
	 * @param userId
	 * @return
	 */
	public List<Notification> findNotificationsForUser(Long userId){
		RestPreconditions.checkNotNull(userId);
		List<RNotification> rn = notifyRepo.findNotificationsForUserById(userId);
		return notifyMapper.toNotificationList(rn);
	}

}
