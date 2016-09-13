package com.movement.controller.dto;

import com.movement.dto.Operation;
import com.movement.dto.User;
import com.movement.dto.Workout;

/**
 * User Response for User controllers
 * @author DN
 *
 */
public class UserResponse {
	private User user;
	private Operation op;
	private Long id;
	
	public UserResponse (User user, Operation op){
		this.user = user;
		this.op = op;
	}
	
	public UserResponse(Operation op, Long id){
		this.op = op;
		this.id = id;
	}
	
	public UserResponse(User user){
		this.user = user;
	}
	
	public User getUser(){
		return user;
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
	public Operation getOperation(){
		return op;
	}
	
	public void setOperation(Operation op){
		this.op = op;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
}
