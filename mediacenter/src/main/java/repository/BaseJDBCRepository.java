package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BaseJDBCRepository {

	@Autowired 
    private Environment environment;
	
	protected JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	public String readQueryFromProperties(String query){
		return environment.getProperty(query);
	}
	
}
