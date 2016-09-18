package com.movement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movement.domain.Authority;
import com.movement.domain.RComment;

public interface CommentRepository extends JpaRepository<RComment, Long> {

}
