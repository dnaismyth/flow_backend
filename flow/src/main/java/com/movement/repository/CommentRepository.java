package com.movement.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.movement.domain.RComment;

public interface CommentRepository extends JpaRepository<RComment, Long> {

	@Query("SELECT rc FROM RComment rc WHERE workoutId = ?1")
	public List<RComment> getAllCommentsForWorkout(Long workoutId, Pageable pageable);
}
