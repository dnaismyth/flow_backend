package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;


@EnableJpaRepositories("repository")
@SpringBootApplication
@ComponentScan(basePackages={"service", "dto", "controller"})
@EntityScan(basePackages = "entities")
public class MainApplication {
	
	public static void main(String [] args){
			SpringApplication.run(MainApplication.class, args);
	}
	 
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/garmin");
		dataSource.setUsername("postgres");
		dataSource.setPassword("admin");
		return dataSource;
	}
	
	 @Bean
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setDatabase(Database.POSTGRESQL);
	    vendorAdapter.setGenerateDdl(true);
	    vendorAdapter.setShowSql(true);
	    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    factory.setJpaVendorAdapter(vendorAdapter);
	    factory.setPackagesToScan(getClass().getPackage().getName());
	    factory.setDataSource(dataSource());

	    return factory;
	  }
	 
	  @Bean
	  @Autowired
	  public JpaTransactionManager transactionManager() {
	    JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setEntityManagerFactory(entityManagerFactory().getObject());

	    return txManager;
	  }

}
