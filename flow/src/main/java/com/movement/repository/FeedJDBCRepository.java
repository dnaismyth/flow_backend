package com.movement.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class FeedJDBCRepository extends BaseJDBCRepository {

	public static final String DELETE_FEED_REFERENCES_BY_USER_ID = "sql.feed.deleteFeedReferencesByUserId";
	
	/**
	 * Delete feed references with user id
	 * @param userId
	 * @return
	 */
	public boolean deleteFeedReferencesByUserId(Long userId){
		String query = readQueryFromProperties(DELETE_FEED_REFERENCES_BY_USER_ID);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		int delete = jdbcTemplate.update(query, params);	
		if(delete > 0){
			return true;
		}
		return false;
	}
}
