package com.movement.service.mapper;

import com.movement.domain.RUser;
import com.movement.dto.User;
/**
 * Map user objects and entities
 */
public class UserMapper {
	
	private LocationMapper locationMapper = new LocationMapper();

	/**
	 * TO DTO Object
	 * @param user
	 * @return
	 */
	public User toUser(RUser user){
		User u = null;
		if(user != null){
			u = new User();
			u.setBio(user.getBio());
			u.setEmail(user.getEmail());
			u.setId(user.getId());
			u.setPhone(user.getPhone());
			u.setName(user.getName());
			u.setUsername(user.getUsername());
			u.setPassword(user.getPassword());
			u.setCreatedDate(user.getCreatedDate());
			u.setUserRole(user.getUserRole());
			u.setAvatar(user.getAvatar());
			u.setLocation(locationMapper.toLocation(user.getLocation()));
		}
		return u;
		
	}
	
	/**
	 * TO Entity User
	 * @param user
	 * @return
	 */
	public RUser toEntityUser(User user){
		RUser ru = null;
		if(user != null){
			ru = new RUser();
			ru.setBio(user.getBio());
			ru.setCreatedDate(user.getCreatedDate());
			ru.setEmail(user.getEmail());
			ru.setId(user.getId());
			ru.setPhone(user.getPhone());
			ru.setPassword(user.getPassword());
			ru.setName(user.getName());
			ru.setUsername(user.getUsername());
			ru.setCreatedDate(user.getCreatedDate());
			ru.setUserRole(user.getUserRole());
			ru.setAvatar(user.getAvatar());
			ru.setLocation(locationMapper.toRLocation(user.getLocation()));
		}
		return ru;
	}
}
