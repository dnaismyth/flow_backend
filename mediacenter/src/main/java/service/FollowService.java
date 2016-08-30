package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dto.Relationship;
import entities.RFollow;
import entities.RUser;
import entities.RUserRelationPK;
import exception.BadRequestException;
import exception.ResourceNotFoundException;
import repository.FollowRepository;
import repository.UserRepository;

@Service
@Transactional
public class FollowService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private FollowRepository followRepo;

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
