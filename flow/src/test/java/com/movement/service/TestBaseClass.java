package com.movement.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.movement.GetMovingMainApplication;
import com.movement.dto.Event;
import com.movement.dto.Location;
import com.movement.dto.Quest;
import com.movement.dto.User;
import com.movement.dto.Workout;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.repository.FollowRepository;
import com.movement.repository.UserRepository;
import com.movement.repository.WorkoutJDBCRepository;
import com.movement.repository.WorkoutRepository;
import com.movement.service.FollowService;
import com.movement.service.UserService;
import com.movement.service.WorkoutService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=GetMovingMainApplication.class)
public class TestBaseClass {
	protected static final Logger logger = Logger.getLogger(TestBaseClass.class); 

	@Autowired
	protected UserService userService;
	
	@Autowired
	protected EventService eventService;
	
	@Autowired
	protected FollowService followService;
	
	@Autowired
	protected FollowRepository followRepo;
	
	@Autowired
	protected UserRepository userRepo;
	
	@Autowired
	protected WorkoutService workoutService;
	
	@Autowired
	protected WorkoutRepository workoutRepo;
	
	@Autowired
	protected QuestService questService;
	
	@Autowired
	protected MediaService mediaService;
	
	// Helper method to create test workouts
	protected Workout createWorkout(String distance, String duration, User owner, String location){
		Workout workout = new Workout();
		workout.setDuration(duration);
		workout.setDistance(distance);
		workout.setLocation(location);
		return workoutService.createWorkout(owner, workout);
	}
	
	protected Event createEvent(User owner, String title, Date eventDate, String address) throws ResourceNotFoundException{
		Event e = new Event();
		Location l = new Location();
		l.setAddress(address);
		l.setLatitude(49.2462f);
		l.setLongitude(	-123.1162f);
		e.setLocation(l);
		e.setTitle(title);
		e.setEventDate(eventDate);
		return eventService.createEvent(e, owner.getId());
	}
	
	protected Quest createQuest(User admin, String title) throws NoPermissionException{
		Quest quest = new Quest();
		quest.setTitle("Testing start new quest");
		Quest created = questService.createQuest(quest, admin);
		return created;
	}
}
