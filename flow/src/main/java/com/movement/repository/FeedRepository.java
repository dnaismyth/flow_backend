package com.movement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movement.domain.RFeed;

@Repository
public interface FeedRepository extends JpaRepository<RFeed, Long> {

}