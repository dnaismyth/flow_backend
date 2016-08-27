package repository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class WorkoutJDBCRepository extends BaseJDBCRepository{
	private static final Logger logger = Logger.getLogger(WorkoutJDBCRepository.class); 
	public static final String QUERY_DELETE_WORKOUT_REFERENCES = "sql.workout.queryDeleteWorkoutReferences";

	
	public void deleteWorkoutQueryReferences(Long workoutId){
		
		String query = readQueryFromProperties(QUERY_DELETE_WORKOUT_REFERENCES);
		logger.info("My query is:" + query);
	}
}
