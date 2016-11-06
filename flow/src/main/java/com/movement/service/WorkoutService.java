package com.movement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.domain.RMedia;
import com.movement.domain.RWorkout;
import com.movement.domain.RWorkoutFavourite;
import com.movement.dto.BaseUser;
import com.movement.dto.Media;
import com.movement.dto.ShowType;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.dto.Workout;
import com.movement.dto.WorkoutInfo;
import com.movement.dto.WorkoutStats;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.repository.WorkoutFavouriteRepository;
import com.movement.repository.WorkoutJDBCRepository;
import com.movement.repository.WorkoutRepository;
import com.movement.service.mapper.MediaMapper;
import com.movement.service.mapper.UserMapper;
import com.movement.service.mapper.WorkoutMapper;
import com.movement.service.util.CompareUtil;
import com.movement.util.RestPreconditions;


/**
 * Service class for Workout objects
 * @author DN
 */
@Service
@Transactional
public class WorkoutService {

	@Autowired
	private WorkoutRepository workoutRepo;
	
	@Autowired
	private WorkoutJDBCRepository workoutJDBCRepo;
	
	@Autowired
	private WorkoutFavouriteRepository workoutFavRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FeedService feedService;
	
	private WorkoutMapper workoutMapper = new WorkoutMapper();
	private UserMapper userMapper = new UserMapper();
	private MediaMapper mediaMapper = new MediaMapper();
	
	/**
	 * Create a workout object
	 * @param workout
	 * @throws ResourceNotFoundException 
	 */
	public Workout createWorkout(User owner, Workout workout) throws IllegalArgumentException {
		RestPreconditions.checkNotNull(owner);
		
		// If the user has not selected a specific privacy level, by default
		// the workout will be set to 'PUBLIC'
		if(workout.getShowType() == null){
			workout.setShowType(ShowType.PUBLIC);
		}
		
		RWorkout rw = workoutMapper.toEntityWorkout(workout);
		rw.setOwner(userMapper.toEntityUser(owner));
		RWorkout saved = workoutRepo.save(rw);
		sendWorkoutToFollowerFeeds(owner.getId(), saved);
		return workoutMapper.toWorkout(saved);
	}
	
	/**
	 * Update a previous workout
	 * @param workoutId
	 * @throws ResourceNotFoundException 
	 */
	@CacheEvict
	public Workout updateWorkout(Long workoutId, Workout updated) throws IllegalArgumentException, ResourceNotFoundException  {
		RestPreconditions.checkNotNull(workoutId);
		RWorkout rw = workoutRepo.findOne(workoutId);
		
		// Cannot find the workout in the db
		if(rw == null){
			String message = String.format("Cannot find workout with id: %s", workoutId);
			throw new ResourceNotFoundException(message);
		}

		if(!CompareUtil.compare(rw.getDistance(), updated.getDistance())){
			rw.setDistance(updated.getDistance());
		}
		
		if(!CompareUtil.compare(rw.getDuration(), updated.getDuration())){
			rw.setDuration(updated.getDuration());
		}
		
		//TODO: fix mapper
		if(!CompareUtil.compare(rw.getLocation(), updated.getLocation())){
			//rw.setLocation(updated.getLocation());
		}
		
		RMedia updatedMedia = mediaMapper.toRMedia(updated.getMedia());
		if(!CompareUtil.compare(rw.getMedia(), updatedMedia)){
			rw.setMedia(updatedMedia);
		}
		
		if(!CompareUtil.compare(rw.getCreatedDate(), updated.getCreatedDate())){
			rw.setCreatedDate(updated.getCreatedDate());
		}
		
		RWorkout saved = workoutRepo.save(rw);
		return workoutMapper.toWorkout(saved);	
	}
	
	/**
	 * Delete a workout by provided id
	 * @param workoutId
	 * @return
	 * @throws ResourceNotFoundException 
	 * @throws NoPermissionException 
	 */
	public boolean deleteWorkout(Long userId, Long workoutId) throws ResourceNotFoundException, NoPermissionException{
		RestPreconditions.checkNotNull(workoutId);
		RestPreconditions.checkNotNull(userId);
		RWorkout found = workoutRepo.findOne(workoutId);
		if(found == null){
			String message = String.format("Cannot find workout with provided id: " + workoutId);
			throw new ResourceNotFoundException(message);
		}
		
//		if(!found.getOwner().getId().equals(user.getId()) && user.getUserRole()!= UserRole.ADMIN){
//			throw new NoPermissionException("User does not have access to modify this content.");
//		}
		workoutRepo.delete(workoutId);
		return true;
	}
	
	/**
	 * Find a workout by provided id
	 * @param workoutId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public WorkoutInfo findWorkoutById(Long workoutId) throws ResourceNotFoundException{
		RestPreconditions.checkNotNull(workoutId);
		RWorkout rw = workoutRepo.findOne(workoutId);
		if(rw == null){
			String message = String.format("Cannot find workout with provided id: %s", workoutId);
			throw new ResourceNotFoundException(message);
		}
		return convertToWorkoutInfo(rw);
	}
	
	/**
	 * Return all of the user workouts (this needs to be updated so entity not being exposed)
	 * @param userId
	 * @param pageable
	 * @return
	 */
	@Cacheable
	public Page<Workout> findAllWorkoutsByUser(Long userId, Pageable pageable){
		RestPreconditions.checkNotNull(userId);
		Page<RWorkout> rw = workoutRepo.getAllUserWorkouts(userId, pageable);
		return workoutMapper.toWorkoutDTOPage(rw);
	}
	
	/**
	 * Send the workout to feeds of those who are following the
	 * owner of the newly created workout
	 * @param workoutOwnerId
	 */
	private void sendWorkoutToFollowerFeeds(Long workoutOwnerId, RWorkout rw){
		List<Long> followerIds = userService.findFollowersByUserId(workoutOwnerId);
		followerIds.add(workoutOwnerId); // display the owner's workout in their own feed
		feedService.addWorkoutToFeed(followerIds, rw);
	}
	
	/**
	 * Used to update and show workout stats
	 * @param workouts
	 */
	public void exposeWorkoutStats(Collection<WorkoutInfo> workouts, User user){
		Map<Long, WorkoutInfo> map = createWorkoutMap(workouts);
		List<Long> userFavourites = workoutFavRepo.findFavouritedFromCollection(map.keySet(), user.getId());
		
		WorkoutStats stats = null;
		if(userFavourites.size() > 0){
			for(Long l : userFavourites){
				stats = new WorkoutStats();
				stats.setLiked(true);
				map.get(l).setStats(stats);
			}
		}
	}
	
	/**
	 * Create a map of WorkoutInfo and their corresponding id's
	 * @param workouts
	 * @return
	 */
	private Map<Long, WorkoutInfo> createWorkoutMap(Collection<WorkoutInfo> workouts){
		Map<Long, WorkoutInfo> map = new HashMap<Long, WorkoutInfo>();
		for(WorkoutInfo wi : workouts){
			map.put(wi.getId(), wi);
		}
		return map;
	}
	
	/**
	 * Conver RWorkout into workoutInfo to expose WorkoutStats
	 * @param rw
	 * @return
	 */
	private WorkoutInfo convertToWorkoutInfo(RWorkout rw){
		BaseUser owner = new BaseUser(rw.getOwner().getId(), rw.getOwner().getUsername(), rw.getOwner().getAvatar());
		Media media = mediaMapper.toMedia(rw.getMedia());
		String address = rw.getLocation() != null ? rw.getLocation().getAddress() : null;
		WorkoutInfo workoutInfo = new WorkoutInfo(rw.getId(), owner, rw.getCreatedDate(), rw.getDescription(), media,
				rw.getShowType(), address, rw.getDistance(), rw.getDuration());
		
		return workoutInfo;
		
	}
}
