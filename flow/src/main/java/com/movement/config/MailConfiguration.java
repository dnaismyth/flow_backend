package com.movement.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.amazonaws.auth.AWSCredentials;

@Configuration
public class MailConfiguration {
	
	private static final String JAVA_MAIL_FILE = "javamail.properties";
	
	private static final String USER = "mail.aws.user";
	private static final String PASSWORD = "mail.aws.password";
	private static final String PROTOCOL = "mail.transport.protocol";
	
	@Autowired
	private AWSCredentials credentials;

	/**
	 * Java Mail setup to send e-mail to users (eg: password reset)
	 */
	@Bean
	public JavaMailSender mailSender() throws IOException {
	    Properties properties = configProperties();
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    //mailSender.setHost(properties.getProperty("mail.server.host"));
	    //mailSender.setPort(Integer.parseInt(properties.getProperty("mail.server.port")));
	    mailSender.setProtocol(properties.getProperty(PROTOCOL));
	    mailSender.setUsername(properties.getProperty(USER));
	    mailSender.setPassword(properties.getProperty(PASSWORD));
	    mailSender.setJavaMailProperties(javaMailProperties());
	    return mailSender;
	}
	
	// Configuration properties for Java Mail with AWS 
	private Properties configProperties() throws IOException {
	    Properties props = new Properties();
		props.setProperty(PROTOCOL, "aws");
		props.setProperty(USER, credentials.getAWSAccessKeyId());
		props.setProperty(PASSWORD, credentials.getAWSSecretKey());
		return props;
	}
	
	// Java Mail properties 
	private Properties javaMailProperties() throws IOException {
	    Properties properties = new Properties();
	    properties.load(new ClassPathResource(JAVA_MAIL_FILE).getInputStream());
	    return properties;
	}


}
