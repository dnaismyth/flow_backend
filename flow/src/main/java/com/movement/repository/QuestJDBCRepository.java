package com.movement.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.movement.dto.MeasurementUnit;
import com.movement.dto.Objective;
import com.movement.dto.ObjectiveType;
import com.movement.dto.Quest;

@Repository
public class QuestJDBCRepository extends BaseJDBCRepository {

	public static final String QUERY_USER_CURRENT_QUESTS = "sql.quest.queryFindUsersCurrentQuests";
	
	public List<Quest> findUsersCurrentQuests(Long userId){
		String query = readQueryFromProperties(QUERY_USER_CURRENT_QUESTS);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return jdbcTemplate.query(query, params, new QuestMapper());
	}
	
	/**
	 * Find all quests that a user is currently involved in
	 * @author DN
	 *
	 */
	public class QuestMapper implements RowMapper<Quest> {
		   public Quest mapRow(ResultSet rs, int rowNum) throws SQLException {
		      Quest quest = new Quest();
		      
		      // ot = objective_type
		      ObjectiveType type = rs.getString("ot") != null ? ObjectiveType.valueOf(rs.getString("ot")) : null;
		      // uom = unit_of_measurement
		      MeasurementUnit unit = rs.getString("uom") != null ? MeasurementUnit.valueOf(rs.getString("uom")) : null;
		      Objective obj = new Objective(rs.getInt("target_value"), type, unit);
		      quest.setId((Long)rs.getObject("id"));
		      quest.setCreatedDate(rs.getDate("created_date"));
		      quest.setStartTime(rs.getDate("start_time"));
		      quest.setEndTime(rs.getDate("end_time"));
		      quest.setDescription(rs.getString("description"));
		      quest.setTitle(rs.getString("title"));
		      quest.setObjective(obj);
		      return quest;
		   }
	}

}
