package service;

import service.mapper.UserMapper;
import util.RestPreconditions;
import dto.User;
import entities.RUser;

/**
 * Service class for Users
 * @author DN
 */
public class UserService extends ServiceBase {

	private UserMapper userMapper = new UserMapper();
	
	/**
	 * Creates a user
	 * @param user
	 * @return
	 */
	public User create(User user){
		RestPreconditions.checkNotNull(user);
		
		RUser ru = userMapper.toEntityUser(user);
		RUser saved = userRepo.save(ru);
		return userMapper.toUser(saved);
	}
	
	
}
