package com.movement.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.movement.domain.RWorkout;


@Repository
@Transactional
public interface WorkoutRepository extends CrudRepository<RWorkout, Long> {

	@Modifying
	@Query("delete from RWorkout rw where rw.owner.id=?l")
	public void deleteWorkoutByOwnerId(Long ownerId);
	
	@Query("SELECT rw from RWorkout rw where rw.owner.id=?1")
	public Page<RWorkout> getAllUserWorkouts(Long ownerId, Pageable pageable);
}
