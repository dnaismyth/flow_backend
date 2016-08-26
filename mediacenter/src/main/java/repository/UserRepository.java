package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.RUser;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<RUser, Long> {

	@Query("SELECT ru FROM RUser ru WHERE ru.userName=?")
	public RUser findUserByUsername(String username);
}
