package com.movement.service.mapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.movement.domain.RMedia;
import com.movement.domain.RWorkout;
import com.movement.dto.Workout;


public class WorkoutMapper {

	private UserMapper userMapper = new UserMapper();
	private MediaMapper mediaMapper = new MediaMapper();
	//private LocationMapper locationMapper = new LocationMapper();
	/**
	 * RWorkout to Workout
	 * @param rw
	 * @return
	 */
	public Workout toWorkout(RWorkout rw){
		
		Workout w = null;
		if(rw != null){
			w = new Workout();
			w.setId(rw.getId());
			w.setOwner(userMapper.toUser(rw.getOwner()));
			w.setDescription(rw.getDescription());
			w.setShowType(rw.getShowType());
			// and location mapper
		
			w.setDistance(rw.getDistance());
			w.setDuration(rw.getDuration());
			if(rw.getLocation() != null)
				w.setLocation(rw.getLocation().getAddress());
			
			w.setMedia(mediaMapper.toMedia(rw.getMedia()));
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
		if(workout != null){
			rw = new RWorkout();
			rw.setCreatedDate(workout.getCreatedDate());
			rw.setId(workout.getId());
			rw.setDescription(workout.getDescription());
			rw.setShowType(workout.getShowType());
			rw.setDistance(workout.getDistance());
			rw.setDuration(workout.getDuration());
			rw.setMedia(mediaMapper.toRMedia(workout.getMedia()));
			//TODO: create location mapper
			//if(workout.getLocation()!=null)
				//rw.setLocation(locationMapper.toRLocation(workout.getLocation()));
		}
		
		return rw;
	}
	
	/**
	 * To Page<Workout>
	 * @param rw
	 * @return
	 */
	public Page<Workout> toWorkoutDTOPage(Page<RWorkout> rw){
		List<Workout> workout = new ArrayList<Workout>();
		Iterator<RWorkout> iterator = rw.iterator();
		while(iterator.hasNext()){
			workout.add(toWorkout(iterator.next()));
		}
		
		return new PageImpl<Workout>(workout);
	}
	
}
