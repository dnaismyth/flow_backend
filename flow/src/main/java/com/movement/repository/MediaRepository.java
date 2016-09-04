package com.movement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movement.domain.RMedia;

@Repository
public interface MediaRepository extends JpaRepository<RMedia, Long>{
	
}
