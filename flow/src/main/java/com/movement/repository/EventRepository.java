package com.movement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movement.domain.REvent;

public interface EventRepository extends JpaRepository<REvent, Long> {

}
