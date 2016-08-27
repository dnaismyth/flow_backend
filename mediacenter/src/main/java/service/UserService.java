package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repository.UserRepository;
import service.mapper.UserMapper;
import service.util.CompareUtil;
import util.RestPreconditions;
import dto.User;
import entities.RUser;
import exception.ResourceNotFoundException;

/**
 * Service class for Users
 * @author DN
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepo;
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
		
		if(!CompareUtil.compare(ru.getCreatedDate(), user.getCreatedDate())){
			ru.setCreatedDate(user.getCreatedDate());
		}
		
		if(!CompareUtil.compare(ru.getName(), user.getName())){
			ru.setName(user.getName());
		}
		
		//TODO: compare locations
		
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
		RUser found = userRepo.findUserByUsername(username);
		return userMapper.toUser(found);
	}
	
	
	
	
	
	
	
	
	
	
}
