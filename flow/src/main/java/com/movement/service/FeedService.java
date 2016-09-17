package com.movement.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.movement.domain.RFeed;
import com.movement.domain.RUser;
import com.movement.domain.RWorkout;
import com.movement.dto.User;
import com.movement.dto.Workout;
import com.movement.repository.FeedJDBCRepository;
import com.movement.repository.FeedRepository;
import com.movement.repository.WorkoutJDBCRepository;
import com.movement.service.mapper.WorkoutMapper;
import com.movement.util.RestPreconditions;

@Service
public class FeedService {
	
	@Autowired
	private FeedRepository feedRepo;
	
	@Autowired
	private WorkoutJDBCRepository workoutJDBCRepo;
		
	/**
	 * Add workout to followers feed
	 * @param followers (w.owner's followers)
	 * @param rw
	 */
	public void addWorkoutToFeed(List<Long> followerIds, RWorkout rw){
		RestPreconditions.checkNotNull(followerIds);
		RestPreconditions.checkNotNull(rw);
		for(Long id: followerIds){
			RFeed feed = feedRepo.findFeedByUserId(id);
			feed.getWorkouts().add(rw);
			feedRepo.save(feed);
		}
	}
	
	/**
	 * Find workouts to be displayed in a user's feed
	 * @param userId
	 * @return
	 */
	public Page<Workout> findWorkoutsInUserFeed(Long userId, Pageable pageable){
		RestPreconditions.checkNotNull(userId);
		List<Workout> workouts =  workoutJDBCRepo.queryWorkoutsForUserFeed(userId);
		return new PageImpl<Workout>(workouts, pageable, workouts.size());
	}
	
	/**
	 * Create an empty feed for a user when they sign up
	 * @param user
	 * @return
	 */
	public void createEmptyFeed(RUser user){
		// Check that the user does not already have a created feed
		RFeed feed = feedRepo.findFeedByUserId(user.getId());
		
		if(feed == null){
			//If they do not already have a feed, create a new one and save it
			feed = new RFeed(user);
			feedRepo.save(feed);
		}	
	}
	
//	public Page<Workout> getUsersFeed(Long userId){
//		//TODO: find feeds for each user
//	}
	
}
