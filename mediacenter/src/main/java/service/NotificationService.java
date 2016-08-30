package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repository.NotificationRepository;
import service.mapper.NotificationMapper;
import util.RestPreconditions;
import dto.Notification;
import dto.NotificationType;
import dto.User;
import exception.BadRequestException;

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
	
	//TODO: create like and comment notifications when services are added
}
