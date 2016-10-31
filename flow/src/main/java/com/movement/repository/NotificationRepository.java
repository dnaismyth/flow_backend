package com.movement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.movement.domain.RNotification;

@Repository
public interface NotificationRepository extends JpaRepository<RNotification, Long>{

	@Query("SELECT rn FROM RNotification rn WHERE rn.user_id = :userId")
	public List<RNotification> findNotificationsForUserById(@Param("userId") Long userId);
}
