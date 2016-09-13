package com.movement;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@SpringBootApplication
public class GetMovingMainApplication extends WebMvcAutoConfiguration {
	protected static final Logger logger = Logger.getLogger(GetMovingMainApplication.class); 

    public static void main(String[] args) {
        SpringApplication.run(GetMovingMainApplication.class, args);
        logger.info("\n\t--------------------------------------------------------------------\n"
        		+ "\t--------------------> Starting Main Application <-----------------\n"
        		+ "\t--------------------------------------------------------------------");
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
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws IOException {
	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setDatabase(Database.POSTGRESQL);
	    vendorAdapter.setGenerateDdl(true);
	    vendorAdapter.setShowSql(true);
	    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    factory.setJpaVendorAdapter(vendorAdapter);
	    factory.setPackagesToScan(getClass().getPackage().getName());
	    factory.setDataSource(dataSource());
	    Properties properties = new Properties();
	    
	    InputStream user_props = this.getClass().getResourceAsStream("/application.properties");
	    if (user_props != null) {
	      properties.load(user_props);
	    } else {
	      properties.load(this.getClass().getResourceAsStream("/application.properties"));
	    }
	    factory.setJpaProperties(properties);
	    return factory;
	  }
	 
	  @Bean
	  @Autowired
	  public JpaTransactionManager transactionManager() throws IOException {
	    JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setEntityManagerFactory(entityManagerFactory().getObject());

	    return txManager;
	  }
	 
}
