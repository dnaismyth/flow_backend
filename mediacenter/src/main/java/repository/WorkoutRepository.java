package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dto.Workout;
import entities.RWorkout;

@Repository
@Transactional
public interface WorkoutRepository extends CrudRepository<RWorkout, Long> {

	@Query("delete from RWorkout rw where rw.owner_id=?l")
	public void deleteWorkoutByOwnerId(Long ownerId);
}
