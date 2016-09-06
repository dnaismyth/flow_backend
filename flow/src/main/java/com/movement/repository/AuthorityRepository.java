package com.movement.repository;


import com.movement.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorityRepository extends JpaRepository<Authority, String> {


}
