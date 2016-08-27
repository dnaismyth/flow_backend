package repository;

import org.springframework.jdbc.core.JdbcTemplate;

public class BaseJDBCRepository {

	protected JdbcTemplate jdbcTemplate;
	
	public String readQueryFromProperties(String query){
		
		return null;
	}
	
}
