package com.movement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.domain.RFollow;
import com.movement.domain.RUser;
import com.movement.domain.RUserRelationPK;
import com.movement.dto.Relationship;
import com.movement.exception.BadRequestException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.repository.FollowRepository;
import com.movement.repository.UserRepository;
import com.movement.service.mapper.UserMapper;

@Service
@Transactional
public class FollowService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private FollowRepository followRepo;
	
	@Autowired
	private NotificationService notifyService;
	
	private UserMapper userMapper = new UserMapper();

	/**
	 * Allow a user to follow another user
	 * @param followerId
	 * @param followingId
	 * @return
	 * @throws ResourceNotFoundException
	 * @throws BadRequestException 
	 */
	public boolean followUser(Long followerId, Long followingId) throws ResourceNotFoundException, BadRequestException{
		RUser follower = userRepo.findOne(followerId);
		RUser following = userRepo.findOne(followingId);
		if(follower == null){
			String message = String.format("Cannot find follower with provided id: %s", followerId);
			throw new ResourceNotFoundException(message);
		}
		
		if(following == null){
			String message = String.format("Cannot find following with provided id: %s", followingId);
			throw new ResourceNotFoundException(message);
		}
		
		// Check if the relationship already exists
		RFollow rf = followRepo.findRelationshipByFollowerAndFollowingId(followerId, followingId);
		if(rf != null){
			throw new BadRequestException("You have already followed this user.");
		}
		// If the relationship does not exist, create a new relationship
		RUserRelationPK relation = new RUserRelationPK();
		relation.setUserId(followerId);
		relation.setTargetId(followingId);
		
		rf = new RFollow();
		rf.setUserRelation(relation);
		rf.setRelationship(Relationship.FOLLOW);
		followRepo.save(rf);
		
		// Send a notification to the targeted user that they have been followed
		notifyService.createFollowNotification(userMapper.toUser(follower), userMapper.toUser(following));
		
		return true;
	}
	
	public boolean unfollowUser(Long followerId, Long followingId) throws ResourceNotFoundException, BadRequestException{
		RUser follower = userRepo.findOne(followerId);
		RUser following = userRepo.findOne(followingId);
		if(follower == null){
			String message = String.format("Cannot find follower with provided id: %s", followerId);
			throw new ResourceNotFoundException(message);
		}
		
		if(following == null){
			String message = String.format("Cannot find following with provided id: %s", followingId);
			throw new ResourceNotFoundException(message);
		}
		
		// Check if the relationship already exists
		RFollow rf = followRepo.findRelationshipByFollowerAndFollowingId(
				followerId, followingId);
		if (rf == null) {
			throw new BadRequestException(
					"You cannot unfollow a user you have not yet followed.");
		}
		
		followRepo.delete(rf);
		return true;
	}
	
	
}
