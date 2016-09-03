package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.RUser;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<RUser, Long> {


    @Query("SELECT u FROM RUser u WHERE LOWER(u.username) = LOWER(:username)")
    RUser findByUsernameCaseInsensitive(@Param("username") String username);
    
	@Query("SELECT ru FROM RUser ru WHERE ru.username=?")
	RUser findUserByUsername(String username);

    @Query
    RUser findByEmail(String email);

    @Query
    RUser findByEmailAndActivationKey(String email, String activationKey);

    @Query
    RUser findByEmailAndResetPasswordKey(String email, String resetPasswordKey);
}
