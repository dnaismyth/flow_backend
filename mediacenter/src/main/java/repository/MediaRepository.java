package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dto.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long>{
	
}
