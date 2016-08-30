package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.RNotification;

@Repository
public interface NotificationRepository extends JpaRepository<RNotification, Long>{

}
