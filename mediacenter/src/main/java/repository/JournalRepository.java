package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dto.Journal;
import entities.RJournal;

@Repository
@Transactional
public interface JournalRepository extends JpaRepository<RJournal, Long> {

}
