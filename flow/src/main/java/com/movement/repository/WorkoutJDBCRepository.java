package com.movement.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.movement.dto.BaseUser;
import com.movement.dto.ShowType;
import com.movement.dto.Workout;

@Repository
public class WorkoutJDBCRepository extends BaseJDBCRepository{
	
	private static final Logger logger = Logger.getLogger(WorkoutJDBCRepository.class); 
	public static final String QUERY_DELETE_WORKOUT_REFERENCES = "sql.workout.queryDeleteWorkoutReferences";
	public static final String QUERY_WORKOUTS_FOR_USER_FEED = "sql.workout.findWorkoutsForUserFeed";
	public static final String QUERY_DELETE_WORKOUT_REFERENCES_BY_OWNER = "sql.workout.queryDeleteWorkoutReferencesByOwnerId";

	
	public void deleteWorkoutQueryReferences(Long workoutId){
		
		String query = readQueryFromProperties(QUERY_DELETE_WORKOUT_REFERENCES);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("workoutId", workoutId);
		jdbcTemplate.update(query, params);	
	}
	
	public void deleteWorkoutAndReferencesByOwner(Long ownerId){
		String query = readQueryFromProperties(QUERY_DELETE_WORKOUT_REFERENCES_BY_OWNER);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ownerId", ownerId);
		jdbcTemplate.update(query, params);
	}
	
	//TODO: Implement different strategy using paging
	public List<Workout> queryWorkoutsForUserFeed(Long userId){
		String query = readQueryFromProperties(QUERY_WORKOUTS_FOR_USER_FEED);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return jdbcTemplate.query(query, params, new WorkoutRowMapper());
	}
	
	public class WorkoutRowMapper implements RowMapper<Workout> {
		   public Workout mapRow(ResultSet rs, int rowNum) throws SQLException {
			  BaseUser baseUser = new BaseUser((Long)rs.getObject("owner_id"), rs.getString("username"), rs.getString("avatar"));
			  
			  ShowType showType = rs.getString("showtype") != null ? ShowType.valueOf(rs.getString("showtype")) : null;
			   
		      Workout w = new Workout((Long)rs.getObject("id"),
		    		  rs.getDate("created_date"),
		    		  rs.getString("description"),
		    		  showType);
		      
		      w.setOwner(baseUser);
		      w.setAddress(rs.getString("address"));
		      return w;
		      
		   }
		}
	}
