package com.movement.service.mapper;

import com.movement.domain.RUser;
import com.movement.dto.User;
/**
 * Map user objects and entities
 */
public class UserMapper {

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
		}
		return u;
		
	}
	
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
		}
		return ru;
	}
}
