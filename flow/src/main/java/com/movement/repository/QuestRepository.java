package com.movement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movement.domain.RQuest;

@Repository
public interface QuestRepository extends JpaRepository<RQuest, Long> {

}
