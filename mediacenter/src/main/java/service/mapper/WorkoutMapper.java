package service.mapper;

import java.util.ArrayList;
import java.util.List;

import dto.Activity;
import dto.Workout;
import entities.RActivity;
import entities.RWorkout;

public class WorkoutMapper {

	private UserMapper userMapper = new UserMapper();
	private ActivityMapper activityMapper = new ActivityMapper();
	/**
	 * RWorkout to Workout
	 * @param rw
	 * @return
	 */
	public Workout toWorkout(RWorkout rw){
		
		Workout w = null;
		if(rw != null){
			List<Activity> activities;
			w = new Workout();
			w.setId(rw.getId());
			w.setOwner(userMapper.toUser(rw.getOwner()));
			
			if(rw.getActivities().size() > 0){
				activities = new ArrayList<Activity>();
				for(RActivity a : rw.getActivities()){
					activities.add(activityMapper.toActivity(a));
				}
				w.setActivities(activities);
			}
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
		List<RActivity> activities;

		if(workout != null){
			rw = new RWorkout();
			rw.setCreatedDate(workout.getCreatedDate());
			rw.setId(workout.getId());
			if(workout.getActivities().size() > 0){
				activities = new ArrayList<RActivity>();
				for(Activity a : workout.getActivities()){
					activities.add(activityMapper.toEntityActivity(a));
				}
				rw.setActivities(activities);
			}
			//TODO: create location mapper
			//rw.setLocation(w.getLocation());
		}
		
		return rw;
	}
	
	
}
