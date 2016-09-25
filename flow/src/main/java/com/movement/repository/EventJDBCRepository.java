package com.movement.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class EventJDBCRepository extends BaseJDBCRepository {

	private final static String QUERY_DELETE_EVENT_REFERENCES_BY_ID = "sql.event.removeEventReferencesByEventId";
	
	/**
	 * Remove rows in tables referencing event
	 * @param eventId
	 */
	public void deleteEventReferencesByEventId(Long eventId){
		
		String query = readQueryFromProperties(QUERY_DELETE_EVENT_REFERENCES_BY_ID);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("eventId", eventId);
		jdbcTemplate.update(query, params);	
	}
	
}
