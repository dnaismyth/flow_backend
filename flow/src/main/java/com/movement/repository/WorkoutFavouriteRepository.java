package com.movement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.movement.domain.RUser;
import com.movement.domain.RWorkoutFavourite;

@Repository
public interface WorkoutFavouriteRepository extends JpaRepository<RWorkoutFavourite, Long> {

	@Query("SELECT wf FROM RWorkoutFavourite wf WHERE wf.workoutFavouritePK.workout_id = :workoutId AND wf.workoutFavouritePK.user_id = :userId")
	public RWorkoutFavourite findByUserIdAndWorkoutId(@Param("workoutId") Long workoutId, @Param("userId") Long userId);
}
