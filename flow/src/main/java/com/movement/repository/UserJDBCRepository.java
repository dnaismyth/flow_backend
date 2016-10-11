package com.movement.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.movement.dto.BaseUser;


@Repository
public class UserJDBCRepository extends BaseJDBCRepository {
	public static final String QUERY_SEARCH_USER_BY_NAME= "sql.user.queryUserByName";
	public static final String QUERY_TRENDING_USERS = "sql.user.queryTrendingUsers";
	public static final String QUERY_FIND_USERS_IN_QUEST = "sql.user.queryFindUsersEnrolledInQuest";
	
	public List<BaseUser> searchUserByName(String name){
		String query = readQueryFromProperties(QUERY_SEARCH_USER_BY_NAME);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		return jdbcTemplate.query(query, params, new BaseUserMapper());
	}
	
	public List<BaseUser> findTrendingUsers(){
		String query = readQueryFromProperties(QUERY_TRENDING_USERS);
		return jdbcTemplate.query(query, new BaseUserMapper());
	}
	
	public List<BaseUser> findUsersInQuest(Long questId, Pageable pageable){
		String query = readQueryFromProperties(QUERY_FIND_USERS_IN_QUEST);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("questId", questId);
		params.put("limit", pageable.getPageSize());
		params.put("offset", pageable.getPageSize() * (pageable.getPageNumber()-1));
		return jdbcTemplate.query(query, params, new BaseUserMapper());
	}
	
	public class BaseUserMapper implements RowMapper<BaseUser> {
		   public BaseUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		      BaseUser user = new BaseUser();
		      user.setId(rs.getLong("id"));
		      user.setName(rs.getString("name"));
		      user.setAvatar(rs.getString("avatar"));
		      user.setUsername(rs.getString("username"));
		      return user;
		   }
		}

}
