package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dto.Journal;

@Repository
@Transactional
public interface JournalRepository extends JpaRepository<Journal, Long> {

}
