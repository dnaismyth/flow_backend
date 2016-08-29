package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entities.RUser;
import repository.UserRepository;
import repository.WorkoutRepository;
import util.RestPreconditions;

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
