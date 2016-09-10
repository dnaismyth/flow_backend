package com.movement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

@Configuration
public class AWSConfiguration {
	
	   @Value("${AWSAccessKeyId}")
	   private String awsId;
		
	   @Value("${AWSSecretKey}")
	   private String awsKey;
		
	   @Bean
	   public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setLocations(new Resource[] {
			new ClassPathResource("/S3Credentials.properties")
	        });
		return ppc;
	   }
		
	   @Bean
	   public AWSCredentials credential() {
	   	return new BasicAWSCredentials(awsId, awsKey);
	   }
		
	   @Bean
	   public AmazonS3 s3client() {
		   AmazonS3 s3Client = new AmazonS3Client(credential());
		   Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		   s3Client.setRegion(usWest2);
		   return s3Client;
	   }
}
