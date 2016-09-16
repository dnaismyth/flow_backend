package com.movement.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class FollowJDBCRepository extends BaseJDBCRepository {

public static final String SQL_QUERY_FIND_FOLLOWERS_BY_USER_ID = "sql.follow.findFollowersByUserId";
	
	public List<Long> findFollowersByUserId(Long userId){
		String query = readQueryFromProperties(SQL_QUERY_FIND_FOLLOWERS_BY_USER_ID);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		List<Long> followerIds = jdbcTemplate.query(query, params, new RowMapper<Long>() {
			public Long mapRow(ResultSet rs, int i) throws SQLException {
				return rs.getLong(1);
			}
		});
		
		return followerIds;
		
	}

}
