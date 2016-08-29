package mediacenter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dto.User;
import dto.UserRole;
import entities.RFollow;
import exception.BadRequestException;
import exception.ResourceNotFoundException;

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
