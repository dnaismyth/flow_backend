package com.movement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movement.domain.RConfirmation;

@Repository
public interface ConfirmationRepository extends JpaRepository<RConfirmation, Long> {

	@Query
    public RConfirmation findByConfirmationKey(String confirmationKey);
	
	@Query
	public RConfirmation findByUserId(Long userId);
}
