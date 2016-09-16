package com.movement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.movement.domain.RFollow;

@Repository
@Transactional
public interface FollowRepository extends JpaRepository<RFollow, Long> {

	@Query("SELECT rf FROM RFollow rf WHERE rf.userRelation.user_id= :followerId AND rf.userRelation.target_id= :followingId")
	public RFollow findRelationshipByFollowerAndFollowingId(@Param("followerId") Long followerId, @Param("followingId") Long followingId);

}
