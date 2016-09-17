package com.movement.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;

import com.movement.domain.RFollow;
import com.movement.dto.BaseUser;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.exception.BadRequestException;
import com.movement.exception.ResourceNotFoundException;

public class FollowServiceTest extends TestBaseClass {

	
	private User follower, following;
	private String userName1 = "followerTest@media.com";
	private String userName2 = "followingTest@media.com";
	
	@Before
	public void setUp() throws ResourceNotFoundException{
		
		follower = userService.findUserByUsername(userName1);
		if(follower == null){
			follower = new User();
			follower.setName("Follower");
			follower.setUsername(userName1);
			follower.setEmail(userName1);
			follower.setPassword("test12");
			follower.setUserRole(UserRole.USER);
			follower = userService.create(follower);
		}
		
		following = userService.findUserByUsername(userName2);
		if(following == null){
			following = new User();
			following.setName("Following");
			following.setUsername(userName2);
			following.setEmail(userName2);
			following.setPassword("test12");
			following.setUserRole(UserRole.USER);
			following = userService.create(following);
		}
		
	}
	
	@After
	public void tearDown(){
		userService.delete(follower.getId());
		userService.delete(following.getId());
	}
	
	// Check that a User can be followed
	@Test
	public void testFollowUser() throws ResourceNotFoundException, BadRequestException{
		boolean followed = followService.followUser(follower.getId(), following.getId());
		Assert.assertTrue(followed);
	}
	
	@Test
	public void searchUserByUsername(){
		List<BaseUser> output = userService.searchUserByName("follower", new PageRequest(0,5));
		Assert.assertNotNull(output);
	}
	
	// Check that once a user is followed that they can unfollow them
	@Test
	public void testUnfollowUser() throws ResourceNotFoundException, BadRequestException{
		boolean followed = followService.followUser(follower.getId(), following.getId());
		Assert.assertTrue(followed);
		boolean unfollow = followService.unfollowUser(follower.getId(), following.getId());
		Assert.assertTrue(unfollow);
		RFollow found = followRepo.findRelationshipByFollowerAndFollowingId(follower.getId(), following.getId());
		Assert.assertNull(found);
	}
}
