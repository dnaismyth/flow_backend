package com.movement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.movement.domain.RFeed;
import com.movement.domain.RWorkout;
import com.movement.dto.User;
import com.movement.dto.Workout;
import com.movement.repository.FeedRepository;
import com.movement.service.mapper.WorkoutMapper;
import com.movement.util.RestPreconditions;

@Service
public class FeedService {
	
	@Autowired
	private FeedRepository feedRepo;
		
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
		}
	}
	
}
