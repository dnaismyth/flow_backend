package com.movement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.movement.domain.RQuestProgress;
import com.movement.domain.RWorkoutFavourite;

@Repository
public interface QuestProgressRepository extends JpaRepository <RQuestProgress, Long>{

	@Query("SELECT uq FROM RQuestProgress qu WHERE qu.userQuestPK.user_id = :userId AND qu.userQuestPK.quest_id = :questId")
	public RQuestProgress findByUserIdAndQuestId(@Param("userId") Long userId, @Param("questId") Long questId);
}
