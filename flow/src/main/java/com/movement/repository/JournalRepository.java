package com.movement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.movement.domain.RJournal;

@Repository
@Transactional
public interface JournalRepository extends JpaRepository<RJournal, Long> {

}
