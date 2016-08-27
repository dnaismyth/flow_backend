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
			//rw.setLocation(w.getLocation());
		}
		
		return rw;
	}
	
	
}
