package com.movement.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.domain.Authority;
import com.movement.domain.RUser;
import com.movement.dto.BaseUser;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.exception.BadRequestException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.repository.AuthorityJDBCRepository;
import com.movement.repository.AuthorityRepository;
import com.movement.repository.UserJDBCRepository;
import com.movement.repository.UserRepository;
import com.movement.repository.WorkoutJDBCRepository;
import com.movement.repository.WorkoutRepository;
import com.movement.security.Authorities;
import com.movement.service.mapper.UserMapper;
import com.movement.service.util.CompareUtil;
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
	private FollowService followService;
	
	@Autowired
	private UserJDBCRepository userJDBCRepo;
	
	@Autowired
	private AuthorityJDBCRepository authorityJDCBRepo;
	
	
	@Autowired
	private PasswordEncoder passwordEncode;
	
	private UserMapper userMapper = new UserMapper();
	//private LocationMapper locationMapper = new LocationMapper();
	
	
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
		ru.setUserRole(UserRole.USER);
		
		RUser saved = userRepo.save(ru);
		return userMapper.toUser(saved);
	}
	
	/**
	 * Update a user account
	 * @param user
	 * @return
	 */
	public User updateUser(User user){
		RestPreconditions.checkNotNull(user);
		RUser ru = userRepo.findOne(user.getId());
		
		if(!CompareUtil.compare(ru.getBio(), user.getBio())){
			ru.setBio(user.getBio());
		}
		
//		if(!CompareUtil.compare(ru.getCreatedDate(), user.getCreatedDate())){
//			ru.setCreatedDate(user.getCreatedDate());
//		}
		
		if(!CompareUtil.compare(ru.getName(), user.getName())){
			ru.setName(user.getName());
		}
		
		if(!CompareUtil.compare(ru.getLocation().getAddress(), user.getAddress())){
			//ru.setLocation(locationMapper.toRLocation(user.getAddress()));
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
	 */
	public void delete(Long userId){
		//TODO: check if current logged in user matches the provided id, or if
		// the user role is admin
		RestPreconditions.checkNotNull(userId);
		workoutRepo.deleteWorkoutByOwnerId(userId);
		userRepo.delete(userId);
	}
	
	/**
	 * Find a user with provided username
	 * @param username
	 * @return
	 * @throws ResourceNotFoundException
	 */
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
	public List<BaseUser> searchUserByName(String name, Pageable pageable){
		return userJDBCRepo.searchUserByName(name);
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
	
	
	
	
}
