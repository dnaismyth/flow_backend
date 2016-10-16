package com.movement.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.movement.domain.RWorkoutFavourite;

@Repository
public interface WorkoutFavouriteRepository extends JpaRepository<RWorkoutFavourite, Long> {

	@Query("SELECT wf FROM RWorkoutFavourite wf WHERE wf.workoutFavouritePK.workout_id = :workoutId AND wf.workoutFavouritePK.user_id = :userId")
	public RWorkoutFavourite findByUserIdAndWorkoutId(@Param("workoutId") Long workoutId, @Param("userId") Long userId);
	
	/**
	 * Query to find a user's favourited workouts when provided a collection of workouts.
	 * Used to fill workout stats.
	 * @param workouts
	 * @param userId
	 * @return
	 */
	@Query("SELECT wf.workoutFavouritePK.workout_id "
			+ "FROM RWorkoutFavourite wf "
			+ "WHERE wf.workoutFavouritePK.user_id = :userId AND wf.workoutFavouritePK.workout_id IN :workoutIds")
	public List<Long> findFavouritedFromCollection(@Param("workoutIds")Set <Long> workoutIds, @Param("userId") Long userId);
	
}
