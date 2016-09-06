package com.movement.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class AuthorityJDBCRepository extends BaseJDBCRepository {
	public static final String INSERT_USER_ROLE = "sql.authority.insertUserRole";
	
	public boolean insertUserRole(Long id, String authority){
		boolean inserted = false;
		String query = readQueryFromProperties(INSERT_USER_ROLE);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("authority", authority);
		if(jdbcTemplate.update(query, params)>0){
			inserted = true;
		}
		
		return inserted;
	}
	
}
