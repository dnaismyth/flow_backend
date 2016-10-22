package com.movement.repository;

import com.movement.domain.RUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<RUser, Long> {

    @Query("SELECT u FROM RUser u WHERE LOWER(u.username) = LOWER(:username)")
    RUser findByUsernameCaseInsensitive(@Param("username") String username);

    @Query("SELECT u FROM RUser u WHERE LOWER(u.email) = LOWER(:email)")
    RUser findByEmail(@Param("email") String email);

    @Query
    RUser findByEmailAndActivationKey(String email, String activationKey);

    @Query
    RUser findByEmailAndResetPasswordKey(String email, String resetPasswordKey);
    
    @Query
    RUser findByResetPasswordKey(String resetPasswordKey);

}