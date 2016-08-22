package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dto.Media;
import entities.RMedia;

@Repository
public interface MediaRepository extends JpaRepository<RMedia, Long>{
	
}
