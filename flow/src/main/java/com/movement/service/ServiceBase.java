package com.movement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.domain.RUser;
import com.movement.repository.UserRepository;
import com.movement.repository.WorkoutRepository;
import com.movement.util.RestPreconditions;

public class ServiceBase {

	@Autowired
	protected UserService userService;
	
	@Autowired
	protected UserRepository userRepo;
	
	@Autowired
	protected WorkoutRepository workoutRepo;
	
	@Autowired
	protected WorkoutService workoutService;
	
	protected RUser loadUserEntity(Long userId){
		RestPreconditions.checkNotNull(userId);
		return userRepo.findOne(userId);
	}
}
