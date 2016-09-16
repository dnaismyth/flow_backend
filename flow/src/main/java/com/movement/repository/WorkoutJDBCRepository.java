package com.movement.repository;

import java.util.HashMap;
import java.util.Map;

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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("workoutId", workoutId);
		jdbcTemplate.update(query, params);	
	}
}
