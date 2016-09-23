package com.movement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.movement.domain.REventInterest;
import com.movement.domain.RWorkoutFavourite;

public interface EventInterestRepository extends JpaRepository<REventInterest, Long> {
	
	@Query("SELECT ei FROM REventInterest ei WHERE ei.eventInterestPK.user_id = :userId AND ei.eventInterestPK.event_id = :eventId")
	public REventInterest findByUserIdAndEventId(@Param("userId") Long userId, @Param("eventId") Long eventId);
}
