package com.movement.service.mapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.movement.domain.RActivity;
import com.movement.domain.RWorkout;
import com.movement.dto.Activity;
import com.movement.dto.Workout;


public class WorkoutMapper {

	private UserMapper userMapper = new UserMapper();
	private ActivityMapper activityMapper = new ActivityMapper();
	//private LocationMapper locationMapper = new LocationMapper();
	/**
	 * RWorkout to Workout
	 * @param rw
	 * @return
	 */
	public Workout toWorkout(RWorkout rw){
		
		Workout w = null;
		if(rw != null){
			List<Activity> activities = new ArrayList<Activity>();
			w = new Workout();
			w.setId(rw.getId());
			w.setOwner(userMapper.toUser(rw.getOwner()));
			w.setDescription(rw.getDescription());
			//TODO: set media, create media mapper
			// and location mapper
			if(rw.getActivities().size() > 0 && rw.getActivities()!= null){
				for(RActivity a : rw.getActivities()){
					activities.add(activityMapper.toActivity(a));
				}
			}
			w.setActivities(activities);
			if(rw.getLocation() != null)
				w.setLocation(rw.getLocation().getAddress());
		}
		return w;
	}
	
	/**
	 * Workout to RWorkout
	 * @param rWorkout
	 * @return
	 */
	public RWorkout toEntityWorkout(Workout workout){
		RWorkout rw = null;
		List<RActivity> activities = new ArrayList<RActivity>();

		if(workout != null){
			rw = new RWorkout();
			rw.setCreatedDate(workout.getCreatedDate());
			rw.setId(workout.getId());
			rw.setDescription(workout.getDescription());
			if(workout.getActivities().size() > 0 && workout.getActivities()!= null){
				for(Activity a : workout.getActivities()){
					activities.add(activityMapper.toEntityActivity(a));
				}
			}
			rw.setActivities(activities);
			//TODO: create location mapper, set media and create media mapper
			//if(workout.getLocation()!=null)
				//rw.setLocation(locationMapper.toRLocation(workout.getLocation()));
		}
		
		return rw;
	}
	
	public Page<Workout> toWorkoutDTOPage(Page<RWorkout> rw){
		List<Workout> workout = new ArrayList<Workout>();
		Iterator<RWorkout> iterator = rw.iterator();
		while(iterator.hasNext()){
			workout.add(toWorkout(iterator.next()));
		}
		
		return new PageImpl<Workout>(workout);
	}
	
}
