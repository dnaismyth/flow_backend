package com.movement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movement.domain.RFeed;

@Repository
public interface FeedRepository extends JpaRepository<RFeed, Long> {

	@Query("SELECT rf FROM RFeed rf WHERE rf.user.id = ?1")
	public RFeed findFeedByUserId(Long userId);
	
	@Modifying
	@Query("delete from RFeed rw where rw.user.id=?l")
	public void deleteFeedByUserId(Long userId);
}
