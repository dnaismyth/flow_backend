package com.movement.service;

import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.domain.RLocation;
import com.movement.domain.RUser;
import com.movement.domain.RWorkout;
import com.movement.domain.RWorkoutFavourite;
import com.movement.domain.RWorkoutFavouritePK;
import com.movement.dto.BaseUser;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.exception.BadRequestException;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.repository.AuthorityJDBCRepository;
import com.movement.repository.FeedJDBCRepository;
import com.movement.repository.FeedRepository;
import com.movement.repository.FollowJDBCRepository;
import com.movement.repository.FollowRepository;
import com.movement.repository.UserJDBCRepository;
import com.movement.repository.UserRepository;
import com.movement.repository.WorkoutFavouriteRepository;
import com.movement.repository.WorkoutJDBCRepository;
import com.movement.repository.WorkoutRepository;
import com.movement.service.mapper.LocationMapper;
import com.movement.service.mapper.UserMapper;
import com.movement.service.util.CompareUtil;
import com.movement.service.util.LocationUtil;
import com.movement.util.RestPreconditions;


/**
 * Service class for Users
 * @author DN
 */
@Service
@Transactional
public class UserService {
	protected static final Logger logger = Logger.getLogger(UserService.class); 

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private WorkoutRepository workoutRepo;
	
	@Autowired
	private WorkoutJDBCRepository workoutJDBCRepo;
	
	@Autowired
	private FeedJDBCRepository feedJDBCRepo;
	
	@Autowired
	private FollowService followService;
	
	@Autowired
	private UserJDBCRepository userJDBCRepo;
	
	@Autowired
	private AuthorityJDBCRepository authorityJDCBRepo;
	
	@Autowired
	private WorkoutFavouriteRepository workoutFavRepo;
	
	@Autowired
	private FollowRepository followRepo;
		
	@Autowired
	private FollowJDBCRepository followJDBCRepo;
	
	@Autowired
	private PasswordEncoder passwordEncode;
	
	@Autowired
	private FeedService feedService;
	
	@Autowired
	private FeedRepository feedRepo;
	
	@Autowired
	private MailService mailService;
	
	private UserMapper userMapper = new UserMapper();
	private LocationMapper locationMapper = new LocationMapper();
	
	
	/**
	 * Find a User with provided id
	 * @param userId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public User getUser(Long userId) throws ResourceNotFoundException{
		RestPreconditions.checkNotNull(userId);
		RUser found = userRepo.findOne(userId);
		if(found == null){
			String message = String.format("Cannot find user with provided id: %s" , userId);
			throw new ResourceNotFoundException(message);
		}
		
		return userMapper.toUser(found);
		
	}
	
	/**
	 * Creates a user
	 * @param user
	 * @return
	 */
	@Transactional
	public User create(User user){
		RestPreconditions.checkNotNull(user);
		
		RUser ru = userMapper.toEntityUser(user);
		// Set the user as activated once they have signed up and encode password
		ru.setActivated(true);
		ru.setPassword(passwordEncode.encode(ru.getPassword()));
		
		// By Default set the role to USER
		if(ru.getUserRole() == null){
			ru.setUserRole(UserRole.USER);
		}
		
		RUser saved = userRepo.save(ru);
		// Initially create an empty feed for the user
		feedService.createEmptyFeed(saved);
		return userMapper.toUser(saved);
	}
	
	/**
	 * Update a user account
	 * @param user
	 * @return
	 */
	@CacheEvict
	public User updateUser(User user){
		RestPreconditions.checkNotNull(user);
		RUser ru = userRepo.findOne(user.getId());
		
		if(!CompareUtil.compare(ru.getBio(), user.getBio())){
			ru.setBio(user.getBio());
		}
		
		if(!CompareUtil.compare(ru.getCreatedDate(), user.getCreatedDate())){
			ru.setCreatedDate(user.getCreatedDate());
		}
		
		if(!CompareUtil.compare(ru.getName(), user.getName())){
			ru.setName(user.getName());
		}
		
		if(!LocationUtil.compareLocation(ru.getLocation(), user.getLocation())){
			RLocation rl = locationMapper.toRLocation(user.getLocation());
			ru.setLocation(rl);
		}
		
		if(!CompareUtil.compare(ru.getPhone(), user.getPhone())){
			ru.setPhone(user.getPhone());
		}
		
		RUser saved = userRepo.save(ru);
		return userMapper.toUser(saved);
		
	}
	
	/**
	 * Delete a user
	 * @param userId
	 * @throws NoPermissionException 
	 */
	public void delete(User user, Long userId) throws NoPermissionException{
		RestPreconditions.checkNotNull(userId);
		if(!user.getId().equals(userId) && user.getUserRole()!= UserRole.ADMIN){
			throw new NoPermissionException("You do not have permission to delete this user.");
		}
		feedJDBCRepo.deleteFeedReferencesByUserId(userId);
		workoutJDBCRepo.deleteWorkoutAndReferencesByOwner(userId);
		feedRepo.deleteFeedByUserId(userId);
		userRepo.delete(userId);
	}
	
	/**
	 * Find a user with provided username
	 * @param username
	 * @return
	 * @throws ResourceNotFoundException
	 */
	
	@Cacheable
	public User findUserByUsername(String username) throws ResourceNotFoundException{
		RestPreconditions.checkNotNull(username);
		RUser found = userRepo.findByUsernameCaseInsensitive(username);
		return userMapper.toUser(found);
	}
	
	/**
	 * Follow another user
	 * @param follower
	 * @param following
	 * @return
	 * @throws ResourceNotFoundException
	 * @throws BadRequestException
	 */
	public boolean followAnotherUser(User follower, User following) throws ResourceNotFoundException, BadRequestException{
		RestPreconditions.checkNotNull(follower);
		RestPreconditions.checkNotNull(following);
		return followService.followUser(follower.getId(), following.getId());
	}
	
	/**
	 * Unfollow another user
	 * @param follower
	 * @param following
	 * @return
	 * @throws ResourceNotFoundException
	 * @throws BadRequestException
	 */
	public boolean unfollowAnotherUser(User follower, User following) throws ResourceNotFoundException, BadRequestException{
		RestPreconditions.checkNotNull(follower);
		RestPreconditions.checkNotNull(following);
		return followService.unfollowUser(follower.getId(), following.getId());
	}
	
	/**
	 * Search a user by their name
	 * @param name
	 * @param pageable
	 * @return
	 */
	public Page<BaseUser> searchUserByName(String name, Pageable pageable){
		RestPreconditions.checkNotNull(name);
		RestPreconditions.checkNotNull(pageable);
		String searchName = "%" + name + "%";
		List<BaseUser> results = userJDBCRepo.searchUserByName(searchName);
		return new PageImpl<BaseUser>(results, pageable, results.size());
	}
	
	/**
	 * Return the current logged in user
	 * @return
	 */
	public User getCurrentUser(){
		UserDetails details = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		RUser ru = userRepo.findByUsernameCaseInsensitive(details.getUsername());
		return userMapper.toUser(ru);
	}
	
	/**
	 * To add a workout to user favourites
	 * @param user
	 * @param workoutId
	 * @return
	 * @throws BadRequestException
	 */
	public boolean addWorkoutToFavourites(User user, Long workoutId) throws BadRequestException{
		RestPreconditions.checkNotNull(user);
		RestPreconditions.checkNotNull(workoutId);
		
		RWorkout rw = workoutRepo.findOne(workoutId);
		if(rw.getOwner().getId().equals(user.getId())){
			throw new BadRequestException("Cannot favourite your own workout");
		}
		
		if(workoutFavRepo.findByUserIdAndWorkoutId(workoutId, user.getId()) != null){
			throw new BadRequestException("You have already favourited this workout.");
		}
		
		RWorkoutFavouritePK pk = new RWorkoutFavouritePK();
		pk.setUserId(user.getId());
		pk.setWorkoutId(workoutId);
		RWorkoutFavourite wf = new RWorkoutFavourite();
		wf.setWorkoutFavouritePK(pk);
		workoutFavRepo.save(wf);
		return true;
		
	}
	
	/**
	 * Return a user's followers
	 * @param userId
	 * @return
	 */
	public List<Long> findFollowersByUserId(Long userId){
		RestPreconditions.checkNotNull(userId);
		return followJDBCRepo.findFollowersByUserId(userId);
	}
	
	/**
	 * Find user by their e-mail address
	 * @param emailAddress
	 * @return
	 * @throws ResourceNotFoundException
	 */
	private User findUserByEmailAddress(String emailAddress) throws ResourceNotFoundException{
		RestPreconditions.checkNotNull(emailAddress);
		RUser user = userRepo.findByEmail(emailAddress);
		if(user == null){
			throw new ResourceNotFoundException("Could not find user with provided email: " + emailAddress);
		}
		
		return userMapper.toUser(user);
	}
	
	/**
	 * Returns a list of users who have the most amount of workout "likes"
	 * @return
	 */
	public List<BaseUser> findTrendingUsersByWorkoutLikes(){
		return userJDBCRepo.findTrendingUsers();
	}
	
	/**
	 * Returns all of the users who are currently participating in the quest
	 * corresponding to the input questId param.
	 * @param questId
	 * @param page
	 * @return
	 */
	public Page<BaseUser> getUsersEnrolledInQuest(Long questId, Pageable pageable){
		RestPreconditions.checkNotNull(questId);
		RestPreconditions.checkNotNull(pageable);
		List<BaseUser> users = userJDBCRepo.findUsersInQuest(questId, pageable);
		return new PageImpl<BaseUser>(users, pageable, users.size());	
	}
	
	/**
	 * To provide for a user to reset their password if the e-mail address
	 * corresponds to an entry in the database. 
	 * @param emailAddress
	 * @return
	 * @throws ResourceNotFoundException 
	 * @throws MessagingException 
	 */
	public void userPasswordResetRequest(String emailAddress) throws ResourceNotFoundException, MessagingException{
		RestPreconditions.checkNotNull(emailAddress);
		User user = findUserByEmailAddress(emailAddress);
		if(user != null){
			mailService.sendPasswordResetMail(emailAddress, user);
		}
	}
	
	/**
	 * Find a user via a provided reset password key
	 * @param key
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public User findUserByResetPasswordKey(String key) throws ResourceNotFoundException{
		RestPreconditions.checkNotNull(key);
		RUser found = userRepo.findByResetPasswordKey(key);
		if(found == null){
			throw new ResourceNotFoundException("Cannot find user with provided reset password key.");
		}
		
		return userMapper.toUser(found);
	}
	
	
	/**
	 * Reset the user's password and remove reset password key
	 * @param user
	 * @param password
	 */
	public void changePassowordFromResetRequest(User user, String password){
		RestPreconditions.checkNotNull(user.getId());
		RestPreconditions.checkNotNull(password);
		
		RUser ru = userRepo.findOne(user.getId());
		ru.setPassword(passwordEncode.encode(password));	// update as new password
		ru.setResetPasswordKey(null);						// remove reset key
		userRepo.save(ru);
	}
	
	/**
	 * Check if a username is unique (for client side to check dynamically
	 * during user initial sign-up)
	 * @param username
	 * @return
	 */
	public boolean isUniqueUsername(String username){
		RestPreconditions.checkNotNull(username);
		RUser ru = userRepo.findByUsernameCaseInsensitive(username);
		if(ru != null){
			return false;
		}else 
			return true;
	}
	
	/**
	 * Check if an e-mail address is unique (for client side to dynamically
	 * during user initial sign-up)
	 * @param emailAddress
	 * @return
	 */
	public boolean isUniqueEmailAddress(String emailAddress){
		RestPreconditions.checkNotNull(emailAddress);
		RUser ru = userRepo.findByEmail(emailAddress);
		if(ru != null){
			return false;
		} else {
			return true;
		}
	}
	
}
